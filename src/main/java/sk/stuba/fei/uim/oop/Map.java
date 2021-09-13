package sk.stuba.fei.uim.oop;

public class Map {
    public int[][] Map = new int[14][14];

    public Map() {
        MapCreator mapCreator = new MapCreator();
        MapCell[][] pole = mapCreator.MapCreate();
        int x=0;
        int y=0;
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                if(pole[i][j].isWalkable()){
                    Map[i][j]=0;
                    x=i;
                    y=j;
                }else{
                    Map[i][j]=1;
                }
            }
       }
        Map[x][y]=5;
    }
    public int getMap(int xInArray, int yInArray){
        return Map[xInArray][yInArray];
    }

}
