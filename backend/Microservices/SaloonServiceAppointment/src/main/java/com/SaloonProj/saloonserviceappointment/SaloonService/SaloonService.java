package com.SaloonProj.saloonserviceappointment.SaloonService;

import com.SaloonProj.saloonserviceappointment.SaloonServiceDto.SaloonServiceDto;
import com.SaloonProj.saloonserviceappointment.SaloonServiceDto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SaloonService {

    SaloonServiceDto createSaloon(SaloonServiceDto saloonServiceDto, UserDto userDto);

    SaloonServiceDto updateSaloon(SaloonServiceDto saloonServiceDto, UserDto userDto, Long SaloonId) throws Exception;

    List<SaloonServiceDto> getAllSaloons();

    SaloonServiceDto getSaloonById(Long SaloonId) throws Exception;

    SaloonServiceDto getSaloonByOwnerId(Long ownerId);


//    List<SaloonServiceDto> searchSaloonByCity(String city);

    List<SaloonServiceDto> searchSaloon(String SearchBy);


}
