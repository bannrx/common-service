package com.bannrx.common_service.controller;

import com.bannrx.common.dtos.BankDetailsDto;
import com.bannrx.common_service.apis.VerifyBankDetailsApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rklab.utility.dto.ApiOutput;
import rklab.utility.expectations.InvalidInputException;

@RestController
@RequestMapping("v1/api/verify")
@RequiredArgsConstructor
public class VerificationController {

    private final VerifyBankDetailsApi verifyBankDetailsApi;

    @PostMapping("/bank-details")
    public ApiOutput<?> verifyBankDetails(@RequestBody BankDetailsDto bankDetailsDto) throws InvalidInputException {
        return verifyBankDetailsApi.process(bankDetailsDto);
    }

}
