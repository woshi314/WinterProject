package com.pocket_monster.ai;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

import javax.swing.text.AbstractDocument;
import java.io.InputStream;
import java.util.Properties;

public class LLM {
    private static final Properties PROPERTIES = new Properties();

    static {
        InputStream inputStream = LLM.class.getClassLoader().getResourceAsStream("application.properties");
        if (inputStream != null) {
            try {
                PROPERTIES.load(inputStream);
            } catch (Exception e) {
                throw new RuntimeException("加载配置文件失败", e);
            }
        } else {
            throw new RuntimeException("找不到application.properties配置文件");
        }
    }

    //读取配置
    String apiKey = PROPERTIES.getProperty("openai.api-key");
    String baseUrl = PROPERTIES.getProperty("openai.base-url");
    OpenAIClient client = OpenAIOkHttpClient.builder()
            .apiKey(apiKey)
            .baseUrl(baseUrl)
            .build();

    public String AIGenerate(String prompt) {
        String model = PROPERTIES.getProperty("openai.chat.options.model");
        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .addUserMessage("宝可梦们在关都地区生活,请根据相关的背景信息,并且结合宝可梦的世界观,为他们生成一段70字以内的故事,可以多体现宝可梦之间的互动,不生成故事以外的内容"+prompt)
                .model(model)
                .build();

        try {
            ChatCompletion chatCompletion = client.chat().completions().create(params);
            return chatCompletion
                    .choices()
                    .get(0)
                    .message()
                    .content()
                    .orElse("AI未返回内容");
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }

        return "生成失败了";
    }
}