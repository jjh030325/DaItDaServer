package io.github.jjh030325.daitdaserver.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/*
 * 주문내역을 저장하는 JPA 엔터티
 * 특정 유저가 구매한 내역을 확인하는데 사용됨.
 * */
@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistoryTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 내부 식별자

    @Column(nullable = false)
    private Long buyer_id; // 구매자 아이디

    @Column(nullable = false)
    private Long item_id; // 상품 아이디

    @Column(nullable = false)
    private Integer quantity; // 구매 상품 개수

    @Column(nullable = false)
    private Long total_price; // 상품의 총 구매 가격

    @Column(nullable = false)
    private LocalDateTime purchased_at; // 사용자 생성 시각
}
