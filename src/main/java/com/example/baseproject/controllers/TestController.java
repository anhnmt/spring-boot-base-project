package com.example.baseproject.controllers;

import com.example.baseproject.common.utils.Constants;
import com.example.baseproject.services.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {
    private final RedisService redisService;

    @GetMapping("/bloom-add")
    public String setBloomFilter(@RequestParam("orderNum") String orderNum) {
        try {
            redisService.setBloomFilter(Constants.REDIS_KEY.BLOOM_FILTER, orderNum);
        } catch (Exception e) {
            e.printStackTrace();
            return "Add failed";
        }

        return "Added successfully";
    }

    @ResponseBody
    @GetMapping("/bloom-check")
    public boolean checkBloomFilter(@RequestParam("orderNum") String orderNum) {
        boolean b = redisService.checkBloomFilter(Constants.REDIS_KEY.BLOOM_FILTER, orderNum);

        return b;
    }

    @ResponseBody
    @GetMapping("/bloom-del")
    public String delBloomFilter(@RequestParam("orderNum") String orderNum) {
        try {
            redisService.delBloomFilter(Constants.REDIS_KEY.BLOOM_FILTER, orderNum);
        } catch (Exception e) {
            e.printStackTrace();
            return "Remove failed";
        }

        return "Remove successfully";
    }
}
