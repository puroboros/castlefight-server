package org.castlefight.castlefight.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/websocket").setAllowedOrigins("*").setAllowedOrigins("http://localhost:3000")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/menu");
        registry.setApplicationDestinationPrefixes("/game");
        
    }
    
    @Bean
	public WebSocketHandler myHandler() {
		return new MyHandler();
	}
    
    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry message) {
      message
        .nullDestMatcher().permitAll()
        .simpDestMatchers("**").permitAll()
        .anyMessage().permitAll();
     }
    
    @Override
    protected boolean sameOriginDisabled() {
      return true;
    }    
}
