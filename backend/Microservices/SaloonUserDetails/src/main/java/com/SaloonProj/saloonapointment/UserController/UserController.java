package com.SaloonProj.saloonapointment.UserController;

import com.SaloonProj.saloonapointment.Model.User;
import com.SaloonProj.saloonapointment.ModelDto.UserDto;
import com.SaloonProj.saloonapointment.Service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private  final ModelMapper modelMapper;

    @PostMapping("/user/add")
    public ResponseEntity<UserDto> addUser(@RequestBody @Valid UserDto userDto) {
       UserDto userDto1= userService.createUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDto>> getAllusers() {
        List<UserDto> userDtos = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(userDtos);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) throws Exception {
        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }


    @GetMapping("/user/profile")
    public ResponseEntity<UserDto> getUserByEmail(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.getuserByJwt(jwt);
        UserDto userDto=modelMapper.map(user,UserDto.class);
        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }

    @PutMapping("/user/update/{userId}")
    public ResponseEntity<String> updateUserbyId(@PathVariable Long userId, @RequestBody UserDto updatedUserDto) throws Exception {
        userService.updateUser(updatedUserDto, userId);
        return new ResponseEntity<>("User Updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/user/delete/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId) throws Exception {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User Deleted successfully", HttpStatus.OK);
    }

}
