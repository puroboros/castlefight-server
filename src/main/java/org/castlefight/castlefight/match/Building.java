package org.castlefight.castlefight.match;

public class Building extends ActiveUnit {
    private Troop summonedTroop;

    public Troop getSummonedTroop() {
        return summonedTroop;
    }

    public void setSummonedTroop(Troop summonedTroop) {
        this.summonedTroop = summonedTroop;
    }
}
