package com.example.jugalbeats.chatapis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import com.example.jugalbeats.models.BaseModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "chat_room")
public class ChatRoom extends BaseModel{
  
    private String chatId;
    private String senderId;
    private String recipientId;
}
