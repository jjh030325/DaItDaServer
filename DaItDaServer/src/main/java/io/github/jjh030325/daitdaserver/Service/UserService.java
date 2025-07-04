package io.github.jjh030325.daitdaserver.Service;

import io.github.jjh030325.daitdaserver.Config.CustomUserDetails;
import io.github.jjh030325.daitdaserver.Config.JwtUtil;
import io.github.jjh030325.daitdaserver.DTO.LoginDTO;
import io.github.jjh030325.daitdaserver.DTO.RegisterDTO;
import io.github.jjh030325.daitdaserver.DTO.UserInfoDTO;
import io.github.jjh030325.daitdaserver.Domain.UserTable;
import io.github.jjh030325.daitdaserver.Enum.eRole;
import io.github.jjh030325.daitdaserver.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

/*
* 사용자 관련 핵심 비즈니스 로직을 처리하는 서비스 클래스입니다.
* 회원가입, 로그인, 사용자 정보 수정 등 도메인 로직을 담당합니다.
* */
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // 회원가입
    public void UserRegister(RegisterDTO registerDTO) {
        if(UserNameAndUserIdExists(registerDTO.getName(), registerDTO.getUserId())) {
            // 중복
            throw new IllegalArgumentException("이미 존재하는 사용자 이름 또는 아이디입니다.");
        }else{
            UserTable userTable = new UserTable();
            userTable.setName(registerDTO.getName());
            userTable.setUserId(registerDTO.getUserId());
            userTable.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
            userTable.setCash(1000000L);
            userTable.setRole(eRole.USER);
            userTable.setCreated_at(LocalDateTime.now());
            userTable.setUpdated_at(LocalDateTime.now());
            userRepository.save(userTable);
            // 회원 생성 로직
        }
    }

    // 회원가입시 아이디 중복 체크
    public boolean UserIdExists(String userId) {
        return userRepository.existsByUserId(userId);
    }

    // 회원가입시 닉네임 중복 체크
    public boolean UserNameExists(String name) {
        return userRepository.existsByName(name);
    }

    // 회원가입시 중복 여부 전체 체크
    public boolean UserNameAndUserIdExists(String name, String userId) {
        return userRepository.existsByNameAndUserId(name, userId);
    }

    // 로그인
    public String login(LoginDTO loginDTO) {
        // 1. 사용자 조회
        UserTable user = userRepository.findByUserId(loginDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // 2. 비밀번호 비교
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }else
        {
            // 3. 로그인 성공 로직 (예: 토큰 발급, 세션 설정 등)
            return jwtUtil.generateToken(user.getId(), user.getRole().getKey());
        }
    }

    // 회원 정보 확인
    public Optional<UserInfoDTO> getUserInfoById(Long id) {
        return userRepository.FindUserInfoById(id);
    }
}
