package ru.ism.test_service.domain.mapper;

import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import ru.ism.test_service.domain.dto.SignUpRequest;
import ru.ism.test_service.domain.dto.UserDto;
import ru.ism.test_service.domain.model.User;

@Mapper(componentModel = "spring", uses = PasswordEncoderMapper.class)
public interface UserMapper {

    @Mapping(source = "signUpRequest.name", target = "password", qualifiedBy = EncodedMapping.class)
    @Mapping(source = "signUpRequest.name", target = "username")
    User requestToModel(SignUpRequest signUpRequest);

    @Mapping(source = "user.username", target = "name")
    UserDto modelToDto(User user);
    @Mapping(source = "userDto.name", target = "username")
    User dtoToModel(UserDto userDto);
}
