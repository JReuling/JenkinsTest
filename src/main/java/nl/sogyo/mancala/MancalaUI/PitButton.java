package nl.sogyo.mancala.MancalaUI;

import javax.swing.*;

/**
 * Created by jreuling on 14-10-2015.
 */
public class PitButton extends JButton {
    int ID;

    PitButton(int ID)
    {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
}
