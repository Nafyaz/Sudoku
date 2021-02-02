/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.io.FileWriter;
import java.io.IOException;
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
import static sudoku.Intermediate.*;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PuzzlesController implements Initializable
{
    @FXML
    private void clickPuzzle(ActionEvent event) throws Exception
    {
        Button puzzle_no = ((Button)event.getSource());
        String temp = puzzle_no.getId();
        int puzzle = (temp.charAt(6) - 48)*10 + (temp.charAt(7) - 48);
        
        level = puzzle + mode*levels_per_mode;
        
        System.out.println(puzzle + " " + mode + " " + level);
        
        Parent puzzlesParent = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
        Scene puzzlesScene = new Scene(puzzlesParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(puzzlesScene);
        window.show();
    }
    
    @FXML
    private void clickMode(ActionEvent event)
    {
        Button mode_no = ((Button)event.getSource());
        String temp = mode_no.getId();
        
        mode = temp.charAt(4) - 48;
    }
    
    @FXML
    private void clickClearHistory(ActionEvent event) throws IOException
    {
        FileWriter writer = new FileWriter("User Data\\bestTime.csv");
        for(int i = 1; i < 50; i++)
        {
            writer.write(-1 + "\n");
        }
        writer.close();
    }
    
    @FXML
    private void clickback(ActionEvent event) throws Exception
    {
        Parent puzzlesParent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
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
