package com.example.md_back.controller;

import com.example.md_back.model.*;
import com.example.md_back.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @Autowired
    private CodeRepository codeRepository;

    @Autowired
    private DomainRepository domainRepository;

    @Autowired
    private TermRepository termRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WordRepository wordRepository;


    /** Repository.save 테스트
     *
     * @return
     */
    @RequestMapping("/test/repo")
    public String repo(){
        Code code = Code.builder()
                .engName("Kimchi")
                .korName("김치")
                .banWord(false)
                .domainName("KoreanFood")
                .build();
        Domain domain = Domain.builder()
                .engName("KoreanFood")
                .korName("한식")
                .shortName("K-food")
                .banWord(false)
                .type("String")
                .deleteStatus(false)
                .build();
        Term term = Term.builder()
                .engName("KimchiSoup")
                .korName("김치찌개")
                .banWord(false)
                .deleteStatus(false)
                .build();
        User user = User.builder()
                .memberName("gildong")
                .password("wkrwjs!@")
                .email("gildong@gmail.com")
                .quitDate(null)
                .quitStatus(false)
                .access("manager")
                .build();
        Word word = Word.builder()
                .engName("soup")
                .korName("찌개")
                .banWord(false)
                .deleteStatus(false)
                .build();

        System.out.println(code);
        codeRepository.save(code);

        System.out.println(domain);
        domainRepository.save(domain);

        System.out.println(term);
        termRepository.save(term);

        System.out.println(user);
        userRepository.save(user);

        System.out.println(word);
        wordRepository.save(word);

        return "repo Test";
    }


    /** Repository @AutoWired 테스트, find{MODEL} select 테스트
     *
     * @param testId
     */
    @RequestMapping("/test/showrepos/{testId}")
    public void showRepo(@PathVariable int testId){

        Code findCode = codeRepository.findById(testId).orElseThrow(()->{
                    return new IllegalArgumentException("코드 찾기 실패 : 코드를 찾을 수 없습니다.");
                }
        );
        User findUser = userRepository.findById(testId).orElseThrow(()->{
                    return new IllegalArgumentException("유저 찾기 실패 : 유저를 찾을 수 없습니다.");
                }
        );
        Word findWord = wordRepository.findById(testId).orElseThrow(()->{
                    return new IllegalArgumentException("단어 찾기 실패 : 단어를 찾을 수 없습니다.");
                }
        );
        Domain findDomain = domainRepository.findById(testId).orElseThrow(()->{
                    return new IllegalArgumentException("도메인 찾기 실패 : 도메인을 찾을 수 없습니다.");
                }
        );
        Term findTerm = termRepository.findById(testId).orElseThrow(()->{
                    return new IllegalArgumentException("용어 찾기 실패 : 용어를 찾을 수 없습니다.");
                }
        );

        System.out.println(findCode);
        System.out.println(findUser);
        System.out.println(findWord);
        System.out.println(findDomain);
        System.out.println(findTerm);
    }
}
