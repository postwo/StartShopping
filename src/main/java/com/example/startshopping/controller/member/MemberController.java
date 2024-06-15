package com.example.startshopping.controller.member;

import com.example.startshopping.dto.MemberDTO;
import com.example.startshopping.entity.Member;
import com.example.startshopping.service.member.MemberDeleteService;
import com.example.startshopping.service.member.MemberListService;
import com.example.startshopping.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("register")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final MemberListService memberListService;
    private final MemberDeleteService memberDeleteService;



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


    //관리자 페이지에서 보여줄거
    @GetMapping("memberList")
    public String list(@RequestParam(value = "searchWord",required = false) String searchWord
                        ,@RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "5") int size,
                       Model model){

        //회원정보를 담아 memberList.html에 보낸다
        Page<MemberDTO> members = memberListService.getAllMembers(searchWord,page,size);

        model.addAttribute("dtos", members.getContent());//현재 페이지목록
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", members.getTotalPages());

        return "member/memberList";
    }



    @PostMapping("membersDelete")
    public void del(String[] checkbox){
    memberDeleteService.Delete(checkbox);
    }

}
