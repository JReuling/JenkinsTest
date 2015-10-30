package nl.sogyo.mancala;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jreuling on 7-10-2015.
 */
//The player class determines whose turn it is, asks for plays while plays are possible and ends the game if no plays are possible anymore.
//The methods in this class all assume that the play() method is called upon player1.
public class Player {
    private boolean onPlay;
    private Player opponent;

    public Player()
    {
        this.onPlay = true;  //The first player object created is the first player in the game.
        this.opponent = new Player(this);
    }

    private Player(Player player1)
    {
        this.onPlay = false; //This player is called second and is Player 2.
        this.opponent = player1;
    }

    public void passTurn() {
        onPlay = !onPlay;
        if (opponent.isOnPlay()==onPlay) {
            opponent.passTurn();
        }
    }

    public boolean isOnPlay() {
        return onPlay;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Player getOpponent() {
        return opponent;
    }

    //Determines if there are any possible moves for the player whose turn it is.
//    public boolean canPlay()
//    {
//        if (onPlay)
//        {
//            int sum = 0;
//            if (board[6].getOwner() == this)
//            {
//                for (int i = 0; i < 6; i++) {
//                    sum = sum + board[i].getStones();
//                }
//            }
//            else
//            {
//                for (int i = 7; i < 13; i++) {
//                    sum = sum + board[i].getStones();
//                }
//            }
//            if (sum==0)
//            {
//                return false;
//            }
//            else
//            {
//                return true;
//            }
//        }
//        else
//        {
//            return false;
//        }
//    }

    //Determines if there are possible moves by the playing player and starts the turn for that player.
    //If there are no possible moves the method to wrap up the game is called.
//    public void play()
//    {
//        while (this.canPlay() || opponent.canPlay())
//        {
//            if (this.isOnPlay())
//            {
//                this.beginTurn();
//            }
//            else
//            {
//                opponent.beginTurn();
//            }
//        }
//        gameEnd();
//    }

//    public void beginTurn() {
//        drawBoard(board);
//        int pitToPlay = askPlay();
//        play(pitToPlay);
//    }
//
//    //Prints the board on the screen. It always has the playing players side on the bottom of the screen.
//    private void drawBoard(Pit[] board) {
//        if (board[6].getOwner() == this)
//        {
//            System.out.print("  ");
//            for (int i = 12; i > 6; i--) {
//                System.out.print(board[i].getStones() + " ");
//            }
//            System.out.println(" ");
//
//            System.out.print(board[13].getStones() + " ");
//            for (int i = 0; i < 6*2; i++)
//            {
//                System.out.print(" ");
//            }
//            System.out.println(board[6].getStones());
//
//            System.out.print("  ");
//            for (int i = 0; i < 6; i++) {
//                System.out.print(board[i].getStones() + " ");
//            }
//            System.out.println(" ");
//        }
//        else
//        {
//            System.out.print("  ");
//            for (int i = 5; i >= 0; i--) {
//                System.out.print(board[i].getStones() + " ");
//            }
//            System.out.println(" ");
//
//            System.out.print(board[6].getStones() + " ");
//            for (int i = 0; i < 6*2; i++)
//            {
//                System.out.print(" ");
//            }
//            System.out.println(board[13].getStones());
//
//            System.out.print("  ");
//            for (int i = 7; i < 13; i++) {
//                System.out.print(board[i].getStones() + " ");
//            }
//            System.out.println(" ");
//        }
//    }
//
//    //Asks the player (a person, not an instance of this class) for a pit to play. If the play cannot be made
//    //a message will be printed and the player will be asked again.
//    private int askPlay() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Which pit would you like to play? (Choose a number from 1 up to and including 6.)");
//        String answer = scanner.next();
//        int answerInt = Integer.parseInt(answer);
//        if (answerInt>0 && answerInt<=6)
//        {
//            if (board[6].getOwner() == this)
//            {
//                return askPlayHelper(answerInt,1);
//            }
//            else
//            {
//                return askPlayHelper(answerInt,2);
//            }
//
//        }
//        else
//        {
//            System.out.println("That is not a playable pit, please choose a number from 1 up to and including 6.");
//            return askPlay();
//        }
//    }
//
//    private int askPlayHelper(int answerInt, int player1or2)
//    {
//        int index = answerInt + 7*(player1or2-1);
//        if (board[index-1].getStones()!=0)
//        {
//            return answerInt;
//        }
//        else
//        {
//            System.out.println("You cannot play an empty pit, please choose another one.");
//            return askPlay();
//        }
//    }
//
//    //Calls upon the requested pit for a play.
//    private void play(int pitToPlay) {
//        if (board[6].getOwner() == this)
//        {
//            ((SmallPit)board[pitToPlay-1]).play();
//        }
//        else
//        {
//            ((SmallPit)board[pitToPlay+6]).play();
//        }
//    }
//
//    //Empties the board on the side opposite of the player who couldnt play anymore.
//    private void gameEnd() {
//        if (this.isOnPlay())
//        {
//            for (int i = 7; i < 13; i++) {
//                ((SmallPit)board[i]).Empty();
//            }
//        }
//        else
//        {
//            for (int i = 0; i < 6; i++) {
//                ((SmallPit)board[i]).Empty();
//            }
//        }
//        showResult();
//    }
//
//    //Prints the result of the game.
//    public void showResult()
//    {
//        int player1Score = board.getPit(6).getStones();
//        int player2Score = board.getPit(13).getStones();
//        if (player1Score > player2Score) {
//            System.out.println("The winner is....  Player 1!");
//        }
//        else if (player2Score > player1Score) {
//            System.out.println("The winner is....  Player 2!");
//        }
//        else {
//            System.out.println("It's a tie.");
//        }
//        System.out.println("Player 1 has " + player1Score + " stones,");
//        System.out.println("Player 2 has " + player2Score + " stones.");
//    }





}
