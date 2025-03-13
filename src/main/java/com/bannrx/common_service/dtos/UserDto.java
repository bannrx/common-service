package com.bannrx.common_service.dtos;

import com.bannrx.common_service.entities.User;
import com.bannrx.common_service.validationGroups.AddOrderValidationGroup;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;



@Data
@NoArgsConstructor
public class UserDto {

    @NotEmpty(groups = AddOrderValidationGroup.class, message = "User Id is mandatory.")
    private String id;
    private String name;
    private String phoneNo;


    public UserDto(String id){
        this.id = id;
    }

    public static UserDto parse(User user){
        var mapper = new ModelMapper();
        return  mapper.map(user, UserDto.class);
    }

}
