package sk.stuba.fei.uim.oop;

public class Tracker {
    private int[] yAxis = new int[1000];
    private int[] xAxis = new int[1000];
    private int orderNumber=0;
    private boolean[] tracked = new boolean[1000];

    public Tracker() {
    }

    public void setBothAxis(int yLabel, int xLabel) {
        this.yAxis[orderNumber] = yLabel;
        this.xAxis[orderNumber] = xLabel;
        this.tracked[orderNumber]=false;
        orderNumber++;

    }
    public boolean getTracked(int orderNumber){return tracked[orderNumber];}
    public void setTracked(int orderNumber){tracked[orderNumber]=true;}
    public int getyAxis(int orderNumber) {
        return yAxis[orderNumber];
    }

    public int getxAxis(int orderNumber) {
        return xAxis[orderNumber];
    }


    public int getOrderNumber() {
        return orderNumber;
    }

}
