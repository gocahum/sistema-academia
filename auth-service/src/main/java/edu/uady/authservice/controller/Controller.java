package edu.uady.authservice.controller;

import edu.uady.authservice.dto.RequestDto;
import edu.uady.authservice.dto.TokenDto;
import edu.uady.authservice.dto.UserAuthDto;
import edu.uady.authservice.entity.UserAuth;
import edu.uady.authservice.service.UserAuthService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
@Log4j2
public class Controller {
    @Autowired
    private UserAuthService userAuthService;

    @PostMapping("/loging")
    public ResponseEntity<TokenDto> login(@RequestBody UserAuthDto authDto){
        TokenDto tokenDto = userAuthService.login(authDto);
        if(tokenDto == null){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(tokenDto, HttpStatus.OK);//ResponseEntity<> .ok(tokenDto);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token, @RequestBody RequestDto requestDto){
        TokenDto tokenDto = userAuthService.validateToken(token, requestDto);
        if(tokenDto == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserAuth user){
        UserAuth userAuth = userAuthService.createUser(user);
        if(userAuth == null){
            return new ResponseEntity<>("This user already exist", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userAuth);

    }

    @GetMapping("/${username}")
    public  ResponseEntity<?> findUSerByName(@PathVariable("username")String userName){
        try {
            return ResponseEntity.ok(userAuthService.findUserAut(userName));
        }catch(Exception ex){
            log.error("Usuario no encontrado");
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}
