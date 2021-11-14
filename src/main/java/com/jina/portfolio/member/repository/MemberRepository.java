package com.jina.portfolio.member.repository;

import com.jina.portfolio.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {


}
