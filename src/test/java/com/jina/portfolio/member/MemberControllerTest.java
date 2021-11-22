package com.jina.portfolio.member;

import com.jina.portfolio.entity.Member;
import com.jina.portfolio.member.controller.MemberController;
import com.jina.portfolio.member.service.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration()
public class MemberControllerTest {

    private MockMvc mockMvc;

    @Mock
    MemberService memberService;

    @InjectMocks
    public MemberController memberController;


    @Before
    public void createController() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    public void createMemberTest() throws Exception {


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String memberNm = "박진아";
        String memberHp = "01031652102";
        Date memberSttDate = sdf.parse("2020-01-01");
        Date memberEndDate = sdf.parse("2021-01-01");


        Member member = new Member().builder().memberNm(memberNm).memberHp(memberHp).memberSttDate(memberSttDate).memberEndDate(memberEndDate).build();



        when(memberService.createMember(member)).thenReturn(true);

        RequestBuilder requestBuilder= MockMvcRequestBuilders
                .post("/member/create")
                .contentType(MediaType.APPLICATION_JSON)
                .param("member",String.valueOf(member));

        this.mockMvc.perform(requestBuilder);

//        verify(memberService).createMember(member);
    }


}
