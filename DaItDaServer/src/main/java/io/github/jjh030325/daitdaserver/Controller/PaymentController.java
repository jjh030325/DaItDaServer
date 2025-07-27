package io.github.jjh030325.daitdaserver.Controller;

import io.github.jjh030325.daitdaserver.Config.CustomUserDetails;
import io.github.jjh030325.daitdaserver.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    // 상품 구매 API
    @PostMapping("/buy")
    public ResponseEntity<String> buyItem(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestParam Long itemId, @RequestParam Integer quantity) {
        Long userId = userDetails.getId();
        String result = paymentService.purchaseItem(userId, itemId, quantity);
        return ResponseEntity.ok(result);
    }

    // 관리자 권한으로 특정 유저에게 캐쉬 선물
    @PostMapping("/gift")
    public ResponseEntity<String> cashPlus(@RequestParam Long userId, @RequestParam Long price) {
        String result = paymentService.cashPlus(userId, price);
        return ResponseEntity.ok(result);
    }

    // 남은 잔액 확인 API
    @GetMapping("/myMoney")
    public ResponseEntity<Long> myMoney(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getId();
        return ResponseEntity.ok(paymentService.myMoney(userId));
    }
}
