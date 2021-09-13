package sk.stuba.fei.uim.oop;

import javax.swing.*;

public class MyButton extends JButton{
    private Player player;
    private Map m;



    public void setPlayer(Player player) {
        this.player = player;

    }

    public void setM(Map m) {
        this.m = m;
    }

    public MyButton(String text) {
        super(text);
        setFocusable(false);

    }

}
