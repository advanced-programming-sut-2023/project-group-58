import controller.MapMenuController;
import model.Map;
import model.TileTexture;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

public class MapTests {
    MapMenuController mapMenuController = new MapMenuController();
    @Test
    public void mapSetUp() {
        for(int i = 1; i < 9; i++)
            System.out.println("RANDOM MAP WITH SIZE " + i * 100 + "\n\n"+ mapMenuController.setUpACustom(i * 100));
    }

    @Test
    public void changeTexture() throws IOException {
        mapMenuController.setUpACustom(200);
        System.out.println(mapMenuController.setTextureForTheWholeMap(mapMenuController.selectedMap,"-x 49 -y 49 -t lawn"));
        System.out.println(mapMenuController.showMap("-x 49 -y 49"));
        Assertions.assertEquals(mapMenuController.selectedMap.getTile(149,49).getTexture(), TileTexture.LAWN);
        Assertions.assertEquals(mapMenuController.selectedMap.getTile(151,49).getTexture(), TileTexture.LAWN);
        Assertions.assertEquals(mapMenuController.selectedMap.getTile(49,49).getTexture(), TileTexture.LAWN);
        Assertions.assertEquals(mapMenuController.selectedMap.getTile(150,49).getTexture(), TileTexture.LAWN);


    }
}
