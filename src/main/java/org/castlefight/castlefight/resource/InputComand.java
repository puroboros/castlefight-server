package org.castlefight.castlefight.resource;

import java.security.Principal;

import org.castlefight.castlefight.match.Match;
import org.castlefight.castlefight.match.MatchService;
import org.castlefight.castlefight.model.UserComand;
import org.castlefight.castlefight.model.UserResponse;
import org.castlefight.castlefight.model.UserSelection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class InputComand {

    private MatchService matchService;
    SimpMessagingTemplate template;

    public InputComand(@Autowired MatchService matchService, @Autowired SimpMessagingTemplate template) {
        this.matchService = matchService;
        this.template = template;
    }

    @MessageMapping("/game-selection")
    public UserComand getUser(Principal principal, @Payload UserSelection selection) {
        System.out.println("[InputComand] Principal: " + principal.getName() + "\n User: " + selection.toString());
        try {
            switch (selection.getAction()) {
                case "":

                    break;
                case "mainMenu":

                    break;
                case "createMatch":
                    gameSelectionResponse(principal.getName(), BroadcastEvents.CREATE, matchService.createMatch(principal.getName()));

                    break;
                case "joinMatch":
                        Match match = matchService.joinMatch(selection.getDetails(), principal.getName());
                        gameSelectionResponse(principal.getName(), BroadcastEvents.JOIN, match);
                        matchService.broadcastMatchMessage(match,match.getId(),BroadcastEvents.JOIN);
                        gameSelectionResponse(match.getOwner(), BroadcastEvents.JOIN, match);
                    break;
                case "closeMatch":
                    matchService.closeMatch(selection.getDetails(), principal.getName());
                    break;
                case "listMatches":
                    gameSelectionResponse(principal.getName(), BroadcastEvents.MENU, matchService.getOpenMatches());
                    break;
                case "playerReady":
                    String[] detailsSplit = selection.getDetails().split("\n");
                    matchService.setStatustoPlayer(Integer.parseInt(detailsSplit[0]),detailsSplit[1],detailsSplit[2]);
                    matchService.broadcastMatchMessage(matchService.getMatchById(Integer.parseInt(detailsSplit[0])),Integer.parseInt(detailsSplit[0]),BroadcastEvents.UPDATE_STATUS);
                    break;
                case "startGame":
                    Match startedMatch = matchService.startGame(Integer.parseInt(selection.getDetails()), principal.getName());
                    matchService.broadcastMatchMessage(startedMatch, startedMatch.getId(), BroadcastEvents.START);
                    break;
                default:
                    System.out.println("Method not implemented: " + selection.getAction());
                    break;
            }
        } catch (Exception ex){
            gameSelectionResponse(principal.getName(), "error", ex.getMessage());
        }
        return null;
    }



    private void gameSelectionResponse(String user, String method, Object payload){
        template.convertAndSendToUser(user, "/menu/game-selection", new UserResponse(method, payload));
    }
}
