package nl.sogyo.mancala;

import nl.sogyo.mancala.MancalaUI.GameControl;
import nl.sogyo.mancala.MancalaUI.JavaUI;
import nl.sogyo.mancala.MancalaUI.UI;
import nl.sogyo.mancala.MancalaUI.WebUI;
import nl.sogyo.mancala.Web.WebGameControl;

import java.util.ArrayList;

/**
 * Created by jreuling on 14-10-2015.
 */
public class Game {
    //Infrastructure/Factory class
    private Board board;
    private Control control;
    private UI ui;

    public Game()
    {
        this(new int[]{4,4,4,4,4,4,0,4,4,4,4,4,4,0});
    }

    public Game(int[] initialState)
    {
        board = new Board();
        Player player1 = new Player();
        ArrayList<Pit> boardList = new ArrayList<>(); //Initialize the board array with nulls and create the board by calling the smallpit constructor.
        for (int i = 0; i < 14; i++) {
            boardList.add(null);
        }
        new SmallPit(initialState,boardList,player1);
        board.setPit1(boardList.get(0));
        board.setPlayer1(player1);
    }

    public void firstStartOnLocal()
    {
        control = new GameControl();
        control.setBoard(board);
        UI ui = new JavaUI((GameControl) control);
        control.setUi(ui);
    }

    public Board getBoard() {
        return board;
    }

    public void firstStartOnWeb() {
        control = new WebGameControl();
        control.setBoard(board);
        ui = new WebUI();
        control.setUi(ui);
    }

    public Control getControl() {
        return control;
    }

    public UI getUI() {
        return ui;
    }
}
