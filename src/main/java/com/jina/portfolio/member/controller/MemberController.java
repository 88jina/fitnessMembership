package com.jina.portfolio.member.controller;


import com.jina.portfolio.entity.Member;
import com.jina.portfolio.member.service.MemberService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/add")
    public ResponseEntity<Void> createMember(@RequestBody Member member) throws Exception {
        boolean flag = memberService.createMember(member);
        if (!flag) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public List<Member> retrieveMemberByMemberEntCd(@RequestParam String memberEntCd) {
        return memberService.retrieveMemberByMemberEntCd(memberEntCd);
    }

    @PutMapping("/put")
    public Member putMember(@RequestBody Member member) throws Exception {
        return memberService.putMember(member);
    }


    @Scheduled(cron = "0 0 05 * * ?")
    public void deleteMember() throws ParseException {
        Date now = new Date();
        memberService.deleteMember(now);
    }

    @GetMapping("/alert-exp")
    public String alertExpMember(){
        return "일주일 후 회원권이 만료되는 회원이 있습니다";
    }
}
