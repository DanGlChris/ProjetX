/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx.Object.Design_object;

import projectx.Interface.General_data;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

/**
 *
 * @author DanGlChris
 */
public class Line_Relational extends Line implements General_data{
    private boolean etat = false;
    private Task_design Start_Task, End_Task;
    public Line_Relational(){
        super();
        MouseEventclick();
    }
    public Line_Relational(double x1, double y1, double x2, double y2){
        super(x1, y1, x2, y2);
        MouseEventclick();
    }
    private void MouseEventclick(){
        this.setStrokeWidth(2);
        this.setStrokeLineCap(StrokeLineCap.ROUND);
        this.setStroke(Color.BLACK);
        this.setOnMouseClicked(e->{
            if(e.getButton() == MouseButton.SECONDARY){
                if(!isEtat()){
                    list_line_relational_tempo.add(this);
                    Line_is_selected();
                    setEtat(true);
                }else{
                    list_line_relational_tempo.remove(this);
                    Line_is_deselected();
                    setEtat(false);
                }
            }
        });
        this.setOnMouseEntered(e->{
            if(!isEtat())MouseOverline();
        });
        this.setOnMouseExited(e->{
            if(!isEtat())Line_is_deselected();
        });
    }
    public void MouseOverline(){
        this.setStrokeWidth(3);
        this.setStroke(new Color(0.2, 0.9, 0.3, 1));        
    }
    public void Line_is_selected(){
        this.setStrokeWidth(2.5);
        this.setStroke(Color.GREEN.brighter());
    }
    public void Line_is_deselected(){
        this.setStrokeWidth(2);
        this.setStroke(Color.BLACK);
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public Task_design getStart_Task() {
        return Start_Task;
    }

    public void setStart_Task(Task_design Start_Task) {
        this.Start_Task = Start_Task;
    }

    public Task_design getEnd_Task() {
        return End_Task;
    }

    public void setEnd_Task(Task_design End_Task) {
        this.End_Task = End_Task;
    }
    
}
