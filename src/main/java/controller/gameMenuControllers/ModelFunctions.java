package controller.gameMenuControllers;

import model.Resource;
import model.User;
import view.enums.GameControllerOut;
import view.enums.ProfisterControllerOut;

public class ModelFunctions {
    public void produce(Resource input, Resource output, User currentPlayer) {
        currentPlayer.getGovernance().getResource().changeWheat(output.getWheat() - input.getWheat());
        currentPlayer.getGovernance().getResource().changeIron(output.getIron() - input.getIron());
        currentPlayer.getGovernance().getResource().changeHop(output.getHop() - input.getHop());
        currentPlayer.getGovernance().getResource().changeFlour(output.getFlour() - input.getFlour());
        currentPlayer.getGovernance().getResource().changeCheese(output.getCheese() - input.getCheese());
        currentPlayer.getGovernance().getResource().changeBread(output.getBread() - input.getBread());
        currentPlayer.getGovernance().getResource().changeApples(output.getApples() - input.getApples());
        currentPlayer.getGovernance().getResource().changeStone(output.getStone() - input.getStone());
        currentPlayer.getGovernance().getResource().changeWood(output.getWood() - input.getWood());
        currentPlayer.getGovernance().getResource().changeAle(output.getAle() - input.getAle());
        currentPlayer.getGovernance().getResource().changePitch(output.getPitch() - input.getPitch());
        currentPlayer.getGovernance().getResource().changeMeat(output.getMeat() - input.getMeat());
    }
}
