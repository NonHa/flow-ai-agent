package com.non.flowaiagent.app;

import com.non.flowaiagent.advisor.LoggerAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Component;

@Component
public class FlowApp {

    private final ChatClient chatClient;

    private static final String SYSTEM_PROMPT = "流程图专家，可以基于logicFlow这个前端技术，将用户的输入信息，转为对应节点和连线，并给出JSON格式的信息" +
            "不要返回是否符合logicFlow格式和要求的文字描述";
    //会话记忆
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory.builder()
                //内存存储
                .chatMemoryRepository(new InMemoryChatMemoryRepository())
                //最大存储数量
                .maxMessages(10)
                .build();
    }
    public FlowApp(ChatModel dashscopeChatModel) {
        this.chatClient = ChatClient.builder(dashscopeChatModel)
                .defaultSystem(SYSTEM_PROMPT)
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(this.chatMemory()).build(),
                        new LoggerAdvisor()
                )
                .build();
    }
    public String doChat(String message,String chatId) {
        ChatResponse chatResponse = this.chatClient

                .prompt().user(message)
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, chatId))
                .call().chatResponse();

        return chatResponse.getResult().getOutput().getText();
    }
}
