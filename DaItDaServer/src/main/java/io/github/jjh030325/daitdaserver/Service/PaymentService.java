package io.github.jjh030325.daitdaserver.Service;

import io.github.jjh030325.daitdaserver.Domain.ItemTable;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
* 가상 캐쉬 결제 관련 핵심 비즈니스 로직을 처리하는 서비스 클래스입니다.
* 결제 도메인 로직을 담당합니다.
* */
@Service
public class PaymentService {
    private final UserService userService;
    private final ItemService itemService;
    private final OrderHistoryService orderHistoryService;

    @Autowired
    public PaymentService(UserService userService, ItemService itemService, OrderHistoryService orderHistoryService) {
        this.userService = userService;
        this.itemService = itemService;
        this.orderHistoryService = orderHistoryService;
    }

    @Transactional
    public String purchaseItem(Long userId, Long itemId, Integer quantity) {
        ItemTable item = itemService.getItemById(itemId);
        userService.decreaseBalance(userId, item.getPrice());
        orderHistoryService.saveOrder(userId, itemId, item.getPrice(), quantity);
        return "구매 성공";
    }
}
