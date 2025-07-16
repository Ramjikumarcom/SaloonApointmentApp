package com.SaloonProj.saloonapointment.Service;

import com.SaloonProj.saloonapointment.ExceptionHandlePackage.UserException;
import com.SaloonProj.saloonapointment.Model.User;
import com.SaloonProj.saloonapointment.ModelDto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long UserId) throws UserException;

    List<UserDto> getAllUser();

    String deleteUser(Long UserId);

    String updateUser(UserDto userDto,Long id);

    User getuserByJwt(String jwt) throws Exception;
}
