/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx.Object.Design_object;

import projectx.Interface.General_data;
import javafx.scene.layout.VBox;
import projectx.Object.Task;
import projectx.Object.Design_object.Attache_circle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import projectx.Object.feature.Task_feature;
import java.io.IOException;
import java.util.Set;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class Task_design extends VBox implements General_data,Task_feature{
    //cette objet permet de voir une premiere aprecu d'une tache
    private double posX,  posY;
    private VBox bottom_enregist=new VBox();
    private BorderPane en_tete = new BorderPane();
    private AnchorPane Parents;
    private Attache_circle tete, pied;
    private boolean bt_hover = false;
    public Task_design(Task task, AnchorPane parents){
        this.Parents = parents;
        this.setStyle("-fx-background-color: white;"+
            "-fx-border-color: #039ED3;");
        task_obj_prefX.bind(Zoom_value.divide(50).multiply(task_obj_prefX.get()));
        task_obj_prefY.bind(Zoom_value.divide(50).multiply(task_obj_prefY.get()));
        
        //this.prefWidthProperty().bind(task_obj_prefX);
        
        en_tete.setPadding(new Insets(2));
        Attache_circle attache_haute = new Attache_circle(true);
        setTete(attache_haute);
        list_attache_taskD.put(attache_haute, this);
        en_tete.getChildren().add(attache_haute);
        attache_haute.centerXProperty().bind(en_tete.widthProperty().divide(2));
        attache_haute.centerYProperty().bind(en_tete.heightProperty().divide(2));
        this.getChildren().add(en_tete);
        Pane img_exit = new Pane();
        try{
            img_exit = FXMLLoader.load(getClass().getResource("task_design/exit_btn/exit_btn.fxml"));
            en_tete.setRight(img_exit);
            //img_exit.translateXProperty().bind(Zoom_value.divide(50).multiply(en_tete.widthProperty().subtract(2.38)));            
        }catch (IOException h){}
        
        Rectangle rectangle = new Rectangle();
        rectangle.widthProperty().bind(Zoom_value.divide(50).multiply(Square_size.getValue()));
        rectangle.heightProperty().bind(Zoom_value.divide(50).multiply(Square_size.getValue()));
        rectangle.setFill(Color.GRAY);
        rectangle.setStyle("-fx-border-width: 2; -fx-border-color: black;");     
        rectangle.xProperty().bindBidirectional(this.translateXProperty());
        rectangle.yProperty().bindBidirectional(this.translateYProperty());  
        
        Label task_tag = new Label(defaut_name_task);
        task_tag.textProperty().bind(task.getNameProprety());
        VBox tag_affiche = new VBox(task_tag);
        setBottom_enregist(tag_affiche);
        tag_affiche.setAlignment(Pos.CENTER);
        this.getChildren().addAll(rectangle, tag_affiche);
        
        Attache_circle attache = new Attache_circle(false);
        setPied(attache);
        tag_affiche.getChildren().add(attache);
        list_attache_taskD.put(attache, this);
        tag_affiche.setPadding(new Insets(5));
        tag_affiche.maxHeightProperty().setValue(30.0);
        rectangle.setOnMousePressed(h->{
            if(h.getButton()== MouseButton.PRIMARY){
                setPosX(h.getX());
                setPosY(h.getY());
            }
        });
        rectangle.setOnMouseDragged(e ->{
            if(e.getButton()== MouseButton.PRIMARY){
                double draggedDistanceX = e.getX() - getPosX();
                double draggedDistanceY = e.getY() - getPosY();
            
                setPosX(e.getX());
                setPosY(e.getY());
            
                double curMinX = rectangle.getX();
                double curMinY = rectangle.getY();
            
                double newMinX = curMinX + draggedDistanceX;
                double newMinY = curMinY + draggedDistanceY;
            
                rectangle.setX(newMinX);
                rectangle.setY(newMinY);
                
            }
        });
        this.setOnMousePressed(e->{
            getParents().getChildren().remove(this);
            getParents().getChildren().add(this);
            getParents().getChildren().removeAll(list_line_relational);
            getParents().getChildren().addAll(list_line_relational);
        });
        img_exit.setOnMousePressed(h->{
            if(h.getButton()==MouseButton.PRIMARY){
                Current_Action.set("delete_task");
                list_line_relational_task_attache.addAll(attache_haute.getList_own_line_relational());
                list_line_relational_task_attache.addAll(attache.getList_own_line_relational());
                list_line_relational_tempo.removeAll(list_line_relational_task_attache);
                Removing_line(list_line_relational_task_attache);
                list_task_design_tempo.add(this);
            }
            
        });
        attache.setL_autre_attache(attache_haute);
        attache_haute.setL_autre_attache(attache);
        Check_and_set_line(attache_haute);
        Check_and_set_line(attache);
        
    }
    @Override
    public void Check_and_set_line(Attache_circle attache_circle){
        attache_circle.setOnMouseClicked(e->{
            /*
            une fois l'attache cliqué du bouton Droit, on verifie
            si l'attache stockée dans la liste general est de type different
            et s'il est dans une qutre tache
            */
            if(e.getButton()== MouseButton.PRIMARY && !attache_circle_relational_tempo.isEmpty() 
                    && attache_circle.isType_attache()!=attache_circle_relational_tempo.get(0).isType_attache()
                    && attache_circle.getL_autre_attache()!=attache_circle_relational_tempo.get(0)
                    && !Already_bind(attache_circle) && !Other_already_bind(attache_circle)){
                Attache_circle attache = attache_circle_relational_tempo.get(0);
                Line_Relational line = new Line_Relational();
                line.startXProperty().bind(attache_circle.layoutXProperty()
                        .add(this.translateXProperty())
                        .add((!attache_circle.isType_attache()) ? this.getBottom_enregist().layoutXProperty():this.getEn_tete().widthProperty().divide(2)));
                line.startYProperty().bind(attache_circle.layoutYProperty()
                        .add(this.translateYProperty())
                        .add((!attache_circle.isType_attache()) ? this.getBottom_enregist().layoutYProperty():this.getEn_tete().heightProperty().divide(2)));
                line.endXProperty().bind(attache.layoutXProperty()
                        .add(list_attache_taskD.get(attache).translateXProperty())
                        .add(((!attache.isType_attache()) ? list_attache_taskD.get(attache).getBottom_enregist().layoutXProperty():
                                list_attache_taskD.get(attache).getEn_tete().widthProperty().divide(2))));
                line.endYProperty().bind(attache.layoutYProperty()
                        .add(list_attache_taskD.get(attache).translateYProperty())
                        .add(((!attache.isType_attache()) ? list_attache_taskD.get(attache).getBottom_enregist().layoutYProperty():
                                list_attache_taskD.get(attache).getEn_tete().heightProperty().divide(2))));
                pane_principale.getChildren().add(line);
                
                //on supprime l'attache de la lite principale et on le met dans la liste Map<pied, tete>
                attache_Relational.add(!attache_circle.isType_attache()? new Couple_attache_circle(attache_circle, attache):
                        new Couple_attache_circle(attache, attache_circle));
                attache_circle_relational_tempo.get(0).Attache_is_deselected();
                attache_circle_relational_tempo.clear();
                
                //on definie les different lien(tache) attache de la ligne
                line.setStart_Task(!attache_circle.isType_attache() ? this:list_attache_taskD.get(attache));
                line.setEnd_Task(attache_circle.isType_attache() ? this:list_attache_taskD.get(attache));
                attache.getList_own_line_relational().add(line);
                attache_circle.getList_own_line_relational().add(line);
                list_line_relational.add(line);
            }
            else{
                if(e.getButton()== MouseButton.PRIMARY && !attache_circle_relational_tempo.isEmpty()){
                    attache_circle_relational_tempo.get(0).Attache_is_deselected();
                    attache_circle_relational_tempo.clear();
                    attache_circle_relational_tempo.add(attache_circle);
                    attache_circle.Attache_is_selected();
                }
            }
        });
    }
    private void Removing_line(Set<Line_Relational> liste){/// faire un retour d'action
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
    private boolean Already_bind(Attache_circle attache){//pour verifier s"il existe deja la combinaison de ses deux point selectionenr
        Attache_circle attacheP = attache_circle_relational_tempo.get(0);
        return !attache.isType_attache()?attache_Relational.contains(new Couple_attache_circle(attache, attacheP))
                :attache_Relational.contains(new Couple_attache_circle(attacheP, attache));
    }
    private boolean Other_already_bind(Attache_circle attache){
        Attache_circle attache1 = attache.getL_autre_attache();
        Attache_circle attache2 = attache_circle_relational_tempo.get(0).getL_autre_attache();
        return !attache1.isType_attache()? attache_Relational.contains(new Couple_attache_circle(attache1, attache2)):
                attache_Relational.contains(new Couple_attache_circle(attache2, attache1));        
    }
    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public VBox getBottom_enregist() {
        return bottom_enregist;
    }

    public void setBottom_enregist(VBox bottom_enregist) {
        this.bottom_enregist = bottom_enregist;
    }

    public AnchorPane getParents() {
        return Parents;
    }

    public void setParents(AnchorPane Parents) {
        this.Parents = Parents;
    }

    public Attache_circle getTete() {
        return tete;
    }

    public void setTete(Attache_circle tete) {
        this.tete = tete;
    }

    public Attache_circle getPied() {
        return pied;
    }

    public void setPied(Attache_circle pied) {
        this.pied = pied;
    }

    public boolean isBt_hover() {
        return bt_hover;
    }

    public void setBt_hover(boolean bt_hover) {
        this.bt_hover = bt_hover;
    }

    public BorderPane getEn_tete() {
        return en_tete;
    }

    public void setEn_tete(BorderPane en_tete) {
        this.en_tete = en_tete;
    }
    
}
