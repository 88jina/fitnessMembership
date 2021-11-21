package com.jina.portfolio.member.service;

import com.jina.portfolio.entity.Member;
import com.jina.portfolio.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Slf4j
@Service
public class MemberService {
    static String regex = "^[0-9]+$";
    @Autowired
    private MemberRepository memberRepository;
    @PersistenceContext
    private EntityManager em;


    public boolean createMember(Member member) throws Exception {

        if (!"".equals(member.getMemberHp()) && member.getMemberHp() != null) {
            String hpTemp = member.getMemberHp();
            String memberHp = hpTemp.replaceAll("[^0-9]", "");
            String memEntCd = memberHp.substring(memberHp.length() - 4, memberHp.length());
            member.setMemberEntCd(memEntCd);
            memberRepository.save(member);
            return true;
        } else {
            return false;
        }


    }


    public List<Member> retrieveMemberBymemberEntCd(String memberEntCd) {

        String temp = "SELECT m FROM Member m WHERE m.memberEntCd = :memberEntCd";
        TypedQuery<Member> query = em.createQuery(temp, Member.class);
        query.setParameter("memberEntCd", memberEntCd);
        List<Member> members = query.getResultList();
        for (Member mem : members) {
            log.debug(mem.getMemberNm());
        }
        return members;
    }

    public Member putMember(Long memberCd, Member member) {
        final Optional<Member> fetchedMember = memberRepository.findById(memberCd);
        if (fetchedMember.isPresent()) {
            member.setMemberCd(memberCd);
            return memberRepository.save(member);
        } else {
            return null;
        }
    }

    public boolean deleteMember(Long memberCd) {
        final Optional<Member> fetchedMember = memberRepository.findById(memberCd);
        if (fetchedMember.isPresent()) {
            memberRepository.deleteById(memberCd);
            return true;
        } else {
            return false;
        }

    }
}
