package sk.stuba.fei.uim.oop;

import java.util.Random;

public class MapCreator {

    public MapCell[][] MapCreate() {
        int[][] mapOfNumbers = new int[14][14];
        MapCell[][] mapOfCells = new MapCell[14][14];
        Tracker tracker = new Tracker();
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                if(i==0||i==13||j==0||j==13){
                    mapOfNumbers[i][j]=2;
                    mapOfCells[i][j]=new MapCell(false,true);

                }else{
                    mapOfNumbers[i][j]=1;
                    mapOfCells[i][j]=new MapCell(false,false);
                }
                mapOfCells[i][j].setDimensionX(i);
                mapOfCells[i][j].setDimensionY(j);
            }
        }
        Random rand = new Random();
        int x=1;
        int y=1;
        mapOfCells[y][x].setWalkable(true);
        mapOfCells[y][x].setAlreadyUsed(true);
        mapOfNumbers[y][x]=0;
        int trackerY;
        int trackerX;
        tracker.setBothAxis(y,x);
        while(!tracker.getTracked(2)){

            switch (rand.nextInt(4)) {
                case 0:
                    if(mapOfNumbers[y][x-1]!=2&&mapOfNumbers[y][x-1]!=0){//MoveLeft
                        if(isNextTwoBoard(mapOfNumbers,y,x-1,0,-1)){
                            mapOfNumbers[y][x-1]=2;
                            mapOfCells[y][x-1].setAlreadyUsed(true);
                            mapOfCells[y][x-1].setWalkable(false);
                            break;
                        }
                        if (checkNeighbourForPath(mapOfNumbers, y , x-1)) {
                            mapOfNumbers[y][x-1]=2;
                            mapOfCells[y][x-1].setAlreadyUsed(true);
                            mapOfCells[y][x-1].setWalkable(false);
                            break;
                        }
                        x--;
                        tracker.setBothAxis(y,x);
                        mapOfCells[y][x].setWalkable(true);
                        mapOfCells[y][x].setAlreadyUsed(true);
                        mapOfNumbers[y][x]=0;
                        createWallUnderOrAboveOrBoth(mapOfNumbers, mapOfCells, rand, x, y);
                    }

                    break;
                case 1:
                    if(mapOfNumbers[y][x+1]!=2&&mapOfNumbers[y][x+1]!=0){//MoveRight
                        if(isNextTwoBoard(mapOfNumbers,y,x+1,0,+1)){
                            mapOfNumbers[y][x+1]=2;
                            mapOfCells[y][x+1].setAlreadyUsed(true);
                            mapOfCells[y][x+1].setWalkable(false);
                            break;
                        }
                        if (checkNeighbourForPath(mapOfNumbers, y , x+1)) {
                            mapOfNumbers[y][x+1]=2;
                            mapOfCells[y][x+1].setAlreadyUsed(true);
                            mapOfCells[y][x+1].setWalkable(false);
                            break;
                        }
                        x++;
                        tracker.setBothAxis(y,x);
                        mapOfCells[y][x].setWalkable(true);
                        mapOfCells[y][x].setAlreadyUsed(true);
                        mapOfNumbers[y][x]=0;
                        createWallUnderOrAboveOrBoth(mapOfNumbers, mapOfCells, rand, x, y);
                    }
                    break;
                case 2:
                    if(mapOfNumbers[y+1][x]!=2&&mapOfNumbers[y+1][x]!=0){//MoveDown
                        if(isNextTwoBoard(mapOfNumbers,y+1,0,1,0)){
                            mapOfNumbers[y+1][x]=2;
                            mapOfCells[y+1][x].setAlreadyUsed(true);
                            mapOfCells[y+1][x].setWalkable(false);
                            break;
                        }
                        if (checkNeighbourForPath(mapOfNumbers, y+1 , x)) {
                            mapOfNumbers[y+1][x]=2;
                            mapOfCells[y+1][x].setAlreadyUsed(true);
                            mapOfCells[y+1][x].setWalkable(false);
                            break;
                        }
                        y++;
                        tracker.setBothAxis(y,x);
                        mapOfCells[y][x].setWalkable(true);
                        mapOfCells[y][x].setAlreadyUsed(true);
                        mapOfNumbers[y][x]=0;
                        createWallOnLeftOrRightOrBoth(mapOfNumbers, mapOfCells, rand, x, y);
                    }

                    break;
                case 3:
                    if(mapOfNumbers[y-1][x]!=2&&mapOfNumbers[y-1][x]!=0){
                        if(isNextTwoBoard(mapOfNumbers,y-1,0,-1,0)){
                            mapOfNumbers[y-1][x]=2;
                            mapOfCells[y-1][x].setAlreadyUsed(true);
                            mapOfCells[y-1][x].setWalkable(false);
                            break;
                        }
                        if (checkNeighbourForPath(mapOfNumbers, y-1 , x)) {
                            mapOfNumbers[y-1][x]=2;
                            mapOfCells[y-1][x].setAlreadyUsed(true);
                            mapOfCells[y-1][x].setWalkable(false);
                            break;
                        }
                        y--;
                        tracker.setBothAxis(y,x);
                        mapOfCells[y][x].setWalkable(true);
                        mapOfCells[y][x].setAlreadyUsed(true);
                        mapOfNumbers[y][x]=0;
                        createWallOnLeftOrRightOrBoth(mapOfNumbers, mapOfCells, rand, x, y);
                    }

                    break;
            }

            if (!canIMoveAnyDirection(y, x, mapOfNumbers)) {
                for(int i=(tracker.getOrderNumber()-1);i>1;i--){
                    if(tracker.getTracked(i)){
                        continue;
                    }
                    trackerY=tracker.getyAxis(i);
                    trackerX=tracker.getxAxis(i);
                    tracker.setTracked(i);
                    if(canIMoveAnyDirection(trackerY,trackerX,mapOfNumbers)){
                        y=trackerY;
                        x=trackerX;
                        break;
                    }

                }

            }
        }
        return mapOfCells;
    }

    private void createWallOnLeftOrRightOrBoth(int[][] mapOfNumbers, MapCell[][] mapOfCells, Random rand, int x, int y) {
        switch (rand.nextInt(3)){
            case 0:{
                if(!mapOfCells[y][x+1].isAlreadyUsed()){
                    mapOfCells[y][x+1].setWalkable(false);
                    mapOfCells[y][x+1].setAlreadyUsed(true);
                    mapOfNumbers[y][x+1]=2;
                }
                if(!mapOfCells[y][x-1].isAlreadyUsed()){
                    mapOfCells[y][x-1].setWalkable(false);
                    mapOfCells[y][x-1].setAlreadyUsed(true);
                    mapOfNumbers[y][x-1]=2;
                }

            }break;
            case 1:{
                if(!mapOfCells[y][x+1].isAlreadyUsed()){
                    mapOfCells[y][x+1].setWalkable(false);
                    mapOfCells[y][x+1].setAlreadyUsed(true);
                    mapOfNumbers[y][x+1]=2;
                }

            }break;
            case 2:{
                if(!mapOfCells[y][x-1].isAlreadyUsed()){
                    mapOfCells[y][x-1].setWalkable(false);
                    mapOfCells[y][x-1].setAlreadyUsed(true);
                    mapOfNumbers[y][x-1]=2;
                }

            }break;
        }
    }

    private void createWallUnderOrAboveOrBoth(int[][] mapOfNumbers, MapCell[][] mapOfCells, Random rand, int x, int y) {
        switch(rand.nextInt(3)){
            case 0:{
                if(!mapOfCells[y+1][x].isAlreadyUsed()){
                    mapOfCells[y+1][x].setAlreadyUsed(true);
                    mapOfCells[y+1][x].setWalkable(false);
                    mapOfNumbers[y+1][x]=2;
                }
                if(!mapOfCells[y-1][x].isAlreadyUsed()){
                    mapOfCells[y-1][x].setAlreadyUsed(true);
                    mapOfCells[y-1][x].setWalkable(false);
                    mapOfNumbers[y-1][x]=2;
                }

                break;}

            case 1:
                if(!mapOfCells[y+1][x].isAlreadyUsed()){
                    mapOfCells[y+1][x].setAlreadyUsed(true);
                    mapOfCells[y+1][x].setWalkable(false);
                    mapOfNumbers[y+1][x]=2;
                }
                break;
            case 2:
                if(!mapOfCells[y-1][x].isAlreadyUsed()){
                    mapOfCells[y-1][x].setAlreadyUsed(true);
                    mapOfCells[y-1][x].setWalkable(false);
                    mapOfNumbers[y-1][x]=2;
                }
                break;
        }
    }


    public boolean isNextTwoBoard(int[][] map,int y,int x,int newY,int newX){
        return map[y + newY][x + newX] == 0;
    }

    public boolean checkNeighbourForPath(int[][] map,int y,int x){
        int counter=0;
        if(map[y+1][x]==0){
            counter++;
        }
        if(map[y-1][x]==0){
            counter++;
        }
        if(map[y][x+1]==0){
            counter++;
        }
        if(map[y][x-1]==0){
            counter++;
        }
        return counter >= 2;
    }

    public boolean canIMoveAnyDirection(int y, int x, int[][] map){
        if(map[y+1][x]==1&&!isNextTwoBoard(map,y+1,x,1,0)){
            return true;
        }
        if(map[y-1][x]==1&&!isNextTwoBoard(map,y-1,x,-1,0)){
            return true;
        }
        if(map[y][x+1]==1&&!isNextTwoBoard(map,y,x+1,0,1)){
            return true;
        }
        return map[y][x - 1] == 1 && !isNextTwoBoard(map, y, x - 1, 0, -1);
    }

}
