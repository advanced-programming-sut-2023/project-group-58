package view.enums;

import model.Tree;

public enum TreeTypes {
    DESERT_SHRUB("desert shrub"),
    CHERRY_PALM("cherry palm"),
    OLIVE_TREE("olive tree"),
    COCONUT_PALM("coconut palm"),
    DATE_PALM("date palm");
    private final String name;

    TreeTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
