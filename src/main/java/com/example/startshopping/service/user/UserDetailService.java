package com.example.startshopping.service.user;

import com.example.startshopping.dto.MemberDTO;
import com.example.startshopping.entity.Member;
import com.example.startshopping.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService {

    private final UserRepository userRepository;

    public MemberDTO detail(String userEmail) {
        Member user = userRepository.findByMemberEmail(userEmail);
        MemberDTO userdetail = MemberDTO.createMemberDTO(user);

      return userdetail;
    }

    //수정페이지 정보 띄워주기
    public MemberDTO userUpdateDeatil(String memberName) {
        Member user = userRepository.findByMemberName(memberName);
        MemberDTO userinfomation = MemberDTO.createMemberDTO(user);
        return userinfomation;
    }
}
