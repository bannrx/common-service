package com.bannrx.common_service.apis;

import com.bannrx.common.service.UserService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import rklab.utility.annotations.Loggable;
import rklab.utility.dto.ApiOutput;
import rklab.utility.expectations.InvalidInputException;


@Loggable
@Service
@RequiredArgsConstructor
public class DeleteUserProfile {

    private final UserService service;
    private static final String MESSAGE = "User profile deleted successfully";

    public ApiOutput<?> process(String userId) throws InvalidInputException {
        validate(userId);
        return new ApiOutput<>(HttpStatus.OK.value(), MESSAGE, service.deleteUserProfile(userId) );

    }

    public void validate(String userId) throws InvalidInputException {
        if(StringUtils.isBlank(userId)){
            throw new InvalidInputException("User id cant be null or empty");
        }
        service.fetchById(userId);
    }
}
