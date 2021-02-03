/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
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
    private Button puzzles;
    @FXML
    private Button exit;
    @FXML
    private Button srowthStatsBtn;
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
    
    @FXML
    private void clickexit(ActionEvent event)
    {
        Platform.exit();
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
        Scanner sc = new Scanner(new File("User Data\\bestTime.txt"));
        sc.useDelimiter("\r\n");
        
        int i = 0;
        String s;
        while(sc.hasNext())
        {            
            s = sc.next();            
            alltimes[i] = Integer.parseInt(s);  
            i++;
        }
        sc.close();
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

    @FXML
    private void drawGraph(ActionEvent event) throws IOException {
//        Parent statsParent = FXMLLoader.load(getClass().getResource("growthStats.fxml"));
//        Scene statsScene = new Scene(statsParent);
//        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        window.setScene(statsScene);
//        window.show();


          //Defining the x axis             
            NumberAxis xAxis = new NumberAxis(1, 6, 1); 
            xAxis.setLabel("Levels"); 

            //Defining the y axis   
            NumberAxis yAxis = new NumberAxis   (0, 150, 10); 
            yAxis.setLabel("Best Time"); 

            //Creating the line chart 
            LineChart linechart = new LineChart(xAxis, yAxis);  

            //Prepare XYChart.Series objects by setting data 
            XYChart.Series series_easy = new XYChart.Series(); 
            series_easy.setName("Easy"); 
            
            XYChart.Series series_med = new XYChart.Series(); 
            series_med.setName("Medium"); 
            
            XYChart.Series series_hard = new XYChart.Series(); 
            series_hard.setName("Hard"); 

            for(int x = 0; x < 18; x++){
                if(x < 6 && alltimes[x] != -1){
                    series_easy.getData().add(new XYChart.Data((x % 6) + 1, alltimes[x]));
                }
                else if(x < 12 && alltimes[x] != -1){
                    series_med.getData().add(new XYChart.Data((x % 6) + 1, alltimes[x])); 
                }
                else if(alltimes[x] != -1){
                    series_hard.getData().add(new XYChart.Data((x % 6) + 1, alltimes[x]));
                }
            }
            
            //Setting the data to Line chart    
            linechart.getData().add(series_easy);
            linechart.getData().add(series_med);
            linechart.getData().add(series_hard);

            //Creating a Group object  
            Group root = new Group(linechart); 

            //Creating a scene object 
            Scene scene = new Scene(root, 630, 700);  

            //Setting title to the Stage 
            stage.setTitle("Line Chart"); 

            //Adding scene to the stage 
            stage.setScene(scene);

            //Displaying the contents of the stage 
            stage.show(); 
    }
    
}
