/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import static javafx.scene.layout.GridPane.getRowIndex;

/**
 *
 * @author ASUS
 */
public class FXMLDocumentController implements Initializable
{
    
    @FXML
    private Label box;
    private Label prevbox;
    
    private int[][] grid = new int[9][9];
    
    @FXML
    private void clicknum(ActionEvent event)
    {
        Button b = ((Button)event.getSource());
        String bText = b.getText();
        
        box.setText(bText);
        int c = box.getId().charAt(4) - 48;
        int r = box.getId().charAt(5) - 48;
        
//        System.out.println(c);
        
        if(Integer.parseInt(bText) == grid[c][r])
            box.setStyle("-fx-background-color: rgb(0, 255, 0);");
        else
            box.setStyle("-fx-background-color: rgb(255, 0, 0);");
    }
    
    @FXML
    private void clickclear(ActionEvent event)
    {
        box.setText("");
        box.setStyle("-fx-opaciy: 1;");
    }
    
    @FXML
    private void clickgrid(MouseEvent event)
    {
        Label temp = ((Label)event.getSource());
        String tempStyleClass = temp.getStyleClass().toString();
        
        if(!tempStyleClass.equals("label const"))
        {
            prevbox = box;
            box = ((Label)event.getSource());
        }
        
//        prevbox.setStyle("-fx-opaciy: 1;");
        box.setStyle("-fx-background-color: rgb(0, 0, 200);");
        
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        int[][] temp = {
        {4, 3, 5, 2, 6, 9, 7, 8, 1},
        {6, 8, 2, 5, 7, 1, 4, 9, 3},
        {1, 9, 7, 8, 3, 4, 5, 6, 2},
        {8, 2, 6, 1, 9, 5, 3, 4, 7},
        {3, 7, 4, 6, 8, 2, 9, 1, 5},
        {9, 5, 1, 7, 4, 3, 6, 2, 8},
        {5, 1, 9, 3, 2, 6, 8, 7, 4},
        {2, 4, 8, 9, 5, 7, 1, 3, 6},
        {7, 6, 3, 4, 1, 8, 2 , 5, 9}       
        };
        
        grid = temp;
    }    
    
}
