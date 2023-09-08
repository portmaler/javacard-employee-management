package com.sisbd.confirmationdb.javacard;

import com.sisbd.confirmationdb.utils.Constants;

import javax.smartcardio.*;
import java.io.IOException;
import java.net.Socket;

public class EmployeeClient {

    private static CardChannel cardChannel;

    public ResponseAPDU sendAPDU(byte cla, byte ins, byte p1, byte p2, byte length, byte[] data, byte le) throws CardException {
        CommandAPDU commandAPDU = new CommandAPDU(cla, ins, p1, p2, data, length, le);
        return cardChannel.transmit(commandAPDU);
    }

    public void connect() throws CardException {
        TerminalFactory terminalFactory = TerminalFactory.getDefault();
        CardTerminals cardTerminals = terminalFactory.terminals();
        CardTerminal cardTerminal = cardTerminals.list().get(0);

        try {
            Card card = cardTerminal.connect("*");
            cardChannel = card.getBasicChannel();
        } catch (CardException e) {
            System.out.println("Error: Unable to connect to the JavaCard");
            throw e;
        }
    }

    public void verifyPIN(byte[] pin) throws CardException {
        // C-APDU: [CLA, INS, P1, P2, LC, [PIN]]
        CommandAPDU commandAPDU = new CommandAPDU(Constants.CLA_MONAPPLET, Constants.INS_TEST_CODE_PIN, 0, 0, pin);
        ResponseAPDU responseAPDU = cardChannel.transmit(commandAPDU);

        if (responseAPDU.getSW() == Constants.SW_NO_ERROR) {
            System.out.println("OK");
        } else {
            System.out.println("Error: " + Integer.toHexString(responseAPDU.getSW()));
        }
    }

    public short checkEmployeeId() throws CardException {
        CommandAPDU commandAPDU = new CommandAPDU(Constants.CLA_MONAPPLET, Constants.GET_EMPLOYEE_ID, 0, 0);
        ResponseAPDU responseAPDU = cardChannel.transmit(commandAPDU);

        short idEmp = 0;

        if (responseAPDU.getSW() == Constants.SW_NO_ERROR) {
            byte[] responseData = responseAPDU.getData();

            // Extract the short from the response data
            if (responseData.length >= 2) {
                short receivedShort = (short) ((responseData[0] << 8) | (responseData[1] & 0xFF));
                System.out.println("Received short: " + receivedShort);
                idEmp = receivedShort;
            }

            System.out.println("OK");
        } else {
            System.out.println("Error: " + Integer.toHexString(responseAPDU.getSW()));
        }

        return idEmp;
    }

    public void selectApplet() throws CardException {
        /* Select the applet: create the SELECT APDU command */
        byte[] appletAID = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x00, 0x00};
        CommandAPDU selectApdu = new CommandAPDU(0x00, Constants.INS_SELECT, 0x04, 0x00, appletAID);
        ResponseAPDU responseApdu = cardChannel.transmit(selectApdu);
        if (responseApdu.getSW() != Constants.SW_NO_ERROR) {
            System.out.println("Error selecting the applet");
            System.exit(1);
        }
    }



    public void deselectApplet() throws CardException {
        cardChannel.getCard().disconnect(true);
    }

    private static byte[] parseByteArray(String s) {
        byte[] array = new byte[s.length() / 2];
        for (int i = 0; i < s.length(); i += 2) {
            array[i / 2] = (byte) Integer.parseInt(s.substring(i, i + 2), 16);
        }
        return array;
    }

    public ResponseAPDU sendAPDU(CommandAPDU apdu) throws CardException {
        return cardChannel.transmit(apdu);
    }
}

