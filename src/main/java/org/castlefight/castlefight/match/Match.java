package org.castlefight.castlefight.match;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.TimerTask;

import org.springframework.data.util.Pair;

public class Match extends TimerTask {
    private List<PlayerPlaying> players;
    private String owner;
    private Integer maxPlayers;

    private String status;
    private Integer id;
    private List<Troop> troops;

    public Match() {
        troops = new ArrayList<Troop>();
        Troop troop1 = new Troop();
        troop1.setPosition(Pair.of(1, 1));
        troop1.setId(0);
        troops.add(troop1);

        Troop troop2 = new Troop();
        troop2.setPosition(Pair.of(10, 10));
        troop2.setId(1);
        troops.add(troop2);
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

    public List<Troop> getTroops() {
        return troops;
    }

    public void setTroops(List<Troop> troops) {
        this.troops = troops;
    }

    public Troop getTroop(Integer id) {
        Optional<Troop> selectedTroop = this.troops.stream().filter(troop -> troop.getId().equals(id)).findFirst();
        if (selectedTroop.isPresent()) {
            return selectedTroop.get();
        } else {
            return null;
        }
    }

    public void run() {

    }
}
