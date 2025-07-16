package com.SaloonProj.serviceoffering.OfferingController;

import com.SaloonProj.serviceoffering.ServiceOfferingDto.ServiceOfferingDto;
import com.SaloonProj.serviceoffering.ServiceOfferingService.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/service-offering")
public class OfferingController {

    @Autowired
    private final ServiceOfferingService serviceOfferingService;


//
//   @PutMapping("/update/{serviceId}")
//   public ResponseEntity<ServiceOfferingDto>UpdateOffer(@PathVariable Long serviceId,@RequestBody ServiceOfferingDto serviceOfferingDto) throws Exception {
//
//       ServiceOfferingDto serviceOfferingDto1=serviceOfferingService.
//               updateService(serviceId,serviceOfferingDto);
//
//       return  new ResponseEntity<>(serviceOfferingDto1,HttpStatus.OK);
//   }


   //required = false makes it optional. If the client doesn't pass categoryId, it will be set to null.

    @GetMapping("/AllBySaloonId/{saloonId}")
    public ResponseEntity<Set<ServiceOfferingDto>>getAllServiceBySaloonId(@PathVariable Long saloonId,
                                                                          @RequestParam(required = false) Long categoryId) throws Exception {


        serviceOfferingService.
                getAllServiceBySaloonId(saloonId,categoryId);

        return  new ResponseEntity<>( serviceOfferingService.
                getAllServiceBySaloonId(saloonId, categoryId)
,HttpStatus.OK);
    }
 // may be in future you have to change @requestParam to @Pathvariable
    @GetMapping("/getallByIds")
    public ResponseEntity<Set<ServiceOfferingDto>> getServicesByIds(@RequestParam Set<Long> serviceIds) {
        Set<ServiceOfferingDto> services = serviceOfferingService.getServicesByIds(serviceIds);
        return ResponseEntity.ok(services);
    }


    @GetMapping("/get/{serviceId}")
    public ResponseEntity<ServiceOfferingDto>getByServiceId(@PathVariable Long serviceId) throws Exception {

        ServiceOfferingDto serviceOfferingDto1=serviceOfferingService.
                getServiceById(serviceId);

        return  new ResponseEntity<>(serviceOfferingDto1,HttpStatus.OK);
    }




}
