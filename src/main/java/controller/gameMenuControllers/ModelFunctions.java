package controller.gameMenuControllers;

import com.fasterxml.jackson.databind.ObjectWriter;
import model.Governance;
import model.Resource;
import model.ResourceEnum;
import model.User;
import model.buildings.BuildingEnum;
import view.enums.GameControllerOut;

import java.util.ArrayList;

public class ModelFunctions {
    public static ResourceEnum[] setInputAndOutput(BuildingEnum type) {
        ResourceEnum[] ans = new ResourceEnum[3];
        switch (type) {
            case MILL:
                ans = new ResourceEnum[]{ResourceEnum.WHEAT, ResourceEnum.FLOUR, ResourceEnum.NULL};
                break;
            case BAKERY:
                ans = new ResourceEnum[]{ResourceEnum.FLOUR, ResourceEnum.BREAD, ResourceEnum.NULL};
                break;
            case BREWER:
                ans = new ResourceEnum[]{ResourceEnum.WHEAT, ResourceEnum.BEER, ResourceEnum.NULL};
                break;
                //todo:this below adds differently
            case STABLE:
                ans = new ResourceEnum[]{ResourceEnum.NULL, ResourceEnum.HORSE, ResourceEnum.NULL};
                break;
            case ARMOURER:
                ans = new ResourceEnum[]{ResourceEnum.IRON, ResourceEnum.ARMOUR, ResourceEnum.NULL};
                break;
            case FLETCHER:
                ans = new ResourceEnum[]{ResourceEnum.WOOD, ResourceEnum.BOW, ResourceEnum.CROSSBOW};
                break;
            case PITCH_RIG:
                ans = new ResourceEnum[]{ResourceEnum.NULL, ResourceEnum.OIL, ResourceEnum.NULL};
                break;
            case BLACKSMITH:
                ans = new ResourceEnum[]{ResourceEnum.IRON, ResourceEnum.SWORD, ResourceEnum.MACE};
                break;
            case DIARY_FARM:
                ans = new ResourceEnum[]{ResourceEnum.NULL, ResourceEnum.CHEESE, ResourceEnum.LEATHER_ARMOR};
                break;
            case WHEAT_FARM:
                ans = new ResourceEnum[]{ResourceEnum.NULL, ResourceEnum.WHEAT, ResourceEnum.NULL};
                break;
            case HOPS_FARMER:
                ans = new ResourceEnum[]{ResourceEnum.NULL, ResourceEnum.HOPS, ResourceEnum.NULL};
                break;
            case PITCH_DITCH:
                ans = new ResourceEnum[]{ResourceEnum.NULL, ResourceEnum.OIL, ResourceEnum.NULL};
                break;
            case POLE_TURNER:
                ans = new ResourceEnum[]{ResourceEnum.WOOD, ResourceEnum.PIKE, ResourceEnum.SPEAR};
                break;
            case WOODCUTTERS:
                ans = new ResourceEnum[]{ResourceEnum.NULL, ResourceEnum.WOOD, ResourceEnum.NULL};
                break;
            case HUNTERS_POST:
                ans = new ResourceEnum[]{ResourceEnum.NULL, ResourceEnum.MEAT, ResourceEnum.NULL};
                break;
            case APPLE_ORCHARD:
                ans = new ResourceEnum[]{ResourceEnum.NULL, ResourceEnum.APPLE, ResourceEnum.NULL};
                break;
        }
        return ans;
    }

    public static void produceWithResources(Governance owner, int rate, ResourceEnum usedResources, ResourceEnum producedResource, ResourceEnum secondProducedOne) {
        if(!checkTheStorage(usedResources,rate,owner)) return;
        produceAndPayThePrice(owner,usedResources,producedResource,rate);
        if(!checkTheStorage(usedResources,rate,owner)) return;
        produceAndPayThePrice(owner,usedResources,secondProducedOne,rate);
    }

    private static void produceAndPayThePrice(Governance owner, ResourceEnum usedResources, ResourceEnum producedResource,int rate) {
        owner.changeResourse(usedResources,-1 * rate);
        owner.changeResourse(producedResource, -1 * rate);
    }

    private static boolean checkTheStorage(ResourceEnum usedResources, int rate, Governance owner) {
        return usedResources.equals(ResourceEnum.NULL) || owner.getResourceAmount(usedResources) >= rate;
    }

    public static void changeOrAddResource(ArrayList<Resource> resources, ResourceEnum type, int amount) {
        for (Resource resource : resources) {
            if(resource.getType().equals(type)) {
                resource.addAsset(amount);
                return;
            }
        }
        resources.add(new Resource(type,amount));
    }
}
