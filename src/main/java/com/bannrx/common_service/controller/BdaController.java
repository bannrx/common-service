package com.bannrx.common_service.controller;

import com.bannrx.common.dtos.SignUpRequest;
import com.bannrx.common_service.apis.AddUserApi;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rklab.utility.annotations.Loggable;
import rklab.utility.dto.ApiOutput;
import rklab.utility.expectations.InvalidInputException;
import rklab.utility.expectations.ServerException;

/**
 * Separate controller for the BDA user for special needs
 *
 */

@Loggable
@RestController
@RequestMapping("/v1/api/user/bda")
@AllArgsConstructor
public class BdaController {

    private final AddUserApi addUserApi;

    @PostMapping("/add")
    public ApiOutput<?> addUser(@RequestBody SignUpRequest request) throws InvalidInputException, ServerException {
        return addUserApi.process(request);
    }

}
