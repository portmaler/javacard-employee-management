package com.sisbd.confirmationdb.javacard;

import javafx.concurrent.Task;
import javax.smartcardio.CardException;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import java.math.BigInteger;

public class IdEmployeTask extends Task<Integer> {

    @Override
    protected Integer call() throws Exception {
        System.out.println("begin task before");
        EmployeeClient client = new EmployeeClient();
        client.connect();
        client.selectApplet();

        // Create the APDU command to check employee ID (CLA=0x04, INS=0x01)
        CommandAPDU checkEmployeeIDCommand = new CommandAPDU(0x04, 0x01, 0x00, 0x00, 0x04);

        // Transmit the APDU command and get the response
        ResponseAPDU idResponse = client.sendAPDU(checkEmployeeIDCommand);

        if (idResponse.getSW() == 0x9000) {
            byte[] idData = idResponse.getData();
            Integer id = new BigInteger(idData).intValue();

            // Create the APDU command to get the first name (CLA=0x04, INS=0x02)
            CommandAPDU getFirstNameCommand = new CommandAPDU(0x04, 0x02, 0x00, 0x00, 0x07);

            // Transmit the APDU command and get the response
            ResponseAPDU firstNameResponse = client.sendAPDU(getFirstNameCommand);

            if (firstNameResponse.getSW() == 0x9000) {
                byte[] firstNameData = firstNameResponse.getData();
                String firstName = new String(firstNameData);

                // Create the APDU command to get the last name (CLA=0x04, INS=0x03)
                CommandAPDU getLastNameCommand = new CommandAPDU(0x04, 0x03, 0x00, 0x00, 0x07);

                // Transmit the APDU command and get the response
                ResponseAPDU lastNameResponse = client.sendAPDU(getLastNameCommand);

                if (lastNameResponse.getSW() == 0x9000) {
                    byte[] lastNameData = lastNameResponse.getData();
                    String lastName = new String(lastNameData);

                    System.out.println("First Name: " + firstName);
                    System.out.println("Last Name: " + lastName);

                    return id;
                } else {
                    throw new CardException("Error getting last name: " + Integer.toHexString(lastNameResponse.getSW()));
                }
            } else {
                throw new CardException("Error getting first name: " + Integer.toHexString(firstNameResponse.getSW()));
            }
        } else {
            throw new CardException("Error: " + Integer.toHexString(idResponse.getSW()));
        }
    }
}
