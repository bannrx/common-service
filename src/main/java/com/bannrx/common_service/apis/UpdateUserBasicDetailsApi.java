package com.bannrx.common_service.apis;

import com.bannrx.common.dtos.user.UserBasicDetailsDto;
import com.bannrx.common.dtos.user.UserDto;
import com.bannrx.common.service.UserService;
import com.bannrx.common.validationGroups.AvailableValidationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import rklab.utility.annotations.Loggable;
import rklab.utility.dto.ApiOutput;
import rklab.utility.utilities.ValidationUtils;


@Loggable
@Service
public class UpdateUserBasicDetailsApi {

    private static final String UPDATE_MSG = "User is updated successfully";

    @Autowired
    private UserService userService;
    @Autowired
    private ValidationUtils validationUtils;


    public ApiOutput<?> update(UserBasicDetailsDto userDto){
        try{
            validate(userDto);
            var loggedInUser = userService.fetchLoggedInUser();
            userDto.setId(loggedInUser.getId());
            UserDto retVal = userService.updateBasicDetails(userDto);
            return new ApiOutput<>(HttpStatus.OK.value(), UPDATE_MSG, retVal);
        } catch (Exception e) {
            return new ApiOutput<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    private void validate(UserBasicDetailsDto userDto) {
        validationUtils.validate(userDto, AvailableValidationGroup.class);
    }
}
