package com.jina.portfolio.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jina.portfolio.entity.Member;
import com.jina.portfolio.member.controller.MemberController;
import com.jina.portfolio.member.service.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MemberService memberService;

    static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");


    public String setJSONRequest(Member member) throws Exception{
        ObjectMapper objectMapper= new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String memberJson = ow.writeValueAsString(member);
        return memberJson;
    }


    @Test
    public void addMemberTest() throws Exception{

        String memberNm = "박진아";
        String memberHp = "01031652102";
        Date memberSttDate = SDF.parse("2020-01-01");
        Date memberEndDate = SDF.parse("2021-01-01");


        Member member = new Member().builder()
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
    public void retrieveMemberTest() {

    }
}
