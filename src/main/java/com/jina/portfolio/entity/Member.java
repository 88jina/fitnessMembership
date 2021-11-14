package com.jina.portfolio.entity;

import lombok.*;

import javax.persistence.*;

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
    @Column(name = "member_nm")
    private String memberNm;
    @Column(name = "member_hp")
    private String memberHp;
    @Column(name = "member_stt_date")
    private String memberSttDate;
    @Column(name = "member_end_date")
    private String memberEndDate;


}
