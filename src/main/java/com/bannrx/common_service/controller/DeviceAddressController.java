package com.bannrx.common_service.controller;

import com.bannrx.common.dtos.DAM.DAMRegisterDto;
import com.bannrx.common_service.apis.AddDAMApi;
import com.bannrx.common_service.apis.DeleteDAMApi;
import com.bannrx.common_service.apis.UpdateDAMApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rklab.utility.dto.ApiOutput;
import rklab.utility.expectations.InvalidInputException;
import rklab.utility.expectations.ServerException;



@RequestMapping("/v1/api/DAM")
@RestController
@RequiredArgsConstructor
public class DAMController {
    private final AddDAMApi addDAMApi;
    private final DeleteDAMApi deleteDAMApi;

    private final UpdateDAMApi updateDAMApi;


    @PostMapping("/add")
    public ApiOutput<?> add(@RequestBody DAMRegisterDto dto) throws InvalidInputException, ServerException {
        return addDAMApi.process(dto);
    }

    @PatchMapping("/detach")
    public ApiOutput<?> detachDevice(@RequestParam String DAMId) throws InvalidInputException, ServerException {
        return updateDAMApi.process(DAMId);
    }

    @DeleteMapping("/delete")
    public ApiOutput<?> delete(@RequestParam String DAMId) throws InvalidInputException {
        return deleteDAMApi.process(DAMId);
    }
}
