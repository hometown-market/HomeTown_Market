package com.example.market.domain.chat;

import com.example.market.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    private final ChatRoomRepository roomRepository;
    private final ChatMessageRepository messageRepository;


    public Long createRoom() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication {}", authentication);
        if (authentication.isAuthenticated()) {
            User principal = (User) authentication.getPrincipal();
            log.info("user {}", principal);
            ChatRoom chatRoom = ChatRoom.builder()
                    .sellerId(principal.getId())
                    .build();
            roomRepository.save(chatRoom);
            log.info("roomId {}", chatRoom.getRoomId());
            return chatRoom.getRoomId();
        }

        return null;
    }

    @Transactional
    public void saveMessage(ChatMessageDTO messageDTO) {
        Optional<ChatRoom> room = roomRepository.findById(messageDTO.getRoomId());
        ChatMessage message = new ChatMessage(messageDTO.getSenderId(), messageDTO.getMessage());
        room.ifPresent(chatRoom -> {
            List<ChatMessage> chatMessages = chatRoom.getChatMessages();
            chatMessages.add(message);
        });
        messageRepository.save(message);

    }

    @Transactional
    public void roomList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            User principal = (User) authentication.getPrincipal();

        }
    }
}