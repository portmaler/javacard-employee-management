package com.sisbd.employee;

import javacard.framework.APDU;
import javacard.framework.Applet;
import javacard.framework.ISOException;
import javacard.framework.ISO7816;
import javacard.framework.OwnerPIN;
import javacard.framework.Util;

public class Employee extends Applet {
   
    final static byte CLA = (byte) 0x04;
    final static byte GET_EMPLOYEE_ID = (byte) 0x01;
    final static byte GET_FIRST_NAME = (byte) 0x02;
    final static byte GET_LAST_NAME = (byte) 0x03;
	public static final byte INS_TEST_CODE_PIN = 0x00;

    final static short MAX_ID_SIZE = (short) 11;
    final static short MAX_NAME_SIZE = (short) 7; 
    
    public final static byte MAX_ERROR_PIN = (byte) 0x03;


    public final static byte MAX_PIN_LENGTH = (byte) 0x04;


    private byte[] INIT_PIN = { (byte) 1, (byte) 3,(byte) 9,(byte) 3 };
    

	final static short SW_VERIFICATION_FAILED = 0x6300;


	final static short SW_PIN_VERIFICATION_REQUIRED = 0x6301;


	OwnerPIN pin;
    byte[] employeeID = null;
    byte[] firstName = null;
    byte[] lastName = null;


    private Employee() {
    	
    	pin = new OwnerPIN(MAX_ERROR_PIN, MAX_PIN_LENGTH);

		pin.update(INIT_PIN,(short) 0, (byte) 0x04);
    	
        employeeID = new byte[MAX_ID_SIZE];
        firstName = new byte[MAX_NAME_SIZE];
        lastName = new byte[MAX_NAME_SIZE];

        employeeID[0] = (byte) 0x45;
        employeeID[1] = (byte) 0x32;
        employeeID[2] = (byte) 0x37;
        employeeID[3] = (byte) 0x2d;
        employeeID[4] = (byte) 0x30;
        employeeID[5] = (byte) 0x31;
        employeeID[6] = (byte) 0x39;
        employeeID[7] = (byte) 0x33;
        employeeID[8] = (byte) 0x2f;
        employeeID[9] = (byte) 0x30;
        employeeID[10] = (byte) 0x31;

        firstName[0] = (byte) 0x4d;
        firstName[1] = (byte) 0x49;
        firstName[2] = (byte) 0x43;
        firstName[3] = (byte) 0x48;
        firstName[4] = (byte) 0x41;
        firstName[5] = (byte) 0x45;
        firstName[6] = (byte) 0x4c;

        lastName[0] = (byte) 0x4b;
        lastName[1] = (byte) 0x41;
        lastName[2] = (byte) 0x4d;
        lastName[3] = (byte) 0x55;
        lastName[4] = (byte) 0x4e;
        lastName[5] = (byte) 0x47;
        lastName[6] = (byte) 0x45;

        register();
    } 

    public static void install(byte[] bArray, short bOffset, byte bLength) {
        new Employee();
    }

    public void process(APDU apdu) throws ISOException {
        byte[] buffer = apdu.getBuffer();

        if (selectingApplet()) {
            return;
        }

        if (buffer[ISO7816.OFFSET_CLA] != CLA) {
          
            
            ISOException.throwIt(ISO7816.SW_CLA_NOT_SUPPORTED);
        }

        switch (buffer[ISO7816.OFFSET_INS]) {
        	case INS_TEST_CODE_PIN:
        		VerificatioPIN(apdu);
			break;
            case GET_EMPLOYEE_ID: {
                getEmployeeID(apdu);
                return;
            }
            case GET_FIRST_NAME: {
                getFirstName(apdu);
                return;
            }
            case GET_LAST_NAME: {
                getLastName(apdu);
                return;
            }
            default: {
                ISOException.throwIt(ISO7816.SW_INS_NOT_SUPPORTED);
            }
        } 
    } 

    private void getLastName(APDU apdu) {
        byte[] buffer = apdu.getBuffer();
        Util.arrayCopy(lastName, (short) 0, buffer, (short) 0, MAX_NAME_SIZE);
        apdu.setOutgoingAndSend((short) 0, MAX_NAME_SIZE);} 
		private void VerificatioPIN(APDU apdu){		
	}
    
    
    private void getFirstName(APDU apdu) {
        byte[] buffer = apdu.getBuffer();
        Util.arrayCopy(firstName, (short) 0, buffer, (short) 0, MAX_NAME_SIZE);
        apdu.setOutgoingAndSend((short) 0, MAX_NAME_SIZE);
    } 
    
  

  
    private void getEmployeeID(APDU apdu) {
        byte[] buffer = apdu.getBuffer();
       
        short employee = 123; 
        
        Util.setShort(buffer, (short) 0, employee);
        apdu.setOutgoingAndSend((short) 0, (short) 2);
    }
   
    
	private void VerificationPIN(APDU apdu) {

		byte[] buffer = apdu.getBuffer();

		byte byteRead = (byte) (apdu.setIncomingAndReceive());


		
		 if (pin.check(buffer, ISO7816.OFFSET_CDATA, byteRead) == false)
				ISOException.throwIt(SW_VERIFICATION_FAILED);

		 

	    byte[] responseBuffer = apdu.getBuffer();
	    Util.setShort(responseBuffer, (short) 0, (short) 2);
	    responseBuffer[2] = (byte) 0x01;
	    responseBuffer[3] = (byte) 0x23;
	    
	   
	    apdu.setOutgoingAndSend((short) 0, (short) 4);
	    
	
	}
} 

