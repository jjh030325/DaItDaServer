package io.github.jjh030325.daitdaserver.Repository;

import io.github.jjh030325.daitdaserver.Domain.ItemTable;
import io.github.jjh030325.daitdaserver.Enum.eType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
* ItemTable 엔티티에 대한 데이터베이스 접근 인터페이스입니다.
* Spring Data JPA를 활용하여 기본 CRUD 및 사용자 정의 쿼리를 처리합니다.
* */
@Repository
public interface ItemRepository extends JpaRepository<ItemTable, Long> {
    Optional<ItemTable> findByPrice(Long price);

    // 최신순 상품 이름 검색
    @Query("SELECT i FROM ItemTable i WHERE i.name LIKE CONCAT('%', :name, '%') ORDER BY i.updated_at DESC")
    Page<ItemTable> ItemNameSearch(@Param("name") String name, Pageable pageable);

    // 최신순 상품 이름 + 타입 검색
    @Query("SELECT i FROM ItemTable i WHERE i.name LIKE CONCAT('%', :name, '%') AND i.type = :type ORDER BY i.updated_at DESC ")
    Page<ItemTable> ItemNameSearchWithType(@Param("name") String name, @Param("type") eType type , Pageable pageable);
}
