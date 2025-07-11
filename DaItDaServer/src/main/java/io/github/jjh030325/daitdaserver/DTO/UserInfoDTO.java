package io.github.jjh030325.daitdaserver.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserInfoDTO {
    private String name; // 사용자 명
    private Long cash; // 가상 캐쉬 잔액
    private String userId; // 로그인에 사용하는 고유 ID
    private LocalDateTime created_at; // 사용자 생성 시각
    private LocalDateTime updated_at; // 최근 접속 시각

    public UserInfoDTO(String name, Long cash, String userId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.cash = cash;
        this.userId = userId;
        this.created_at = createdAt;
        this.updated_at = updatedAt;
    }
}
