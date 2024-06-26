package com.example.startshopping.controller.member;

import com.example.startshopping.dto.MemberDTO;
import com.example.startshopping.entity.Member;
import com.example.startshopping.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("register")
@RequiredArgsConstructor
public class MemberRegisterController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;




    //자동으로 컨틀롤러에 model을 안넣어도 들어가게 해준다
    @ModelAttribute("memberDto")
    public MemberDTO memberDTO(){
        return new MemberDTO();
    }


    //회원가입 페이지이동
    @GetMapping("userAgree")
    public String register(){
        return "memberRegister/userForm";
    }


    //회원가입
    //@ModelAttribute("memberDto") MemberDTO memberDTO이렇게 해야 데이터가 바인딩된다
    @PostMapping("userRegist")
    public String register(@Valid @ModelAttribute("memberDto") MemberDTO memberDTO, BindingResult result, Model model){


        //오류가 있는 경우 오류 메시지가 출력되게 한다
        if (result.hasErrors()){
            return "memberRegister/userForm";
        }

        //비밀번호
        if (!memberDTO.isMemberPwEqualsMemberPwCon()){
            result.rejectValue("memberPwCon","memberDTO.memberPwCon","비밀번호 확인이 틀렸습니다.");
            return "memberRegister/userForm";
        }

        try{
            System.out.println("데이터 저장중");
            Member member =  Member.createMember(memberDTO,passwordEncoder);
            memberService.Memberinsert(member);

            model.addAttribute("userName", member.getMemberName());
            model.addAttribute("userEmail", member.getMemberEmail());
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "memberRegister/userForm";
        }

        return "memberRegister/welcome";
    }







}
