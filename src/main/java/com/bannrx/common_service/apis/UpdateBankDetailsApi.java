package com.bannrx.common_service.apis;

import com.bannrx.common.dtos.BankDetailsDto;
import com.bannrx.common.service.BankDetailsService;
import com.bannrx.common.service.UserService;
import com.bannrx.common.validationGroups.UpdateValidationGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import rklab.utility.annotations.Loggable;
import rklab.utility.dto.ApiOutput;
import rklab.utility.expectations.InvalidInputException;
import rklab.utility.expectations.ServerException;
import rklab.utility.utilities.ValidationUtils;

@Service
@Loggable
@RequiredArgsConstructor
public class UpdateBankDetailsApi {

    private final UserService userService;
    private final ValidationUtils validationUtils;
    private final BankDetailsService bankDetailsService;

    public ApiOutput<?> process(BankDetailsDto bankDetailsDto) throws InvalidInputException, ServerException {
        validate(bankDetailsDto);
        return new ApiOutput<>(HttpStatus.OK.value(), "Bank Details updated Successfully.", bankDetailsService.update(bankDetailsDto));
    }

    private void validate(BankDetailsDto bankDetailsDto) throws InvalidInputException {
        validationUtils.validate(bankDetailsDto);
        validationUtils.validate(bankDetailsDto, UpdateValidationGroup.class);
        var loggedInUserId = userService.fetchLoggedInUser().getId();
        bankDetailsService.validate(bankDetailsDto, loggedInUserId);
    }

}
