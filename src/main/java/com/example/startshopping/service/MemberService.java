package com.example.startshopping.service;

import com.example.startshopping.entity.Member;
import com.example.startshopping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;

    public Member Memberinsert(Member member)  {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }


    //가입한적이 있는지 확인
    private void validateDuplicateMember(Member member) {
        Optional<Member> findMember = memberRepository.findByMemberEmail(member.getMemberEmail());
        if(findMember.isPresent()) {
            System.out.println(findMember.get().getMemberEmail());
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
