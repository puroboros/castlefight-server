package org.castlefight.castlefight.match;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private String owner;
    private List<PlayerPlaying> players;
    private String status;
    private Integer id;
    private Integer maxPlayers;

    public Match(){
        maxPlayers= 10;
        players = new ArrayList<PlayerPlaying>();
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<PlayerPlaying> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerPlaying> player2) {
        this.players = player2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
