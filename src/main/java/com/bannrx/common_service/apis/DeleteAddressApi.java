package com.bannrx.common_service.apis;

import com.bannrx.common_service.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import rklab.utility.dto.ApiOutput;
import rklab.utility.expectations.InvalidInputException;
import java.util.List;



@Service
public class DeleteAddressApi {

    @Autowired
    private AddressService addressService;

    public ApiOutput<?> delete(String id){
        try{
            validate(id);
            String msg = addressService.delete(id);
            return new ApiOutput<>(HttpStatus.OK.value(), msg,null);

        } catch (Exception e) {
            return new ApiOutput<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    private void validate(String id) throws InvalidInputException {
        if(id.isEmpty()){
            throw new InvalidInputException("Please provide ids to delete");
        }
    }
}
