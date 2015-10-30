package nl.sogyo.mancala.MancalaUI;

/**
 * Created by jreuling on 15-10-2015.
 */
public interface UI {
    //Right now this is just a dud containing abstract versions of methods I call from GameControl in JavaUI since I am
    //not sure if a web-app would need a different interface and I do not want to refractor all the code should that happen.
    //Can be removed if this is not the case.

    abstract void refreshBoard();

    abstract void showResult();
}
