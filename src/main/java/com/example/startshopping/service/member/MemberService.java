package com.example.startshopping.service.member;

import com.example.startshopping.entity.Member;
import com.example.startshopping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService implements UserDetailsService {


    private final MemberRepository memberRepository;

    //회원가입
    public Member Memberinsert(Member member)  {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }


    //가입한적이 있는지 확인(중복검사)
    private void validateDuplicateMember(Member member) {
        Optional<Member> findMember = memberRepository.findByMemberEmail(member.getMemberEmail());
        if(findMember.isPresent()) {
            System.out.println(findMember.get().getMemberEmail());
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    //로그인
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberEmail(email).orElseThrow(()->
                 new UsernameNotFoundException("해당 사용자가 없습니다"+email));

        log.info("======로그인 사용자"+member);

        return User.builder()
                .username(member.getMemberEmail())
                .password(member.getMemberPw())
                .roles(member.getRole().toString())
                .build();
    }


}
