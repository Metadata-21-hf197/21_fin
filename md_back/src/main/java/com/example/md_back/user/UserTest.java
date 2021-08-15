package com.example.md_back.user;

import com.example.md_back.model.User;
import com.example.md_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/test")
    public void userTest(){
        User user = User.builder()
                .memberName("asdf")
                .password("1234")
                .email("yyy@gmail.com")
                .quitDate(null)
                .quitStatus(false)
                .access("admin")
                .build();

        System.out.println(user);
        userRepository.save(user);


    }
}
