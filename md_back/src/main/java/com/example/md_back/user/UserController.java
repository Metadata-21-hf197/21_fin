package com.example.md_back.user;

import com.example.md_back.model.Approval;
import com.example.md_back.model.Term;
import com.example.md_back.model.User;
import com.example.md_back.model.Word;
import com.example.md_back.service.ApprovalService;
import com.example.md_back.service.TermService;
import com.example.md_back.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private WordService wordService;
    @Autowired
    private TermService termService;
    @Autowired
    private ApprovalService approvalService;

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
    public Map<String, Object> insert(@RequestParam("memberName") String memberName,
                                      @RequestParam("password") String password,
                                      @RequestParam("email") String email,
                                      @RequestParam("userRole") String userRole) {
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

    @GetMapping("/mypage")
    @ResponseBody
    public Map<String, Object> userWordList() throws Exception {
        System.out.println("wordList in");
        User user = new User();
        List<Word> wordList = new ArrayList<>();
        List<Term> termList = new ArrayList<>();
        List<Approval> approvalList = new ArrayList<>();

        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        if(!SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains("ADMIN")) {
            MyAuthentication authentication = (MyAuthentication) SecurityContextHolder.getContext().getAuthentication();
            user = (User) authentication.principal.getUser();

            System.out.println(user.getMemberName());
            int userId = user.getMemberId();
            wordList = wordService.getWordListByUserId(userId);
            termList = termService.getTermListByUserId(userId);
            approvalList = approvalService.getApprovalListByUserId(userId);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("wordList", wordList);
        map.put("termList", termList);
        return map;

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
