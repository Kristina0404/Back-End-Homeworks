package de.ait.events.service;

import de.ait.events.dto.NewUserDto;
import de.ait.events.dto.UserDto;

public interface UsersService {
    UserDto register(NewUserDto newUser);

}
