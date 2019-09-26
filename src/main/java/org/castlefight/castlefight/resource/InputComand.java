package org.castlefight.castlefight.resource;

import java.security.Principal;

import org.castlefight.castlefight.model.User;
import org.castlefight.castlefight.model.UserComand;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class InputComand {

    @MessageMapping("/game-selection")
    public UserComand getUser(Principal principal, User user, @Header("simpSessionId") String sessionId) {
    	System.out.println("Principal: " + principal.toString() + "\n User: " + user.toString());
        return new UserComand("Hi " + user.getName());
    }
}
