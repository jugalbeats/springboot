package com.example.jugalbeats.chatapis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jugalbeats.chatapis.model.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>{
	
    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);

}
