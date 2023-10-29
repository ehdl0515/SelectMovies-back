package com.projectFilm.demo.user;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
class UserPK implements Serializable {
    private int userNo;
    private String userId;
}



@NoArgsConstructor
@Entity
@Getter
@Table(name = "User")
@IdClass(UserPK.class)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserNo")
    private int userNo;

    @Id
    @Column(name = "UserId", length = 500)
    private String userId;

    @Column(name = "UserPw")
    private String userPw;

    @Builder
    public User(int userNo, String userId, String userPw) {
        this.userNo = userNo;
        this.userId = userId;
        this.userPw = userPw;
    }
}
