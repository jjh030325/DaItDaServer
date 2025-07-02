package io.github.jjh030325.daitdaserver.Controller;

import io.github.jjh030325.daitdaserver.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
* 사용자 인증 및 회원관리와 관련된 HTTP 요청을 처리하는 REST 컨트롤러입니다.
* 회원가입, 로그인, 회원정보 조회/수정 등의 API 엔드포인트를 제공합니다.
* */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // /user/check-id GET
    // 회원가입 시 아이디 중복 여부

    // /user/register POST
    // 회원가입

    // /user/login POST
    // 로그인

    // /user/{id} GET
    // 회원 정보 확인
}
