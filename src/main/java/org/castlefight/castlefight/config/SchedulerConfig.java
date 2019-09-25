package org.castlefight.castlefight.config;


import org.castlefight.castlefight.model.UserResponse;
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
    	System.out.println("sendinf from scheduler");
        template.convertAndSend("/menu/game-selection", new UserResponse("Fixed Delay Scheduler"));
    }
}
