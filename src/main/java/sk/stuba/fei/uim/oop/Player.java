package sk.stuba.fei.uim.oop;

public class Player {

    private int x;
    private int y;
    private int positionInArrayX;
    private int positionInArrayY;
    private boolean clicked;
    private int numbersOfWin;
    private int mousePotentialXposition;
    private int mousePotentialYPosition;

    public Player() {
        //player
        x=32;
        y=32;
        clicked = false;
        positionInArrayX = 1;
        positionInArrayY = 1;
        numbersOfWin=0;
    }

    public int getMousePotentialXposition() {
        return mousePotentialXposition;
    }

    public void setMousePotentialXposition(int mousePotentialXposition) {
        this.mousePotentialXposition = mousePotentialXposition;
    }

    public int getMouseY() {
        return mousePotentialYPosition;
    }

    public void setMouseYPotentialYposition(int mousePotentialYPosition) {
        this.mousePotentialYPosition = mousePotentialYPosition;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public int getPositionInArrayX() {
        return positionInArrayX;
    }

    public int getPositionInArrayY() {
        return positionInArrayY;
    }

    public int getNumbersOfWin() {
        return numbersOfWin;
    }

    public void setNumbersOfWin(int numbersOfWin) {
        this.numbersOfWin = this.numbersOfWin+numbersOfWin;
    }

    public void move(int xPixels, int yPixels, int xInArray, int yInArray){
        x +=xPixels;
        y +=yPixels;

        positionInArrayX += xInArray;
        positionInArrayY += yInArray;
    }
    public boolean textureCheck(int value){
        {
            if(value==1){
                return false;
            }else{
                return true;
            }
        }
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}