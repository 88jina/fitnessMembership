package com.jina.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_cd")
    private Long memberCd;


    @NotNull
    @Column(name = "member_nm")
    private String memberNm;


    @NotNull
    @Column(name = "member_hp")
    private String memberHp;

    @Column(name = "member_ent_cd")
    private String memberEntCd;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "member_stt_date")
    private Date memberSttDate;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "member_end_date")
    private Date memberEndDate;


}
