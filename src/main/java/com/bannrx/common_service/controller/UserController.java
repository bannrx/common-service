package com.bannrx.common_service.controller;
import com.bannrx.common.dtos.BankDetailsDto;
import com.bannrx.common.dtos.BusinessDto;
import com.bannrx.common.dtos.requests.GenerateTokenRequest;
import com.bannrx.common.dtos.requests.PasswordLoginRequest;
import com.bannrx.common.dtos.user.UserBasicDetailsDto;
import com.bannrx.common_service.apis.*;
import com.bannrx.common.dtos.requests.SignUpRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rklab.utility.annotations.Loggable;
import rklab.utility.dto.ApiOutput;
import rklab.utility.expectations.InvalidInputException;
import rklab.utility.expectations.ServerException;


@Loggable
@RestController
@RequestMapping("/v1/api/user")
@AllArgsConstructor
public class UserController {

    private final AddUserApi addUserApi;
    private final UpdateUserBasicDetailsApi updateUserBasicDetailsApi;
    private final DeleteUserApi deleteUserApi;
    private final GenerateTokenApi generateTokenApi;
    private final LoginApi loginApi;
    private final UpdateBankDetailsApi updateBankDetailsApi;
    private final UpdateBusinessApi updateBusinessApi;
    private final DeleteUserProfile deleteUserProfile;


    @PostMapping("/add")
    public ApiOutput<?> addUser(@RequestBody SignUpRequest request) throws InvalidInputException, ServerException {
        return addUserApi.process(request);
    }

    @PutMapping("/update/basic-details")
    public ApiOutput<?> updateUser(@RequestBody UserBasicDetailsDto userDto) {
        return updateUserBasicDetailsApi.update(userDto);
    }

    @PutMapping("/update/bank-details")
    public ApiOutput<?> updateBankDetails(@RequestBody BankDetailsDto bankDetailsDto) throws InvalidInputException, ServerException {
        return updateBankDetailsApi.process(bankDetailsDto);
    }

    @DeleteMapping("/delete/{phoneNo}/{addressId}")
    public ApiOutput<?> deleteUser(@PathVariable String phoneNo, @PathVariable String addressId) throws InvalidInputException, ServerException {
        return deleteUserApi.delete(addressId, phoneNo);
    }

    @GetMapping("/token")
    public ApiOutput<?> generateToken(
            @RequestHeader("username") String username,
            @RequestHeader("password") String password
    ) throws InvalidInputException {
        return generateTokenApi.process(new GenerateTokenRequest(username, password));
    }

    @PostMapping("/login")
    public ApiOutput<?> login(@RequestBody PasswordLoginRequest request) throws InvalidInputException {
        return loginApi.process(request);
    }

    @PutMapping("/update/business-details")
    public ApiOutput<?> updateBusinessDetails(@RequestBody BusinessDto businessDto) throws InvalidInputException, ServerException {
        return updateBusinessApi.update(businessDto);
    }

    @DeleteMapping("userProfile/delete/{userId}")
    public ApiOutput<?> deleteUserProfile(@PathVariable String userId) throws InvalidInputException {
        return deleteUserProfile.process(userId);
    }

}

