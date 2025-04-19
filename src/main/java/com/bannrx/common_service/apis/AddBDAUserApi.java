package com.bannrx.common_service.apis;

import com.bannrx.common.dtos.requests.SignUpRequest;
import com.bannrx.common.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rklab.utility.annotations.Loggable;
import rklab.utility.dto.ApiOutput;
import rklab.utility.expectations.InvalidInputException;
import rklab.utility.expectations.ServerException;

import java.io.IOException;


@Service
@Loggable
@RequiredArgsConstructor
public class AddBDAUserApi {
    private static final String SUCCESS = "BDA User %s signed up successfully";
    private final UserService userService;

    public ApiOutput<?> process(MultipartFile file, int sheetNo) throws ServerException, IOException, InvalidInputException {
        validate(file, sheetNo);
        return new ApiOutput<>(HttpStatus.OK.value(), SUCCESS, userService.createBDAUser(file, sheetNo));
    }
    
    public void validate(MultipartFile file, int sheetNo) throws InvalidInputException {
        if(file == null){
            throw new InvalidInputException("Excel sheet should not be null");
        }

        if(sheetNo < 0){
            throw new InvalidInputException("SheetNo should not negative");
        }
    }
}
