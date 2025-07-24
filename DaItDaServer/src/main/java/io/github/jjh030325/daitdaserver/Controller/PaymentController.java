package io.github.jjh030325.daitdaserver.Controller;

import io.github.jjh030325.daitdaserver.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
* 결제 관련 HTTP 요청을 처리하는 REST 컨트롤러입니다.
* 결제 및 결제 내역 갱신과 같은 API 엔드포인트를 제공합니다.
* */
@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyItem(@RequestParam Long userId, @RequestParam Long itemId, @RequestParam Integer quantity) {
        String result = paymentService.purchaseItem(userId, itemId, quantity);
        return ResponseEntity.ok(result);
    }
}
