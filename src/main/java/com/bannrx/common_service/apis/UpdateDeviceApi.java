package com.bannrx.common_service.apis;

import com.bannrx.common.dtos.device.DeviceDto;
import com.bannrx.common.service.DeviceService;
import com.bannrx.common.validationGroups.UpdateValidationGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import rklab.utility.annotations.Loggable;
import rklab.utility.dto.ApiOutput;
import rklab.utility.expectations.InvalidInputException;
import rklab.utility.expectations.ServerException;
import rklab.utility.utilities.ValidationUtils;
import java.util.Optional;


@Service
@Loggable
@RequiredArgsConstructor
public class UpdateDeviceApi {
    private final DeviceService deviceService;
    private final ValidationUtils validationUtils;

    private static final String Message = "Device updated Successful";

    public ApiOutput<?> process(DeviceDto request) throws InvalidInputException, ServerException {
        validate(request);
        return new ApiOutput<>(HttpStatus.OK.value(), Message, deviceService.update(request));
    }

    private void validate(DeviceDto request) throws InvalidInputException {
        validationUtils.validate(request, UpdateValidationGroup.class);

        if(!deviceService.existById(request.getId())){
            throw new InvalidInputException(String.format("Invalid device id %s.", request.getId()));
        }

        var dimension = Optional.ofNullable(request).map(DeviceDto::getDimension).orElse(null);

        if(dimension != null){
            validationUtils.validate(dimension);
        }
    }
}
