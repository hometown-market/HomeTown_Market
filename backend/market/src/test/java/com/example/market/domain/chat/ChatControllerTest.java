package com.example.market.domain.chat;

import com.example.market.MarketApplication;
import com.example.market.domain.user.User;
import com.example.market.domain.user.repository.UserRepository;
import com.example.market.global.config.ChatStompSessionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = MarketApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
class ChatControllerTest {
    @Autowired
    private MockMvc mvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    UserRepository userRepository;

    @Test
    void roomChat() throws Exception {
        User userA = User.builder().
                email("userA@usreA.com").
                name("userA").
                password("1234").
                role("ROLE_USER").
                build();
        User userB = User.builder().
                email("userB@usreB.com").
                name("userB").
                password("1234").
                role("ROLE_USER").
                build();
        userRepository.save(userA);
        userRepository.save(userB);


        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userA, userA.getPassword(), userA.getAuthorities());
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        ResultActions resultActions = mvc.perform(post("/api/chat")).
                andExpect(status().isOk())
                .andDo(print());
        Long roomId = Long.parseLong(resultActions.andReturn().getResponse().getContentAsString());

        String host = "ws://localhost:8080/";
        String url = "ws://localhost:8080/websocket";

        WebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketStompClient userAClient = new WebSocketStompClient(webSocketClient);
        userAClient.setMessageConverter(new StringMessageConverter());

        WebSocketClient UserBWebSocketClient = new StandardWebSocketClient();
        WebSocketStompClient userBClient = new WebSocketStompClient(UserBWebSocketClient);
        userBClient.setMessageConverter(new StringMessageConverter());

        StompSessionHandler chatStompSessionHandler = new ChatStompSessionHandler();


        ListenableFuture<StompSession> sessionA = userAClient.connect(url, chatStompSessionHandler);
        ListenableFuture<StompSession> sessionB = userBClient.connect(url, chatStompSessionHandler);
        StompSession stompSessionA = sessionA.get();
        StompSession stompSessionB = sessionB.get();

        String roomUrl = "/queue/" + "chat/" + roomId.toString();
        System.out.println("roomUrl = " + roomUrl);
        String sendUrl = "/app/chat/" + roomId.toString();
        System.out.println("sendUrl = " + sendUrl);
        stompSessionA.subscribe(roomUrl, chatStompSessionHandler);
        stompSessionB.subscribe(roomUrl, chatStompSessionHandler);


        stompSessionA.send(roomUrl, objectMapper.writeValueAsString(new ChatMessage(userA.getId(), "i'm userA")));
        stompSessionA.send(sendUrl, objectMapper.writeValueAsString(new ChatMessage(userA.getId(), "i'm userA")));
        stompSessionA.send(roomUrl, objectMapper.writeValueAsString(new ChatMessage(userA.getId(), "i'm userA")));

    }


}