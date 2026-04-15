package com.non.flowaiagent.controller;

import com.non.flowaiagent.app.FlowApp;
import com.non.flowaiagent.model.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AiController {

    @Resource
    private FlowApp flowApp;

    @GetMapping("/chat")
    public Result<String> aiChat(String message, String chatId) {
      String response =  flowApp.doChat(message,chatId);
      return  Result.success(response);
    }
}
