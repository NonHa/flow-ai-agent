package com.non.flowaiagent.app;

import cn.hutool.core.lang.UUID;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FlowAppTest {

    @Resource
    public FlowApp flowApp;
    @Test
    void doChat() {
        String chatId = UUID.randomUUID().toString();
        String message = flowApp.doChat("我上一个问题是什么",chatId);
        Assertions.assertNotNull(message);
    }
}