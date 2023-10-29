package com.projectFilm.demo.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class UserController {

    @GetMapping("/")
    public String HomePage() {
        return "OK! this is Tomcat HomePage!";
    }
    public final UserRepository userRepository;

    @PostMapping("/regist")
    public String SetUser(@RequestBody User user) {
        try {
            userRepository.save(user);
            return "SAVE COMPLETE";
        } catch (Exception e) {
            e.printStackTrace();
            return "SAVE FAILED";
        }

    }

}
