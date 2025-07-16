package com.SaloonProj.serviceoffering.OfferingController;

import com.SaloonProj.serviceoffering.OtherDTO.CategoryDto;
import com.SaloonProj.serviceoffering.OtherDTO.SaloonServiceDto;
import com.SaloonProj.serviceoffering.ServiceOfferingDto.ServiceOfferingDto;
import com.SaloonProj.serviceoffering.ServiceOfferingService.ServiceOfferingService;
import com.SaloonProj.serviceoffering.ServiceOfferingService.client.CategoryFeignClient;
import com.SaloonProj.serviceoffering.ServiceOfferingService.client.SaloonServiceFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service-offering/saloon-owner")
@RequiredArgsConstructor

public class OfferBySaloonOwnerController {

    @Autowired
    private final ServiceOfferingService serviceOfferingService;
    private  final SaloonServiceFeignClient saloonServiceFeignClient;
    private  final CategoryFeignClient categoryFeignClient;
    @PostMapping("/create")

    public ResponseEntity<ServiceOfferingDto> createService(@RequestBody ServiceOfferingDto serviceOfferingDto,
                                                            @RequestHeader("Authorization") String token)
            throws Exception {
        SaloonServiceDto saloonServiceDto=saloonServiceFeignClient.getSaloonbyOwner(token).getBody();

       if (saloonServiceDto==null||saloonServiceDto.getSaloonId()==null)
           throw  new Exception("saloon not found with this saloon Id"+saloonServiceDto.getSaloonId());

        CategoryDto categoryDto= categoryFeignClient.getCategoryByIdAndSaloonId(serviceOfferingDto.getCategoryId(),
                saloonServiceDto.getSaloonId()).getBody();
        System.out.println(categoryDto);
        ServiceOfferingDto serviceOfferingDto1=serviceOfferingService.createService(saloonServiceDto,serviceOfferingDto,categoryDto);

        return  new ResponseEntity<>(serviceOfferingDto1, HttpStatus.CREATED);
    }

    @PutMapping("/update/{serviceId}")
    public ResponseEntity<ServiceOfferingDto>UpdateOffer(@PathVariable Long serviceId,@RequestBody ServiceOfferingDto serviceOfferingDto) throws Exception {

        ServiceOfferingDto serviceOfferingDto1=serviceOfferingService.
                updateService(serviceId,serviceOfferingDto);

        return  new ResponseEntity<>(serviceOfferingDto1,HttpStatus.OK);
    }
}
