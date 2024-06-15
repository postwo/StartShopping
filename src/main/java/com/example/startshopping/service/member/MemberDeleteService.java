package com.example.startshopping.service.member;

import com.example.startshopping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDeleteService {

    private final MemberRepository memberRepository;

    //전체 삭제
    public void Delete(String [] memDels) {

        for (String id : memDels){
           memberRepository.deleteById(Long.valueOf(id));
        }
    }

    //단일 삭제
    public void del(String id) {
        memberRepository.deleteById(Long.valueOf(id));
    }
}
