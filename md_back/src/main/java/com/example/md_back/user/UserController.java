package com.example.md_back.user;

import com.example.md_back.dto.LoginDTO;
import com.example.md_back.model.*;
import com.example.md_back.service.ApprovalService;
import com.example.md_back.service.DomainService;
import com.example.md_back.service.TermService;
import com.example.md_back.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @Autowired
    private DomainService domainService;

    @PostMapping
    @ResponseBody
    public Map<String, Object> insert(
            @RequestParam("memberName") String memberName,
            @RequestParam("password") String password,
            @RequestParam("email") String email) { //userRole?
        User user = new User(memberName, password, email);
        userService.insertUser(user);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", "success");
        map.put("memberName", user.getMemberName());

        return map;




    }



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
    public Map<String, Object> userWordList(
            @AuthenticationPrincipal LoginDTO principal) throws Exception {
        User user = new User();
        List<Word> wordList = new ArrayList<>();
        List<Term> termList = new ArrayList<>();
        List<Approval> approvalList = new ArrayList<>();
        List<Domain> domainList = new ArrayList();

        if(!SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains("ADMIN")) {
            MyAuthentication authentication = (MyAuthentication) SecurityContextHolder.getContext().getAuthentication();
            user = (User) authentication.principal.getUser();


            int userId = user.getMemberId();
            wordList = wordService.getWordListByUserId(userId);
            termList = termService.getTermListByUserId(userId);
            approvalList = approvalService.getApprovalsByCreateUserId(userId);
            domainList = domainService.getDomainListByUserid(userId);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("wordList", wordList);
        map.put("termList", termList);
        map.put("approvalList", approvalList);
        map.put("domainList", domainList);
        return map;

    }
}
