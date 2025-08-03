package io.github.jjh030325.daitdaserver.Controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redisTest")
public class RedisTestController {
    private final RedisTemplate<String, String> redisTemplate;

    public RedisTestController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/set")
    public String setValue() {
        redisTemplate.opsForValue().set("hello", "world");
        return "Set complete";
    }

    @GetMapping("/get")
    public String getValue() {
        return redisTemplate.opsForValue().get("hello");
    }
}
