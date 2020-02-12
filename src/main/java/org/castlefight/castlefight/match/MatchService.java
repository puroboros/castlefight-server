package org.castlefight.castlefight.match;

import org.castlefight.castlefight.model.GameException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchService {
    List<Match> matches;

    public MatchService() {
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

    public Match joinMatch(String owner, String joiner) throws GameException {
        Optional<Match> match = matches.stream().filter(iteratedMatch -> iteratedMatch.getPlayer1().getId().equals(owner) && iteratedMatch.getStatus().equals("open") ).findFirst();
        if (match.isPresent()) {
            if(owner.equals(joiner)){
                return match.get();
            }
            PlayerPlaying player2 = new PlayerPlaying();
            player2.setId(joiner);
            match.get().setPlayer2(player2);
            match.get().setStatus("ready");
            return match.get();
        } else {
            throw new GameException("Can't join this game");
        }
    }

    public List<Match> getOpenMatches() {
        return matches.stream().filter(match -> match.getStatus().equals("open")).collect(Collectors.toList());
    }
}
