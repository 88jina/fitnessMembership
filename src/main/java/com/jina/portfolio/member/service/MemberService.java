package com.jina.portfolio.member.service;

import com.jina.portfolio.entity.Member;
import com.jina.portfolio.member.repository.MemberRepository;
import jdk.jfr.Description;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MemberService {
    static String regex = "^[0-9]+$";


    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Description("회원추가")
    public boolean createMember(Member member) throws Exception {
//TODO 하이픈(-) 제거하고 숫자만 남기려고 하는데 왜 안되노
        if (!"".equals(member.getMemberHp()) && member.getMemberHp() != null) {
            String hpTemp = member.getMemberHp();
            String memberHp = hpTemp.replaceAll(regex, "");
            String memEntCd = memberHp.substring(memberHp.length() - 4);
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
            String memEntCd = memberHp.substring(memberHp.length() - 4);
            member.setMemberEntCd(memEntCd);
            member.setMemberCd(member.getMemberCd());
            return memberRepository.save(member);
        } else {
            return null;
        }
    }

    @Description("회원정보삭제(회원권 만료후 1년이 지나면 자동으로 삭제되도록 함)")
    public void deleteMember(Date now) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowStr = simpleDateFormat.format(now);
        now = simpleDateFormat.parse(nowStr);
        log.debug("===================scheduling Start");
        List <Member> expMemberList = memberRepository.findMembersByMemberEndDate(now);
        expMemberList.forEach(member -> memberRepository.deleteById(member.getMemberCd()));

        log.debug("===================scheduling End");
    }

}
