package com.bannrx.common_service.apis;

import com.bannrx.common.dtos.UserDto;
import com.bannrx.common.service.UserService;
import com.bannrx.common.utilities.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import rklab.utility.dto.ApiOutput;
import rklab.utility.expectations.InvalidInputException;



@Service
public class UpdateUserApi {

    private static final String UPDATE_MSG = "User is updated successfully";

    @Autowired
    private UserService userService;


    public ApiOutput<?> update(UserDto userDto){
        try{
            validate(userDto);
            UserDto retVal = userService.update(userDto);
            return new ApiOutput<>(HttpStatus.OK.value(), UPDATE_MSG, retVal);
        } catch (Exception e) {
            return new ApiOutput<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    private void validate(UserDto userDto) throws InvalidInputException {
        if(!StringUtil.isNullOrEmpty(userDto.getPhoneNo()) && userDto.getPhoneNo().length() != 10){
            throw new InvalidInputException("Invalid phone number.");
        }
        if (!StringUtil.isNullOrEmpty(userDto.getPhoneNo())){
            var existing = userService.existingContactNo(userDto.getPhoneNo());
            if (existing){
                throw new InvalidInputException("User exists with the given phone no "+userDto.getPhoneNo());
            }
        }
    }
}
