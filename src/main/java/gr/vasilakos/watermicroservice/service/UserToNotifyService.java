package gr.vasilakos.watermicroservice.service;

import gr.vasilakos.watermicroservice.dto.WaterQueueDataDto;
import gr.vasilakos.watermicroservice.dto.WaterQueueNotifyUsersDto;
import gr.vasilakos.watermicroservice.dto.UserToNotifyDto;
import gr.vasilakos.watermicroservice.model.WaterData;
import gr.vasilakos.watermicroservice.model.UserToNotify;
import gr.vasilakos.watermicroservice.repository.UserToNotifyRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserToNotifyService {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.water.routing.key}")
    private String routingKey;
    private final UserToNotifyRepository userToNotifyRepository;

    private final RabbitTemplate rabbitTemplate;

    public UserToNotifyService(UserToNotifyRepository userToNotifyRepository, RabbitTemplate rabbitTemplate) {
        this.userToNotifyRepository = userToNotifyRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public UserToNotifyDto userSubToWaterData(UserToNotifyDto userToNotifyDto){
        userToNotifyRepository.save(mapUserToNotifyDtoToUserToNotify(userToNotifyDto));
        return userToNotifyDto;
    }

    public void deleteUser(String email){
        List<UserToNotify> userToNotifyList = userToNotifyRepository.findByEmail(email);
        userToNotifyRepository.deleteAll(userToNotifyList);
    }


    public void getNotifyUserList(WaterData waterData){
        List<UserToNotify> userToNotifyList = userToNotifyRepository.findByCoordinatesAndLessDissolvedOxygen(waterData.getCoordinates(), waterData.getDissolvedOxygen());
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndMoreDissolvedOxygen(waterData.getCoordinates(), waterData.getDissolvedOxygen()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndLessOxidationReductionPotential(waterData.getCoordinates(), waterData.getOxidationReductionPotential()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndMoreOxidationReductionPotential(waterData.getCoordinates(), waterData.getOxidationReductionPotential()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndLessPH(waterData.getCoordinates(), waterData.getPH()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndMorePH(waterData.getCoordinates(), waterData.getPH()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndLessTurbidity(waterData.getCoordinates(), waterData.getTurbidity()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndMoreTurbidity(waterData.getCoordinates(), waterData.getTurbidity()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndLessTotalDissolvedSolids(waterData.getCoordinates(), waterData.getTotalDissolvedSolids()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndMoreTotalDissolvedSolids(waterData.getCoordinates(), waterData.getTotalDissolvedSolids()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndLessTemperature(waterData.getCoordinates(), waterData.getTemperature()));
        userToNotifyList.addAll(userToNotifyRepository.findByCoordinatesAndMoreTemperature(waterData.getCoordinates(), waterData.getTemperature()));

        List<String> emails = userToNotifyList.stream()
                .map(UserToNotify::getEmail).toList();

        WaterQueueNotifyUsersDto waterQueueNotifyUsersDto = new WaterQueueNotifyUsersDto();
        waterQueueNotifyUsersDto.setWaterData(mapWaterDataToWaterQueueDataDto(waterData));
        waterQueueNotifyUsersDto.setEmails(emails);

        if(!emails.isEmpty()){
            rabbitTemplate.convertAndSend(exchange,routingKey,waterQueueNotifyUsersDto);
        }
    }

    public WaterQueueDataDto mapWaterDataToWaterQueueDataDto(WaterData waterData){
        return WaterQueueDataDto.builder()
                .dissolvedOxygen(waterData.getDissolvedOxygen())
                .oxidationReductionPotential(waterData.getOxidationReductionPotential())
                .pH(waterData.getPH())
                .turbidity(waterData.getTurbidity())
                .totalDissolvedSolids(waterData.getTotalDissolvedSolids())
                .temperature(waterData.getTemperature())
                .timestamp(waterData.getTimestamp().toString())
                .coordinates(waterData.getCoordinates())
                .build();
    }

    private UserToNotify mapUserToNotifyDtoToUserToNotify(UserToNotifyDto userToNotifyDto){
        return UserToNotify.builder()
                .email(userToNotifyDto.getEmail())
                .coordinates(userToNotifyDto.getCoordinates())
                .lessDissolvedOxygen(userToNotifyDto.getLessDissolvedOxygen())
                .moreDissolvedOxygen(userToNotifyDto.getMoreDissolvedOxygen())
                .lessOxidationReductionPotential(userToNotifyDto.getLessOxidationReductionPotential())
                .moreOxidationReductionPotential(userToNotifyDto.getMoreOxidationReductionPotential())
                .lessPH(userToNotifyDto.getLessPH())
                .morePH(userToNotifyDto.getMorePH())
                .lessTurbidity(userToNotifyDto.getLessTurbidity())
                .moreTurbidity(userToNotifyDto.getMoreTurbidity())
                .lessTotalDissolvedSolids(userToNotifyDto.getLessTotalDissolvedSolids())
                .moreTotalDissolvedSolids(userToNotifyDto.getMoreTotalDissolvedSolids())
                .lessTemperature(userToNotifyDto.getLessTemperature())
                .moreTemperature(userToNotifyDto.getMoreTemperature())
                .build();
    }



}
