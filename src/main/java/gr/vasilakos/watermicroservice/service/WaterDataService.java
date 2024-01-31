package gr.vasilakos.watermicroservice.service;

import gr.vasilakos.watermicroservice.dto.WaterDataDto;
import gr.vasilakos.watermicroservice.model.WaterData;
import gr.vasilakos.watermicroservice.repository.WaterDataRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class WaterDataService {

    private final UserToNotifyService userToNotifyService;

    private final WaterDataRepository waterDataRepository;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.water.routing.key}")
    private String routingKey;

    public WaterDataService(UserToNotifyService userToNotifyService, WaterDataRepository waterDataRepository) {
        this.userToNotifyService = userToNotifyService;
        this.waterDataRepository = waterDataRepository;
    }

    public List<WaterDataDto> findByParameter(String coordinates, Instant start, Instant end){
        List<WaterData> res = null;

        res = waterDataRepository.findByTimestampAndCoordinates(start,end,coordinates);

        return res.stream().map(this::mapWaterDataToWaterDataDto).collect(Collectors.toList());
    }

    public WaterDataDto findLastEntry(String location){
        return mapWaterDataToWaterDataDto(waterDataRepository.findFirstByCoordinatesOrderByTimestampDesc(location));
    }

    private WaterDataDto mapWaterDataToWaterDataDto(WaterData waterData){
        return WaterDataDto.builder()
                .dissolvedOxygen(waterData.getDissolvedOxygen())
                .oxidationReductionPotential(waterData.getOxidationReductionPotential())
                .pH(waterData.getPH())
                .turbidity(waterData.getTurbidity())
                .totalDissolvedSolids(waterData.getTotalDissolvedSolids())
                .temperature(waterData.getTemperature())
                .timestamp(waterData.getTimestamp())
                .coordinates(waterData.getCoordinates())
                .build();
    }
// Old method for creating data
//    public WaterData createData(){
//        WaterData waterData = WaterData.builder()
//                .dissolvedOxygen(9.8)
//                .oxidationReductionPotential(278.0)
//                .pH(5.1)
//                .turbidity(6.0)
//                .totalDissolvedSolids(156.0)
//                .temperature(23.5)
//                .timestamp(Instant.now())
//                .coordinates("Athens")
//                .build();
//
//        userToNotifyService.getNotifyUserList(waterData);
//
//        return waterDataRepository.save(waterData);
//    }
//
//    private String getRandomEuropeanCapital() {
//        Random random = new Random();
//        int randomIndex = random.nextInt(europeanCapitals.size());
//        return europeanCapitals.get(randomIndex);
//    }
//
//    private static final List<String> europeanCapitals = Arrays.asList(
//            "Athens", "Berlin", "Paris", "Madrid", "Rome", "London", "Lisbon", "Amsterdam", "Vienna", "Brussels");
}
