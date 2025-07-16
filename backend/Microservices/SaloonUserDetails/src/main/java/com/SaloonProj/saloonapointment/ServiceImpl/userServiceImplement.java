package com.SaloonProj.saloonapointment.ServiceImpl;

import com.SaloonProj.saloonapointment.ExceptionHandlePackage.UserException;
import com.SaloonProj.saloonapointment.Model.User;
import com.SaloonProj.saloonapointment.ModelDto.UserDto;
import com.SaloonProj.saloonapointment.OtherDto.KeycloakUserDto;
import com.SaloonProj.saloonapointment.Service.UserService;
import com.SaloonProj.saloonapointment.UserRepository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class userServiceImplement implements UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    private final KeyCloakService keyCloakService;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(Long UserId) throws UserException {
        User user = userRepo.findById(UserId).orElseThrow(() -> new UserException("user not found with UserId=" + UserId));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepo.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public String deleteUser(Long UserId) {
        User user = userRepo.findById(UserId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + UserId));
        userRepo.delete(user);
        return  "user deleted successfully";
    }

    @Override
    public String updateUser(UserDto userDto, Long id) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existingUser.setEmail(userDto.getEmail());
        existingUser.setRole(userDto.getRole());
        existingUser.setUserName(userDto.getUserName());
        existingUser.setFullName(userDto.getFullName());
        existingUser.setPhone(userDto.getPhone());
        existingUser.setPassword(userDto.getPassword());

        userRepo.save(existingUser);
        return "User updated successfully";
    }

    @Override
    public User getuserByJwt(String jwt) throws Exception {
        KeycloakUserDto keycloakUserDto=keyCloakService.fetchUserProfileFromJwt(jwt);

        User user=userRepo.findByEmail(keycloakUserDto.getEmail());

        return user;
    }
}
