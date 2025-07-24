package io.github.jjh030325.daitdaserver.Service;

import io.github.jjh030325.daitdaserver.Domain.ItemTable;
import io.github.jjh030325.daitdaserver.Enum.eType;
import io.github.jjh030325.daitdaserver.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
* 상품 관련 핵심 비즈니스 로직을 처리하는 서비스 클래스입니다.
* 상품 정보 확인과 관련된 도메인 로직을 담당합니다.
* */
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // 이름 검색
    public ResponseEntity<Page<ItemTable>> SearchItem(String input, int page) {
        Pageable pageable = PageRequest.of(page, 20);
        Page<ItemTable> resultPage = itemRepository.ItemNameSearch(input, pageable);
        return ResponseEntity.ok(resultPage);
    }

    // 이름 검색 + 타입
    public ResponseEntity<Page<ItemTable>> SearchItemWithType(String input, eType type, int page) {
        Pageable pageable = PageRequest.of(page, 20);
        Page<ItemTable> resultPage = itemRepository.ItemNameSearchWithType(input, type, pageable);
        return ResponseEntity.ok(resultPage);
    }

    // id로 상품 테이블 리턴
    public ItemTable getItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("상품 없음"));
    }
}
