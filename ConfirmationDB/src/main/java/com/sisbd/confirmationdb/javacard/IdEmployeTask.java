package com.sisbd.confirmationdb.javacard;

import javafx.concurrent.Task;
import javax.smartcardio.CardException;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;

public class IdEmployeTask extends Task<Short> {

    @Override
    protected Short call() throws Exception {
        System.out.println("begin task before");
        // TODO Auto-generated method stub
        EmployeeClient client = new EmployeeClient();
        client.connect();
        client.selectApplet();

        // Create the APDU command to check employee ID (CLA=0x04, INS=0x01)
        CommandAPDU checkEmployeeIDCommand = new CommandAPDU(0x04, 0x01, 0x00, 0x00, 0x02);

        // Transmit the APDU command and get the response
        ResponseAPDU response = client.sendAPDU(checkEmployeeIDCommand);

        // Check if the response was successful (SW=0x9000)
        if (response.getSW() == 0x9000) {
            byte[] responseData = response.getData();
            short idResult = parseIDResult(responseData);
            System.out.println("ID result from id task: " + idResult);
            return idResult;
        } else {
            throw new CardException("Error: " + Integer.toHexString(response.getSW()));
        }
    }

    private short parseIDResult(byte[] responseData) {
        // Implement your logic to parse the ID result from the response data
        // and convert it to a short value
        // Example: assuming responseData contains a 2-byte ID result in big-endian format
        return (short) ((responseData[0] << 8) | (responseData[1] & 0xFF));
    }
}


