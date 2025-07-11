package io.github.jjh030325.daitdaserver.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/*
 * 상품 좋아요 내역을 저장하는 JPA 엔터티
 * 상품에 좋아요를 누르거나 좋아요 개수를 파악하는 데 사용됨.
 * */
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "item_id"})
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductLikedTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long like_id; // 내부 식별자

    @Column(nullable = false)
    private Long user_id; // 판매자 아이디

    @Column(nullable = false)
    private Long item_id; // 상품 아이디

    @Column(nullable = false)
    private LocalDateTime liked_at; // 사용자 생성 시각
}
