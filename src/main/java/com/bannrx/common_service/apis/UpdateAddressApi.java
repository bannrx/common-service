package com.bannrx.common_service.apis;

import com.bannrx.common.dtos.AddressDto;
import com.bannrx.common.service.AddressService;
import com.bannrx.common.validationGroups.AvailableValidationGroup;
import com.bannrx.common.validationGroups.UpdateValidationGroup;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import rklab.utility.dto.ApiOutput;
import rklab.utility.expectations.InvalidInputException;
import rklab.utility.utilities.ValidationUtils;

import java.util.Objects;
import java.util.Set;

@Service
public class UpdateAddressApi {


    private static final String UPDATE_MSG = "The address has been updated successfully";

    @Autowired
    private AddressService addressService;
    @Autowired
    private ValidationUtils validationUtils;

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
        validationUtils.validate(addressDto, Set.of(UpdateValidationGroup.class, AvailableValidationGroup.class));
    }

}
