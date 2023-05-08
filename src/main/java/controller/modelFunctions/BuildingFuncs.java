package controller.modelFunctions;

import model.ResourceEnum;
import model.User;

public class BuildingFuncs {
    public void hovel(User master) {
        master.getGovernance().changeMaximumPopulation(8);
    }
    public void stable(User master) {
        master.getGovernance().changeResourceAmount(ResourceEnum.HORSE,4);
    }
}
