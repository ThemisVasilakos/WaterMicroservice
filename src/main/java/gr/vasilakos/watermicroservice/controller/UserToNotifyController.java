package gr.vasilakos.watermicroservice.controller;

import gr.vasilakos.watermicroservice.dto.UserToNotifyDto;
import gr.vasilakos.watermicroservice.service.UserToNotifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/water")
@RequiredArgsConstructor
public class UserToNotifyController {

    private final UserToNotifyService userToNotifyService;

    @PostMapping("/notify")
    public ResponseEntity<UserToNotifyDto> userSubToWaterData(@RequestBody UserToNotifyDto userToNotifyDto){
        return new ResponseEntity<>(userToNotifyService.userSubToWaterData(userToNotifyDto), HttpStatus.OK);
    }

    @DeleteMapping ("/notify")
    public void userSubToWaterData(@RequestParam String email){
        userToNotifyService.deleteUser(email);
    }
}
