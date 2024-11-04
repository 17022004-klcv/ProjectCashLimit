package com.example.cashlimit.validations;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.swing.*;
import java.awt.*;

public class validation {

    public static boolean emptyText(TextField campo){
        if(campo.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Empty field!\nPlease fill in all fields");
            return false;
        }
        return true;
    }
}
