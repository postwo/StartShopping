package com.example.startshopping.controller.user;

import com.example.startshopping.dto.MemberDTO;
import com.example.startshopping.service.user.UserDetailService;
import com.example.startshopping.service.user.UserUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserDetailService userDetailService;
    private final UserUpdateService userUpdateService;

    @GetMapping("userDetail")
    public String userdetail(Model model, Principal principal
    ){
        String userEmail = principal.getName(); //email정보를가지고온다
        MemberDTO user= userDetailService.detail(userEmail);
        model.addAttribute("dto",user);
        return "user/userDetail";
    }


    @GetMapping("userUpdate/{memberName}")
    public String userUpdate(@PathVariable("memberName") String memberName, Model model){
        MemberDTO user= userDetailService.userUpdateDeatil(memberName);
        model.addAttribute("memberDto",user);
        return "user/userUpdate";
    }


    @PostMapping("memberModify")
    public String userUpdate(@Valid @ModelAttribute("memberDto") MemberDTO memberDto, BindingResult result
                             ,Principal principal,Model model){

        if (result.hasErrors()) { //서비스에서 MemberDTO타입으로 반환을 안해줘서 동작을 안했던거임
            return "user/userUpdate";
        }

        String userEmail = principal.getName();//이메일을가지고온다

        System.out.println(userEmail);
        userUpdateService.update(memberDto,userEmail);


        return "redirect:userDetail?memberEmail=" + memberDto.getMemberEmail();
    }

}
