package io.github.jjh030325.daitdaserver.Repository;

import io.github.jjh030325.daitdaserver.Domain.OrderHistoryTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/*
* OrderHistoryTable 엔티티에 대한 데이터베이스 접근 인터페이스입니다.
* Spring Data JPA를 활용하여 기본 CRUD 및 사용자 정의 쿼리를 처리합니다.
* */
@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistoryTable, Long> {
    @Query("SELECT o FROM OrderHistoryTable o WHERE o.buyer_id = :id ORDER BY o.purchased_at DESC")
    Page<OrderHistoryTable> OrderHistorySearchWithId(@Param("id") Long id, Pageable pageable);
}
