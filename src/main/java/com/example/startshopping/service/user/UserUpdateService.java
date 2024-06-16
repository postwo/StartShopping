package com.example.startshopping.service.user;

import com.example.startshopping.dto.MemberDTO;
import com.example.startshopping.entity.Member;
import com.example.startshopping.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUpdateService {

    private final UserRepository userRepository;


    public MemberDTO update(MemberDTO memberDto, String userEmail) {

      Member member =  userRepository.findByMemberEmail(userEmail);
        System.out.println("member=="+member);
        if (member != null) {
            member.setMemberName(memberDto.getMemberName());
            member.setMemberEmail(memberDto.getMemberEmail());
            member.setMemberAddr(memberDto.getMemberAddr());
            member.setMemberPost(memberDto.getMemberPost());
            member.setMemberAddrDetail(memberDto.getMemberAddrDetail());
            member.setMemberPhone(memberDto.getMemberPhone());

            Member sow = userRepository.save(member); // 변경된 엔티티 저장
            return MemberDTO.createMemberDTO(sow);

        } else {
            // member가 null인 경우 처리 로직
            // 나중에 추가할 로직
            return null;
        }


    }
}
