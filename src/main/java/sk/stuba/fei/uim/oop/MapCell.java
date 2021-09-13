package sk.stuba.fei.uim.oop;

public class MapCell {
    private boolean walkable;
    private  boolean alreadyUsed;
    private int dimensionX;
    private int dimensionY;

    public void setDimensionX(int dimensionX) {
        this.dimensionX = dimensionX;
    }


    public void setDimensionY(int dimensionY) {
        this.dimensionY = dimensionY;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    public boolean isAlreadyUsed() {
        return alreadyUsed;
    }

    public void setAlreadyUsed(boolean alreadyUsed) {
        this.alreadyUsed = alreadyUsed;
    }

    public MapCell(boolean walkable, boolean alreadyUsed) {
        this.walkable = walkable;
        this.alreadyUsed = alreadyUsed;
    }

}
