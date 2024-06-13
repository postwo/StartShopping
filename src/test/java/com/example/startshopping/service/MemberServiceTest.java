package com.example.startshopping.service;

import com.example.startshopping.dto.MemberDTO;
import com.example.startshopping.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@Transactional //데이터가 저장되지 않게 하기위해 사용
class MemberServiceTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberService memberService;


    // 날짜 형식을 변환하는 메서드
    private Date convertStringToDate(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // 실제 애플리케이션에서는 적절한 예외 처리를 해야 함
        }
    }

    public Member creaetMember(){

        // 문자열을 Date 객체로 변환
        Date memberBirthDate = convertStringToDate("2024-01-12");


        MemberDTO dto = MemberDTO.builder()
                .memberAddr("경기도")
                .memberEmail("codo8188@nvas.com")
                .memberPw("111112312424")
                .memberAddrDetail("과명시")
                .memberBirth(memberBirthDate)
                .memberName("유진")
                .memberNum("10001")
                .memberPost("ㅁㄴㅇㄹㄴㅇㅁ")
                .memberPhone("010-123-124124")
                .build();

        Member member = Member.createMember(dto,passwordEncoder);
        return member;
    }




    @DisplayName("회원정보 저장")
    @Test
    void saveMember() {
        Member member = creaetMember();
        System.out.println(member);
        Member member1 = memberService.Memberinsert(member);
        System.out.println(member1);
    }


    @DisplayName("중복 회원 예외 발생 테스트")
    @Test
    void saveMember2() {
        Member member1 = creaetMember();
        Member member2 = creaetMember();
         memberService.Memberinsert(member1); //통과

        //중복
        Throwable e = Assertions.assertThrows(IllegalStateException.class, ()->{
            memberService.Memberinsert(member2);
        });

        //이미존재하는아이디입니다는 기대하는값이고 e.getMessage()는 본래 떠야할 메시지이다
        Assertions.assertEquals("이미 가입된 회원입니다.",e.getMessage());
    }
}