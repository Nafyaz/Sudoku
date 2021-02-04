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
 * FXML Controller class for the growth statistics page
 *
 * @author Tauseef, Ishmam
 */
public class GrowthStatsController implements Initializable
{
    @FXML 
    private Button backBtn;
    
    /**
     * Sets the previous scene when the back button is pressed.
     * @param event Mouse click
     * @throws Exception Exception when files are not loaded correctly.
     * @author Tauseef
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
     * Initializes the controller class and draws the line plot.
     * @author Ishmam, Tauseef
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //Defining the x axis             
        NumberAxis xAxis = new NumberAxis(1, 6, 1); 
        xAxis.setLabel("Levels"); 

        //Defining the y axis   
        NumberAxis yAxis = new NumberAxis   (0, 420, 20); 
        yAxis.setLabel("Best Time in seconds"); 

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
        

        Stage stage = (Stage)((Node)growthstatsevent.getSource()).getScene().getWindow();   
        
        
        Group root = new Group(); 
        root.getChildren().add(linechart);
        
        backBtn.setStyle(
                        "-fx-background-color: #37474f;\n" +
                        "    -fx-text-fill: white;\n" +
                        "    -fx-font-size: 24px;\n" +
                        "    -fx-alignment: center;"
                        );
                
        root.getChildren().add(backBtn);
        Scene scene = new Scene(root, 700, 630);
        stage.setTitle("Line Chart"); 
        stage.setScene(scene);

        //Displaying the contents of the stage 
        stage.show(); 
    }    
    
}
