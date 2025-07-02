package io.github.jjh030325.daitdaserver.Controller;

import io.github.jjh030325.daitdaserver.DTO.LoginDTO;
import io.github.jjh030325.daitdaserver.DTO.RegisterDTO;
import io.github.jjh030325.daitdaserver.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // 회원가입 시 아이디 중복 여부
    @GetMapping("/check-id")
    public ResponseEntity<Boolean> checkId(@RequestParam("id") String id) {
        return ResponseEntity.ok(userService.UserIdExists(id));
    }

    // 회원가입 시 닉네임 중복 여부
    @GetMapping("/check-name")
    public ResponseEntity<Boolean> checkName(@RequestParam("name") String name) {
        return ResponseEntity.ok(userService.UserNameExists(name));
    }

    // 회원가입 시 닉네임, 아이디 중복 여부
    @GetMapping("/check-name-id")
    public ResponseEntity<Boolean> checkNameId(@RequestParam("id") String id, @RequestParam("name") String name) {
        return ResponseEntity.ok(userService.UserNameAndUserIdExists(id, name));
    }

    // /user/register POST
    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        try {
            userService.UserRegister(registerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); // 아이디 중복
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입 중 오류가 발생했습니다."); // 이외 예외
        }
    }

    // /user/login POST
    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        try {
            String result = userService.login(loginDTO);
            return ResponseEntity.ok(result); // 로그인 성공 메시지 또는 JWT 토큰
        } catch (IllegalArgumentException e) {
            // 로그인 실패: 잘못된 아이디 또는 비밀번호
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            // 서버 에러 등
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("로그인 처리 중 문제가 발생했습니다.");
        }
    }

    // /user/{id} GET
    // 회원 정보 확인
}
