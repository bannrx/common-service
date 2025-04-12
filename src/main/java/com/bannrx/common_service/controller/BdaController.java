package com.bannrx.common_service.controller;

import com.bannrx.common.dtos.requests.SignUpRequest;
import com.bannrx.common.searchCriteria.UserSearchCriteria;
import com.bannrx.common_service.apis.AddUserApi;
import com.bannrx.common_service.apis.ListUserApi;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
    private final ListUserApi listUserApi;

    @PostMapping("/add")
    public ApiOutput<?> addUser(@RequestBody SignUpRequest request) throws InvalidInputException, ServerException {
        return addUserApi.process(request);
    }

    @GetMapping("/user")
    public ApiOutput<?> listUser(UserSearchCriteria searchCriteria){
        return listUserApi.process(searchCriteria);
    }

}
