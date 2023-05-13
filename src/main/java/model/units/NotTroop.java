package model.units;

import model.User;

<<<<<<< HEAD
public class NotTroop {
    public NotTroop(User master, UnitEnum type, int count, String primaryLocation) {
       // super(master, type, count, primaryLocation);
=======
public class NotTroop extends Unit{
    public NotTroop(User master, UnitEnum type, int count, int primaryY, int primaryX) {
        super(master,type,count,primaryY,primaryX);

>>>>>>> 2761e61f836ac40ea30214f08de73608cc33a4e4
    }
}
