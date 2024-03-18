package com.example.oauth2.adopter.out.persistence;

import com.example.oauth2.domain.Role;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TBL_ACCOUNT")
class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TABLE_INDEX")
    private Long id;

    @Column(name = "USER_ID")
    private String userId;

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

    @Column(name = "REG_DATE")
    private String regDate;

    @Column(name = "LAST_LOGIN_TIME")
    private LocalDateTime lastLoginTime;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public AccountEntity(Long id, String userId, String password, String name, String phoneNumber,
        String address, String snsSync, String snsSecret, String regDate,
        LocalDateTime lastLoginTime,
        Role role) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.snsSync = snsSync;
        this.snsSecret = snsSecret;
        this.regDate = regDate;
        this.lastLoginTime = lastLoginTime;
        this.role = role;
    }
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
