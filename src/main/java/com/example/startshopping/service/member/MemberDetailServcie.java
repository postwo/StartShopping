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
public class MemberDetailServcie {

    private final MemberRepository memberRepository;

    public List<MemberDTO> detail(String memberName) {

        List<Member> memdetail =  memberRepository.findByMemberName(memberName);


        return memdetail.stream().map(MemberDTO::createMemberDTO)
                .collect(Collectors.toList());
    }
}
