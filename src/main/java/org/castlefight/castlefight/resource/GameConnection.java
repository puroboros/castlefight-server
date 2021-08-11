package org.castlefight.castlefight.resource;

import org.castlefight.castlefight.match.MatchService;
import org.castlefight.castlefight.model.UserComand;
import org.castlefight.castlefight.model.UserResponse;
import org.castlefight.castlefight.model.UserSelection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class GameConnection {
    MatchService matchService;
    SimpMessagingTemplate template;

    public GameConnection(@Autowired MatchService matchService, @Autowired SimpMessagingTemplate template) {
        this.matchService = matchService;
        this.template = template;
    }

    @MessageMapping("/game-command")
    public UserComand getUser(Principal principal, @Payload UserSelection selection) {
        System.out.println("[GameConnection] User: " + principal.getName() + "\n Command: " + selection.toString());
        try {
            switch (selection.getAction()) {
                case "moveUnit":
                    System.out.println("movee");
                    String[] detailsSplit = selection.getDetails().split("\n");
                    Integer matchId = Integer.parseInt(detailsSplit[0]);
                    Integer troopId = Integer.parseInt(detailsSplit[1]);
                    Pair position = Pair.of(Integer.parseInt(detailsSplit[2]), Integer.parseInt(detailsSplit[3]));
                    matchService.broadcastGameMatchMessage(matchService.moveUnit(matchId, troopId, position), matchId, "moveUnit");
                    break;
            }
        } catch (Exception e) {

        }
        return null;
    }

    private void gameSelectionResponse(String user, String method, Object payload) {
        template.convertAndSendToUser(user, "/game", new UserResponse(method, payload));
    }
}
