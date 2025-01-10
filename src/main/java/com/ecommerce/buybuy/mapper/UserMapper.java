package com.ecommerce.buybuy.mapper;

import com.ecommerce.buybuy.dto.response.UserResponse;
import com.ecommerce.buybuy.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Named("UserMapper")
public interface UserMapper {
    UserResponse toUserResponse(User user);

}
