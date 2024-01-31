package gr.vasilakos.watermicroservice.repository;

import gr.vasilakos.watermicroservice.model.WaterData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.Instant;
import java.util.List;

public interface WaterDataRepository extends MongoRepository<WaterData, String> {

    WaterData findFirstByCoordinatesOrderByTimestampDesc(String coordinates);
    @Query("{'timestamp': {$gte: ?0, $lte: ?1}, 'coordinates': ?2}")
    List<WaterData> findByTimestampAndCoordinates(Instant startDate, Instant endDate, String coordinates);
}
