package com.bannrx.common_service.apis;

import com.bannrx.common.service.AddressService;
import com.bannrx.common.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import rklab.utility.annotations.Loggable;
import rklab.utility.dto.ApiOutput;
import rklab.utility.expectations.InvalidInputException;
import rklab.utility.expectations.ServerException;


@Loggable
@Service
@Slf4j
@RequiredArgsConstructor
public class DeleteUserApi {

    private static final String MSG = "User deleted successfully.";

    private final UserService userService;
    private final AddressService addressService;

    public ApiOutput<String> delete(String addressId, String phoneNo) throws ServerException {
        addressService.delete(addressId);
        userService.delete(phoneNo);
        return new ApiOutput<>(HttpStatus.OK.value(), null, MSG);
    }

}
