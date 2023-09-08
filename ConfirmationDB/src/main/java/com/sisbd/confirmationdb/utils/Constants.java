package com.sisbd.confirmationdb.utils;

public class Constants {
    public static String FXML_ITEM_JOB= "fxml/JobItem.fxml";

    public static String APP_TITLE = "Employe JAVACARD";
    public static String FXML_HELLO = "fxml/hello-view.fxml";
    public static String FXML_CONFIRMATION = "fxml/confirmation.fxml";



    // read-only INStruction commands
    public static final byte CLA_MONAPPLET = (byte) 0x04;
    /* Constants */
    public static final byte INS_TEST_CODE_PIN = 0x00;
    public final static byte GET_EMPLOYEE_ID = (byte) 0x01;
    public final static byte GET_FIRST_NAME = (byte) 0x02;
    public final static byte GET_LAST_NAME = (byte) 0x03;

    // constants
    public final static short MAX_ID_SIZE = (short) 11; // *** WATCH THIS!!! **
    public final static short MAX_NAME_SIZE = (short) 7; // *** WATCH THIS!!! **
    public final static byte INS_SELECT = (byte) 0XA4;
    /**
     * SW bytes for success
     */
    public final static int SW_NO_ERROR = (int) 0x9000;

}
