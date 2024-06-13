package com.example.startshopping.controller;

import com.example.startshopping.dto.MemberDTO;
import com.example.startshopping.entity.Member;
import com.example.startshopping.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("register")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;


    @ModelAttribute
    public MemberDTO memberDTO(){
        return new MemberDTO();
    }


    //회원가입 페이지이동
    @GetMapping("userAgree")
    public String register(Model model){
        model.addAttribute("memberDto",new MemberDTO());// 빈객체를 생성해서 보낸다
        return "memberRegist/userForm";
    }

    //회원가입
    @PostMapping("")
    public String register(@Validated MemberDTO memberDTO, BindingResult result, Model model){

      Member member =  Member.createMember(memberDTO,passwordEncoder);

        memberService.Memberinsert(member);

        //오류가 있는 경우 오류 메시지가 출력되게 한다
        if (result.hasErrors()){
            return "memberRegist/userForm";
        }

        model.addAttribute("userName", member.getMemberName());
        model.addAttribute("userEmail", member.getMemberEmail());

        return "memberRegist/welcome";
    }

}
