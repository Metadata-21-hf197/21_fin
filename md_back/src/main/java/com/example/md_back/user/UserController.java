package com.example.md_back.user;

import com.example.md_back.model.AccessType;
import com.example.md_back.model.User;
import com.example.md_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    public static final String LOGINFORM = "user/loginform";

    //login form
    @GetMapping("/user/loginform")
    @ResponseBody
    public String loginForm() {return LOGINFORM;}

    //login func
    @GetMapping("/user/login")
    @ResponseBody
    public Map<String, Object> login(
            HttpServletRequest request,
            @RequestParam String memberName, @RequestParam String password) {
        //setting login command
        User user = new User();
        user.setMemberName(memberName);
        user.setPassword(password);

        //compare DB
        User checkUser = userService.checkUser(user);
        HttpSession userSession = null;
        Map<String, Object> map = new HashMap<String, Object>();
        if (checkUser == null) { //not correct
            map.put("errormsg", "No User");
        } else { //correct
            System.out.println("login Success");
            userSession = request.getSession();
            userSession.setAttribute("user", checkUser);


        }

        map.put("user", userSession);

        return map;

    }
    //join form
    @GetMapping("/user/join")
    public String joinForm() {
        return "user/joinForm";
    }

    /**
     * get updateForm
     *
     * @param @AuthenticationPrincipal PrincipalDetail 받아와야 함
     * @return user/updateForm
     */
    @GetMapping("/user/update")
    public String updateForm() {
        return "user/updateForm";
    }

    /**
     * get quitForm
     *
     * @return user/quitForm
     */
    @GetMapping("/user/quit")
    public String quitForm() {
        return "user/quitForm";
    }


    /**
     * insert user
     * react 사용에 따라 return 타입 변경 가능
     *
     * @return
     */
    @Transactional
    @PostMapping("/user/join")
    @ResponseBody
    public Map<String, Object> insert(User user) {
        // String rawPassword = user.getPassword();
        // String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        // user.setMemberName(encPassword);
        userService.insertUser(user);
        //return result
        return null;
    }

    /**
     * userUpdate
     *
     * @param user
     * @return
     */
    @Transactional
    @PutMapping("/user/update")
    @ResponseBody
    public Map<String, Object> update(User user) {
        User updateUser = userService.getUser(user.getMemberName());
        // updateUser Exceptions~

        // String rawPassword = user.getPassword();
        // String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        updateUser.setEmail(user.getEmail());

        // 세션 업데이트 부분
        // Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        // SecurityContextHolder.getContext().setAuthentication(authentication);
        return null;
    }

    /**
     * quit
     * 로그인에서 quitStatus 확인 절차 필요
     *
     * @param user
     * @return
     */
    @Transactional
    @PutMapping("/user/quit")
    @ResponseBody
    public Map<String, Object> quit(User user) {
        User updateUser = userService.getUser(user.getMemberName());
        // updateUser Exceptions~
        updateUser.setQuitStatus(true);
        // updateUser.setQuitDate();
        return null;
    }


    /**
     * delete동작에서는 session으로 사용자 확인 후 동작 가능
     *
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
