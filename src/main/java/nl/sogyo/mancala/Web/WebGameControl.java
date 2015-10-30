package nl.sogyo.mancala.Web;

import nl.sogyo.mancala.Board;
import nl.sogyo.mancala.Control;
import nl.sogyo.mancala.MancalaUI.UI;
import nl.sogyo.mancala.MancalaUI.WebUI;
import nl.sogyo.mancala.SmallPit;

/**
 * Created by jreuling on 27-10-2015.
 */
public class WebGameControl implements Control {

    Board board;
    UI ui;

    @Override
    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public void setUi(UI ui) {
        this.ui = ui;
    }

    public String getUIasString() {
        return ((WebUI)ui).asString();
    }

    public String getStones(int i) {
        return board.getPit(i).getStones() + "";
    }

    private boolean possiblePlay(int pitToPlay) {
        return (board.getPit(pitToPlay).getStones()>0);
    }


    public void play()
    {
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
    }
}
