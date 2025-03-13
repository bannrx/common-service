package com.bannrx.common_service.apis;

import com.bannrx.common.utilities.StringUtil;
import com.bannrx.common.dtos.AddressDto;
import com.bannrx.common.entities.User;
import com.bannrx.common.service.AddressService;
import com.bannrx.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import rklab.utility.dto.ApiOutput;
import rklab.utility.expectations.InvalidInputException;
import java.util.Objects;



@Service
public class AddAddressApi {
    private static final String ADD_ADDRESS_MSG = "Address added Successfully";

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;

    public ApiOutput<AddressDto> addAddress(AddressDto addressDto){
        try{
            validate(addressDto);
            AddressDto response = addressService.addAddress(addressDto);
            return new ApiOutput<>(HttpStatus.OK.value(), ADD_ADDRESS_MSG,response);
        } catch (Exception e) {
            return new ApiOutput<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    private void validate(AddressDto addressDto) throws InvalidInputException {
        if(Objects.isNull(addressDto)){
            throw new NullPointerException("Address is null or empty please add something");
        }
        User user = userService.findByPhoneNo(addressDto.getPhoneNo());

        if(StringUtil.isNullOrEmpty(addressDto.getAddressLine1())){
            throw new InvalidInputException("Address Line is not provided");
        }
        if(StringUtil.isNullOrEmpty(addressDto.getCity())){
            throw new InvalidInputException("City is not provided");
        }
        if(StringUtil.isNullOrEmpty(addressDto.getPincode())){
            throw new InvalidInputException("Pincode is not provided");
        }
        if(StringUtil.isNullOrEmpty(addressDto.getState())){
            throw new InvalidInputException("State is not provided");
        }
        if(Objects.isNull(addressDto.getLongitude())){
            throw new InvalidInputException("Longitude is not provided");
        }
        if(Objects.isNull(addressDto.getLatitude())){
            throw new InvalidInputException("Latitude is not provided");
        }
        addressService.canAddressBeAdded(user, addressDto);
        /*if(addressService.isSameAddressExist(addressDto,user)){
            throw new InvalidInputException("Address already exist");
        }*/

    }
}
