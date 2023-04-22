package controller;

import model.Map;
import model.TileTexture;
import view.ScanMatch;

import java.io.IOException;
import java.util.Random;

public class MapMenuController {
    private final Map map1 = new Map(100,100);
    private final Map map2 = new Map(200,200);
    private final Map map3 = new Map(300,300);

    public Map selectedMap;
    int xTexture  = 0;
    int yTexture  = 0;
    int x2Texture = 0;
    int y2Texture = 0;
    String typeTexture;

    int xShowingMap = 0;
    int yShowingMap = 0;

    public Map getSelectedMap() {
        return selectedMap;
    }

    public Map setUpMap() {
        System.out.println("Would you like to choose a map from archive?\nType y for yes or n for no");
        String answer = ScanMatch.getScanner().nextLine().trim();
        if(answer.equals("y")) {
            setUpDefaultMaps();
            int[] range = new int[4];
            System.out.println("This is the first map:");
            range = setRange(49,49,100,100);
            printMap(map1, range);
            System.out.println("The second:");
            range = setRange(99,99,200,200);
            printMap(map2, range);
            System.out.println("The third:");
            range = setRange(149,149,300,300);
            printMap(map3, range);
            System.out.println("What would it be? Type 1 , 2 or 3");
            //todo: handle errors
            int mapNumber = ScanMatch.getScanner().nextInt();
            if(mapNumber == 1) return map1;
            else if(mapNumber == 2) return map2;
            else if(mapNumber == 3) return map3;
        }
        else if(answer.equals("n")) {
            int length;
            int width;
            //todo: handle errors when taking input.
            System.out.println("Then custom map shall it be!");
            System.out.println("Choose the map scale:");
            for(int i = 1; i < 9; i++)
                System.out.println(i + ". " + i * 100 + " * " + i * 100);
            length = width = ScanMatch.getScanner().nextInt() * 100;
            System.out.println("I give you this map. You can change the map texture any time with this order:\n" +
                               "settexture -x [x] -y [y] -t [type]");
            int[] ranges = setRange(length/2 -1, width/2 - 1, length, width);
            Map randomMap = makeRandomMap(length,width);
            printMap(randomMap, ranges);
            return randomMap;
        }
        else {
            System.out.println("Your answer was not defined. I am tired, so I'll just pick map 1 for you.");
            return map1;
        }
        return null;
    }

    private Map makeRandomMap(int length, int width) {
        Map makingOne = new Map(width,length);
        for(int i = 0; i < width; i++)
            for(int j = 0; j < 35; j++)
                makingOne.getTile(i,j).setTexture(TileTexture.SEA);
        for(int i = 0; i < width; i++)
        {
            makingOne.getTile(i,i+35).setTexture(TileTexture.FORD);
            makingOne.getTile(i,i+36).setTexture(TileTexture.SMALL_POND);
            makingOne.getTile(i,i+37).setTexture(TileTexture.SMALL_POND);
            makingOne.getTile(i,i+38).setTexture(TileTexture.FORD);
        }
        for(int i = width/2; i < width/2 + 2; i++)
            for(int j = 35; j < length; j++)
                makingOne.getTile(i,j).setTexture(TileTexture.SCRUB);
        for(int i = 0; i < 10; i++)
        {
            for(int j = length - 1; j > length - 15; j--)
                makingOne.getTile(i,j).setTexture(TileTexture.IRON);
            for(int j = 0; j < 5; j++)
                makingOne.getTile(i,length - 20 - j).setTexture(TileTexture.THICK_SCRUB);
        }
        for(int i = width - 1; i > width - 20; i--)
            for(int j = 35; j < 45; j++)
                makingOne.getTile(i,j).setTexture(TileTexture.OIL);
        return makingOne;
    }

    //(0,0) is top left.
    private void setUpDefaultMaps() {
        for(int i = 0, j = 0; i <= 100; i++) {
            map1.getTile(i,j).setTexture(TileTexture.SEA);
            j++;
        }
        //maps should be initialized like this.
    }

    public int[] setRange(int x, int y, int length, int width) {
        //showing map range: (5*6) * (5*3)
        //we first see if the coordinates above our point exist:
        //int[0] = min y; int[1] = max y; int[2] = min x; int[3] = max x;
        int[] result = new int[4];
        result[0] = Math.max(y - 7, 0);
        result[1] = Math.min(y + 7, width - 1);
        //assuming: (*14) x (*15)
        result[2] = Math.max(x - 14, 0);
        result[3] = Math.min(x + 15, length - 1);
        return result;
    }

    public void printMap(Map map, int[] ranges) {
        char tileOccupation;
        for(int i = ranges[0]; i <= ranges[1]; i++)
            for(int j = ranges[2]; j <= ranges[3]; j++) {
                tileOccupation = map.getTile(i,j).getTileOccupation();
                if(i % 3 == 0) {System.out.println();for(int k = 0; k < 99; k++) System.out.print("-"); System.out.println();}
                if(j % 6 == 0) System.out.print("\033[49m|");
                switch (map.getTile(i,j).getTexture()) {
                    case OIL:
                        System.out.print("\033[100m" + tileOccupation);
                        break;
                    case SEA:
                        System.out.print("\033[44m" + tileOccupation);
                        break;
                    case EARTH:
                        System.out.print("\033[40m" + tileOccupation);
                        break;
                    case FORD:
                        System.out.print("\033[45m" + tileOccupation);
                        break;
                    case IRON:
                        System.out.print("\033[41m" + tileOccupation);
                        break;
                    case SCRUB:
                        System.out.print("\033[43m" + tileOccupation);
                        break;
                    case THICK_SCRUB:
                        System.out.print("\033[42m" + tileOccupation);
                        break;
                    case SMALL_POND:
                        System.out.print("\033[104m" + tileOccupation);
                        break;
                }
                if(j == ranges[3]) System.out.println("\033[49m");
            }
        //Adding a guidance table:
        System.out.println("-------------Table Info-------------");
        System.out.println("OIL         ----------------    \033[100m    \033[49m");
        System.out.println("SEA         ----------------    \033[44m    \033[49m");
        System.out.println("EARTH       ----------------    \033[40m    \033[49m");
        System.out.println("FORD        ----------------    \033[45m    \033[49m");
        System.out.println("IRON        ----------------    \033[41m    \033[49m");
        System.out.println("SCRUB       ----------------    \033[43m    \033[49m");
        System.out.println("THICK_SCRUB ----------------    \033[42m    \033[49m");
        System.out.println("SMALL_POND  ----------------    \033[104m    \033[49m");
    }

    public String setTextureForTheWholeMap(Map map, String data) {
        try {
            extractDataForTexture(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        yTexture = map.getWidth() - 1 - yTexture;
        y2Texture= map.getWidth() - 1 - y2Texture;
        if(!validateTextureCoordinates(map.getLength(), map.getWidth())) return "Mission failed: invalid coordinates!";
        if(x2Texture != 0) {
            if(map.getTile(yTexture,xTexture).getBuildings().size() != 0) return "Mission failed: You can't change the" +
                                                                         "texture while there is a building on it!";
            map.getTile(yTexture,xTexture).setTexture(convertStringTextureToEnum(typeTexture));
        }
        else {
            for(int i = yTexture; i <= y2Texture; i++)
                for(int j = xTexture; j <= x2Texture; j++) {
                    if(map.getTile(i,j).getBuildings().size() != 0) return "Mission failed: You can't change a tile's" +
                                                                           "texture while there is a building on it!";
                    map.getTile(i,j).setTexture(convertStringTextureToEnum(typeTexture));
                }
        }
        xTexture  = 0;
        x2Texture = 0;
        y2Texture = 0;
        yTexture  = 0;
        return "Texture set successfully!";
    }

    public boolean validateTextureCoordinates(int mapLength, int mapWidth) {
        return xTexture >= 0 && xTexture <= mapWidth - 1 && yTexture >= 0 && yTexture <= mapLength - 1 &&
                x2Texture >= 0 && x2Texture <= mapWidth - 1 && y2Texture >= 0 && y2Texture <= mapLength - 1;
    }

    private TileTexture convertStringTextureToEnum(String typeTexture) {
        switch (typeTexture) {
            case "oil" :
                return TileTexture.OIL;
            case "small pond" :
                return TileTexture.SMALL_POND;
            case "earth" :
                return TileTexture.EARTH;
            case "iron" :
                return TileTexture.IRON;
            case "sea" :
                return TileTexture.SEA;
            case "ford" :
                return TileTexture.FORD;
            case "thick scrub" :
                return TileTexture.THICK_SCRUB;
            case "scrub" :
                return TileTexture.SCRUB;
        }
        return null;
    }

    public void extractDataForTexture(String data) throws IOException {
        //todo: handle errors
        xTexture    = Integer.parseInt(CommonController.dataExtractor(data, "((?<!\\S)-x\\s+(?<wantedPart>(\\d+)(?<!\\s))").trim());
        yTexture    = Integer.parseInt(CommonController.dataExtractor(data, "((?<!\\S)-y\\s+(?<wantedPart>(\\d+)(?<!\\s))").trim());
        typeTexture = CommonController.dataExtractor(data, "((?<!\\S)-t\\s+(?<wantedPart>(.+)(?<!\\s))").trim();
        String x2T = CommonController.dataExtractor(data, "((?<!\\S)-x2\\s+(?<wantedPart>(.+)(?<!\\s))");
        if(x2T.length() != 0) x2Texture = Integer.parseInt(x2T.trim());
        String y2T = CommonController.dataExtractor(data, "((?<!\\S)-y2\\s+(?<wantedPart>(.+)(?<!\\s))");
        if(y2T.length() != 0) y2Texture = Integer.parseInt(y2T.trim());
    }

    public void extractDataxandy(String data) throws IOException {
        //todo: handle errors
        xTexture    = Integer.parseInt(CommonController.dataExtractor(data, "((?<!\\S)-x\\s+(?<wantedPart>(\\d+)(?<!\\s))").trim());
        yTexture    = Integer.parseInt(CommonController.dataExtractor(data, "((?<!\\S)-y\\s+(?<wantedPart>(\\d+)(?<!\\s))").trim());
    }

    public void showMap(String data) {
        try {
            extractDataxandy(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int[] range = setRange(xTexture,yTexture,selectedMap.getLength(), setUpMap().getWidth());
        printMap(selectedMap,range);
        xShowingMap = xTexture;
        yShowingMap = yTexture;
    }

    public void moveMap(String data) {
        String upStr    = CommonController.dataExtractor(data, "((?<!\\S)up\\s+(?<wantedPart>(\\d+)(?<!\\s))").trim();
        String leftStr  = CommonController.dataExtractor(data, "((?<!\\S)left\\s+(?<wantedPart>(\\d+)(?<!\\s))").trim();
        String downStr  = CommonController.dataExtractor(data, "((?<!\\S)down\\s+(?<wantedPart>(\\d+)(?<!\\s))").trim();
        String rightStr = CommonController.dataExtractor(data, "((?<!\\S)right\\s+(?<wantedPart>(\\d+)(?<!\\s))").trim();
        boolean doWeHaveRight = rightStr.length() > 0;
        boolean doWeHaveUp    = upStr.length() > 0;
        int up    = upStr.length() > 0 ? Integer.parseInt(upStr) : 1;
        int left  = leftStr.length() > 0 ? Integer.parseInt(leftStr) : 1;
        int down  = downStr.length() > 0 ? Integer.parseInt(downStr) : 1;
        int right = rightStr.length() > 0 ? Integer.parseInt(rightStr) : 1;
        if(doWeHaveRight) xTexture = xShowingMap + right;
        else xTexture = xShowingMap - left;
        if(doWeHaveUp) yTexture = yShowingMap + up;
        else yTexture = yShowingMap - down;
        if(!validateTextureCoordinates(this.selectedMap.getLength(),this.getSelectedMap().getWidth()))
            System.out.println("Mission failed: invalid coordinates after moving");
        int[] ranges = setRange(xTexture,yTexture,this.selectedMap.getLength(), this.selectedMap.getWidth());
        printMap(this.selectedMap,ranges);
    }


//    public void showDetail(String data) {
//        try {
//            extractDataxandy(data);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("The texture is: " + selectedMap.getTile(yTexture,xTexture));
//        System.out.println();
//        xTexture = 0;
//        yTexture = 0;
//    }
}
