package com.bannrx.common_service.apis;

import com.bannrx.common.dtos.BusinessDto;
import com.bannrx.common.service.BusinessService;
import com.bannrx.common.service.UserService;
import com.bannrx.common.validationGroups.UpdateValidationGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import rklab.utility.dto.ApiOutput;
import rklab.utility.expectations.InvalidInputException;
import rklab.utility.expectations.ServerException;
import rklab.utility.utilities.ValidationUtils;



@Service
@RequiredArgsConstructor
public class UpdateBusinessApi {
    private static final String UPDATE_MSG = "The Business Details has been updated successfully";
    private final BusinessService businessService;
    private final ValidationUtils validationUtils;
    private final UserService userService;

    public ApiOutput<?> update(BusinessDto businessDto) throws InvalidInputException, ServerException {
        validate(businessDto);
        return new ApiOutput<>(HttpStatus.OK.value(), UPDATE_MSG, businessService.update(businessDto) );
    }

    private void validate(BusinessDto businessDto)throws InvalidInputException {
        validationUtils.validate(businessDto, UpdateValidationGroup.class);
        var loggedInUserId = userService.fetchLoggedInUser().getId();
        businessService.validate(businessDto, loggedInUserId);
    }
}
