package io.github.jjh030325.daitdaserver.Service;

import io.github.jjh030325.daitdaserver.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
* 사용자 관련 핵심 비즈니스 로직을 처리하는 서비스 클래스입니다.
* 회원가입, 로그인, 사용자 정보 수정 등 도메인 로직을 담당합니다.
* */
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원가입
    public void UserRegister(String name, String userId, String password) {
        if(UserNameAndUserIdExists(name, userId)) {
            // 중복
        }else{
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

    // 회원 정보 확인
}
