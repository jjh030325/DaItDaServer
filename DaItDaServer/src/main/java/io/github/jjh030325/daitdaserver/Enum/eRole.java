package io.github.jjh030325.daitdaserver.Enum;

import lombok.Getter;

/*
* 사용자의 권한(Role)을 정의하는 열거형 Enum 입니다.
* Spring Security 의 권한 체크에 사용되는 키와,
* UI 또는 관리자 페이지에 출력할 한글 설명을 함께 제공합니다.
* */
@Getter
public enum eRole {
    USER("ROLE_USER", "일반 사용자"),
    ADMIN("ROLE_ADMIN", "관리자"),
    MANAGER("ROLE_MANAGER", "매니저");

    private final String key;
    private final String label;

    eRole(String key, String label) {
        this.key = key;
        this.label = label;
    }
}
