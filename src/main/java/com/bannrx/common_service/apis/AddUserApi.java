package com.bannrx.common_service.apis;

import com.bannrx.common_service.dtos.SignUpRequest;
import com.bannrx.common_service.service.UserService;
import com.bannrx.common_service.utilities.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import rklab.utility.dto.ApiOutput;
import rklab.utility.expectations.InvalidInputException;



@Service
@Slf4j
@RequiredArgsConstructor
public class AddUserApi {
    private UserService userService;

    private static final String SUCCESS = "User %s signed up successfully";

    public ApiOutput<?> process(SignUpRequest request) throws InvalidInputException {
        validate(request);
        return new ApiOutput<>(HttpStatus.OK.value(),
                String.format(SUCCESS, request.getPhoneNo()),
                userService.createUser(request));
    }

    private void validate(SignUpRequest request) throws InvalidInputException {
        if (StringUtil.isNullOrEmpty(request.getName())){
            throw new InvalidInputException("Name of the user is mandatory.");
        }

        if (StringUtil.isNullOrEmpty(request.getPhoneNo())){
            throw new InvalidInputException("Phone number of the user is mandatory.");
        }
        if (userService.isAlreadyRegister(request)){
            throw new InvalidInputException("User already exists with same phone.");
        }

    }
}
