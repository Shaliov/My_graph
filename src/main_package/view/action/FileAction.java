package main_package.view.action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Andrey on 3/2/2016.
 */
public class FileAction {
    public static javax.swing.Action newAction = new AbstractAction("New", new ImageIcon("new.gif")) {
        public void actionPerformed(ActionEvent event) {

        }
    };


    public static javax.swing.Action openAction = new AbstractAction("Open", new ImageIcon("open.gif")) {
        public void actionPerformed(ActionEvent event) {

        }
    };


    public static javax.swing.Action saveActionBar = new AbstractAction("Save", new ImageIcon("save.gif")) {
        public void actionPerformed(ActionEvent event) {

        }
    };


    public static javax.swing.Action saveAsActionBar = new AbstractAction("SaveAs", new ImageIcon("saveAs.gif")) {
        public void actionPerformed(ActionEvent event) {


        }
    };

    public static javax.swing.Action exitAction = new AbstractAction("Exit", new ImageIcon("exit.gif")) {
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    };

    public static void actioN(JFrame menuFrame) {

        newAction.putValue(javax.swing.Action.SHORT_DESCRIPTION, "New");
        openAction.putValue(javax.swing.Action.SHORT_DESCRIPTION, "Open");
        saveActionBar.putValue(javax.swing.Action.SHORT_DESCRIPTION, "Save");
        saveAsActionBar.putValue(javax.swing.Action.SHORT_DESCRIPTION, "Save aa");
        exitAction.putValue(javax.swing.Action.SHORT_DESCRIPTION, "Exit");


        JToolBar bar = new JToolBar();
        bar.add(newAction);
        bar.add(openAction);
        bar.add(saveActionBar);
        bar.add(saveAsActionBar);
        bar.add(exitAction);

        menuFrame.add(bar, BorderLayout.NORTH);
    }
}
