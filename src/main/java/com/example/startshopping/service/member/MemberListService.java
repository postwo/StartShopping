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


    // 모든 회원 리스트 조회 및 검색 기능
    public List<MemberDTO> getAllMembers(String searchWord) {
        List<Member> members;
        if (searchWord != null && !searchWord.isEmpty()) {
            members = memberRepository.findAllBySearchWord("%" + searchWord + "%");
        } else {
            members = memberRepository.findAll();
        }
        return members.stream()
                .map(MemberDTO::createMemberDTO)
                .collect(Collectors.toList());
    }







}
