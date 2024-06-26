package Assingment.example.task.Controller;

import Assingment.example.task.Dto.AuthenticationResponse;
import Assingment.example.task.Dto.UserDto;
import Assingment.example.task.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private  final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public String register(@RequestBody UserDto request){
        return authenticationService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody UserDto request

    ){
        System.out.println("rrr   : "+request);
        if(!authenticationService.signinVerify(request)){
            System.out.println("faild");
            System.out.println(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        else{
            System.out.println("okeeee : "+ResponseEntity.ok(authenticationService.authenticate(request)));
            return ResponseEntity.ok(authenticationService.authenticate(request));
        }
    }
}


