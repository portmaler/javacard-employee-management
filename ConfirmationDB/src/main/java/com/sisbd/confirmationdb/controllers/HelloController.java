package com.sisbd.confirmationdb.controllers;

import com.sisbd.confirmationdb.HelloApplication;
import com.sisbd.confirmationdb.javacard.EmployeeTask;
import com.sisbd.confirmationdb.utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HelloController implements Initializable {


    private final byte[] entryPin = new byte[4];
    private int indexByte = 0;


    @FXML
    private Button btn0;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnEX;
    @FXML
    private Button btnOk;
    @FXML
    private GridPane gridPin;
    @FXML
    private Label labelField;
    @FXML
    private TextField pinFiels;

    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    protected void onEnterPinButtonClick() throws IOException {
        EmployeeTask validatePINTask = new EmployeeTask();
        ExecutorService executorService = Executors.newCachedThreadPool();
        validatePINTask.setEntryPin(entryPin);
       // validatePINTask.setEntryPin(new byte[]{(byte) 1, (byte) 3, (byte) 9, (byte) 2});
        //Byte[] givenPin = new Byte[]{4,3,2,1};

        executorService.submit(validatePINTask);
        StringBuilder maskedPinBuilder = new StringBuilder();
        for (int i = 0; i < indexByte; i++) {
            maskedPinBuilder.append(entryPin[i]);
        }

    /*  if (Arrays.equals(entryPin, givenPin)) {
            labelField.setText("PIN correct" + indexByte);
            labelField.setStyle("-fx-text-fill: green;");
            HelloApplication.changeScene(Constants.FXML_CONFIRMATION);
        } else {
            labelField.setText("PIN incorrect" + maskedPinBuilder+ " " + indexByte);
            labelField.setStyle("-fx-text-fill: red;");
        }*/
        System.out.println("begin task in class");
        System.out.println("pin = " + maskedPinBuilder);

       /* try {
            HelloApplication.changeScene(Constants.FXML_CONFIRMATION);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        validatePINTask.setOnSucceeded((event) -> {
            System.out.println("begin task iscucces");
            if (validatePINTask.getValue() == true) {
                try {
                    HelloApplication.changeScene(Constants.FXML_CONFIRMATION);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                labelField.setText("PIN incorrect");
                labelField.setStyle("-fx-text-fill: red;");
                System.out.println("code pin is incorrect");
            }
        });


    }

    public void attachCode() {
        //have each button run BTNCODE when clicked
        pinFiels.setEditable(false);
        btn1.setOnAction(this::btncode);
        btn2.setOnAction(this::btncode);
        btn3.setOnAction(this::btncode);
        btn4.setOnAction(this::btncode);
        btn5.setOnAction(this::btncode);
        btn6.setOnAction(this::btncode);
        btn7.setOnAction(this::btncode);
        btn8.setOnAction(this::btncode);
        btn9.setOnAction(this::btncode);
        btn0.setOnAction(this::btncode);
        btnClear.setOnAction(this::btncode);
    }

    private void btncode(ActionEvent e) {
        if (e.getSource() instanceof Button sourceButton) {
            String buttonId = sourceButton.getId();
            if(indexByte == 4){
                if (indexByte > 0) {
                    indexByte--;
                }
            }else if(buttonId.equals("btnClear")){
                if (indexByte > 0) {
                    indexByte--;
                }
            }

            if(indexByte < 4 && indexByte >= 0){
                switch (buttonId) {
                    case "btn1":
                        entryPin[indexByte] = Byte.valueOf(btn1.getText());
                        indexByte++;
                        break;
                    case "btn2":
                        entryPin[indexByte] = Byte.valueOf(btn2.getText());
                        indexByte++;
                        break;
                    case "btn3":
                        entryPin[indexByte] = Byte.valueOf(btn3.getText());
                        indexByte++;
                        break;
                    case "btn4":
                        entryPin[indexByte] = Byte.valueOf(btn4.getText());
                        indexByte++;
                        break;
                    case "btn5":
                        entryPin[indexByte] = Byte.valueOf(btn5.getText());
                        indexByte++;
                        break;
                    case "btn6":
                        entryPin[indexByte] = Byte.valueOf(btn6.getText());
                        indexByte++;
                        break;
                    case "btn7":
                        entryPin[indexByte] = Byte.valueOf(btn7.getText());
                        indexByte++;
                        break;
                    case "btn8":
                        entryPin[indexByte] = Byte.valueOf(btn8.getText());
                        indexByte++;
                        break;
                    case "btn9":
                        entryPin[indexByte] = Byte.valueOf(btn9.getText());
                        indexByte++;
                        break;
                    case "btn0":
                        entryPin[indexByte] = Byte.valueOf(btn0.getText());
                        indexByte++;
                        break;
                   /* case "btnClear":
                        if (indexByte > 0) {
                            indexByte--;
                        }
                        entryPin[indexByte] = null;
                        break;*/
                    default:
                        // Default code
                        break;
                }


                // Update the pinField TextField with asterisks
                StringBuilder maskedPinBuilder = new StringBuilder();
                for (int i = 0; i < indexByte; i++) {
                    maskedPinBuilder.append("*");
                }
                pinFiels.setText(maskedPinBuilder.toString());

            }




        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        attachCode();
    }
}