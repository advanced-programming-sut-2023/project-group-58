import controller.MapMenuController;
import model.Map;
import org.junit.Test;

public class MapTests {
    MapMenuController mapMenuController = new MapMenuController();
    @Test
    public void mapSetUp() {
        for(int i = 1; i < 9; i++)
            System.out.println("RANDOM MAP WITH SIZE " + i * 100 + "\n\n"+ mapMenuController.setUpACustom(i * 100));
    }

    @Test
    public void changeTexture() {
        mapMenuController.setUpACustom(200);
        mapMenuController.setTextureForTheWholeMap(mapMenuController.selectedMap,"")
    }
}
