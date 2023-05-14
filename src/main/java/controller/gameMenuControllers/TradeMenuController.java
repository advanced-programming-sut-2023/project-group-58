package controller.gameMenuControllers;

import controller.CommonController;
import model.Governance;
import model.ResourceEnum;
import model.User;
import model.TradeItem;
import view.enums.ShopAndTradeControllerOut;

import java.util.ArrayList;

public class TradeMenuController {
    private static ArrayList<TradeItem> trades;
    private TradeItem newRequset;
    private User currentUser;

    public TradeMenuController(User currentUser) {
        this.currentUser = currentUser;
    }

    public ShopAndTradeControllerOut newTradeRequest(String data) {
        if (CommonController.dataExtractor(data, "((?<!\\S)-t\\s+(?<wantedPart>([^-]+))(?<!\\s))").length() == 0 ||
                CommonController.dataExtractor(data, "((?<!\\S)-a\\s+(?<wantedPart>(\\d+))(?<!\\s))").length() == 0 ||
                CommonController.dataExtractor(data, "((?<!\\S)-p\\s+(?<wantedPart>(\\d+))(?<!\\s))").length() == 0 ||
                CommonController.dataExtractor(data, "((?<!\\S)-m\\s+(?<wantedPart>([^-]+))(?<!\\s))").length() == 0)
            return ShopAndTradeControllerOut.INVALID_INPUT_FORMAT;
        if (CommonController.dataExtractor(data, "((?<!\\S)-t\\s+(?<wantedPart>([^-]+))(?<!\\s))").trim().length() == 0 ||
                CommonController.dataExtractor(data, "((?<!\\S)-a\\s+(?<wantedPart>(\\d+))(?<!\\s))").trim().length() == 0 ||
                CommonController.dataExtractor(data, "((?<!\\S)-p\\s+(?<wantedPart>(\\d+))(?<!\\s))").trim().length() == 0 ||
                CommonController.dataExtractor(data, "((?<!\\S)-m\\s+(?<wantedPart>([^-]+))(?<!\\s))").trim().length() == 0)
            return ShopAndTradeControllerOut.INVALID_INPUT_FORMAT;

        String item = CommonController.dataExtractor(data, "((?<!\\S)-t\\s+(?<wantedPart>([^-]+))(?<!\\s))").trim();
        int amount = Integer.parseInt(CommonController.dataExtractor(data, "((?<!\\S)-a\\s+(?<wantedPart>(\\d+))(?<!\\s))").trim());
        int price = Integer.parseInt(CommonController.dataExtractor(data, "((?<!\\S)-p\\s+(?<wantedPart>(\\d+))(?<!\\s))").trim());
        String message = CommonController.dataExtractor(data, "((?<!\\S)-m\\s+(?<wantedPart>([^-]+))(?<!\\s))").trim();
        ResourceEnum type = CommonController.resourceFinder(item);
        if (type.equals(ResourceEnum.NULL))
            return ShopAndTradeControllerOut.INVALID_RESOURCE_NAME;
        if (currentUser.getGovernance().getGold() < price)
            return ShopAndTradeControllerOut.CANNOT_AFFORD_TRADE;
        String idMaker = currentUser.getUsername() + currentUser.getGovernance().getUserTrades().size();
        this.newRequset = new TradeItem(idMaker, currentUser, type, amount, price, message, true);
        currentUser.getGovernance().addToUserTrades(newRequset);
        Governance.addToAllTrades(newRequset);
        return ShopAndTradeControllerOut.REQUEST_ADDED;
    }

    public String showTradeList() {
        String ans = new String();
        int counter = 1;
        if (Governance.getAllTrades().size() != 0)
            for (TradeItem trade : Governance.getAllTrades()) {
                if (!trade.getOneWhoRequests().getUsername().equals(currentUser.getUsername()) && trade.getActive())
                    ans += counter++ + ")\n     Id: " + trade.getId() + "\n     Type: " + trade.getTypeName() + "\n     Amount: " + trade.getAmount() +
                            "\n     Price : " + trade.getPrice() + "\n     Message: " + trade.getMessage() + "\n" + "     Who's asking? " + trade.getOneWhoRequests().getUsername() + "\n";
            }
        else ans += "No trade requests here yet!\n";
        if (ans.trim().length() == 0)
            ans = "All the trades have been requested by you. Wait and see if anyone answers your call\n";
        return ans;
    }

    public String showTradeHistory() {
        String ansRequested = new String();
        String ansAnswered = new String();
        int counterReq = 1;
        int counterAns = 1;
        if (currentUser.getGovernance().getUserTrades().size() != 0)
            for (TradeItem trade : currentUser.getGovernance().getUserTrades()) {
                if (trade.getOneWhoRequests().getUsername().equals(currentUser.getUsername())) {
                    if (counterReq == 1) ansRequested += "Requested items:\n";
                    ansRequested += counterReq++ + ")\n     Id: " + trade.getId() + "\n     Type: " + trade.getTypeName() + "\n     Amount: " + trade.getAmount() +
                            "\n     Price : " + trade.getPrice() + "\n     Message: " + trade.getMessage() + "\n" +
                            "     Is it still active? " + trade.getActive() + "\n";
                } else {
                    if (counterAns == 1) ansAnswered += "Traded items:\n";
                    ansAnswered += counterAns++ + ")\n     Id: " + trade.getId() + "\n     Type: " + trade.getTypeName() + "\n     Amount: " + trade.getAmount() +
                            "\n     Price : " + trade.getPrice() + "\n     Message: " + trade.getMessage() + "\n";
                }
            }
        else
            ansRequested = "You haven't traded with anyone yet!\n";
        return ansRequested + ansAnswered;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public ShopAndTradeControllerOut doTheTrade(String data) {
        if (CommonController.dataExtractor(data, "((?<!\\S)-i\\s+(?<wantedPart>([^-]+))(?<!\\s))").length() == 0 ||
                CommonController.dataExtractor(data, "((?<!\\S)-m\\s+(?<wantedPart>([^-]+))(?<!\\s))").length() == 0)
            return ShopAndTradeControllerOut.INVALID_INPUT_FORMAT;
        if (CommonController.dataExtractor(data, "((?<!\\S)-i\\s+(?<wantedPart>([^-]+))(?<!\\s))").trim().length() == 0 ||
                CommonController.dataExtractor(data, "((?<!\\S)-m\\s+(?<wantedPart>([^-]+))(?<!\\s))").trim().length() == 0)
            return ShopAndTradeControllerOut.INVALID_INPUT_FORMAT;

        String id = CommonController.dataExtractor(data, "((?<!\\S)-i\\s+(?<wantedPart>([^-]+))(?<!\\s))").trim();
        String message = CommonController.dataExtractor(data, "((?<!\\S)-m\\s+(?<wantedPart>([^-]+))(?<!\\s))").trim();
        TradeItem trade = findTradeById(id);
        if (trade == null)
            return ShopAndTradeControllerOut.TRADE_NOT_FOUND;
        if (currentUser.getUsername().equals(trade.getOneWhoRequests().getUsername()))
            return ShopAndTradeControllerOut.SELF_TRADE;
        if (currentUser.getGovernance().getResourceAmount(trade.getType()) < trade.getAmount())
            return ShopAndTradeControllerOut.NOT_ENOUGH_COMMODITY;
        currentUser.getGovernance().changeGold(trade.getPrice());
        trade.getOneWhoRequests().getGovernance().changeGold(-1 * trade.getPrice());
        currentUser.getGovernance().changeResourceAmount(trade.getType(), -1 * trade.getAmount());
        trade.getOneWhoRequests().getGovernance().changeResourceAmount(trade.getType(), trade.getAmount());
        trade.setOneWhoAnswersTheCall(currentUser);
        trade.setActive(false);
        trade.setMessage(message);
        updateTradeStatus(trade);
        return ShopAndTradeControllerOut.SUCCESS_FOR_TRADE;
    }

    private void updateTradeStatus(TradeItem trade) {
        ArrayList<TradeItem> updated = new ArrayList<>();
        for (TradeItem userTrade : trade.getOneWhoRequests().getGovernance().getUserTrades()) {
            if (!userTrade.getId().equals(trade.getId()))
                updated.add(userTrade);
        }
        updated.add(trade);
        trade.getOneWhoRequests().getGovernance().setUserTrades(updated);
        updated = new ArrayList<>();
        for (TradeItem userTrade : trade.getOneWhoAnswersTheCall().getGovernance().getUserTrades()) {
            if (!userTrade.getId().equals(trade.getId()))
                updated.add(userTrade);
        }
        updated.add(trade);
        trade.getOneWhoAnswersTheCall().getGovernance().setUserTrades(updated);
        updated = new ArrayList<>();
        for (TradeItem allTrade : Governance.getAllTrades()) {
            if (!allTrade.getId().equals(trade.getId()))
                updated.add(allTrade);
        }
        updated.add(trade);
        Governance.setAllTrades(updated);
    }

    private TradeItem findTradeById(String id) {
        for (TradeItem deal : Governance.getAllTrades()) {
            if (deal.getId().equals(id))
                return deal;
        }
        return null;
    }

    public String popup() {
        String result = new String();
        String ans = new String();
        for (TradeItem trade : currentUser.getGovernance().getUserTrades()) {
            if (!trade.getNotified() && !trade.getActive() && trade.getOneWhoRequests().getUsername().equals(currentUser.getUsername())) {
                if (trade.getPrice() != 0)
                    ans += trade.getOneWhoAnswersTheCall().getNickname() + " (" + trade.getOneWhoAnswersTheCall().getUsername()
                            + ") has accepted your request " + " (" + trade.getTypeName() + " for " + trade.getPrice() +
                            " golds, id = " + trade.getId() + "), and left you this message: " +
                            trade.getMessage() + "\n";
                else
                    ans += trade.getOneWhoAnswersTheCall().getNickname() + " ( " + trade.getOneWhoAnswersTheCall().getUsername()
                            + " ) has donated " + trade.getAmount() + " unit(s) of " + trade.getTypeName() + " to your empire, and left you this message: "
                            + trade.getMessage() + "\n";
                trade.setNotified(true);
            }
        }
        if (ans.length() == 0 || ans.trim().length() == 0)
            result = "Welcome! Not much has happened since your last visit\n";
        else
            result = "Welcome! here's a brief report of the trade market while you were gone:\n" + ans;
        return result;
    }
}
