package model.buildings;

import controller.gameMenuControllers.ModelFunctions;
import model.Governance;
import model.ResourceEnum;
import model.User;

public class ResourceMaker extends Building{
    private final ResourceEnum usedResources;
    private final ResourceEnum producedResource;
    private final ResourceEnum secondProducedOne;
    public ResourceMaker(BuildingEnum type, User owner, int direction) {
        super(type, owner, direction);
        ResourceEnum[] initializing = new ResourceEnum[3];
        initializing = ModelFunctions.setInputAndOutput(type);
        this.usedResources = initializing[0];
        this.producedResource = initializing[1];
        this.secondProducedOne = initializing[2];
    }
    public void produceAfterEachTurn() {
        ModelFunctions.produceWithResources(owner.getGovernance(),type.getRate(),usedResources,producedResource,secondProducedOne);
    }
}
