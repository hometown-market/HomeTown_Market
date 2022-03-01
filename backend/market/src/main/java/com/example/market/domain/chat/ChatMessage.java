package com.example.market.domain.chat;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {

    @ManyToOne
    @JoinColumn(name = "room_id")
    private ChatRoom chatRoom;

    private Long sender; // 메시지 보낸사람
    private String message; // 메시지

    @CreatedDate
    private LocalDateTime sendTime;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "VARCHAR(255)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;


    @Builder
    public ChatMessage(Long sender, String message) {
        this.sender = sender;
        this.message = message;
    }


}