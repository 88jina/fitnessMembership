package com.jina.portfolio.member.service;

import com.jina.portfolio.entity.Member;
import com.jina.portfolio.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public boolean createMember(Member member) {
        try {
            memberRepository.save(member);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public Optional<Member> retrieveMember(Long memberCd) {
        return memberRepository.findById(memberCd);
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
