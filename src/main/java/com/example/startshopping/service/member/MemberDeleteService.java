package com.example.startshopping.service.member;

import com.example.startshopping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDeleteService {

    private final MemberRepository memberRepository;

    public void Delete(String[] checkbox) {
//        memberRepository.
    }
}
