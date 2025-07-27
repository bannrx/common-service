package com.bannrx.common_service.controller;

import com.bannrx.common.dtos.requests.SignUpRequest;
import com.bannrx.common.searchCriteria.UserSearchCriteria;
import com.bannrx.common_service.apis.AddBDAUserApi;
import com.bannrx.common_service.apis.AddUserApi;
import com.bannrx.common_service.apis.ListUserApi;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rklab.utility.annotations.Loggable;
import rklab.utility.dto.ApiOutput;
import rklab.utility.expectations.InvalidInputException;
import rklab.utility.expectations.ServerException;
import java.io.IOException;

/**
 * Separate controller for the BDA user for special needs
 *
 */

@PreAuthorize("hasAnyRole('ADMIN','BDA')")
@Loggable
@RestController
@RequestMapping("/v1/api/user/bda")
@AllArgsConstructor
public class BdaController {

    private final AddUserApi addUserApi;
    private final AddBDAUserApi addBdaUserApi;
    private final ListUserApi listUserApi;

    @PostMapping("/add")
    public ApiOutput<?> addUser(@RequestBody SignUpRequest request) throws InvalidInputException, ServerException {
        return addUserApi.process(request);
    }

    @PostMapping("/create")
    public ApiOutput<?> addUser(@RequestParam MultipartFile file, @RequestParam(defaultValue = "0") int sheetNo) throws InvalidInputException, ServerException, IOException {
        return addBdaUserApi.process(file, sheetNo);
    }

    @GetMapping("/user")
    public ApiOutput<?> listUser(UserSearchCriteria searchCriteria){
        return listUserApi.process(searchCriteria);
    }

}
