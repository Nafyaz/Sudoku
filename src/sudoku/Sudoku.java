/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Main Class that holds the stage for the application.
 * @author Nafis
 */
public class Sudoku extends Application
{
    
    /**
     * Function that sets and shows the loaded scene in the stage.
     * @param stage JavaFx stage
     * @throws Exception when file is not loaded correctly.
     * @author Nafis
     */
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        
        Scene scene = new Scene(root);
        
//        stage.setFullScreen(true);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launches the application.
     * @param args the command line arguments
     * @author Nafis
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
