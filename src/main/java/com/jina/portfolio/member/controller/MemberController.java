package com.jina.portfolio.member.controller;


import com.jina.portfolio.entity.Member;
import com.jina.portfolio.member.service.MemberService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/add")
    public ResponseEntity<Void> createMember(@RequestBody Member member) throws Exception {
        boolean flag = memberService.createMember(member);
        if(flag==false){
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public List<Member> retrieveMemberByMemberEntCd(@RequestParam String memberEntCd){
        return memberService.retrieveMemberBymemberEntCd(memberEntCd);
    }

    @PutMapping("/put")
    public Member putMember(@RequestBody Member member)throws Exception{
        return memberService.putMember(member);
    }

}
