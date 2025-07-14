package io.github.jjh030325.daitdaserver.Controller;

import io.github.jjh030325.daitdaserver.Domain.ItemTable;
import io.github.jjh030325.daitdaserver.Enum.eType;
import io.github.jjh030325.daitdaserver.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
* 상품 정보 확인과 관련된 HTTP 요청을 처리하는 REST 컨트롤러입니다.
* 상품 정보 확인, 검색, 삭제 등의 API 엔드포인트를 제공합니다.
* */
@RestController
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // 아이템 검색
    @GetMapping("/search/{name}")
    public ResponseEntity<Page<ItemTable>> SearchItem(
            @PathVariable String name,
            @RequestParam(required = false) eType type,
            @RequestParam (defaultValue = "0") int page) {
        if(type == null)
        {
            return itemService.SearchItem(name, page);
        }else {
            return itemService.SearchItemWithType(name, type, page);
        }
    }
}
