package com.example.md_back.user;

import com.example.md_back.model.User;
import com.example.md_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public class UserController {
    @Autowired
    private UserRepository userRepository;

    //join form
    @GetMapping("/user/join")
    @ResponseBody
    public String userForm() {
        return "user join";
    }

    /**
     * insert user
     * react 사용에 따라 return 타입 변경 가능
     * @return
     */
    @PostMapping("/user/join")
    @ResponseBody
    public Map<String, Object> insert() {
        //User 객체 생성
        //값 setting
        //insert 동작
        //return result
        return null;
    }

    @PutMapping("/user/update")
    @ResponseBody
    public Map<String, Object> update(User user) {
        //User 객체 확인
        // 새 값 setting
        //update
        //return result
        return null;
    }

    /**
     * delete동작에서는 session으로 사용자 확인 후 동작 가능
     * @param user
     * @return
     */
    @DeleteMapping("/user/delete")
    @ResponseBody
    public Map<String, Object> delete(User user) {
        //사용자 id로 객체 제거
        // delete 동작
        //return result
        return null;
    }
}
