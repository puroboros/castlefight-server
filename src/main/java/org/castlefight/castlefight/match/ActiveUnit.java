package org.castlefight.castlefight.match;


import org.springframework.data.util.Pair;

public abstract class ActiveUnit {
    private Pair<Integer, Integer> position;
    private Integer id;
    private Float currentHp;
    private Float currentHpRegen;
    private Float currentMana;
    private Float currentManaRegen;

    ActiveUnit(){
        this.position = Pair.of(0, 0);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    public void setPosition(Pair<Integer, Integer> position) {
        this.position = position;
    }

    public Float getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(Float currentHp) {
        this.currentHp = currentHp;
    }

    public Float getCurrentHpRegen() {
        return currentHpRegen;
    }

    public void setCurrentHpRegen(Float currentHpRegen) {
        this.currentHpRegen = currentHpRegen;
    }

    public Float getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(Float currentMana) {
        this.currentMana = currentMana;
    }

    public Float getCurrentManaRegen() {
        return currentManaRegen;
    }

    public void setCurrentManaRegen(Float currentManaRegen) {
        this.currentManaRegen = currentManaRegen;
    }
}
