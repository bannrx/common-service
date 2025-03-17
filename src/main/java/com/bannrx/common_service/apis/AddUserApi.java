package com.bannrx.common_service.apis;

import com.bannrx.common.dtos.SignUpRequest;
import com.bannrx.common.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import rklab.utility.annotations.Loggable;
import rklab.utility.dto.ApiOutput;
import rklab.utility.expectations.InvalidInputException;
import rklab.utility.expectations.ServerException;
import rklab.utility.utilities.ValidationUtils;


@Service
@Loggable
@RequiredArgsConstructor
public class AddUserApi {

    private final UserService userService;
    private final ValidationUtils validationUtils;

    private static final String SUCCESS = "User %s signed up successfully";

    public ApiOutput<?> process(SignUpRequest request) throws InvalidInputException, ServerException {
        validate(request);
        return new ApiOutput<>(HttpStatus.OK.value(),
                String.format(SUCCESS, request.getPhoneNo()),
                userService.createUser(request));
    }

    private void validate(SignUpRequest request) throws InvalidInputException {
        validationUtils.validate(request);
        if (userService.isAlreadyRegister(request)){
            throw new InvalidInputException("User already exists.");
        }

    }
}
