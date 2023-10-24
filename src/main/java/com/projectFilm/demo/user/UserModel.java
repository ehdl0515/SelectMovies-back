package com.projectFilm.demo.user;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(length = 500, nullable = false)
    private String userId;

    private String userPw;

    @Builder
    public UserModel(String userId, String userPw) {
        this.userId = userId;
        this.userPw = userPw;
    }
}
