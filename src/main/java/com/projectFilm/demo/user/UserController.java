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
    public String HomePage() throws Exception{
        return "OK! this is HomePage!";
    }
    public final UserRepository userRepository;

    @PostMapping("/regist")
    public String SetUser(@RequestBody UserModel userModel) throws Exception{
        userRepository.save(userModel);
        return "SAVE COMPLETE";
    }

}
