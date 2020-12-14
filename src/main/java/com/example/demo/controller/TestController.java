package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import proto.Login;
import proto.MessageUserLogin;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping(value = "/t1", produces = "application/x-protobuf")
    public Login.User t1(@RequestBody Login.User user){
        String password = user.getPassword();
        String username = user.getUsername();
        return Login.User.newBuilder().setUsername(password).setUsername(username).build();
    }

    @GetMapping("/t2")
    public void t2(){
        System.out.println("--------------------");
    }


    @RequestMapping(value = "/t3", produces = "application/x-protobuf")
    public MessageUserLogin.MessageUserLoginResponse getPersonProto(@RequestBody MessageUserLogin.MessageUserLoginRequest request) {
        MessageUserLogin.MessageUserLoginResponse.Builder builder = MessageUserLogin.MessageUserLoginResponse.newBuilder();
        builder.setAccessToken("12384179");
        builder.setUserName(request.getUserName()+"_res");
        return builder.build();
    }
}
