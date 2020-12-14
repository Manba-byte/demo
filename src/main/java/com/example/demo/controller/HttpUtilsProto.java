package com.example.demo.controller;

import com.google.protobuf.GeneratedMessageV3;
import com.googlecode.protobuf.format.JsonFormat;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClients;
import proto.Login;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;

/**
 * @Author Thomas
 * @create 2020/12/10
 */
public class HttpUtilsProto {
    public static HttpResponse doPost(HttpPost post, GeneratedMessageV3 message) throws IOException {
        HttpClient httpclient = HttpClients.createDefault();
        String requestUrl = post.getURI().toString();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(message.toByteArray());
        InputStreamEntity inputStreamEntity = new InputStreamEntity(inputStream);
        post.setEntity(inputStreamEntity);

        post.addHeader("Content-Type", "application/x-protobuf");
        for (Header header : post.getAllHeaders()) {
            System.out.println(header.getName() + ":" + header.getValue());
        }

        StringBuilder sb = new StringBuilder();
        sb.append("curl -XPOST ");
        for (Header header : post.getAllHeaders()) {
            sb.append(" -H \"").append(header.getName()).append(":").append(header.getValue()).append("\"");
        }

        String jsonBody = JsonFormat.printToString(message);
        jsonBody = jsonBody.replace("\"", "\\\"");
        sb.append(" -d \"").append(jsonBody).append("\"");
        sb.append(" ").append(requestUrl);

        System.out.println(sb.toString());
        return httpclient.execute(post);
    }

    public static void main(String[] args) {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/test/t1", "", null);
            HttpPost request = new HttpPost(uri);
            Login.User.Builder builder = Login.User.newBuilder();
            builder.setPassword("xxx");
            builder.setUsername("aaaa");

            HttpResponse response = HttpUtilsProto.doPost(request, builder.build());
            /*Login.User user = Login.User.parseFrom(response.getEntity().getContent());
            System.out.println(user.toString());*/
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
