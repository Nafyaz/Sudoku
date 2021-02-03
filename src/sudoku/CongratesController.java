/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import static sudoku.Intermediate.*;



public class CongratesController implements Initializable
{
    @FXML
    private Label timerLabel, congratesLabel;
    
    @FXML
    private void clickback(ActionEvent event) throws Exception
    {
        Parent puzzlesParent = FXMLLoader.load(getClass().getResource("Puzzles.fxml"));
        Scene puzzlesScene = new Scene(puzzlesParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(puzzlesScene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {        
        timerLabel.setText("You solved it in " + MM + " : " + SS);
        
        if(new_record == true)
        {
            System.out.println("Before");
            congratesLabel.setText("New Record");
            System.out.println("After");
        }
    }    
    
}
