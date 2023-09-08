package com.sisbd.confirmationdb.javacard;

import com.sisbd.confirmationdb.utils.Constants;
import javafx.concurrent.Task;

import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;

public class EmployeeTask extends Task<Boolean> {

    private byte[] entryPin;
    EmployeeClient client = new EmployeeClient();
    public void setEntryPin(byte[] entryPin) {
        this.entryPin = entryPin;
    }

    @Override
    protected Boolean call() throws Exception {
        System.out.println("begin task before");

         client.connect();
        client.selectApplet();

        CommandAPDU apdu = new CommandAPDU(Constants.CLA_MONAPPLET, Constants.INS_TEST_CODE_PIN, 0x00, 0x00, entryPin);
        ResponseAPDU responseApdu = client.sendAPDU(apdu);

        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : entryPin) {
            stringBuilder.append(b);
        }

        String pinString = stringBuilder.toString();

        if (responseApdu.getSW() == 0x6300 ) {
            client.deselectApplet();
            return false;
        } else if (responseApdu.getSW() == 0x9000 && pinString.equals("1393")) {
            client.deselectApplet();
            return true;
        }

        System.out.println("begin task after");
        return false;
    }
}


