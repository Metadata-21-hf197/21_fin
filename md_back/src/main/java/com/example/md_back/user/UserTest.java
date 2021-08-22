package com.example.md_back.user;

import com.example.md_back.model.AccessType;
import com.example.md_back.model.User;
import com.example.md_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserTest {

//    @Autowired
//    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/user/login/{memberName}")
    @ResponseBody
    public String getUserTest(@PathVariable String memberName) {
        User user = userService.getUser(memberName);

        return user.getMemberName() + " / " + user.getPassword();

    }

    @GetMapping("/user/test")
    public void userTest(){
        User user = User.builder()
                .memberName("asdf")
                .password("1234")
                .email("yyy@gmail.com")
                .quitDate(null)
                .quitStatus(false)
                .access(AccessType.ADMIN)
                .build();

        System.out.println(user);
//        userRepository.save(user);

    }
}
