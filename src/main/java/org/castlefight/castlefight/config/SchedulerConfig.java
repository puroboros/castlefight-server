package org.castlefight.castlefight.config;


import java.util.Date;

import org.castlefight.castlefight.model.UserComand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class SchedulerConfig {

    @Autowired
    SimpMessagingTemplate template;

    
    @Scheduled(fixedDelay = 3000)
    public void sendAdhocMessages() {
    	UserComand response = new UserComand("Fixed Delay Scheduler " + new Date().getTime());
        template.convertAndSend("/menu/game-selection", response);
    }
    
    @Scheduled(fixedDelay = 3000)
    public void sendAdhocMessagesToPikachu() {
    	UserComand response = new UserComand("Fixed Delay Scheduler pikachu " + new Date().getTime());
    	template.convertAndSendToUser("pikachu", "/menu/game-selection", response);
    }
    
    
}
