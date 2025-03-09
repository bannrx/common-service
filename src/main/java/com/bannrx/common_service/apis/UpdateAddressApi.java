package com.bannrx.common_service.apis;

import com.bannrx.common_service.dtos.AddressDto;
import com.bannrx.common_service.service.AddressService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import rklab.utility.dto.ApiOutput;
import rklab.utility.expectations.InvalidInputException;
import java.util.Objects;

@Service
public class UpdateAddressApi {


    private static final String UPDATE_MSG = "The address has been updated successfully";

    @Autowired
    private AddressService addressService;

    public ApiOutput<AddressDto> update(AddressDto addressDto){
        try{
            validate(addressDto);
            var response = addressService.update(addressDto);
            return new ApiOutput<>(HttpStatus.OK.value(), UPDATE_MSG, response );
        } catch (Exception e) {
            return new ApiOutput<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    private void validate(AddressDto addressDto)throws InvalidInputException {
        if(Objects.isNull(addressDto)){
            throw new InvalidInputException("Please provide something to update.");
        }
        if(Strings.isEmpty(addressDto.getId())){
            throw new InvalidInputException(("You need to provide id to update the address."));
        }
    }

}
