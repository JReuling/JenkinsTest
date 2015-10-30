package nl.sogyo.mancala.MancalaUI;

import nl.sogyo.mancala.Board;
import nl.sogyo.mancala.Control;
import nl.sogyo.mancala.SmallPit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jreuling on 14-10-2015.
 */
public class GameControl implements ActionListener, Control {
    Board board;
    UI ui;

    @Override
    public void actionPerformed(ActionEvent e) {
        int pitToPlay = ((PitButton)(e.getSource())).getID();
        play(pitToPlay);
    }

    private boolean possiblePlay(int pitToPlay) {
        return (board.getPit(pitToPlay).getStones()>0);
    }


    public void play()
    {
        ui.refreshBoard();
        if (!canPlay())
        {
            gameEnd();
        }
    }

    public void play(int pitToPlay)
    {
        if (possiblePlay(pitToPlay)) {
            ((SmallPit) (board.getPit(pitToPlay))).play();
            play();
        }
        else
        {
            //do nothing
        }
    }

    private boolean canPlay() {
        int sum = 0;
        if (board.getPlayer1().isOnPlay())
        {
            for (int i = 1; i <= 6 ; i++) {
                sum+=board.getPit(i).getStones();
            }
        }
        else
        {
            for (int i = 8; i <= 13 ; i++) {
                sum+=board.getPit(i).getStones();
            }
        }
        return sum>0;
    }

    //Empties the board on the side opposite of the player who couldnt play anymore.
    private void gameEnd() {
        if (board.getPlayer1().isOnPlay())
        {
            for (int i = 8; i <= 13; i++) {
                ((SmallPit)board.getPit(i)).Empty();
            }
        }
        else
        {
            for (int i = 1; i <= 6; i++) {
                ((SmallPit)board.getPit(i)).Empty();
            }
        }
        ui.showResult();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }
}
