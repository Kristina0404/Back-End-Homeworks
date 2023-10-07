package de.ait.events.service;

import de.ait.events.dto.NewUserDto;
import de.ait.events.dto.UserDto;
import de.ait.events.exceptions.RestException;
import de.ait.events.models.User;
import de.ait.events.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public UserDto register(NewUserDto newUser) {
        if(usersRepository.existsByEmail(newUser.getEmail())){
            throw new RestException(HttpStatus.CONFLICT,
                    "User with email < " + newUser.getEmail() + "> already exists");
        }
        User user = User.builder()
                .email(newUser.getEmail())
                .password(passwordEncoder.encode(newUser.getPassword()))
                .role(User.Role.USER)
                .build();
        usersRepository.save(user);
        return UserDto.from(user);
    }
}
