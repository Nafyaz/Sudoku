/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static sudoku.Intermediate.*;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class WelcomeController implements Initializable
{   
    @FXML
    private void clickexit(ActionEvent event)
    {
        Platform.exit();
    }
    
    @FXML
    private void clickpuzzles(ActionEvent event) throws Exception
    {
        Parent puzzlesParent = FXMLLoader.load(getClass().getResource("Puzzles.fxml"));
        Scene puzzlesScene = new Scene(puzzlesParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(puzzlesScene);
        window.show();
    }
    
    @FXML
    private void clickHowToPlay(ActionEvent event) throws Exception
    {
        Parent puzzlesParent = FXMLLoader.load(getClass().getResource("HowToPlay.fxml"));
        Scene puzzlesScene = new Scene(puzzlesParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(puzzlesScene);
        window.show();
    }
    
    private void load_levels() throws FileNotFoundException
    {
        //load easy levels
        Scanner sc = new Scanner(new File("Levels\\levels.csv"));
        sc.useDelimiter(",|\r\n"); 
        
        int i = 0, j = 0, k = 0, num;
        String s;
        while(sc.hasNext()) 
        {
            s = sc.next();
            num = Integer.parseInt(s);            
            alllevels[i][j][k] = num;
            
            k++;
            if(k > 8)
            {
                k = 0;
                j++;
            }
            if(j > 8)
            {
                j = 0;
                i++;
            }
            
        }
        sc.close();
        
        //load normal levels
        sc = new Scanner(new File("Levels\\levels_med.csv"));
        sc.useDelimiter(",|\r\n"); 
        while(sc.hasNext()) 
        {
            s = sc.next();
            num = Integer.parseInt(s);            
            alllevels[i][j][k] = num;
            
            k++;
            if(k > 8)
            {
                k = 0;
                j++;
            }
            if(j > 8)
            {
                j = 0;
                i++;
            }
            
        }
        sc.close();
        
        //load hard levels
        sc = new Scanner(new File("Levels\\levels_hard.csv"));
        sc.useDelimiter(",|\r\n"); 
        while(sc.hasNext()) 
        {
            s = sc.next();
            num = Integer.parseInt(s);            
            alllevels[i][j][k] = num;
            
            k++;
            if(k > 8)
            {
                k = 0;
                j++;
            }
            if(j > 8)
            {
                j = 0;
                i++;
            }
            
        }
        sc.close();
    }
    
    private void load_times() throws FileNotFoundException //Load user time
    {                
        Scanner sc = new Scanner(new File("User Data\\bestTime.csv"));
        sc.useDelimiter(",");
        
        int i = 0;
        String s;
        while(sc.hasNext())
        {            
            s = sc.next();            
            alltimes[i] = Integer.parseInt(s);  
            i++;
        }
        sc.close();
        
//        int[][] solved = {
//        {4, 3, 5, 2, 6, 9, 7, 8, 1},
//        {6, 8, 2, 5, 7, 1, 4, 9, 3},
//        {1, 9, 7, 8, 3, 4, 5, 6, 2},
//        {8, 2, 6, 1, 9, 5, 3, 4, 7},
//        {3, 7, 4, 6, 8, 2, 9, 1, 5},
//        {9, 5, 1, 7, 4, 3, 6, 2, 8},
//        {5, 1, 9, 3, 2, 6, 8, 7, 4},
//        {2, 4, 8, 9, 5, 7, 1, 3, 6},
//        {7, 6, 3, 4, 1, 8, 2 , 5, 9}       
//        };

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        mode = 0;
        if(all_loaded == false)
        {
            try
            {
                load_levels();
                load_times();
            }
            catch(FileNotFoundException ex)
            {
                Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            all_loaded = true;                       
        }
        
    }    
    
}
