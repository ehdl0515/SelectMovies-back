package com.projectFilm.demo.user;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void UserTest() {

        userRepository.save(User.builder()
                        .userNo(1)
                        .userId("test_user")
                        .userPw("1234qwer")
                        .build());

        List<User> userList = userRepository.findAll();

        User user = userList.get(0);
        System.out.println(user.getUserId());
        System.out.println(user.getUserNo());
        System.out.println(user.getUserPw());
        assertThat(user.getUserNo()).isEqualTo(1);
        assertThat(user.getUserId()).isEqualTo("test_user");
        assertThat(user.getUserPw()).isEqualTo("1234qwer");
    }
}
