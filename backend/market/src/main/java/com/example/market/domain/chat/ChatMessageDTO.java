package com.example.market.domain.chat;

import lombok.Getter;

@Getter
public class ChatMessageDTO {

    private Long senderId;
    private Long roomId;
    private String message;
}
