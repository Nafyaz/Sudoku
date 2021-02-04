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
 * FXML Controller class for the puzzles choice page.
 *
 * @author Ishmam
 */
public class PuzzlesController implements Initializable
{
    @FXML
    private Button Mode0, Mode1, Mode2;  
    private Button allmodes[] = new Button[3];
    
    @FXML
    private Button puzzle00, puzzle01, puzzle02,
                   puzzle03, puzzle04, puzzle05;
    private Button allpuzzles[] = new Button[levels_per_mode];
    
    /**
     * Loads the puzzle that has been clicked.
     * @param event Mouse click
     * @throws Exception when files are not loaded correctly
     * @author Ishmam
     */
    @FXML
    private void clickPuzzle(ActionEvent event) throws Exception
    {
        Button puzzle_no = ((Button)event.getSource());
        String temp = puzzle_no.getId();
        int puzzle = (temp.charAt(6) - 48)*10 + (temp.charAt(7) - 48);
        
        level = puzzle + mode*levels_per_mode;
                
        Parent puzzlesParent = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
        Scene puzzlesScene = new Scene(puzzlesParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(puzzlesScene);
        window.show();
    }
    
    /**
     * Controls which levels are locked and which are playable. Sets the color of the selected difficulty level button too.
     * @author Ishmam
     */
    private void puzzle_button_control()
    {
        
//        System.out.println("-------" + mode + "---------");
        
        int i, temp_level;
        for(i = 0; i < levels_per_mode; i++)
        {      
            allpuzzles[i].setDisable(false);
//            System.out.println("Enabled Puzzle " + i);
            
            temp_level = i + mode*levels_per_mode;
            if(alltimes[temp_level] == -1)            
                break;   
        }
                
        i++;
        
        for(; i < levels_per_mode; i++)
        {
//            System.out.println("Disabled Puzzle " + i);
            allpuzzles[i].setDisable(true);
        }
        
//        System.out.println("-------" + mode + "---------");
//        for(i = 0; i < 18; i++)
//            System.out.println(i + " " + alltimes[i]);
        
        for(i = 0; i < 3; i++)
        {
            if(i == mode)
                allmodes[i].setStyle("-fx-background-color: deepskyblue;");
            else
                allmodes[i].setStyle("-fx-opaciy: 1;");
        }
    }
    
    /**
     * Determines which difficulty has been selected.
     * @param event Mouse click
     * @author Ishmam
     */
    @FXML
    private void clickMode(ActionEvent event)
    {
        Button mode_no = ((Button)event.getSource());
        String temp = mode_no.getId();
        
        mode = temp.charAt(4) - 48;
        
        puzzle_button_control();
    }
    
    /**
     * Clears all the best times and locks all unlocked puzzles.
     * @param event Mouse click
     * @throws IOException when it cannot read from file.
     * @author Ishmam
     */
    @FXML
    private void clickClearHistory(ActionEvent event) throws IOException
    {
        FileWriter writer = new FileWriter("User Data\\bestTime.txt");
        for(int i = 0; i < 50; i++)
        {
            alltimes[i] = -1;
            writer.write(-1 + "\r\n");
        }
        writer.close();
        
        puzzle_button_control();
    }
    
    /**
     * Sets the previous scene when the back button is pressed.
     * @param event Mouse click
     * @throws Exception when files are not loaded correctly.
     * @author Ishmam
     */
    @FXML
    private void clickback(ActionEvent event) throws Exception
    {
        Parent puzzlesParent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Scene puzzlesScene = new Scene(puzzlesParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(puzzlesScene);
        window.show();
    }

    /**
     * Returns the current puzzle level
     * @return current level
     * @author Ishmam
     */
    public static int getPuzzleLevel()
    {
        return level;
    }
    
    /**
     * Initializes the controller class.
     * @author Ishmam
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        allpuzzles[0] = puzzle00; allpuzzles[1] = puzzle01; allpuzzles[2] = puzzle02;
        allpuzzles[3] = puzzle03; allpuzzles[4] = puzzle04; allpuzzles[5] = puzzle05;
        
        allmodes[0] = Mode0; allmodes[1] = Mode1; allmodes[2] = Mode2;
        
        puzzle_button_control();
    }    
    
}
