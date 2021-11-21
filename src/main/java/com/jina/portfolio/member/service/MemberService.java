package com.jina.portfolio.member.service;

import com.jina.portfolio.entity.Member;
import com.jina.portfolio.member.repository.MemberRepository;
import jdk.jfr.Description;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MemberService {
    static String regex = "^[0-9]+$";
    @Autowired
    private MemberRepository memberRepository;


    @Description("회원추가")
    public boolean createMember(Member member) throws Exception {
//TODO 하이픈(-) 제거하고 숫자만 남기려고 하는데 왜 안되노
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

    @Description("전화번호 뒷자리로 회원 조회")
    public List<Member> retrieveMemberByMemberEntCd(String memberEntCd) {
        return memberRepository.findMembersByMemberEntCd(memberEntCd);
    }

    @Description("회원정보수정")
    public Member putMember(Member member) {
        final Optional<Member> fetchedMember = memberRepository.findById(member.getMemberCd());
        if (fetchedMember.isPresent()) { //TODO 하이픈(-) 제거하고 숫자만 남기려고 하는데 왜 안되노
            String hpTemp = member.getMemberHp();
            String memberHp = hpTemp.replaceAll("[^0-9]", "");
            String memEntCd = memberHp.substring(memberHp.length() - 4, memberHp.length());
            member.setMemberEntCd(memEntCd);
            member.setMemberCd(member.getMemberCd());
            return memberRepository.save(member);
        } else {
            return null;
        }
    }

    @Description("회원정보삭제")
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
