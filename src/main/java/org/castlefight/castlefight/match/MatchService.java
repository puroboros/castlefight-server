package org.castlefight.castlefight.match;

import org.castlefight.castlefight.model.GameException;
import org.castlefight.castlefight.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchService {
    List<Match> matches;

    SimpMessagingTemplate template;

    public MatchService(@Autowired SimpMessagingTemplate template) {
        this.template = template;
        this.matches = new ArrayList<Match>();
    }

    public Match createMatch(String playerId) throws GameException {
        if (matches.stream().noneMatch(match -> match.getPlayer1().getId().equals(playerId) && match.getStatus().equals("open"))) {
            Match match = new Match();
            match.setPlayer1(new PlayerPlaying());
            match.getPlayer1().setStatus("waiting");
            match.getPlayer1().setId(playerId);
            match.setId(this.matches.size());
            match.setStatus("open");
            this.matches.add(match);
            return match;
        } else {
            throw new GameException(playerId.concat(" already has an open game"));
        }
    }

    public Match joinMatch(String owner, String joiner) throws Exception{
        Optional<Match> match = matches.stream().filter(iteratedMatch -> iteratedMatch.getPlayer1().getId().equals(owner) && iteratedMatch.getStatus().equals("open") ).findFirst();
        if(match.isPresent()){
            if(owner.equals(joiner)){
                return match.get();
            }
            //List<PlayerPlaying> player2 = new ArrayList<PlayerPlaying>();
            PlayerPlaying newPlayer = new PlayerPlaying();
            newPlayer.setId(joiner);
            //match.get().setPlayer2(player2);
            match.get().getPlayers().add(newPlayer);
            match.get().setStatus("ready");
            return match.get();
        } else {
            throw new GameException("Can't join this game");
        }
    }

    public List<Match> getOpenMatches() {
        return matches.stream().filter(match -> match.getStatus().equals("open")).collect(Collectors.toList());
    }

    public Match getMatchById(String id){
        return matches.stream().filter(iteratedMatch -> iteratedMatch.getId().equals(id)).findFirst().get();
    }

    public void setStatustoPlayer(String idMatch, String idPlayer, String status){
        this.getMatchById(idMatch).getPlayers().stream().filter(players -> players.getId().equals(idPlayer)).findFirst().get().setStatus(status);
    }

    public void broadcastMatchMessage(Object message, String matchId, String method){
        Match match = this.getMatchById(matchId);
        for(int i = 0; i<match.getPlayers().size(); i++){
            template.convertAndSendToUser(match.getPlayers().get(i).getId(), "/menu/game-selection", new UserResponse(method,message));
        }
    }

}
