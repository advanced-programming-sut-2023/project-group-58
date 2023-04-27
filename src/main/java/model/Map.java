package model;

public class Map {
    Tile[][] selectedMap;
    private int width;
    private int length;
    public Map(int width, int length) {
        this.length = length;
        this.width  = width;
        selectedMap = new Tile[width][length];
        for(int i = 0; i < width; i++)
            for(int j = 0; j < length; j++) {
                selectedMap[i][j] = new Tile();
            }
    }
    public Tile getTile(int y, int x) {
        return this.selectedMap[y][x];
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

}
