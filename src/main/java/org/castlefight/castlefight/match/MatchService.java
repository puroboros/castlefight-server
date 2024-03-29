package org.castlefight.castlefight.match;

import org.castlefight.castlefight.model.GameException;
import org.castlefight.castlefight.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.data.util.Pair;
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
        if (matches.stream().noneMatch(match -> match.getOwner().equals(playerId) && match.getStatus().equals("open"))) {
            Match match = new Match();
            match.setOwner(playerId);
            PlayerPlaying newPlayer = new PlayerPlaying();
            newPlayer.setStatus("waiting");
            newPlayer.setId(playerId);
            match.getPlayers().add(newPlayer);
            match.setId(this.matches.size());
            match.setStatus("open");
            this.matches.add(match);
            return match;
        } else {
            throw new GameException(playerId.concat(" already has an open game"));
        }
    }

    public void closeMatch(String ownerId, String callerId) throws GameException {
        if (ownerId.equals(callerId)) {
            Optional<Match> match = matches.stream().filter(iteratedMatch -> iteratedMatch.getOwner().equals(ownerId)).findFirst();
            if (match.isPresent()) {
                matches.remove(match.get());
            }
        } else {
            throw new GameException("You have no rights to do this");
        }
    }

    public Match joinMatch(String owner, String joiner) throws Exception {
        Optional<Match> match = matches.stream().filter(iteratedMatch -> iteratedMatch.getOwner().equals(owner) && iteratedMatch.getStatus().equals("open")).findFirst();
        if (match.isPresent()) {
            if (owner.equals(joiner)) {
                return match.get();
            }
            PlayerPlaying newPlayer = new PlayerPlaying();
            newPlayer.setId(joiner);
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

    public Match getMatchById(Integer id) {
        return matches.stream().filter(iteratedMatch -> iteratedMatch.getId().equals(id)).findFirst().get();
    }

    public void setStatustoPlayer(Integer idMatch, String idPlayer, String status) {
        this.getMatchById(idMatch)
                .getPlayers().stream()
                .filter(players ->
                        players.getId().equals(idPlayer)).findFirst().get().setStatus(status);
    }

    public void broadcastMatchMessage(Object message, Integer matchId, String method) {
        System.out.println("broadcastMatchMessage");
        Match match = this.getMatchById(matchId);
        for (int i = 0; i < match.getPlayers().size(); i++) {
            System.out.println("sending match " + message.toString() + " to: " + match.getPlayers().get(i).getId());
            template.convertAndSendToUser(match.getPlayers().get(i).getId(), "/menu/game-selection", new UserResponse(method, message));
        }
    }

    public void broadcastGameMatchMessage(Object message, Integer matchId, String method) {
        System.out.println("broadcastMatchMessage");
        Match match = this.getMatchById(matchId);
        for (int i = 0; i < match.getPlayers().size(); i++) {
            System.out.println("sending game " + message.toString() +" to: " + match.getPlayers().get(i).getId());
            template.convertAndSendToUser(match.getPlayers().get(i).getId(), "/menu/game-command", new UserResponse(method, message));
        }
    }

    public Match moveUnit(Integer matchId, Integer troopId, Pair position) throws GameException {
        System.out.println("moveUnit");
        Optional<Match> match = matches.stream().filter(iteratedMatch -> iteratedMatch.getId().equals(matchId)).findFirst();
        if (match.isPresent()) {
            Match existingMatch = match.get();
            System.out.println("isPresent " + existingMatch.getTroops());
            existingMatch.getTroop(troopId).setPosition(position);
            return existingMatch;
        } else {
            throw new GameException("Game does not exists");
        }
    }

    public Boolean checkMatch(Integer matchId) throws GameException {
        boolean result = true;
        Optional<Match> match = matches.stream().filter(iteratedMatch -> iteratedMatch.getId().equals(matchId)).findFirst();
        if (match.isPresent()) {
            for (PlayerPlaying player : match.get().getPlayers()) {
                if (!player.getStatus().equals("ready")) {
                    throw new GameException("Not all players are ready.");
                }
            }
        } else {
            throw new GameException("Match does not exist.");
        }
        return result;
    }

    public Match startGame(Integer matchId, String requester) throws GameException {
        System.out.println("Start Game: " + matchId + " requested by: " + requester + " on: " + matches.toString());
        Optional<Match> match = matches.stream().filter(iteratedMatch -> iteratedMatch.getId().equals(matchId) && iteratedMatch.getOwner().equals(requester)).findFirst();
        if (match.isPresent()) {
            match.get().setStatus("running");
            return match.get();
        } else {
            throw new GameException("Match not present or not the owner.");
        }
    }
}
