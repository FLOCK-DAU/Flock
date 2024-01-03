package com.Flock.domain.Member.Controller;

import com.Flock.domain.Member.Entity.Member;
import com.Flock.domain.Member.Entity.MemberDetail;
import com.Flock.domain.Member.Entity.Role;
import com.Flock.domain.Member.Repository.MemberRepository;
import com.Flock.domain.Response.SingleResponse;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {

    @Autowired
    MemberRepository memberRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping("/login")
    public String login(){
        return "/login";
    }


    /**
     * admin 계정 생성
     */
//    @GetMapping("/make-admin")
//    @ResponseBody
//    public ResponseEntity<?> addAdmin(){
//        Member member = Member.builder()
//                .userName("admin")
//                .password(encoder.encode("1234"))
//                .phoneNumber("01064228462")
//                .memberName("admin")
//                .mail("ewqeqwewqe")
//                .role(Role.ADMIN)
//                .build();
//
//        memberRepository.save(member);
//        return ResponseEntity.ok(member);
//
//    }


    @GetMapping("/test")
    @ResponseBody
    public ResponseEntity<?> test(@AuthenticationPrincipal MemberDetail memberDetail){
        String loginId = memberDetail.getUsername();

        return ResponseEntity.ok(loginId);
    }

}
