package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {//, KeyListener

    MyButton up;
    MyButton left;
    MyButton down;
    MyButton right;
    Board board;
    JButton resetButton;
    Label counter;


    MyFrame(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(462, 538);
        JPanel frame = new JPanel();
        frame.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 0;
        this.counter = new Label("U won 0");
        frame.add(counter,c);
        c.gridx = 1;
        this.up = new MyButton("Up");
        frame.add(up,c);
        c.gridx = 2;

        resetButton = new JButton();
        resetButton.setText("Reset");
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);
        frame.add(resetButton,c);

        this.getContentPane().setLayout((new BorderLayout()));
        c.gridx = 0;
        c.gridy = 1;
        this.left = new MyButton("Left");
        frame.add(left,c);
        c.gridx = 1;
        this.down = new MyButton("Down");
        frame.add(down,c);
        c.gridx = 2;
        this.right = new MyButton("Right");
        frame.add(right,c);

        board = new Board(up,down,left,right, this,counter);
        this.add(frame,BorderLayout.PAGE_START);
        this.add(board,BorderLayout.CENTER);

        this.setResizable(false);
        this.setPreferredSize(new Dimension(464,565));
        this.setLocationRelativeTo(null);
        this.setTitle("Lukac Maze");
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==resetButton) {
            board.reset();
        }
    }

}
