package gr.vasilakos.watermicroservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WaterQueueNotifyUsersDto {

    private List<String> emails;
    private WaterQueueDataDto waterData;
}
