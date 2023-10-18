package com.example.serviceUser.adopter.out;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TBL_ACCOUNT")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TABLE_INDEX")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "SNS_SYNC")
    private String snsSync;

    @Column(name = "SNS_SECRET")
    private String snsSecret;

    @Enumerated(EnumType.STRING)
    private Role role;
}


/*

create table TBL_ACCOUNT
(
    TABLE_INDEX  int auto_increment
        primary key,
    USERNAME     varchar(50)  not null,
    PASSWORD     varchar(150) null,
    NAME         varchar(50)  not null,
    PHONE_NUMBER varchar(50)  null,
    ADDRESS      varchar(150) null,
    SNS_SYNC     varchar(50)  null,
    SNS_SECRET   varchar(150) null,
    ROLE         varchar(50)  null,
    constraint TBL_ACCOUNT_uk
        unique (USERNAME)
);

create index ACCOUNT_USERNAME_SNS_SYNC_index
    on TBL_ACCOUNT (USERNAME, SNS_SYNC);


 */
