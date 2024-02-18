package gr.vasilakos.watermicroservice.controller;

import gr.vasilakos.watermicroservice.dto.WaterDataDto;
import gr.vasilakos.watermicroservice.model.WaterData;
import gr.vasilakos.watermicroservice.service.WaterDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/water")
@RequiredArgsConstructor
public class WaterDataController {

    private final WaterDataService waterDataService;

    @GetMapping("/search-all")
    public ResponseEntity<List<WaterDataDto>> getData(
            @RequestParam String location,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Instant start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Instant end
    ){

        return new ResponseEntity<>(waterDataService.findByParameter(location,start,end), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<WaterDataDto> getData(
            @RequestParam String location
    ){
        return new ResponseEntity<>(waterDataService.findLastEntry(location), HttpStatus.OK);
    }

}
