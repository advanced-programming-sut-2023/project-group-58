package model;

import java.util.ArrayList;

public class Governance {
    private static ArrayList<User> empires = new ArrayList<>();
    public void makeGovernanceNew(){
        empires.clear();
    }

    public static ArrayList<User> getEmpires() {
        return empires;
    }

    public static void setEmpires(ArrayList<User> empires) {
        Governance.empires = empires;
    }
}
