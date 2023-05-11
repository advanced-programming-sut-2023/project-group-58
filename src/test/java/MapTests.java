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
        Assertions.assertEquals(mapMenuController.setTextureForTheWholeMap(mapMenuController.selectedMap,"-x 49 -y 49 -t lawn"),
                "Texture set successfully!");
        Assertions.assertEquals(mapMenuController.selectedMap.getTile(150,49).getTexture(), TileTexture.LAWN);
        Assertions.assertEquals(mapMenuController.setTextureForTheWholeMap(mapMenuController.selectedMap,"-x 49 -y 49 -t earth"),
                "Texture set successfully!");
        Assertions.assertEquals(mapMenuController.selectedMap.getTile(150,49).getTexture(), TileTexture.EARTH);
        Assertions.assertEquals(mapMenuController.setTextureForTheWholeMap(mapMenuController.selectedMap,"-y 49 -x 52 -t river"),
                "Texture set successfully!");
        Assertions.assertEquals(mapMenuController.selectedMap.getTile(150,52).getTexture(), TileTexture.RIVER);
        Assertions.assertEquals(mapMenuController.setTextureForTheWholeMap(mapMenuController.selectedMap,"-t iron -y 49 -x 52"),
                "Texture set successfully!");
        Assertions.assertEquals(mapMenuController.selectedMap.getTile(150,52).getTexture(), TileTexture.IRON);
        

    }
}
