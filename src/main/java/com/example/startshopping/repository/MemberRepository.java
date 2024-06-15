    package com.example.startshopping.repository;

    import com.example.startshopping.entity.Member;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;

    import java.util.List;
    import java.util.Optional;

    public interface MemberRepository extends JpaRepository<Member,Long> {

            Optional<Member> findByMemberEmail(String memberEmail);// 이메일이 있을수도 있고 없을수도 있어서 optional을 사용


        @Query("SELECT m FROM Member m WHERE " +
                "(:searchWord IS NULL OR m.memberName LIKE %:searchWord% OR m.memberEmail LIKE %:searchWord%)")
            List<Member> findAllBySearchWord(@Param("searchWord") String searchWord);



    }
