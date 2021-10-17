package com.example.md_back.user;

import com.example.md_back.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    public static final String LOGINFORM = "/user/loginform";
    public static final String JOINFORM = "/user/joinform";

    //login form
    @GetMapping("/user/loginform")
    @ResponseBody
    public String loginForm() {return LOGINFORM;}


    //join form
    @GetMapping("/user/join")
    public String joinForm(@ModelAttribute("user") User user) {
        return JOINFORM;
    }

    @PostMapping
    @ResponseBody
    public Map<String, Object> insert(
            @RequestParam("memberName") String memberName,
            @RequestParam("password") String password,
            @RequestParam("email") String email) { //userRole?
        //생성자로 테스트해보고 안되면 다 풀어 작성할예정
        User user = new User(memberName, password, email);
        //sequence 생성해야함
        user.setMemberId(userService.getUserNo());
        userService.insertUser(user);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", "success");
        map.put("memberName", user.getMemberName());

        System.out.println(map);
        return map;




    }


    /**
     * get updateForm
     *
     * @param @AuthenticationPrincipal PrincipalDetail 받아와야 함
     * @return user/updateForm
     */
    @GetMapping("/user/update")
    public String updateForm() {
        return "/user/updateForm";
    }

//    @PutMapping("/user/update")
//    public Map<String, Object> updateUser() {
//
//    }

    /**
     * get quitForm
     *
     * @return user/quitForm
     */
    @GetMapping("/user/quit")
    public String quitForm() {
        return "templates/user/quitForm";
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
    public Map<String, Object> insert(@RequestParam String memberName,
                                      @RequestParam String password,
                                      @RequestParam String email,
                                      @RequestParam String userRole) {
        // String rawPassword = user.getPassword();
        // String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        // user.setMemberName(encPassword);
        User user = new User();
        user.setMemberName(memberName);
        user.setPassword(password);
        user.setEmail(email);
        user.setUserRole(userRole);
        userService.insertUser(user);
        //return result

        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("result", "success");

        return returnMap;
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
//        User updateUser = userService.getUser(user.getMemberName());
        // updateUser Exceptions~

        // String rawPassword = user.getPassword();
        // String encPassword = bCryptPasswordEncoder.encode(rawPassword);
//        updateUser.setEmail(user.getEmail());

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
//        User updateUser = userService.getUser(user.getMemberName());
        // updateUser Exceptions~
//        updateUser.setQuitStatus(true);
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
