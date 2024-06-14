package com.example.startshopping.controller;

import com.example.startshopping.entity.Member;
import com.example.startshopping.service.EmailCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CheckRestController {


    private final EmailCheckService emailCheckService;


    @PostMapping(value="checkRest/userEmailCheck")
    public String userEmail(@RequestParam(value="userEmail") String userEmail){
        Optional<Member> resultEmail =  emailCheckService.emailcheck(userEmail);

        if (resultEmail == null){
            return "사용가능한 Email입니다.";
        }else{
            return "사용중인 Email입니다.";
        }
    }



}
