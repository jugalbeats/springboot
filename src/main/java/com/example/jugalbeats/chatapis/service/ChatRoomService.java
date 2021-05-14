package com.example.jugalbeats.chatapis.service;

import com.example.jugalbeats.chatapis.model.ChatRoom;
import com.example.jugalbeats.chatapis.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomService {

	@Autowired
	private ChatRoomRepository chatRoomRepository;

	public Optional<String> getChatId(
            String senderId, String recipientId, boolean createIfNotExist) {
    	
    	Optional<ChatRoom>room=chatRoomRepository
                .findBySenderIdAndRecipientId(senderId, recipientId);
    	String chatId=null;
    	if(room.isPresent())
    	{	 chatId=room.map(ChatRoom::getChatId).get();
    	         return Optional.of(chatId);
	}
        if(!createIfNotExist) {
            return  Optional.empty();
        }
    	    chatId =String.format("%s_%s", senderId, recipientId);

    	                    ChatRoom senderRecipient = ChatRoom
    	                            .builder()
    	                            .chatId(chatId)
    	                            .senderId(senderId)
    	                            .recipientId(recipientId)
    	                            .build();

    	                    ChatRoom recipientSender = ChatRoom
    	                            .builder()
    	                            .chatId(chatId)
    	                            .senderId(recipientId)
    	                            .recipientId(senderId)
    	                            .build();
    	                    chatRoomRepository.save(senderRecipient);
    	                    chatRoomRepository.save(recipientSender);

    	                    return Optional.of(chatId);
    	            
}

}
