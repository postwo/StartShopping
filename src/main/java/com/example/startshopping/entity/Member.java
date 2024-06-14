package com.example.startshopping.entity;


import com.example.startshopping.common.entity.BaseEntity;
import com.example.startshopping.constant.Role;
import com.example.startshopping.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Member extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_Id")
    private Long id;
    @Column(unique = true) //등록할때 중복이 안되도록하기 위해 사용
    private String memberEmail; //아이디로 사용
    private String memberPw;
    private String memberName;
    private String memberAddr;
    private String memberAddrDetail;
    private String memberPost;
    private String memberPhone;
    private Date memberBirth;


    @Enumerated(EnumType.STRING) //ORDINAL숫자를 다룬다는거다
    private Role role;



    // 프론트에서 정보를 받아서 데이터베이스 저장해주기 위해 사용
    public static Member createMember(MemberDTO memberDTO, PasswordEncoder passwordEncoder){

        Member member = Member.builder()
                .id(memberDTO.getId())
                .memberAddr(memberDTO.getMemberAddr())
                .memberEmail(memberDTO.getMemberEmail())
                .role(Role.USER)
                .memberPw(passwordEncoder.encode(memberDTO.getMemberPw()))
                .memberAddrDetail(memberDTO.getMemberAddrDetail())
                .memberBirth(memberDTO.getMemberBirth())
                .memberName(memberDTO.getMemberName())
                .memberPost(memberDTO.getMemberPost())
                .memberPhone(memberDTO.getMemberPhone())
                .build();

        return member;
    }

}
