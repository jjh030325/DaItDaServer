package io.github.jjh030325.daitdaserver.Domain;

import io.github.jjh030325.daitdaserver.Enum.eRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/*
* 사용자 정보를 저장하는 JPA 엔터티
* 회원가입, 로그인, 권한 관리 등 사용자 관련 기능에 사용됨.
* */
@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 내부 식별자

    @Column(nullable = false, unique = true, length = 30)
    private String name; // 사용자 명

    @Column(nullable = false)
    private Long cash; // 가상 캐쉬 잔액

    @Column(nullable = false, unique = true, length = 30)
    private String userId; // 로그인에 사용하는 고유 ID

    @Column(nullable = false)
    private String password; // 암호화된 비밀번호

    @Column(nullable = false)
    private LocalDateTime created_at; // 사용자 생성 시각

    @Column(nullable = false)
    private LocalDateTime updated_at; // 최근 접속 시각

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private eRole role; // USER, ADMIN, MANAGER 권한 구분용

    // 결제가 가능하다면 결제
    public void decreaseBalance(Long amount) {
        if (this.cash < amount) {
            throw new RuntimeException("잔액 부족");
        }else{
            this.cash -= amount;
        }
    }
}
