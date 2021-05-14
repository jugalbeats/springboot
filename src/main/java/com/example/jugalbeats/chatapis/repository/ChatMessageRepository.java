package com.example.jugalbeats.chatapis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.jugalbeats.chatapis.model.ChatMessage;
import com.example.jugalbeats.enums.MessageStatus;


public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>{
	
	  long countBySenderIdAndRecipientIdAndStatus(
	            String senderId, String recipientId, MessageStatus status);

	    List<ChatMessage> findByChatId(String chatId);

		@Query(value = "update chat_message u set status = :status where senderId = :senderId and recipientId = :recipientId ", nativeQuery = true)
	    void updateMessageStatus(@Param("senderId")String senderId, @Param("recipientId")String recipientId, @Param("status")int status);
		 

}
