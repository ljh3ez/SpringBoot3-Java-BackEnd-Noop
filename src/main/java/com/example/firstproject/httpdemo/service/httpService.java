package com.example.firstproject.httpdemo.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class httpService {
    public String getUsers(String address){

        HttpURLConnection conn = null;
        StringBuffer sb = null;
        String responseData = "";

        try {
            URL url = new URL(address);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br;

            int responseCode = conn.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream())); // 정상호출

            } else {

                br = new BufferedReader(new InputStreamReader(conn.getErrorStream())); // 에러발생

            }

            sb = new StringBuffer();

            while ((responseData = br.readLine()) != null) {

                sb.append(responseData);

            }

            br.close();

        } catch (Exception e){
            e.printStackTrace();
        }


        return sb.toString();
    }
}
