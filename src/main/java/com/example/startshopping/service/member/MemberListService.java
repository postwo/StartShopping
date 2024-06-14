package com.example.startshopping.service.member;

import com.example.startshopping.dto.MemberDTO;
import com.example.startshopping.entity.Member;
import com.example.startshopping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberListService {

    private final MemberRepository memberRepository;

    public List<MemberDTO> memberlist(){

       List<Member> list = memberRepository.findAll();

        //MemberDTO 리스트로 변경
       return list.stream().
               map(MemberDTO::createMemberDTO).
               collect(Collectors.toList());
    }

}
