package io.github.jjh030325.daitdaserver.Controller;

import io.github.jjh030325.daitdaserver.Config.CustomUserDetails;
import io.github.jjh030325.daitdaserver.Domain.OrderHistoryTable;
import io.github.jjh030325.daitdaserver.Service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/*
* 주문 내역과 관련된 HTTP 요청을 처리하는 REST 컨트롤러입니다.
* 주문 내역 확인 등의 API 엔드포인트를 제공합니다.
* */
@RestController
@RequestMapping("/orderHistory")
public class OrderHistoryController {
    private final OrderHistoryService orderHistoryService;

    @Autowired
    public OrderHistoryController(OrderHistoryService orderHistoryService) {
        this.orderHistoryService = orderHistoryService;
    }

    // 주문 내역 확인
    @GetMapping("/List")
    public ResponseEntity<Page<OrderHistoryTable>> MyOrderHistory(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam (defaultValue = "0") int page) {
        Long userId = userDetails.getId();
        return orderHistoryService.myOrderHistory(userId, page);
    }
}
