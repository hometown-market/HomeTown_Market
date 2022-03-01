package com.example.market.domain.chat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
@Slf4j
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatService chatService;


    @MessageMapping("/chat/{roomId}")
    @ApiOperation("채팅전송")
    public void RoomChat(@DestinationVariable("roomId") Long roomId, ChatMessageDTO chatMessage) {

        log.info("{}", chatMessage.toString());
        chatService.saveMessage(chatMessage);
          simpMessagingTemplate.
                convertAndSend(
                        "/queue/chat/" + roomId.toString()
                        , chatMessage.getMessage()
                );

    }


    @ResponseBody
    @PostMapping("/api/chat")
    public Long createChatRoom() {
        return chatService.createRoom();
    }

    @ResponseBody
    @GetMapping("/chat/roomlist")
    public Page<Object> roomList() {
        chatService.roomList();
        return null;
    }
}