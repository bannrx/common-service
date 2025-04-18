package com.bannrx.common_service.apis;

import com.bannrx.common.dtos.responses.TokenResponse;
import com.bannrx.common.dtos.requests.PasswordLoginRequest;
import com.bannrx.common.dtos.verification.PasswordVerificationData;
import com.bannrx.common.dtos.verification.VerificationDto;
import com.bannrx.common.service.AuthTokenService;
import com.bannrx.common.service.UserService;
import com.bannrx.common.service.verification.VerificationServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import rklab.utility.annotations.Loggable;
import rklab.utility.dto.ApiOutput;
import rklab.utility.expectations.InvalidInputException;
import rklab.utility.utilities.ValidationUtils;

import static com.bannrx.common.enums.VerificationPurpose.LOGIN;

@Service
@Loggable
public class LoginApi {

    @Autowired private ValidationUtils validationUtils;
    @Autowired private UserService userService;
    @Autowired private AuthTokenService authTokenService;
    @Autowired private VerificationServiceProvider verificationServiceProvider;

    public ApiOutput<?> process(PasswordLoginRequest request) throws InvalidInputException {
        var passwordVerificationDto = new PasswordVerificationData(request);
        var verificationDto = VerificationDto.builder()
                .purpose(LOGIN)
                .verificationData(passwordVerificationDto)
                .verified(false)
                .build();
        var verificationService = verificationServiceProvider.fetchServiceProvider(verificationDto);
        verificationService.validate(verificationDto);
        verificationDto = verificationService.process(verificationDto);
        if (verificationDto.isVerified()){
            return new ApiOutput<>(HttpStatus.OK.value(), null, new TokenResponse(authTokenService.fetchToken(request.getUsername())));
        }
        throw new AccessDeniedException("Invalid Password");
    }

}
