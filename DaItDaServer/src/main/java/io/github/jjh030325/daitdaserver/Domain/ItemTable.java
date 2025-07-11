package io.github.jjh030325.daitdaserver.Domain;

import io.github.jjh030325.daitdaserver.Enum.eType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/*
 * 상품 정보를 저장하는 JPA 엔터티
 * 상품 검색 등 상품 정보를 필요로 하는 서비스에 사용됨.
 * */
@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 내부 식별자

    @Column(nullable = false)
    private Long seller_id; // 판매자 아이디

    @Column(nullable = false, length = 30)
    private String name; // 상품 명

    @Column(nullable = false)
    private Long price; // 상품의 가격

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private eType type; // 상품의 타입. 10종류.

    @Column(nullable = false)
    private LocalDateTime created_at; // 사용자 생성 시각

    @Column(nullable = false)
    private LocalDateTime updated_at; // 최근 접속 시각

    @Column(nullable = false, columnDefinition = "TEXT")
    private String etc; // 기타 상품 정보
}
