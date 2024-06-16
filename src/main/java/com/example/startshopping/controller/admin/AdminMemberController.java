package com.example.startshopping.controller.admin;

import com.example.startshopping.dto.MemberDTO;
import com.example.startshopping.service.member.MemberDeleteService;
import com.example.startshopping.service.member.MemberDetailServcie;
import com.example.startshopping.service.member.MemberListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("admin")
public class AdminMemberController {

    private final MemberListService memberListService;
    private final MemberDeleteService memberDeleteService;
    private final MemberDetailServcie memberDetailServcie;


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

    //전체 삭제
    @PostMapping("membersDelete")
    public String membersdel(@RequestParam("memDels") String[] memDels){
        memberDeleteService.Delete(memDels);
        return "redirect:memberList";
    }


    //단일 삭제
    @GetMapping("memberDel")
    public String del(
            @RequestParam("id") String id
    ){
        memberDeleteService.del(id);
        return "redirect:memberList";
    }

    @GetMapping("memberDetail")
    public String memDetail(@RequestParam("memberName") String memberName,Model model){
        List<MemberDTO> memdetail = memberDetailServcie.detail(memberName);
        System.out.println("so==="+memdetail);
        model.addAttribute("dto",memdetail);
        return "member/AuthMemberDetail";
    }



}
