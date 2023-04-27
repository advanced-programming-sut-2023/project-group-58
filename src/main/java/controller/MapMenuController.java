package controller;

import model.Map;
import model.TileTexture;
import model.Tree;
import view.enums.ProfisterControllerOut;
import view.enums.TreeTypes;

import java.io.IOException;

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

//    public Map setUpMap() {
//        System.out.println("Would you like to choose a map from archive?\nType y for yes or n for no");
//        String answer = ScanMatch.getScanner().nextLine().trim();
//        if(answer.equals("y")) {
//            setUpDefaultMaps();
//            int[] range = new int[4];
//            System.out.println("This is the first map:");
//            range = setRange(49,49,100,100);
//            printMap(map1, range);
//            System.out.println("The second:");
//            range = setRange(99,99,200,200);
//            printMap(map2, range);
//            System.out.println("The third:");
//            range = setRange(149,149,300,300);
//            printMap(map3, range);
//            System.out.println("What would it be? Type 1 , 2 or 3");
//            //todo: handle errors
//            int mapNumber = ScanMatch.getScanner().nextInt();
//            if(mapNumber == 1) return map1;
//            else if(mapNumber == 2) return map2;
//            else if(mapNumber == 3) return map3;
//        }
//        else if(answer.equals("n")) {
//            int length;
//            int width;
//            //todo: handle errors when taking input.
//            System.out.println("Then custom map shall it be!");
//            System.out.println("Choose the map scale:");
//            for(int i = 1; i < 9; i++)
//                System.out.println(i + ". " + i * 100 + " * " + i * 100);
//
//
//
//            length = width = ScanMatch.getScanner().nextInt() * 100;
//            System.out.println("I give you this map. You can change the map texture any time with this order:\n" +
//                               "settexture -x [x] -y [y] -t [type]");
//            int[] ranges = setRange(length/2 -1, width/2 - 1, length, width);
//            Map randomMap = makeRandomMap(length,width);
//            printMap(randomMap, ranges);
//            return randomMap;
//        }
//        else {
//            System.out.println("Your answer was not defined. I am tired, so I'll just pick map 1 for you.");
//            return map1;
//        }
//        return null;
//    }


    public String setUpACustom(int widthAndLength) {
        int[] ranges = setRange(widthAndLength/2 -1, widthAndLength/2 - 1, widthAndLength, widthAndLength);
        Map randomMap = makeRandomMap(widthAndLength,widthAndLength);
        this.selectedMap = randomMap;
        return printMap(randomMap, ranges);
    }

    public String setUpATemplate() {
        setUpDefaultMaps();
        this.selectedMap = map1;
        String ans = "I give you a 200*200 map. You can change the map texture any time with this command:\n" +
                     "settexture -x [x] -y [y] -t [type])";
        int[] range = setRange(99,99,200,200);
        ans += printMap(map1, range) + "\n";
        return ans;
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
        //Designing map template number 1:
       boolean[][] mark1 = new boolean[100][100];
       int pickLand;
       for(int i = 0; i < 15; i++) {
           for (int j = 0; j < 15; j++)
           {
               map1.getTile(i, j).setTexture(TileTexture.SEA);
               mark1[i][j] = true;
           }
           for(int j = 85; j < 100; j++)
           {
               map1.getTile(i, j).setTexture(TileTexture.SEA);
               mark1[i][j] = true;
           }
       }
       for(int i = 85; i < 100; i++) {
           for (int j = 0; j < 15; j++)
           {
               map1.getTile(i, j).setTexture(TileTexture.SMALL_POND);
               mark1[i][j] = true;
           }
           for(int j = 85; j < 100; j++)
           {
               map1.getTile(i, j).setTexture(TileTexture.FORD);
               mark1[i][j] = true;
           }
       }
       for(int i = 15; i < 35; i++) {
           for(int j = 20; j < 24; j++)
           {
               map1.getTile(i , j).setTexture(TileTexture.IRON);
               mark1[i][j] = true;
           }
           if(i > 31)
               for(int j = 24; j < 30; j++)
               {
                   map1.getTile(i , j).setTexture(TileTexture.OIL);
                   mark1[i][j] = true;
               }
       }
       for(int i = 0; i < 100; i++)
           for(int j = 0; j < 100; j++)
                if(!mark1[i][j])
                   {
                       pickLand = (int) (3 * Math.random());
                       if(pickLand == 2) map1.getTile(i,j).setTexture(TileTexture.SCRUB);
                       if(pickLand == 1) map1.getTile(i,j).setTexture(TileTexture.THICK_SCRUB);
                       if(pickLand == 0) map1.getTile(i,j).setTexture(TileTexture.EARTH);
                   }
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

    public static String printMap(Map map, int[] ranges) {
        String ans = new String();
        char tileOccupation;
        int xcounterForBreak = 0;
        int ycounterForBreak = 0;
        for(int i = ranges[0]; i <= ranges[1]; i++) {
            if(ycounterForBreak % 3 == 0) {for(int k = 0; k < (ranges[3]-ranges[2]+1)*7/6+2; k++) ans += "-"; ans += "\n";}
            ycounterForBreak++;
            for (int j = ranges[2]; j <= ranges[3]; j++) {
                tileOccupation = map.getTile(i, j).getTileOccupation();
                if (xcounterForBreak % 6 == 0) ans += "\033[49m|";
                xcounterForBreak++;
                switch (map.getTile(i, j).getTexture()) {
                    case OIL:
                        ans += "\033[100m" + tileOccupation;
                        break;
                    case SEA:
                        ans += "\033[44m" + tileOccupation;
                        break;
                    case EARTH:
                        ans += "\033[40m" + tileOccupation;
                        break;
                    case FORD:
                        ans += "\033[45m" + tileOccupation;
                        break;
                    case IRON:
                        ans += "\033[41m" + tileOccupation;
                        break;
                    case SCRUB:
                        ans += "\033[43m" + tileOccupation;
                        break;
                    case THICK_SCRUB:
                        ans += "\033[42m" + tileOccupation;
                        break;
                    case SMALL_POND:
                        ans += "\033[104m" + tileOccupation;
                        break;
                }
                if (j == ranges[3])
                {
                    ans += "\033[49m|"+"\n";
                    xcounterForBreak = 0;
                }
            }
        }
        //Adding a guidance table:
        ans += "-------------Table Info-------------"+"\n";
        ans += "OIL         ----------------    \033[100m    \033[49m"+"\n";
        ans += "SEA         ----------------    \033[44m    \033[49m"+"\n";
        ans += "EARTH       ----------------    \033[40m    \033[49m"+"\n";
        ans += "FORD        ----------------    \033[45m    \033[49m"+"\n";
        ans += "IRON        ----------------    \033[41m    \033[49m"+"\n";
        ans += "SCRUB       ----------------    \033[43m    \033[49m"+"\n";
        ans += "THICK_SCRUB ----------------    \033[42m    \033[49m"+"\n";
        ans += "SMALL_POND  ----------------    \033[104m    \033[49m"+"\n";
        return ans;
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

    public String showMap(String data) {
        String ans = new String();
        try {
            extractDataxandy(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        yTexture = selectedMap.getWidth() - 1 - yTexture;
        int[] range = setRange(xTexture,yTexture,selectedMap.getLength(), selectedMap.getWidth());
        xShowingMap = xTexture;
        yShowingMap = yTexture;
        return printMap(selectedMap,range);
    }

    public String moveMap(String data) {
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
            return ProfisterControllerOut.INVALID_NEW_COORDINATES.getContent();
        int[] ranges = setRange(xTexture,yTexture,this.selectedMap.getLength(), this.selectedMap.getWidth());
        return printMap(this.selectedMap,ranges);
    }

    public String clearTile(String data) {
        try {
            extractDataxandy(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(!validateTextureCoordinates(this.selectedMap.getLength(),this.selectedMap.getWidth())) return "failed: invalid coordinates";
        this.selectedMap.getTile(yTexture,xTexture).clear();
        return "Tile cleared successfully!";
    }

    public String dropTree(String data) throws IOException {
        extractDataForTexture(data);
        if(!validateTextureCoordinates(selectedMap.getLength(),selectedMap.getWidth()))
            return ProfisterControllerOut.FAILED.getContent();
        switch (typeTexture.trim()) {
            case "desert shrub":
                selectedMap.getTile(yTexture,xTexture).getTrees().add(new Tree(TreeTypes.DESERT_SHRUB));
                break;
            case "cherry palm":
                selectedMap.getTile(yTexture,xTexture).getTrees().add(new Tree(TreeTypes.CHERRY));
                break;
            case "olive tree":
                selectedMap.getTile(yTexture,xTexture).getTrees().add(new Tree(TreeTypes.OLIVE));
                break;
            case "coconut palm":
                selectedMap.getTile(yTexture,xTexture).getTrees().add(new Tree(TreeTypes.COCONUT));
                break;
            case "date palm":
                selectedMap.getTile(yTexture,xTexture).getTrees().add(new Tree(TreeTypes.DATE));
                break;
        }
        return "Tree added successfully!";
    }

    public String dropRock(String data) throws IOException {
        extractDataForTexture(data);
        if(typeTexture != null)typeTexture = typeTexture.trim();
        if(!validateTextureCoordinates(selectedMap.getLength(),selectedMap.getWidth()))
            return ProfisterControllerOut.FAILED.getContent();
        selectedMap.getTile(yTexture,xTexture).setHasRock(true);

        if(typeTexture.equals("r")) {
            int ranadom = (int) (4 * Math.random());
            if(ranadom == 0)
                typeTexture = "n";
            if(ranadom == 1)
                typeTexture = "e";
            if(ranadom == 2)
                typeTexture = "w";
            if(ranadom == 3)
                typeTexture = "s";
        }
        if(typeTexture.equals("n") || typeTexture.equals("e") || typeTexture.equals("w") || typeTexture.equals("s"))
            selectedMap.getTile(yTexture,xTexture).setRockDirection(typeTexture);
        else
            return ProfisterControllerOut.INVALID_INPUT_FORMAT.getContent();
        return "Rock added successfully!";
    }


//    public void showDetail(String data) {
//        try {
//            extractDataxandy(data);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("The texture is: " + selectedMap.getTile(yTexture,xTexture));
//        xTexture = 0;
//        yTexture = 0;
//    }
}
