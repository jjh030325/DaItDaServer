package io.github.jjh030325.daitdaserver.Service;

import io.github.jjh030325.daitdaserver.Domain.OrderHistoryTable;
import io.github.jjh030325.daitdaserver.Repository.OrderHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/*
* 주문 내역 핵심 비즈니스 로직을 처리하는 서비스 클래스입니다.
* 상품 주문 내역 확인과 관련된 도메인 로직을 담당합니다.
* */
@Service
public class OrderHistoryService {
    private final OrderHistoryRepository orderHistoryRepository;

    @Autowired
    public OrderHistoryService(OrderHistoryRepository orderHistoryRepository) {
        this.orderHistoryRepository = orderHistoryRepository;
    }

    // 주문시 내역 추가
    public void saveOrder(Long userId, Long itemId, Long price, Integer quantity) {
        OrderHistoryTable order = new OrderHistoryTable();
        order.setBuyer_id(userId);
        order.setItem_id(itemId);
        order.setPrice(price);
        order.setQuantity(quantity);
        order.setTotal_price(price * quantity);
        order.setPurchased_at(LocalDateTime.now());
        orderHistoryRepository.save(order);
    }
}
