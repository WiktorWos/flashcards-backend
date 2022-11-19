package com.wiktor.wos.flashcards.service;

import com.wiktor.wos.flashcards.dto.ChatNotification;
import com.wiktor.wos.flashcards.dto.MessageDto;
import com.wiktor.wos.flashcards.entity.ChatMessage;
import com.wiktor.wos.flashcards.exception.BadRequestException;
import com.wiktor.wos.flashcards.mapper.ChatMessageMapper;
import com.wiktor.wos.flashcards.repository.ChatMessageRepository;
import com.wiktor.wos.flashcards.repository.ChatRoomRepository;
import com.wiktor.wos.flashcards.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageMapper chatMessageMapper;
    private final UserRepository userRepository;
    @Override
    public void sendMessage(MessageDto dto, String username) {
        var chatMessage = chatMessageMapper.mapToEntity(dto);
        chatMessage.setChatRoom(chatRoomRepository.getById(dto.getChatId()));
        var sender = userRepository.findByEmail(username).get(0);
        chatMessage.setSender(sender);

        ChatMessage saved = chatMessageRepository.save(chatMessage);

        var userAId = chatMessage.getChatRoom().getUserA().getUserId();
        var userBId = chatMessage.getChatRoom().getUserB().getUserId();
        if (!dto.getRecipientId().equals(userAId) && !dto.getRecipientId().equals(userBId))
            throw new BadRequestException("Wrong chat room");

        messagingTemplate.convertAndSendToUser(
                dto.getRecipientId().toString(),"/queue/messages",
                new ChatNotification(
                        saved.getId(),
                        saved.getSender().getUserId(),
                        saved.getSender().getFirstName()));
    }
}
