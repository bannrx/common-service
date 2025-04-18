package com.bannrx.common_service.apis;

import com.bannrx.common.dtos.BankDetailsDto;
import com.bannrx.common.dtos.verification.VerificationDto;
import com.bannrx.common.service.verification.VerificationServiceProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import rklab.utility.annotations.Loggable;
import rklab.utility.dto.ApiOutput;
import rklab.utility.expectations.InvalidInputException;

import static com.bannrx.common.enums.VerificationPurpose.BANK_DETAILS;

@Service
@Loggable
@RequiredArgsConstructor
public class VerifyBankDetailsApi {

    private final VerificationServiceProvider verificationServiceProvider;

    public ApiOutput<?> process(BankDetailsDto bankDetailsDto) throws InvalidInputException {
        bankDetailsDto.setDefaultVerificationProcess();
        var verificationDto = VerificationDto.builder()
                .purpose(BANK_DETAILS)
                .verificationData(bankDetailsDto)
                .build();
        var verificationService = verificationServiceProvider.fetchServiceProvider(verificationDto);
        verificationService.validate(verificationDto);
        var retVal = verificationService.process(verificationDto);
        return new ApiOutput<>(HttpStatus.OK.value(), null, retVal);
    }

}
