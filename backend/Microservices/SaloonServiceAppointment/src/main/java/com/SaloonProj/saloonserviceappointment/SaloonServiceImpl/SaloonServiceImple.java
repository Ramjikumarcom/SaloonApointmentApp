package com.SaloonProj.saloonserviceappointment.SaloonServiceImpl;

import com.SaloonProj.saloonserviceappointment.ExceptionHandler.SaloonNotFoundException;
import com.SaloonProj.saloonserviceappointment.Model.Model;
import com.SaloonProj.saloonserviceappointment.SaloonService.SaloonService;
import com.SaloonProj.saloonserviceappointment.SaloonServiceDto.SaloonServiceDto;
import com.SaloonProj.saloonserviceappointment.SaloonServiceDto.UserDto;
import com.SaloonProj.saloonserviceappointment.SaloonServiceRepository.SaloonServiceRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaloonServiceImple implements SaloonService {


    @Autowired
    private final SaloonServiceRepo saloonServiceRepo;


    @Autowired
    private ModelMapper modelMapper;


    @Override
    public SaloonServiceDto createSaloon(SaloonServiceDto saloonServiceDto, UserDto userDto) {
        saloonServiceDto.setOwnerId(userDto.getUserId());
        Model model = modelMapper.map(saloonServiceDto, Model.class);
//        model.setOwnerId(userDto.getUserId());
        System.out.println(model);
        model.setSaloonId(null); // Ensure INSERT, not UPDATE
        Model saved = saloonServiceRepo.save(model);
        return modelMapper.map(saved, SaloonServiceDto.class);
    }

    @Override
    public SaloonServiceDto updateSaloon(SaloonServiceDto saloonServiceDto, UserDto userDto, Long SaloonId) throws Exception {

        Model Model = saloonServiceRepo.findById(SaloonId).orElseThrow(() -> new SaloonNotFoundException("Saloon not found with saloonId= " + SaloonId));
        if (Model.getOwnerId().equals(userDto.getUserId())) {

            if (saloonServiceDto.getAddress() != null) Model.setAddress(saloonServiceDto.getAddress());
            if (saloonServiceDto.getCity() != null) Model.setCity(saloonServiceDto.getCity());
            if (saloonServiceDto.getEmail() != null) Model.setEmail(saloonServiceDto.getEmail());
            if (saloonServiceDto.getName() != null) Model.setName(saloonServiceDto.getName());
            if (saloonServiceDto.getPhoneNumber() != null)Model.setPhoneNumber(saloonServiceDto.getPhoneNumber());
            if (saloonServiceDto.getOpenTime() != null) Model.setOpenTime(saloonServiceDto.getOpenTime());
            if (saloonServiceDto.getCloseTime() != null) Model.setCloseTime(saloonServiceDto.getCloseTime());
            if (saloonServiceDto.getState() != null) Model.setState(saloonServiceDto.getState());
            if (saloonServiceDto.getOwnerId() != null) Model.setOwnerId(userDto.getUserId());
            if (saloonServiceDto.getImages() != null) Model.setImages(saloonServiceDto.getImages());

            saloonServiceRepo.save(Model);

        }

        return modelMapper.map(Model, SaloonServiceDto.class);
    }

    @Override
    public List<SaloonServiceDto> getAllSaloons() {
        List<Model> Model = saloonServiceRepo.findAll();
        List<SaloonServiceDto> existingAllSaloon = Model.stream().map(
                saloonModel -> modelMapper.map(saloonModel, SaloonServiceDto.class))
                .collect(Collectors.toList());

        return existingAllSaloon;
    }

    @Override
    public SaloonServiceDto getSaloonById(Long SaloonId) throws Exception {
        Model Model = saloonServiceRepo.findById(SaloonId).orElseThrow(() -> new SaloonNotFoundException("Saloon not found with saloonId= " + SaloonId));

        return modelMapper.map(Model, SaloonServiceDto.class);
    }

    @Override
    public SaloonServiceDto getSaloonByOwnerId(Long ownerId) {
        Model Model = saloonServiceRepo.findByOwnerId(ownerId);

        return modelMapper.map(Model, SaloonServiceDto.class);
    }

    @Override
    public List<SaloonServiceDto> searchSaloon(String salooName) {
        List<Model> SearchedSaloon = saloonServiceRepo.searchSaloons(salooName);
        List<SaloonServiceDto> searchedSaloonDTO = SearchedSaloon.stream().
                map(SaloonName -> modelMapper.map(SaloonName, SaloonServiceDto.class))
                .collect(Collectors.toList());
        return searchedSaloonDTO;
    }
}
