package edu.uady.authservice.service;

import edu.uady.authservice.dto.RequestDto;
import edu.uady.authservice.dto.TokenDto;
import edu.uady.authservice.dto.UserAuthDto;
import edu.uady.authservice.entity.UserAuth;
import edu.uady.authservice.repository.UserAuthRepository;
import edu.uady.authservice.security.JwtProvider;
import edu.uady.authservice.security.PasswordEncoderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserAuthService {
    @Autowired
    UserAuthRepository authRepository;

    @Autowired
    PasswordEncoderConfig passwordEncoderConfig;
    @Autowired
    JwtProvider jwtProvider;

    public UserAuth createUser(UserAuth userAuth){
        Optional<UserAuth> user = authRepository.findByUserName(userAuth.getUserName());
        if(user.isPresent()) {
            return null;
        }
        String password = passwordEncoderConfig.passwordEncoder().encode(userAuth.getPassword());
        userAuth.setPassword(password);
        return authRepository.save(userAuth);
    }

    public TokenDto login (UserAuthDto authDto){
        Optional<UserAuth> user = authRepository.findByUserName(authDto.getUserName());
        if(!user.isPresent()){
            return null;
        }
        if(passwordEncoderConfig.passwordEncoder().matches(authDto.getPassword(), user.get().getPassword())){
            return new TokenDto(jwtProvider.creteToken(user.get()));
        }
        return null;
    }

    public TokenDto validateToken(String token, RequestDto requestDto){
        if(!jwtProvider.validete(token,requestDto)){
            return null;
        }
        String userName = jwtProvider.getUserNameFromToken(token);
        if(!authRepository.findByUserName(userName).isPresent()){
            return null;
        }
        return new TokenDto(token);
    }

    public UserAuth findUserAut (String userName) throws Exception{
        return authRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + userName));
    }
}
