package com.example.startshopping.service;

import com.example.startshopping.entity.Member;
import com.example.startshopping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailCheckService {

    private final MemberRepository memberRepository;

    public Optional<Member> emailcheck(String userEmail) {
        Optional<Member> resultEmail = memberRepository.findByMemberEmail(userEmail);
        return resultEmail;
    }
}
