package kz.baltabayev.identityservice.mapper;

import kz.baltabayev.identityservice.model.dto.UserRequest;
import kz.baltabayev.identityservice.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toModel(UserRequest userRequest);
}
