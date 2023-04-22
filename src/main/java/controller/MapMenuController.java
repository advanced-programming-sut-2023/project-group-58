package controller;

import model.Tile;
import model.TileTexture;
import view.ScanMatch;

public class MapMenuController {
    public Tile map1[][] = new Tile[100][100];
    public Tile map2[][] = new Tile[200][200];
    public Tile map3[][] = new Tile[300][300];
    public Tile[][] setUpMap() {
        System.out.println("Would you like to choose a map from archive?\nType y for yes or n for no");
        if(ScanMatch.getScanner().nextLine().trim().equals("y")) {
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
        return null;
    }

    //(0,0) is top left.
    private void setUpDefaultMaps() {
        for(int i = 0, j = 0; i <= 100; i++) {
            map1[i][j].setTexture(TileTexture.SEA);
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

    public void printMap(Tile[][] map, int[] ranges) {
        char tileOccupation;
        for(int i = ranges[0]; i <= ranges[1]; i++)
            for(int j = ranges[2]; j <= ranges[3]; j++) {
                tileOccupation = map[i][j].getTileOccupation();
                if(i % 3 == 0) {System.out.println();for(int k = 0; k < 99; k++) System.out.print("-"); System.out.println();}
                if(j % 6 == 0) System.out.print("\033[49m|");
                switch (map[i][j].getTexture()) {
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
}
