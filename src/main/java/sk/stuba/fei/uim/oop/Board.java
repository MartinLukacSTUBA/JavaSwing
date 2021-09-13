package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Board extends JPanel implements ActionListener,MouseListener,MouseMotionListener, KeyListener{

    private  Map m;
    private  Player p;
    private int winX;
    private int winY;
    private final MyButton up;
    private final MyButton left;
    private final MyButton right;
    private final MyButton down;
    private final MyFrame myFrame;
    private final Label counter;


    public Board(MyButton up,MyButton down,MyButton left, MyButton right,MyFrame myframe,Label counter){
        this.counter=counter;
        this.myFrame=myframe;

        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;

        Random random = new Random();

        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);

        this.setSize(462, 448);
        this.setBackground(new Color(r,g,b));

        addMapToButtons();
        addPlayerToButtons();
        addListeners();
        setFocusable(true);

    }

    protected void addListeners() {
        addMouseListener(this);
        addMouseMotionListener(this);
        this.addKeyListener(this);

        up.addActionListener(this);
        down.addActionListener(this);
        left.addActionListener(this);
        right.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==up){
            if(p.textureCheck(m.getMap(p.getPositionInArrayX(),p.getPositionInArrayY()-1))) {
                p.move(0, -32, 0, -1);
            }
        }
        if(e.getSource()==down){
            if(p.textureCheck(m.getMap(p.getPositionInArrayX(),p.getPositionInArrayY()+1))) {
                p.move(0, 32, 0, 1);
            }
        }
        if(e.getSource()==left){
            if(p.textureCheck(m.getMap(p.getPositionInArrayX()-1,p.getPositionInArrayY()))) {
                p.move(-32, 0, -1, 0);
            }
        }
        if(e.getSource()==right){
            if(p.textureCheck(m.getMap(p.getPositionInArrayX()+1,p.getPositionInArrayY()))) {
                p.move(32, 0, 1, 0);
            }
        }
        if(didIWin(p,winX,winY)) {
            p.setNumbersOfWin(1);
            counter.setText("You won"+p.getNumbersOfWin());
            myFrame.board.winGame(p.getNumbersOfWin());
        }
        p.setMousePotentialXposition(0);
        p.setMouseYPotentialYposition(0);
        repaint();
    }

    public void paint(Graphics g){
        super.paint(g);
        winX=0;
        winY=0;
        for(int y=0;y<14;y++){
            for(int x = 0 ;x <14;x++){
                if (m.getMap(x, y)==0||m.getMap(x,y)==5) {
                    g.setColor(Color.GRAY);
                    g.fillRect(x*32,y*32,31,31);
                }
                if (m.getMap(x, y)==1) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x*32,y*32,31,31);
                }
                if(m.getMap(x,y)==5){
                    g.setColor(Color.MAGENTA);
                    g.fillRect(x*32+6,y*32+6,20,20);
                    winX=x;
                    winY=y;
                }
            }
        }
        g.setColor(Color.RED);
        g.fillRect(p.getX(),p.getY(),31,31);

        if(p .isClicked()) {
            g.setColor(Color.YELLOW);
            for (int i = 1; i < p.getPositionInArrayX(); i++) {
                if (p.textureCheck(m.getMap(p.getPositionInArrayX() - i, p.getPositionInArrayY()))) {
                    g.setColor(Color.YELLOW);
                    g.fillRect(p.getX() - i * 32 + 8, p.getY() + 8, 15, 15);
                }else{

                    break;
                }
            }
            for (int i = 1; i < 14-p.getPositionInArrayX(); i++) {
                if (p.textureCheck(m.getMap(p.getPositionInArrayX() + i, p.getPositionInArrayY()))) {
                    g.setColor(Color.YELLOW);
                    g.fillRect(p.getX() + i * 32 + 8, p.getY() + 8, 15, 15);
                }else{
                    break;
                }
            }
            for (int i = 1; i < p.getPositionInArrayY(); i++) {
                if (p.textureCheck(m.getMap(p.getPositionInArrayX(), p.getPositionInArrayY()-i))) {
                    g.setColor(Color.YELLOW);
                    g.fillRect(p.getX() + 8, p.getY() - i * 32 + 8, 15, 15);
                }else{
                    break;
                }
            }

            for (int i = 1; i < 14-p.getPositionInArrayY(); i++) {
                if (p.textureCheck(m.getMap(p.getPositionInArrayX(), p.getPositionInArrayY()+i))) {
                    g.setColor(Color.YELLOW);
                    g.fillRect(p.getX() + 8, p.getY() + i * 32 + 8, 15, 15);
                }else{
                    break;
                }
            }
        }
        if(p.isClicked()){
            if(!(p.getMouseY()==0&&p.getMousePotentialXposition()==0)){
                g.setColor(Color.CYAN);
                g.fillRect(p.getMousePotentialXposition()*32+10,p.getMouseY()*32+10,12,12);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getX()>p.getPositionInArrayX()*32 && e.getX()<(p.getPositionInArrayX()+1)*32-1 &&e.getY()>p.getPositionInArrayY()*32&&e.getY()<(p.getPositionInArrayY()+1)*32-1){
            p.setClicked(!p.isClicked());
        }
        if(p.isClicked()){
            int helperX;
            int helperCounter=0;

            //Left
            helperX=(e.getX()/32);
            if(e.getY()>p.getPositionInArrayY()*32&&e.getY()<(p.getPositionInArrayY()+1)*32-1){
                for(int i=p.getPositionInArrayX();i>helperX;i--){
                    if(p.textureCheck(m.getMap(i,p.getPositionInArrayY()))){
                        helperCounter++;
                    }
                }
                if(helperCounter==(p.getPositionInArrayX()-helperX)){
                    if(p.textureCheck(m.getMap(helperX,p.getPositionInArrayY()))){
                        p.move(+32 * (helperX-p.getPositionInArrayX()), 0, +(helperX-p.getPositionInArrayX()), 0);
                    }
                }

            }

            //Right
            helperX=(e.getX()/32);
            helperCounter=0;
            if(e.getY()>p.getPositionInArrayY()*32&&e.getY()<(p.getPositionInArrayY()+1)*32-1){
                for(int i=p.getPositionInArrayX();i<helperX;i++){
                    if(p.textureCheck(m.getMap(i,p.getPositionInArrayY()))){
                        helperCounter++;
                    }
                }
                if(helperCounter==(helperX-p.getPositionInArrayX())){
                    if(p.textureCheck(m.getMap(helperX,p.getPositionInArrayY()))){
                        p.move(+32 * (helperX-p.getPositionInArrayX()), 0, +(helperX-p.getPositionInArrayX()), 0);
                    }
                }

            }
            //Up
            int helperY;
            helperCounter=0;
            helperY=(e.getY()/32);
            if(e.getX()>p.getPositionInArrayX()*32&&e.getX()<(p.getPositionInArrayX()+1)*32-1){
                for(int i=p.getPositionInArrayY();i>helperY;i--){
                    if(p.textureCheck(m.getMap(p.getPositionInArrayX(),i))){
                        helperCounter++;
                    }
                }
                if(helperCounter==(p.getPositionInArrayY()-helperY)){
                    if(p.textureCheck(m.getMap(p.getPositionInArrayX(),helperY))){
                        p.move(0, -32 * (p.getPositionInArrayY() - helperY), 0, -(p.getPositionInArrayY() - helperY));
                    }
                }
            }

            //Down
            helperCounter=0;
            helperY=(e.getY()/32);
            if(e.getX()>p.getPositionInArrayX()*32&&e.getX()<(p.getPositionInArrayX()+1)*32-1){
                for(int i=p.getPositionInArrayY();i<helperY;i++){
                    if(p.textureCheck(m.getMap(p.getPositionInArrayX(),i))){
                        helperCounter++;
                    }
                }
                if(helperCounter==(helperY-p.getPositionInArrayY())){
                    if(p.textureCheck(m.getMap(p.getPositionInArrayX(),helperY))){
                        p.move(0, +32 * (helperY-p.getPositionInArrayY()), 0, +(helperY-p.getPositionInArrayY()));
                    }
                }

            }
            if(didIWin(p,winX,winY)) {
                p.setNumbersOfWin(1);
                counter.setText("You won "+p.getNumbersOfWin());
                myFrame.board.winGame(p.getNumbersOfWin());
            }
        }

        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    public void mouseMoved(MouseEvent e) {

        if(p.isClicked()){
            int helperX;
            int helperCounter=0;
            //Left
            helperX=(e.getX()/32);
            if(e.getY()>p.getPositionInArrayY()*32&&e.getY()<(p.getPositionInArrayY()+1)*32-1){

                for(int i=p.getPositionInArrayX();i>helperX;i--){
                    if(p.textureCheck(m.getMap(i,p.getPositionInArrayY()))){
                        helperCounter++;
                    }
                }
                if(helperCounter==(p.getPositionInArrayX()-helperX)){
                    if(p.textureCheck(m.getMap(helperX,p.getPositionInArrayY()))){
                        p.setMousePotentialXposition(e.getX()/32);
                        p.setMouseYPotentialYposition(e.getY()/32);
                        repaint();
                    }
                }
            }

            //Right
            helperX=(e.getX()/32);
            helperCounter=0;
            if(e.getY()>p.getPositionInArrayY()*32&&e.getY()<(p.getPositionInArrayY()+1)*32-1){

                for(int i=p.getPositionInArrayX();i<helperX;i++){
                    if(p.textureCheck(m.getMap(i,p.getPositionInArrayY()))){

                        helperCounter++;
                    }
                }
                if(helperCounter==(helperX-p.getPositionInArrayX())){
                    if(p.textureCheck(m.getMap(helperX,p.getPositionInArrayY()))){
                        p.setMousePotentialXposition(e.getX()/32);
                        p.setMouseYPotentialYposition(e.getY()/32);
                        repaint();
                    }
                }
            }
            //Up
            int helperY;
            helperCounter=0;
            helperY=(e.getY()/32);
            if(e.getX()>p.getPositionInArrayX()*32&&e.getX()<(p.getPositionInArrayX()+1)*32-1){
                for(int i=p.getPositionInArrayY();i>helperY;i--){
                    if(p.textureCheck(m.getMap(p.getPositionInArrayX(),i))){
                        helperCounter++;
                    }
                }
                if(helperCounter==(p.getPositionInArrayY()-helperY)){
                    if(p.textureCheck(m.getMap(p.getPositionInArrayX(),helperY))){
                        if(p.textureCheck(m.getMap(helperX,p.getPositionInArrayY()))){
                            p.setMousePotentialXposition(e.getX()/32);
                            p.setMouseYPotentialYposition(e.getY()/32);
                            repaint();
                        }
                    }
                }
            }

            //Down
            helperCounter=0;
            helperY=(e.getY()/32);
            if(e.getX()>p.getPositionInArrayX()*32&&e.getX()<(p.getPositionInArrayX()+1)*32-1){
                for(int i=p.getPositionInArrayY();i<helperY;i++){
                    if(p.textureCheck(m.getMap(p.getPositionInArrayX(),i))){
                        helperCounter++;
                    }
                }
                if(helperCounter==(helperY-p.getPositionInArrayY())){
                    if(p.textureCheck(m.getMap(p.getPositionInArrayX(),helperY))){
                        if(p.textureCheck(m.getMap(helperX,p.getPositionInArrayY()))){
                            p.setMousePotentialXposition(e.getX()/32);
                            p.setMouseYPotentialYposition(e.getY()/32);
                            repaint();
                        }else{
                            p.setMouseYPotentialYposition(0);
                            p.setMousePotentialXposition(0);
                            repaint();
                        }
                    }
                }

            }
        }

    }

    public void mouseDragged(MouseEvent e){
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode==KeyEvent.VK_UP){
            if(p.textureCheck(m.getMap(p.getPositionInArrayX(),p.getPositionInArrayY()-1))) {
                p.move(0, -32, 0, -1);
            }
        }
        if(keyCode==KeyEvent.VK_DOWN){
            if(p.textureCheck(m.getMap(p.getPositionInArrayX(),p.getPositionInArrayY()+1))) {
                p.move(0, 32, 0, 1);
            }
        }
        if(keyCode==KeyEvent.VK_LEFT){
            if(p.textureCheck(m.getMap(p.getPositionInArrayX()-1,p.getPositionInArrayY()))) {
                p.move(-32, 0, -1, 0);
            }
        }
        if(keyCode==KeyEvent.VK_RIGHT){
            if(p.textureCheck(m.getMap(p.getPositionInArrayX()+1,p.getPositionInArrayY()))) {
                p.move(32, 0, 1, 0);
            }

        }
        repaint();

        if(didIWin(p,winX,winY)) {
            p.setNumbersOfWin(1);
            counter.setText("You won "+p.getNumbersOfWin());
            myFrame.board.winGame(p.getNumbersOfWin());
        }
        p.setMousePotentialXposition(0);
        p.setMouseYPotentialYposition(0);
    }


    @Override
    public void keyReleased(KeyEvent e) {
    }

    protected boolean didIWin(Player player, int winX, int winY){
        return player.getPositionInArrayX() == winX && player.getPositionInArrayY() == winY;
    }

    protected void addPlayerToButtons() {
        p = new Player();
        up.setPlayer(p);
        left.setPlayer(p);
        right.setPlayer(p);
        down.setPlayer(p);
        repaint();
    }

    protected void addPlayerToButtons(int numbersOfWin){
        p = new Player();
        p.setNumbersOfWin(numbersOfWin);
        up.setPlayer(p);
        left.setPlayer(p);
        right.setPlayer(p);
        down.setPlayer(p);
        repaint();
    }

    protected  void addMapToButtons(){
        m = new Map();
        up.setM(m);
        left.setM(m);
        right.setM(m);
        down.setM(m);
    }
    public void reset() {
        this.addMapToButtons();
        this.addPlayerToButtons();
        counter.setText("You won 0");
    }
    public void winGame(int numbersOfWin){
        this.addMapToButtons();
        this.addPlayerToButtons(numbersOfWin);
    }
}