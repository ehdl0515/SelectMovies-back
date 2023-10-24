package com.projectFilm.demo.user;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @After
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void call_userId() {
        String userId = "testuser";
        String userPw = "1234qwer";

        userRepository.save(UserModel.builder()
                .userId(userId)
                .userPw(userPw)
                .build());

        List<UserModel> userModelList = userRepository.findAll();

        UserModel userModel = userModelList.get(0);
        assertThat(userModel.getUserId()).isEqualTo(userId);
        assertThat(userModel.getUserPw()).isEqualTo(userPw);
    }
}
