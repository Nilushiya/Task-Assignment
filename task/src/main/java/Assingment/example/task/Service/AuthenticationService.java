package Assingment.example.task.Service;

import Assingment.example.task.Dto.AuthenticationResponse;
import Assingment.example.task.Dto.UserDto;
import Assingment.example.task.Entity.User;
import Assingment.example.task.Repository.UserRepo;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class AuthenticationService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepo userRepo, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    public String register(UserDto request){
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUser_name(request.getUser_name());
        user.setUser_type(request.getUser_type());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepo.save(user);
        String token = jwtService.generateToken(user, String.valueOf(user.getUser_type()),  String.valueOf(user.getUser_name()), user.getId());

        return token;
    }
    public AuthenticationResponse authenticate(UserDto request){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));

        User user = userRepo.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.generateToken(user, String.valueOf(auth.getAuthorities().stream().findAny().get()), user.getUser_name(), user.getId());

        return new AuthenticationResponse(token);

    }


    public boolean signinVerify(UserDto request) {
//        System.out.println("re  : "+request);
        String userEmail = request.getEmail();
//        System.out.println("userEmail" + userEmail);
        Optional<User> userOptional = userRepo.findByEmail(userEmail);
//        System.out.println("ddd :"+userOptional );
        if (userOptional.isPresent()) {
            User user = userOptional.get();
//            System.out.println("user :"+user);

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if(passwordEncoder.matches(request.getPassword(), user.getPassword())){
//                System.out.println("lll");

                return true;
            }
            else{
//                System.out.println("fff");

                return false;
            }

        }
        else{
            return false;
        }
    }}

