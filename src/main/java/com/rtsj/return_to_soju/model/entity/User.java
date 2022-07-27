package com.rtsj.return_to_soju.model.entity;

import com.rtsj.return_to_soju.model.enums.Role;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Table(name = "RTUser")
public class User extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String kakaoName;

    private String password;

    private String phone;

    private String birthday;

    private Character sex;

    @Column(name = "email_cloud")
    private String cloudEmail;

    @Enumerated(EnumType.STRING)
    private Role role; // ROLE_USER, ROLE_ADMIN

    @Column(name = "email_calendar")
    private String calendarEmail;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "user")
    private List<Calender> calenderList = new ArrayList<>();

    public User(String name, String kakaoName, String password) {
        this.name = name;
        this.kakaoName = kakaoName;
        this.password = password;
    }
}