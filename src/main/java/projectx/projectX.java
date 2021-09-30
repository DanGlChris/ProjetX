/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import projectx.Object.Design_object.Task_design;
import projectx.Object.Design_object.Couple_attache_circle;
import projectx.Object.Design_object.Line_Relational;
import projectx.Object.Design_object.Attache_circle;
import projectx.Interface.General_data;
import projectx.Object.Task;

import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projectx.Object.Design_object.Cursor;

/**
 *
 * @author DanGlChris
 */
public class projectX extends Application implements General_data{
    private KeyCombination Ctrl_X = new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN);
    private boolean Ctrl_pressed = false;
    @Override
    public void start(Stage stage) throws Exception {
        HBox sous_menu0 = FXMLLoader.load(getClass().getResource("menu/sous_menu0.fxml"));
        sous_menu0.maxHeight(35);
        VBox root = new VBox(sous_menu0);
        ScrollPane affiche_tree = new ScrollPane(pane_principale);
        root.getChildren().add(affiche_tree);
        affiche_tree.setStyle("-fx-hbar-policy: always;"
                + "-fx-vbar-policy: always;");
        affiche_tree.prefHeightProperty().bind(stage.heightProperty().subtract(35));
        affiche_tree.setOnScroll(e->{
            if(e.getDeltaY()>0 && isCtrl_pressed()){
                Zoom_value.setValue(Zoom_value.get()+1);
                System.out.println(Zoom_value.get());
            }
            else if(e.getDeltaY()<0  && isCtrl_pressed()){
                Zoom_value.setValue(Zoom_value.get()-1);
                System.out.println(Zoom_value.get());
            }
        });
        Task_design task_design = new Task_design(new Task("Tache1"), pane_principale);

        pane_principale.getChildren().addAll(task_design);

        affiche_tree.setOnKeyPressed(e->{
            System.out.println("touche appuyé");
            if(Ctrl_X.match(e) || e.getCode()==KeyCode.DELETE){
                System.out.println("touche Ctrl+X appuyé");
                Removing_line(list_line_relational_tempo);
            }
            if(e.getCode()==KeyCode.CONTROL){
                System.out.println("ctrl pressed");
                setCtrl_pressed(true);
            }
        });
        affiche_tree.setOnKeyReleased(k->{
            if(k.getCode()==KeyCode.CONTROL){
                System.out.println("ctrl released");
                setCtrl_pressed(false);
            }

        });
        Cursor cursor = new Cursor();
        pane_principale.getChildren().add(cursor);
        PosCursorX.bind(cursor.translateXProperty());
        PosCursorY.bind(cursor.translateYProperty());
        affiche_tree.setOnMousePressed(e->{
            if(e.getButton()==MouseButton.MIDDLE){
                cursor.setTranslateX(e.getX()-10);
                cursor.setTranslateY(e.getY()-10);
                System.out.println(PosCursorX.getValue()+ " , " + PosCursorY.getValue());
            }
        });
        //pour a chaque fois centrer les object, je peux binder les layout des task avec le centre de maniere a ce que la distance entre les deux soit proportionnel au zoom
        affiche_tree.setOnMouseReleased(h->{
            pane_principale.getChildren().remove(cursor);
            pane_principale.getChildren().add(cursor);
            if(h.getButton()== MouseButton.PRIMARY && Current_Action.getValue()=="delete_task"){
                System.out.println("Supprimer");
                Current_Action.set("");
                pane_principale.getChildren().removeAll(list_task_design_tempo);
                list_task_design_tempo.forEach(task_designs->{
                    task_designs = null;
                });
                list_task_design_tempo.clear();
            }
        });

        Scene scene = new Scene(root, 400, 400);
        stage.setTitle("Project X - Mind Mapping DanGlChris");
        stage.setScene(scene);
        stage.show();
        affiche_tree.requestFocus();
    }
    private void Removing_line(List<Line_Relational> liste){/// faire un retour d'action;
        pane_principale.getChildren().removeAll(liste);
        list_line_relational.removeAll(liste);
        liste.forEach((line) -> {
            Attache_circle tete = line.getStart_Task().getPied();
            Attache_circle pied = line.getEnd_Task().getTete();
            attache_Relational.remove(new Couple_attache_circle(tete, pied));
            tete.getList_own_line_relational().remove(line);
            pied.getList_own_line_relational().remove(line);
        });
        liste.clear();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public boolean isCtrl_pressed() {
        return Ctrl_pressed;
    }

    public void setCtrl_pressed(boolean Ctrl_pressed) {
        this.Ctrl_pressed = Ctrl_pressed;
    }

}
