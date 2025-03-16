package com.bannrx.common_service.apis;

import com.bannrx.common.dtos.GenerateTokenRequest;
import com.bannrx.common.dtos.TokenResponse;
import com.bannrx.common.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import rklab.utility.annotations.Loggable;
import rklab.utility.dto.ApiOutput;
import rklab.utility.expectations.InvalidInputException;
import rklab.utility.services.JWTService;
import rklab.utility.services.JwtConfiguration;

@Service
@Loggable
@RequiredArgsConstructor
public class GenerateTokenApi {

    private final UserService userService;
    private final JWTService jwtService;
    private final JwtConfiguration configuration;

    public ApiOutput<TokenResponse> process(GenerateTokenRequest request) throws InvalidInputException, AccessDeniedException {
        var user = userService.fetchByEmail(request.getUsername());
        var password = user.getPassword();
        if (StringUtils.equals(password, request.getPassword())){
            var token = jwtService.generateToken(configuration, user);
            return new ApiOutput<>(HttpStatus.OK.value(),
                    "Token generated.",
                    new TokenResponse(token));
        }
        throw new AccessDeniedException("Invalid password");
    }

}
