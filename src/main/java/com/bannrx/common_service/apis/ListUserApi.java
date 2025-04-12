package com.bannrx.common_service.apis;

import com.bannrx.common.searchCriteria.UserSearchCriteria;
import com.bannrx.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import rklab.utility.annotations.Loggable;
import rklab.utility.dto.ApiOutput;

@Service
@Loggable
public class ListUserApi {

    @Autowired
    private UserService userService;

    public ApiOutput<?> process(UserSearchCriteria searchCriteria){
        return new ApiOutput<>(HttpStatus.OK.value(), "User List", userService.fetch(searchCriteria));
    }

}
