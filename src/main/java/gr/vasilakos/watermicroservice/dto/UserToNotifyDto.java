package gr.vasilakos.watermicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserToNotifyDto {

    private String email;
    private String coordinates;

    private Double lessDissolvedOxygen;
    private Double moreDissolvedOxygen;

    private Double lessOxidationReductionPotential;
    private Double moreOxidationReductionPotential;

    private Double lessPH;
    private Double morePH;

    private Double lessTurbidity;
    private Double moreTurbidity;

    private Double lessTotalDissolvedSolids;
    private Double moreTotalDissolvedSolids;

    private Double lessTemperature;
    private Double moreTemperature;
}
