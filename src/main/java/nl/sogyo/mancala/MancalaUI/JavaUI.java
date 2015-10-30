package nl.sogyo.mancala.MancalaUI;

import nl.sogyo.mancala.Board;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
 * Created by jreuling on 14-10-2015.
 */
public class JavaUI extends JFrame implements UI{

    private Board board;
    private GameControl control;
    private JPanel panel;
    private GridBagConstraints constraints;
    private PitButton[] buttons = new PitButton[12];
    private JLabel[] kalahas = new JLabel[2];
    private JLabel playingPlayerLabel;

    public JavaUI(GameControl control)
    {

        this.control = control;
        this.setSize(500,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        board = control.getBoard();
        drawBoard();
        this.setTitle("Mancala");
        this.pack();
    }

    //Prints the board on the screen. It always has player 1's side on the bottom of the screen.
    private void drawBoard() {

        panel = new JPanel();
        panel.setVisible(true);
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 255));
        constraints = new GridBagConstraints();

        for (int i = 1; i < 7 ; i++)
        {
            buttons[i-1] = addButton(i);
        }
        for (int i = 8; i < 14 ; i++)
        {
            buttons[i-2] = addButton(i);
        }

        addKalahas();
        addPlayerLabel();
        this.add(panel);
    }

    private PitButton addButton(int ID)
    {
        PitButton button = new PitButton(ID);
        button.addActionListener(control);
        button.setText(Integer.toString(board.getPit(ID).getStones()));
        button.setSize(50,50);
        if (ID<7)
        {
            constraints.gridx = ID+1;
            constraints.gridy = 3;
            button.setEnabled(true);
            constraints.insets = new Insets(10,20,50,20);
        }
        else
        {
            constraints.gridx = Math.abs((ID-1) - 14);
            constraints.gridy = 1;
            button.setEnabled(false);
            constraints.insets = new Insets(10,20,10,20);
        }

        constraints.ipadx = 50;
        constraints.ipady = 50;
        panel.add(button, constraints);
        return button;
    }

    private void addKalahas()
    {
        addKalaha1();
        addKalaha2();
    }

    private void addKalaha1()
    {
        JLabel kalaha1 = new JLabel(Integer.toString(board.getPit(7).getStones()));
        constraints.gridx = 8;
        constraints.gridy = 2;
        constraints.insets = new Insets(10,20,10,20);
        panel.add(kalaha1, constraints);
        kalahas[0] = kalaha1;
    }

    private void addKalaha2()
    {
        JLabel kalaha2 = new JLabel(Integer.toString(board.getPit(14).getStones()));
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.insets = new Insets(10,20,10,20);
        panel.add(kalaha2, constraints);
        kalahas[1] = kalaha2;
    }

    private void addPlayerLabel()
    {
        playingPlayerLabel = new JLabel("Player 1: it is your turn.");
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 8;
        constraints.insets = new Insets(10,20,10,20);
        panel.add(playingPlayerLabel, constraints);
    }

    public void refreshBoard()
    {
        refreshButtons();
        refreshKalahas();
        refreshPlayerLabel();
    }

    private void refreshButtons()
    {
        for(PitButton button : buttons)
        {
            button.setText(board.getPit(button.getID()).getStones() + "");
            if (board.getPit(button.getID()).getOwner().isOnPlay())
            {
                button.setEnabled(true);
            }
            else
            {
                button.setEnabled(false);
            }
        }
    }

    private void refreshKalahas()
    {
        kalahas[0].setText(board.getPit(7).getStones() +"");
        kalahas[1].setText(board.getPit(14).getStones() + "");
    }

    private void refreshPlayerLabel()
    {
        if (board.getPit1().getOwner().isOnPlay())
        {
            playingPlayerLabel.setText("Player 1: it is your turn.");
        }
        else
        {
            playingPlayerLabel.setText("Player 2: it is your turn.");
        }
    }

    //Prints the result of the game.
    public void showResult()
    {
        int player1Score = board.getPit(7).getStones();
        int player2Score = board.getPit(14).getStones();
        refreshBoard();
        if (player1Score > player2Score) {
            playingPlayerLabel.setText("The winner is... Player 1!");
        }
        else if (player2Score > player1Score) {
            playingPlayerLabel.setText("The winner is....  Player 2!");
        }
        else {
            System.out.println("It's a tie.");
        }
    }

}
