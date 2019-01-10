package com.gui;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v251.message.ADT_A01;
import ca.uhn.hl7v2.parser.Parser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

class GreenButton extends JButton implements ActionListener {

    GreenButton() {
        super("Odczytaj dane z pliku");
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String filename = "F:\\"+ButtonPanel.textFields[ButtonPanel.textFields.length-1].getText();
        Path path = Paths.get(filename);
        try {
            String codedMessage;
            BufferedReader br = new BufferedReader(new FileReader(filename));
            codedMessage = br.readLine()+"\r";
            codedMessage = codedMessage+br.readLine();


            HapiContext context = new DefaultHapiContext();
            Parser p = context.getGenericParser();
            Message hapiMsg;
            hapiMsg = p.parse(codedMessage);
            ADT_A01 adtMsg = (ADT_A01) hapiMsg;

            ButtonPanel.textFields[0].setText(adtMsg.getPID().getPatientName(0).getGivenName().getValue());
            ButtonPanel.textFields[1].setText(adtMsg.getPID().getPatientName(0).getFamilyName().getSurname().getValue());
            ButtonPanel.textFields[2].setText(adtMsg.getPID().getPatientAddress(0).getAddressRepresentationCode().getValue());
            ButtonPanel.textFields[3].setText(adtMsg.getPID().getPatientIdentifierList(0).getIDNumber().getValue());
            ButtonPanel.textFields[4].setText(adtMsg.getPID().getPatientAccountNumber().getIDNumber().getValue());

        } catch (IOException | HL7Exception ioe){
            ioe.printStackTrace();
        }
    }
}
