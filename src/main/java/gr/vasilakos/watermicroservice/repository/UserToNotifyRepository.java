package gr.vasilakos.watermicroservice.repository;

import gr.vasilakos.watermicroservice.model.UserToNotify;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserToNotifyRepository extends MongoRepository<UserToNotify,String> {

    List<UserToNotify> findByEmail(String email);

    @Query("{'coordinates': ?0, 'lessDissolvedOxygen': {$lte: ?1} }")
    List<UserToNotify> findByCoordinatesAndLessDissolvedOxygen(String coordinates, Double lessDissolvedOxygen);

    @Query("{'coordinates': ?0, 'moreDissolvedOxygen': {$gte: ?1} }")
    List<UserToNotify> findByCoordinatesAndMoreDissolvedOxygen(String coordinates, Double moreDissolvedOxygen);

    @Query("{'coordinates': ?0, 'lessOxidationReductionPotential': {$lte: ?1} }")
    List<UserToNotify> findByCoordinatesAndLessOxidationReductionPotential(String coordinates, Double lessOxidationReductionPotential);

    @Query("{'coordinates': ?0, 'moreOxidationReductionPotential': {$gte: ?1} }")
    List<UserToNotify> findByCoordinatesAndMoreOxidationReductionPotential(String coordinates, Double moreOxidationReductionPotential);

    @Query("{'coordinates': ?0, 'lessPH': {$lte: ?1} }")
    List<UserToNotify> findByCoordinatesAndLessPH(String coordinates, Double lessPH);

    @Query("{'coordinates': ?0, 'morePH': {$gte: ?1} }")
    List<UserToNotify> findByCoordinatesAndMorePH(String coordinates, Double morePH);

    @Query("{'coordinates': ?0, 'lessTurbidity': {$lte: ?1} }")
    List<UserToNotify> findByCoordinatesAndLessTurbidity(String coordinates, Double lessTurbidity);

    @Query("{'coordinates': ?0, 'moreTurbidity': {$gte: ?1} }")
    List<UserToNotify> findByCoordinatesAndMoreTurbidity(String coordinates, Double moreTurbidity);

    @Query("{'coordinates': ?0, 'lessTotalDissolvedSolids': {$lte: ?1} }")
    List<UserToNotify> findByCoordinatesAndLessTotalDissolvedSolids(String coordinates, Double lessTotalDissolvedSolids);

    @Query("{'coordinates': ?0, 'moreTotalDissolvedSolids': {$gte: ?1} }")
    List<UserToNotify> findByCoordinatesAndMoreTotalDissolvedSolids(String coordinates, Double moreTotalDissolvedSolids);

    @Query("{'coordinates': ?0, 'lessTemperature': {$lte: ?1} }")
    List<UserToNotify> findByCoordinatesAndLessTemperature(String coordinates, Double lessTemperature);

    @Query("{'coordinates': ?0, 'moreTemperature': {$gte: ?1} }")
    List<UserToNotify> findByCoordinatesAndMoreTemperature(String coordinates, Double moreTemperature);

}
