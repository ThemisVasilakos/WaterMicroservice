package gr.vasilakos.watermicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WaterQueueDataDto {

    private Double dissolvedOxygen;
    private Double oxidationReductionPotential;
    private Double pH;
    private Double turbidity;
    private Double totalDissolvedSolids;
    private Double temperature;
    private String timestamp;
    private String coordinates;
}
