package org.castlefight.castlefight.match;

import java.util.List;

public class Race {
    private String name;
    private List<Troop> selectableTroops;
    private List<Building> selectableBuildings;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Troop> getSelectableTroops() {
        return selectableTroops;
    }

    public void setSelectableTroops(List<Troop> selectableTroops) {
        this.selectableTroops = selectableTroops;
    }

    public List<Building> getSelectableBuildings() {
        return selectableBuildings;
    }

    public void setSelectableBuildings(List<Building> selectableBuildings) {
        this.selectableBuildings = selectableBuildings;
    }
}
