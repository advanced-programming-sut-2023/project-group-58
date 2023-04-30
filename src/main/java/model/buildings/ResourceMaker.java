package model.buildings;

import controller.gameMenuControllers.ModelFunctions;
import model.Resource;
import model.User;

public class ResourceMaker extends Building{
    Resource input = new Resource();
    Resource output = new Resource();
    public ResourceMaker(BuildingEnum type, User master) {
        super(type, master);
        input = ModelFunctions.makeInput();
        output =ModelFunctions.makeOutput();
    }
    public void produce() {
        new ModelFunctions().produce(input,output,master);
    }
}
