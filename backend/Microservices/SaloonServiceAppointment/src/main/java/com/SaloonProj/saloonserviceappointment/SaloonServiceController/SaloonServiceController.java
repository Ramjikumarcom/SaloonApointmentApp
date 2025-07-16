package com.SaloonProj.saloonserviceappointment.SaloonServiceController;

import com.SaloonProj.saloonserviceappointment.SaloonService.SaloonService;
import com.SaloonProj.saloonserviceappointment.SaloonService.client.UserFeignClient;
import com.SaloonProj.saloonserviceappointment.SaloonServiceDto.SaloonServiceDto;
import com.SaloonProj.saloonserviceappointment.SaloonServiceDto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/saloonService")
@RequiredArgsConstructor
public class SaloonServiceController {

    @Autowired
    private final SaloonService saloonService;

     private final UserFeignClient userFeignClient;
    @PostMapping("/create")

    public ResponseEntity<SaloonServiceDto>createSaloon(@RequestBody @Valid SaloonServiceDto saloonServiceDto,
                                                        @RequestHeader("Authorization") String token) throws Exception {

        UserDto userDto= userFeignClient.getUserByEmail(token).getBody();
        System.out.println(userDto);

        System.out.println( "this is userdto"+userDto);

        saloonServiceDto=saloonService.createSaloon(saloonServiceDto,userDto);
//        System.out.println(saloonServiceDto);
        return new ResponseEntity<>(saloonServiceDto,HttpStatus.CREATED);

    }

    @GetMapping("/all")
    public  ResponseEntity<List<SaloonServiceDto>> getAllSaloon(){

        return new ResponseEntity<>(saloonService.getAllSaloons(),HttpStatus.OK);
    }

    @GetMapping("/find/{saloonId}")
    public ResponseEntity<SaloonServiceDto> findSaloonById(@PathVariable Long saloonId) throws Exception {
        return new ResponseEntity<>(saloonService.getSaloonById(saloonId),HttpStatus.OK);
    }

    @PatchMapping("/update/{saloonId}")
 public ResponseEntity<SaloonServiceDto> updateSaloon( @RequestBody SaloonServiceDto saloonServiceDto,
                                                       @PathVariable Long saloonId,
                                                       @RequestHeader("Authorization") String token) throws Exception {

        UserDto userDto= userFeignClient.getUserByEmail(token).getBody();

        return new ResponseEntity<>( saloonService.updateSaloon(saloonServiceDto,userDto,saloonId),HttpStatus.OK);
    }

    @GetMapping("/owner")
    public ResponseEntity<SaloonServiceDto> getSaloonbyOwner(
    @RequestHeader("Authorization")String token) throws Exception {


        UserDto userDto=userFeignClient.getUserByEmail(token).getBody();
        if(userDto==null)throw  new Exception("User not Found ....");
        return new ResponseEntity<>(saloonService.getSaloonByOwnerId(userDto.getUserId()),HttpStatus.OK);
    }

    @GetMapping("/search")

    public ResponseEntity<List<SaloonServiceDto>>SearchSaloon(@RequestParam("searchKeyword") String searchKeyword){

        return new ResponseEntity<>(saloonService.searchSaloon(searchKeyword),HttpStatus.OK);
    }


}
