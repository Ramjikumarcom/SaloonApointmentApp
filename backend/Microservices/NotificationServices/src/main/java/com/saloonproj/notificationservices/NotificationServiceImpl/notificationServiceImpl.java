package com.saloonproj.notificationservices.NotificationServiceImpl;

import com.saloonproj.notificationservices.Model.NotificationModel;
import com.saloonproj.notificationservices.ModelDTO.NotificationModelDTO;
import com.saloonproj.notificationservices.NotificationRepo.NotificationRepo;
import com.saloonproj.notificationservices.OtherDTO.BookingModelDTO;
import com.saloonproj.notificationservices.Service.NotificationService;
import com.saloonproj.notificationservices.client.BookingFeignClient;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class notificationServiceImpl implements NotificationService {
    private final NotificationRepo notificationRepo;
    private final BookingFeignClient bookingFeignClient;

    private  final ModelMapper modelMapper;
    @Override
    public NotificationModelDTO createNotification(NotificationModelDTO notificationModelDTO) throws Exception {

        NotificationModel notificationModel=new NotificationModel();
        notificationModel=modelMapper.map(notificationModelDTO,NotificationModel.class);

      NotificationModel savedNotification= notificationRepo.save(notificationModel);

         notificationModelDTO.setNotificationId(savedNotification.getNotificationId());

        BookingModelDTO bookingModelDTO=bookingFeignClient.
                getBookingsByBookingId(savedNotification.getBookingId()).getBody();


       notificationModelDTO.setBookingModelDTO(bookingModelDTO);

        return notificationModelDTO;
    }

    @Override
    public List<NotificationModelDTO> getAllNotificationByUserId(Long userId) {
        List<NotificationModel> notificationModels=notificationRepo.findByUserId(userId);
        System.out.println( "this is notfinaction model "+notificationModels);

        List<NotificationModelDTO>notificationModelDTOS=notificationModels.stream().map(

                notificationModel -> {

                    BookingModelDTO bookingModeldto= null;
                    try {
                        bookingModeldto = bookingFeignClient.getBookingsByBookingId(notificationModel.getBookingId()).getBody();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    NotificationModelDTO notificationModelDTO=modelMapper.map(notificationModel, NotificationModelDTO.class);
                        notificationModelDTO.setBookingModelDTO(bookingModeldto);


                   return notificationModelDTO;

                }
        ).collect(Collectors.toList());
        return notificationModelDTOS;
    }

    @Override
    public List<NotificationModelDTO> getAllNotificationBySaloonId(Long SaloonId) {
        List<NotificationModel> notificationModels=notificationRepo.findBySaloonId(SaloonId);
        List<NotificationModelDTO>notificationModelDTOS=notificationModels.stream().map(

                notificationModel -> {

                    BookingModelDTO bookingModeldto= null;
                    try {
                        bookingModeldto = bookingFeignClient.getBookingsByBookingId(notificationModel.getBookingId()).getBody();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    NotificationModelDTO notificationModelDTO=modelMapper.map(notificationModel, NotificationModelDTO.class);
                    notificationModelDTO.setBookingModelDTO(bookingModeldto);


                    return notificationModelDTO;

                }
        ).collect(Collectors.toList());
        return notificationModelDTOS;
    }

    @Override
    public NotificationModelDTO markNotificationAsAread(Long notificationId) throws Exception {

        NotificationModel notificationModel = notificationRepo.findById(notificationId)
                .orElseThrow(() -> new Exception("notification not found ..."));

        notificationModel.setIsRead(true);

        NotificationModel updatedNotification = notificationRepo.save(notificationModel);

           NotificationModelDTO notificationModelDTO= modelMapper.map(updatedNotification,NotificationModelDTO.class);

           BookingModelDTO bookingModelDTO=bookingFeignClient.getBookingsByBookingId(notificationModelDTO.getBookingId()).getBody();
           notificationModelDTO.setBookingModelDTO(bookingModelDTO);

           return notificationModelDTO;

    }
}
