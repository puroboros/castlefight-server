package org.castlefight.castlefight.resource;

import java.security.Principal;

import org.castlefight.castlefight.model.User;
import org.castlefight.castlefight.model.UserResponse;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

	@MessageMapping("/gameselection")
	@SendTo("/menu/game-selection")
    public UserResponse getUser2(UserResponse userResponse) {
    	System.out.println("Sending from controller " + userResponse.toString());
        return userResponse;
    }

    @MessageMapping("/game-selection")
    public UserResponse getUser(Principal principal, User user, @Header("simpSessionId") String sessionId) {
    	System.out.println("Principal: " + principal.toString() + ", User: " + user.toString());
        return new UserResponse("Hi " + user.getName());
    }
}
