/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx.menu;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import projectx.Interface.General_data;
import projectx.Object.Design_object.Task_design;
import projectx.Object.Task;

/**
 * FXML Controller class
 *
 * @author DanGlChris
 */
public class Sous_menu0Controller implements Initializable, General_data {

    /**
     * Initializes the controller class.
     */
    @FXML
    public void Add_new_task_component(MouseEvent e){
        if(e.isPrimaryButtonDown()){
            Task_design task_design4 = new Task_design(new Task("Tache1"), pane_principale);
            pane_principale.getChildren().add(task_design4);
            System.out.println(PosCursorX.getValue()+ " , " + PosCursorY.getValue());
            task_design4.setTranslateX(PosCursorX.getValue()+10); task_design4.setTranslateY(PosCursorY.getValue()+10);
        }   
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
