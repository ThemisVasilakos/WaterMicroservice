package gr.vasilakos.watermicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "water-data")
public class WaterData {

    private Double dissolvedOxygen;
    private Double oxidationReductionPotential;
    private Double pH;
    private Double turbidity;
    private Double totalDissolvedSolids;
    private Double temperature;

    @CreatedDate
    private Instant timestamp;
    private String coordinates;
}
