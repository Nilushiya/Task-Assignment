package Assingment.example.task.Dto;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String token;
    public AuthenticationResponse(String token){
        this.token = token;
    }

    public String getToken(){
        return  token;
    }
}
