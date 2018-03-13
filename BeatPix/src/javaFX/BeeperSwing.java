package javaFX;


import javax.swing.JApplet;
import javax.swing.JButton;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BeeperSwing extends JApplet 
                    implements ActionListener {
    JButton button;

    public void init() {
        button = new JButton("Click Me");
        getContentPane().add(button, BorderLayout.CENTER);
        button.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit().beep();
    }
}