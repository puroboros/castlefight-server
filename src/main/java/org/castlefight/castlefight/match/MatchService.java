package org.castlefight.castlefight.match;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchService {
    List<Match> matches;
    public MatchService(){
        this.matches = new ArrayList<Match>();
    }
    public Match createMatch(String playerId){
        if(matches.stream().noneMatch(match -> match.getPlayer1().getId().equals(playerId) && match.getStatus().equals("open"))){
            Match match = new Match();
            match.setPlayer1(new PlayerPlaying());
            match.getPlayer1().setId(playerId);
            match.setId(this.matches.size());
            match.setStatus("open");
            this.matches.add(match);
            return match;
        } else {
            return null;
        }
    }
    public Match joinMatch(String owner, String joiner) throws Exception{
        Optional<Match> match = matches.stream().filter(iteratedMatch -> iteratedMatch.getPlayer1().getId().equals(owner) && iteratedMatch.getStatus().equals("open") && !owner.equals(joiner)).findFirst();
        if(match.isPresent()){
            PlayerPlaying player2 = new PlayerPlaying();
            player2.setId(joiner);
            match.get().setPlayer2(player2);
            match.get().setStatus("ready");
            return match.get();
        } else {
            throw  new Exception();
        }
    }

    public List<Match> getOpenMatches(){
        return matches.stream().filter( match -> match.getStatus().equals("open")).collect(Collectors.toList());
    }
}
