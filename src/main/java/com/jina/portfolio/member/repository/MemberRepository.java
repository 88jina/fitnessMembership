package com.jina.portfolio.member.repository;

import com.jina.portfolio.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findMembersByMemberEntCd(String memberEntCd);


    List<Member> findMembersByMemberEndDate(Date now);
}
