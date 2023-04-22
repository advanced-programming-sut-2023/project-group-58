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
            System.out.println("This is the first map:");
            printMap(map1,100,100);
            System.out.println("The second:");
            printMap(map2,200,200);
            System.out.println("The third:");
            printMap(map3,300,300);
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

    //todo: The method below shows the "whole" map, when showing it to the user. Some changes should be applied
    // to make it display just a part of map.
    // add a guidance table for the colours.
    // reset the colour to "default background" at the end of each line.
    public void printMap(Tile map[][], int length, int width) {
        char tileOccupation;
        for(int i=0; i<length; i++)
            for(int j=0; j<width; j++) {
                tileOccupation = getTileOccupation(map,i,j);
                if(j % 3 == 0) {for(int k = 0; k < 99; k++) System.out.print("-"); System.out.println();}
                if(i % 6 == 0) System.out.print("|");
                switch (map[i][j].getTexture()) {
                    case OIL:
                        System.out.print("\033[100m" + tileOccupation);
                        break;
                    case SEA:
                        System.out.print("\033[44m" + tileOccupation);
                        break;
                    case EARTH:
                        System.out.print("\033[49m" + tileOccupation);
                        break;
                    case FORD:
                        System.out.print("\033[46m" + tileOccupation);
                        break;
                    case IRON:
                        System.out.print("\033[40m" + tileOccupation);
                        break;
                    case SCRUB:
                        System.out.print("\033[102m" + tileOccupation);
                        break;
                    case THICK_SCRUB:
                        System.out.print("\033[42m" + tileOccupation);
                        break;
                    case SMALL_POND:
                        System.out.print("\033[104m" + tileOccupation);
                        break;
                }
            }
    }
}
