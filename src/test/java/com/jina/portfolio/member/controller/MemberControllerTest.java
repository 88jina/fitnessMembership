package com.jina.portfolio.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jina.portfolio.entity.Member;
import com.jina.portfolio.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MemberService memberService;

    static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    public String setJSONRequest(Member member) throws Exception{
        ObjectMapper objectMapper= new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(member);
    }

    @Test
    void createMember() throws Exception{
        String memberNm = "박진아";
        String memberHp = "01031652102";
        Date memberSttDate = SDF.parse("2020-01-01");
        Date memberEndDate = SDF.parse("2021-01-01");


        Member member = Member.builder()
                .memberNm(memberNm)
                .memberHp(memberHp)
                .memberSttDate(memberSttDate)
                .memberEndDate(memberEndDate).build();

        String memberJson = this.setJSONRequest(member);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/member/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(memberJson);
        mockMvc.perform(requestBuilder).andReturn().getResponse();
    }

    @Test
    void retrieveMemberByMemberEntCd() throws Exception{
    }

    @Test
    void putMember() throws Exception{
    }

    @Test
    void deleteMember() throws Exception{
    }
}