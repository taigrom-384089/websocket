package com.example.simpleapi;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
public class ChatController {

    // SimpMessagingTemplate is used to send messages FROM the server to specific topics
    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Listens for messages sent to the destination /app/chat (from the Angular client)
    @MessageMapping("/chat")
    public void receiveMessage(ChatMessage message) {
        // Process the message (e.g., save to DB, log)
        System.out.println("Received: " + message.getUser() + " - " + message.getContent());
        
        // Send the message back to all clients subscribed to /topic/public
        // The destination is the broker prefix + the topic name
        messagingTemplate.convertAndSend("/topic/public", message);
    }
}