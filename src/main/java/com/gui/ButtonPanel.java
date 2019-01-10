package com.gui;

import java.awt.*;
import java.io.File;

import javax.swing.*;

class ButtonPanel extends JFrame {

    private static final int HEIGHT = 300;
    private static final int WIDTH = 300;
    private File toSave;
    private JButton greenButton;
    private JFrame buttonPanel;
    static JTextField[] textFields = new JTextField[6];
    JLabel[] labels = new JLabel[6];
    private String[] labelNames = new String[]{"Imie", "Nazwisko","Adres", "Pesel", "Numer konta", "Nazwa pliku do odczytu"};

    public ButtonPanel() {
        greenButton = new GreenButton();

        for(int i=0; i<labels.length; i++){
            labels[i] = new JLabel(labelNames[i]);
            this.add(labels[i]);
            textFields[i]=new JTextField();
            if  (i<labels.length-1)
                textFields[i].setEditable(false);
            this.add(textFields[i]);
        }

        this.setSize(WIDTH, HEIGHT);
        buttonPanel = this;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridLayout layout = new GridLayout(0, 1);
        setLayout(layout);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(greenButton);
        setVisible(true);
    }
}