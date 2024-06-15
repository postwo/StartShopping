package com.example.startshopping.service.member;

import com.example.startshopping.dto.MemberDTO;
import com.example.startshopping.entity.Member;
import com.example.startshopping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberListService {

    private final MemberRepository memberRepository;



    // 모든 회원 리스트 조회 및 검색 기능
    public Page<MemberDTO> getAllMembers(String searchWord, int page, int size) {

        Pageable pageable = PageRequest.of(page,size);

        Page<Member> members;
        if (searchWord != null && !searchWord.isEmpty()) {
            members = memberRepository.findAllBySearchWord("%" + searchWord + "%",pageable);
        } else {
            members = memberRepository.findAll(pageable);
        }
        return members.map(MemberDTO::createMemberDTO);
    }







}
