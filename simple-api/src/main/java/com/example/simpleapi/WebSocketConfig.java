package com.example.simpleapi;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 1. Prefix for messages SENT FROM the server TO the client (subscriptions)
        config.enableSimpleBroker("/topic"); 
        
        // 2. Prefix for messages SENT FROM the client TO the server (controller mappings)
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 3. The WebSocket endpoint clients will connect to
        // .withSockJS() is often used for fallback browser compatibility
        registry.addEndpoint("/ws-connect")
                .setAllowedOriginPatterns("http://localhost:4200"); // Angular Dev Server
                //.withSockJS();
    }
    
}