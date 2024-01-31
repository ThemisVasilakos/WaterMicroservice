package gr.vasilakos.watermicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "user-to-notify")
public class UserToNotify {
    @Id
    private String id;
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

