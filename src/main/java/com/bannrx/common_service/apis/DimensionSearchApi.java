package com.bannrx.common_service.apis;

import com.bannrx.common.searchCriteria.DeviceSearchCriteria;
import com.bannrx.common.searchCriteria.DimensionSearchCriteria;
import com.bannrx.common.service.DimensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import rklab.utility.annotations.Loggable;
import rklab.utility.dto.ApiOutput;



@Loggable
@Service
@RequiredArgsConstructor
public class DimensionSearchApi {

    private final DimensionService dimensionService;
    private static final String MESSAGE = "Dimension Search Successful";

    public ApiOutput<?> search(DimensionSearchCriteria searchCriteria) {
        return new ApiOutput<>(HttpStatus.OK.value(), MESSAGE, dimensionService.search(searchCriteria));
    }
}