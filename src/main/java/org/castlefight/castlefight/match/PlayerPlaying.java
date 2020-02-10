package org.castlefight.castlefight.match;

import java.util.List;

public class PlayerPlaying {
    private String id;
    private Race race;
    private String status;
    private Integer gold;
    private Integer wood;
    private List<Troop> activeTroops;
    private List<Building> activeBuilding;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getWood() {
        return wood;
    }

    public void setWood(Integer wood) {
        this.wood = wood;
    }

    public List<Troop> getActiveTroops() {
        return activeTroops;
    }

    public void setActiveTroops(List<Troop> activeTroops) {
        this.activeTroops = activeTroops;
    }

    public List<Building> getActiveBuilding() {
        return activeBuilding;
    }

    public void setActiveBuilding(List<Building> activeBuilding) {
        this.activeBuilding = activeBuilding;
    }
}
