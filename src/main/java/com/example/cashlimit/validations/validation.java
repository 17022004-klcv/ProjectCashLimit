package com.example.cashlimit.validations;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.swing.*;
import java.awt.*;

public class validation {
    //validar campo vacion con espacios
    public static boolean emptyText(TextField campo){
        if(campo.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Empty field!\nPlease fill in all fields");
            return false;
        }
        return true;
    }
    //validar campos vacios y sin espacio
    public static boolean emptyText_Trim(TextField campo) {
        if (campo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Empty field!\nPlease fill in all fields");
            return false;
        }
        return true;
    }

    public static boolean validateNumberFormat(TextField campo) {
        String input = campo.getText().trim();
        if (!input.matches("\\d{4}-\\d{4}")) {
            JOptionPane.showMessageDialog(null, "Invalid format!\nPlease enter in ####-#### format.");
            return false;
        }
        return true;

    }

    public static boolean validateEmail(TextField campo) {
        // Expresión regular para un correo electrónico válido
        String emailPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        boolean isValid = campo.getText().matches(emailPattern);

        if (!isValid) {
            JOptionPane.showMessageDialog(null, "Invalid email format!\nPlease enter a valid email address.");
        }

        return isValid;
    }
//validar montos mayores a cero
    public static boolean validateAmount(TextField campo) {
        String input = campo.getText();
        boolean isValid = input.matches("^(\\d+\\.\\d+|\\d+)$") && Double.parseDouble(input) > 0;

        if (!isValid) {
            JOptionPane.showMessageDialog(null, "Invalid number!\nPlease enter a positive number greater than 0.");
        }
        return isValid;
    }

    public static boolean validateAccountNumber(TextField campo) {
        String accountPattern = "^\\d{16}$";
        boolean isValid = campo.getText().matches(accountPattern);

        if (!isValid) {
            JOptionPane.showMessageDialog(null, "Invalid account number!\nPlease enter a valid 16-digit account number.");
        }
        return isValid;
    }
}
