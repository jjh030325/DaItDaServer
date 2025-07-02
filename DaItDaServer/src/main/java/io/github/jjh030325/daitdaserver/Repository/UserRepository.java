package io.github.jjh030325.daitdaserver.Repository;

import io.github.jjh030325.daitdaserver.Domain.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
* UserTable 엔티티에 대한 데이터베이스 접근 인터페이스입니다.
* Spring Data JPA를 활용하여 기본 CRUD 및 사용자 정의 쿼리를 처리합니다.
* */
public interface UserRepository extends JpaRepository<UserTable, Long> {
    boolean existsByName(String name);
    boolean existsByUserId(String userId);
    boolean existsByNameAndUserId(String name, String userId);

    Optional<UserTable> findByUserId(String userId);
}
