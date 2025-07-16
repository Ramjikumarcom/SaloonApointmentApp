package com.SaloonProj.serviceoffering.ServiceOfferingService;

import com.SaloonProj.serviceoffering.OtherDTO.CategoryDto;
import com.SaloonProj.serviceoffering.OtherDTO.SaloonServiceDto;
import com.SaloonProj.serviceoffering.ServiceOfferingDto.ServiceOfferingDto;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface ServiceOfferingService {

    ServiceOfferingDto createService(SaloonServiceDto saloonServiceDto,ServiceOfferingDto serviceOfferingDto, CategoryDto categoryDto);


    ServiceOfferingDto updateService(Long serviceId,ServiceOfferingDto serviceOfferingDto) throws Exception;


    Set<ServiceOfferingDto>getAllServiceBySaloonId(Long saloonId,Long categoryId);


    Set<ServiceOfferingDto>getServicesByIds(Set<Long> serviceIds);

    ServiceOfferingDto getServiceById(Long serviceId) throws Exception;

}
