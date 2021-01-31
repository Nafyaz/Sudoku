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
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PuzzlesController implements Initializable
{
    private static int level;
    @FXML
    private void clickPuzzle(ActionEvent event) throws Exception
    {
        Button puzzle_no = ((Button)event.getSource());
        String temp = puzzle_no.getId();
        level = (temp.charAt(6) - 48)*10 + (temp.charAt(7) - 48);
        
        Parent puzzlesParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene puzzlesScene = new Scene(puzzlesParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(puzzlesScene);
        window.show();
    }

    public static int getPuzzleLevel()
    {
        return level;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
}
