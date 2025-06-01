package com.bannrx.common_service.controller;

import com.bannrx.common_service.apis.AddAddressApi;
import com.bannrx.common_service.apis.DeleteAddressApi;
import com.bannrx.common_service.apis.UpdateAddressApi;
import com.bannrx.common.dtos.AddressDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rklab.utility.annotations.Loggable;
import rklab.utility.dto.ApiOutput;
import rklab.utility.expectations.InvalidInputException;
import rklab.utility.expectations.ServerException;

@Loggable
@RestController
@RequestMapping("/v1/api/address")
@AllArgsConstructor
public class AddressController {

    private AddAddressApi addAddressApi;
    private UpdateAddressApi updateAddressApi;
    private DeleteAddressApi deleteAddressApi;

    @PostMapping("/add")
    public ApiOutput<?> addAddress(@RequestBody AddressDto addressDto) throws InvalidInputException {
        return addAddressApi.addAddress(addressDto);
    }

    @PutMapping("/update")
    public ApiOutput<?> updateAddress(@RequestBody AddressDto addressDto) throws InvalidInputException, ServerException {
        return updateAddressApi.update(addressDto);
    }

    @DeleteMapping("/delete/{addressId}")
    public ApiOutput<?> deleteAddress( @PathVariable String addressId)  {
        return deleteAddressApi.delete(addressId);
    }
}
