package com.example.market.domain.chat;

import com.example.market.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {
    @Id
    @GeneratedValue
    private Long roomId;
    private String name;

    private long sellerId;
    private long buyerId;


    @OneToMany(mappedBy = "chatRoom", orphanRemoval = true)
    private List<ChatMessage> chatMessages = new ArrayList<>();


    @Builder
    public ChatRoom(String name, long sellerId, long buyerId) {
        this.name = name;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
    }
}