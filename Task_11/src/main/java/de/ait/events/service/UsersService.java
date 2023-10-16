package de.ait.events.service;

import de.ait.events.dto.NewUserDto;
import de.ait.events.dto.UserDto;

import java.util.List;

public interface UsersService {
    UserDto register(NewUserDto newUser);

    List<UserDto> getAllUsers();

    UserDto getUserById(Long currentId);
}
