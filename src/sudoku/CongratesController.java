/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import static sudoku.Timer.*;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CongratesController implements Initializable
{
    @FXML
    private Label timerLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        timerLabel.setText("You solved it in " + MM + " : " + SS);
    }    
    
}
