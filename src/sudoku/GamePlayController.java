/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.io.*;
import java.util.Scanner;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import static javafx.scene.layout.GridPane.getRowIndex;
import javafx.stage.Stage;
import static sudoku.Intermediate.*;


/**
 *
 * @author ASUS
 */
public class GamePlayController implements Initializable
{
    @FXML Parent root;
    
    @FXML
    private Label box, bestTime;
    private Label alllabels[][] = new Label[9][9];
    
    private long start, stop;
    
    private int[][] grid = new int[9][9];
    private int[][] ques = new int[9][9];
    
    
    @FXML
    private Label grid00, grid01, grid02, grid03, grid04, grid05, grid06, grid07, grid08, 
                grid10, grid11, grid12, grid13, grid14, grid15, grid16, grid17, grid18, 
                grid20, grid21, grid22, grid23, grid24, grid25, grid26, grid27, grid28, 
                grid30, grid31, grid32, grid33, grid34, grid35, grid36, grid37, grid38, 
                grid40, grid41, grid42, grid43, grid44, grid45, grid46, grid47, grid48, 
                grid50, grid51, grid52, grid53, grid54, grid55, grid56, grid57, grid58, 
                grid60, grid61, grid62, grid63, grid64, grid65, grid66, grid67, grid68, 
                grid70, grid71, grid72, grid73, grid74, grid75, grid76, grid77, grid78, 
                grid80, grid81, grid82, grid83, grid84, grid85, grid86, grid87, grid88;
    
    @FXML
    private Label puzzleHeader, modeHeader;
    
    private boolean checkcell(int r, int c)
    {
        int val = grid[r][c];
        
        for(int i = 0; i < 9; i++)
        {
            if(i != c && grid[r][i] == val)
                return false;
        }
        
        for(int i = 0; i < 9; i++)
        {
            if(i != r && grid[i][c] == val)
                return false;
        }
                
        int rowSt = r/3*3, colSt = c/3*3;
        
        for(int i = rowSt; i < rowSt+3; i++)
        {
            for(int j = colSt; j < colSt+3; j++)
            {
                if(i != r && j != c && grid[i][j] == val)
                    return false;
            }
        }
        
        return true;
    }
    
    private void check_all() throws Exception
    {
        int i, j, wrong = 0;
        for(i = 0; i < 9; i++)
        {
            for(j = 0; j < 9; j++)
            {
                if(ques[i][j] == 0 && grid[i][j] != 0)
                {
                    if(checkcell(i, j))
                        alllabels[i][j].setStyle("-fx-background-color: rgb(135, 248, 105);");
                    else
                    {
                        alllabels[i][j].setStyle("-fx-background-color: rgb(255, 51, 51);");
                        wrong++;
                    }
                }
                else if(ques[i][j] == 0 && grid[i][j] == 0)
                    wrong++;
            }
        }

        if(wrong == 0)
        {
            stop = System.currentTimeMillis();
            
            new_record = false;
            
            SS = (stop - start)/1000;    
            MM = SS / 60;
            SS = SS - MM*60;
            
            if(alltimes[level] == -1 || SS < alltimes[level])
            {
                new_record = true;
                alltimes[level] = (int)MM*60 + (int) SS;
                
                bestTime.setText(MM + " : " + SS); 

                FileWriter writer = new FileWriter("User Data\\bestTime.csv");
                for(i = 0; i < 50; i++)
                {
                    writer.write(alltimes[i] + "\n");
                }
                writer.close();
            }            
            
                                    
            Parent CongratesParent = FXMLLoader.load(getClass().getResource("Congrates.fxml"));
            Scene CongratesScene = new Scene(CongratesParent);

            Stage window = new Stage();
            window.setScene(CongratesScene);
            window.show();
        }
    }
    
    private void clearcolors()
    {
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                if(ques[i][j] == 0)
                {
                    alllabels[i][j].setStyle("-fx-opaciy: 1;");  
                }
            }
        }
    }
    
    @FXML
    private void clicknum(ActionEvent event) throws Exception
    {
        Button b = ((Button)event.getSource());
        String bText = b.getText();
        
        if(box != null)
        {
            clearcolors();
            box.setStyle("-fx-background-color: lightcyan;");
            box.setText(bText);
            int r = box.getId().charAt(4) - 48;
            int c = box.getId().charAt(5) - 48;

            grid[r][c] = Integer.parseInt(bText);  
            
            check_all();
        }      
    }
    
    @FXML
    private void clickclear(ActionEvent event) throws Exception
    {
        if(box != null)
        {
            box.setText("");
            int r = box.getId().charAt(4) - 48;
            int c = box.getId().charAt(5) - 48;

            grid[r][c] = 0;  
            box.setStyle("-fx-background-color: lightcyan;");
            
            check_all();
        }
    }
    
    @FXML
    private void clickgrid(MouseEvent event)
    {
        clearcolors();
        
        Label temp = ((Label)event.getSource());
        
        int r = temp.getId().charAt(4) - 48;
        int c = temp.getId().charAt(5) - 48;
        
        
        if(ques[r][c] == 0)
        {
            box = temp;
            box.setStyle("-fx-background-color: lightcyan;");
        }
        else
            box = null;
    }
    
        
    @FXML
    private void clickback(ActionEvent event) throws Exception
    {
        Parent puzzlesParent = FXMLLoader.load(getClass().getResource("Puzzles.fxml"));
        Scene puzzlesScene = new Scene(puzzlesParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(puzzlesScene);
        window.show();
    }
    
    
    
    void loadLevel(int level)
    {
        for(int i = 0; i < 9; i++)
        {
            grid[i] = Arrays.copyOf(alllevels[level][i], 9);
            ques[i] = Arrays.copyOf(alllevels[level][i], 9);
        }
        
        int mm, ss;
        ss = alltimes[level];
        if(ss == -1)
            bestTime.setText("--:--"); 
        else
        {
            mm = ss / 60;
            ss = ss - mm*60;
            bestTime.setText(mm + " : " + ss); 
        }        
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {        
        alllabels[0][0]= grid00; alllabels[0][1]= grid01; alllabels[0][2]= grid02; alllabels[0][3]= grid03; alllabels[0][4]= grid04; alllabels[0][5]= grid05; alllabels[0][6]= grid06; alllabels[0][7]= grid07; alllabels[0][8]= grid08; 
        alllabels[1][0]= grid10; alllabels[1][1]= grid11; alllabels[1][2]= grid12; alllabels[1][3]= grid13; alllabels[1][4]= grid14; alllabels[1][5]= grid15; alllabels[1][6]= grid16; alllabels[1][7]= grid17; alllabels[1][8]= grid18; 
        alllabels[2][0]= grid20; alllabels[2][1]= grid21; alllabels[2][2]= grid22; alllabels[2][3]= grid23; alllabels[2][4]= grid24; alllabels[2][5]= grid25; alllabels[2][6]= grid26; alllabels[2][7]= grid27; alllabels[2][8]= grid28; 
        alllabels[3][0]= grid30; alllabels[3][1]= grid31; alllabels[3][2]= grid32; alllabels[3][3]= grid33; alllabels[3][4]= grid34; alllabels[3][5]= grid35; alllabels[3][6]= grid36; alllabels[3][7]= grid37; alllabels[3][8]= grid38; 
        alllabels[4][0]= grid40; alllabels[4][1]= grid41; alllabels[4][2]= grid42; alllabels[4][3]= grid43; alllabels[4][4]= grid44; alllabels[4][5]= grid45; alllabels[4][6]= grid46; alllabels[4][7]= grid47; alllabels[4][8]= grid48; 
        alllabels[5][0]= grid50; alllabels[5][1]= grid51; alllabels[5][2]= grid52; alllabels[5][3]= grid53; alllabels[5][4]= grid54; alllabels[5][5]= grid55; alllabels[5][6]= grid56; alllabels[5][7]= grid57; alllabels[5][8]= grid58; 
        alllabels[6][0]= grid60; alllabels[6][1]= grid61; alllabels[6][2]= grid62; alllabels[6][3]= grid63; alllabels[6][4]= grid64; alllabels[6][5]= grid65; alllabels[6][6]= grid66; alllabels[6][7]= grid67; alllabels[6][8]= grid68; 
        alllabels[7][0]= grid70; alllabels[7][1]= grid71; alllabels[7][2]= grid72; alllabels[7][3]= grid73; alllabels[7][4]= grid74; alllabels[7][5]= grid75; alllabels[7][6]= grid76; alllabels[7][7]= grid77; alllabels[7][8]= grid78; 
        alllabels[8][0]= grid80; alllabels[8][1]= grid81; alllabels[8][2]= grid82; alllabels[8][3]= grid83; alllabels[8][4]= grid84; alllabels[8][5]= grid85; alllabels[8][6]= grid86; alllabels[8][7]= grid87; alllabels[8][8]= grid88;        
 
               
        loadLevel(level);
        
        
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                if(ques[i][j] != 0)
                {
                    alllabels[i][j].setStyle("-fx-background-color: rgb(180, 181, 181);");
                    alllabels[i][j].setText(String.valueOf(ques[i][j]));
                }
            }
        }
        
        puzzleHeader.setText("Puzzle " + (level - mode*levels_per_mode + 1));
        switch(mode)
        {
            case 0:
                modeHeader.setText("Easy");
                break;
            case 1:
                modeHeader.setText("Normal");
                break;
            case 2:
                modeHeader.setText("Hard");
                break;
        }
        
        
        
        start = System.currentTimeMillis();
        
    }    
    
}