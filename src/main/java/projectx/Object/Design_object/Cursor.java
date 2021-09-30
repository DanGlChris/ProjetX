/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx.Object.Design_object;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 *
 * @author DanGlChris
 */
public class Cursor extends Pane{
    public Cursor(){
        this.setPrefHeight(20);
        this.setPrefWidth(20);
        this.setStyle("-fx-background-radius: 10; -fx-border-radius:10;");
        Pane cursor_img = new Pane();
        try {
            cursor_img = FXMLLoader.load(getClass().getResource("CursorFile/Cursor.fxml"));
        } catch (IOException ex) {
        }
        cursor_img.setStyle("-fx-background-radius:10; -fx-border-radius:10;");
        this.getChildren().add(cursor_img);
        
    }
    
}
