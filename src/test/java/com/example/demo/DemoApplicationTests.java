package com.example.demo;

import com.example.demo.controller.HttpUtilsProto;
import com.sun.deploy.net.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import proto.Login;
import proto.MessageUserLogin;

import java.net.URI;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void aa() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/test/t1", "", null);
            HttpPost request = new HttpPost(uri);
            Login.User.Builder builder = Login.User.newBuilder();
            builder.setPassword("xxx");
            builder.setUsername("aaaa");

            HttpResponse response = HttpUtilsProto.doPost(request, builder.build());
            Login.User user = Login.User.parseFrom(response.getEntity().getContent());
            System.out.println(user.toString());
        } catch (Exception e) {

        }
    }

    @Test
    public void t3() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/test/t3", "", null);
            HttpPost request = new HttpPost(uri);
            MessageUserLogin.MessageUserLoginRequest.Builder builder = MessageUserLogin.MessageUserLoginRequest.newBuilder();
            builder.setUserName("tom");
            builder.setPassWord("123456");
            HttpResponse response = HttpUtilsProto.doPost(request, builder.build());
            MessageUserLogin.MessageUserLoginResponse messageUserLoginResponse = MessageUserLogin.MessageUserLoginResponse.parseFrom(response.getEntity().getContent());
            System.err.println(messageUserLoginResponse.getAccessToken());
        } catch (Exception e) {

        }
    }

}
