package com.example.startshopping.repository;

import com.example.startshopping.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Member,Integer> {


    Member findByMemberEmail(String userEmail);

    Member findByMemberName(String memberName);
}
