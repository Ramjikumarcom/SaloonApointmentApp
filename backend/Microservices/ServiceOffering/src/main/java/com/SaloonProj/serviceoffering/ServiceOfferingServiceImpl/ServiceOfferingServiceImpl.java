package com.SaloonProj.serviceoffering.ServiceOfferingServiceImpl;

import com.SaloonProj.serviceoffering.OtherDTO.CategoryDto;
import com.SaloonProj.serviceoffering.OtherDTO.SaloonServiceDto;
import com.SaloonProj.serviceoffering.ServiceOfferingDto.ServiceOfferingDto;
import com.SaloonProj.serviceoffering.ServiceOfferingModel.ServiceOfferingModel;
import com.SaloonProj.serviceoffering.ServiceOfferingRepo.ServiceOfferingRepository;
import com.SaloonProj.serviceoffering.ServiceOfferingService.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceOfferingServiceImpl implements ServiceOfferingService {

    @Autowired
    private  final ServiceOfferingRepository serviceOfferingRepository;

   @Autowired
   private final ModelMapper modelMapper;

    @Override
    public ServiceOfferingDto createService(SaloonServiceDto saloonServiceDto,
                                            ServiceOfferingDto serviceOfferingDto,
                                            CategoryDto categoryDto) {

        serviceOfferingDto.setCategoryId(categoryDto.getCategoryId());
        serviceOfferingDto.setSaloonId(saloonServiceDto.getSaloonId());

        ServiceOfferingModel serviceOfferingModel=modelMapper.
                map(serviceOfferingDto,ServiceOfferingModel.class);
        serviceOfferingRepository.save(serviceOfferingModel);
        return  modelMapper.map(serviceOfferingModel,ServiceOfferingDto.class);
    }

    @Override
    public ServiceOfferingDto updateService(Long serviceId, ServiceOfferingDto serviceOfferingDto) throws Exception {
        ServiceOfferingModel serviceOfferingModel=serviceOfferingRepository.findById(serviceId).
                orElseThrow(()->new Exception("service not found with this Service id="+serviceId));

if(serviceOfferingDto.getName()!=null)serviceOfferingModel.setName(serviceOfferingDto.getName());

if(serviceOfferingDto.getDescription()!=null)serviceOfferingModel.setDescription(serviceOfferingDto.getDescription());
if(serviceOfferingDto.getPrice()!=0)serviceOfferingModel.setPrice(serviceOfferingDto.getPrice());

if(serviceOfferingDto.getDuration()!=0)serviceOfferingModel.setDuration(serviceOfferingDto.getDuration());
if(serviceOfferingDto.getImage()!=null)serviceOfferingModel.setImage(serviceOfferingDto.getImage());

ServiceOfferingModel serviceOfferingModel1= serviceOfferingRepository.save(serviceOfferingModel);
        return  modelMapper.map(serviceOfferingModel1,ServiceOfferingDto.class);

    }

    @Override
    public Set<ServiceOfferingDto> getAllServiceBySaloonId(Long saloonId, Long categoryId) {
        // finding by saloonId
        Set<ServiceOfferingModel>serviceOfferingModels=serviceOfferingRepository.findBySaloonId(saloonId);


        // converting in dtos
        Set<ServiceOfferingDto>serviceOfferingDtos=serviceOfferingModels.stream().
                map(serviceOfferingModel->modelMapper.map(serviceOfferingModel,ServiceOfferingDto.class))
                .collect(Collectors.toSet());


        // filtering the categoryId

            serviceOfferingDtos=serviceOfferingDtos.stream().filter((serviceOfferingDto ->
                    serviceOfferingDto.getCategoryId()!=null&&
                    serviceOfferingDto.getCategoryId().equals(categoryId))).collect(Collectors.toSet());

        return serviceOfferingDtos;

    }

    @Override
    public Set<ServiceOfferingDto> getServicesByIds(Set<Long> serviceIds) {

        List<ServiceOfferingModel> serviceOfferingModels=serviceOfferingRepository.findAllById(serviceIds);


         List<ServiceOfferingDto>serviceOfferingDtos=serviceOfferingModels.stream().
                 map((serviceOfferingModel ->modelMapper.map(serviceOfferingModel,ServiceOfferingDto.class) )).collect(Collectors.toList());

        return new HashSet<>(serviceOfferingDtos);
    }

    @Override
    public ServiceOfferingDto getServiceById(Long serviceId) throws Exception {

        ServiceOfferingModel serviceOfferingModel=serviceOfferingRepository.findById(serviceId).orElseThrow(()->
                new Exception("service is not found with this serviceId="+serviceId));

        ServiceOfferingDto serviceOfferingDto=modelMapper.map(serviceOfferingModel,ServiceOfferingDto.class);

        return  serviceOfferingDto;
    }
}
