package com.example.firstproject.httpdemo.controller;

import com.example.firstproject.httpdemo.service.httpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class httpController {
    @Autowired
    private httpService httpService;

    @GetMapping("/find/user/{id}")
    @ResponseBody
    public String getUserById(@PathVariable Long id) {
        String response = httpService.getUsers("https://koreanjson.com/users/" + id);
        log.info("가져온 유저 데이터 : " + response);
        return response;
    }

}
