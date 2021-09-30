/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx.Object.Design_object;

import projectx.Interface.General_data;
import static projectx.Interface.General_data.Circle_zize;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author DanGlChris
 */
public class Attache_circle extends Circle implements General_data, Comparable{
    private boolean Type_attache = false;
    private List<Line_Relational> list_own_line_relational = new ArrayList<Line_Relational>();
    private Attache_circle l_autre_attache;
    public Attache_circle(boolean Type_attache){
        setType_attache(Type_attache);
        this.radiusProperty().bind(Circle_zize);
        Attache_is_deselected();
        this.setOnMousePressed(e->{
            if(e.getButton()==MouseButton.PRIMARY){
                try{
                    if(attache_circle_relational_tempo.isEmpty()){
                        attache_circle_relational_tempo.add(this);
                        Attache_is_selected();
                    }
                    else{
                        if(attache_circle_relational_tempo.contains(this)){
                            attache_circle_relational_tempo.remove(this);
                            Attache_is_deselected();                        
                        }
                        else{
                            if(!attache_circle_relational_tempo.isEmpty() 
                                    && attache_circle_relational_tempo.get(0).Type_attache==this.Type_attache){
                                attache_circle_relational_tempo.get(0).Attache_is_deselected();
                                attache_circle_relational_tempo.clear();
                                attache_circle_relational_tempo.add(this);
                                Attache_is_selected();
                            }
                        }
                    }
                }catch (NullPointerException h){}
            }
        });
    }
    public void Attache_is_selected(){
        this.setFill(Color.LIGHTGREEN);
        this.setStroke(Color.GREEN);
        this.setStrokeWidth(2);
    }
    public void Attache_is_deselected(){
        this.setFill(Color.DARKGRAY);
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(2);
    }
    public boolean isType_attache() {
        return Type_attache;
    }

    public void setType_attache(boolean Type_attache) {
        this.Type_attache = Type_attache;
    }

    public List<Line_Relational> getList_own_line_relational() {
        return list_own_line_relational;
    }

    public Attache_circle getL_autre_attache() {
        return l_autre_attache;
    }

    public void setL_autre_attache(Attache_circle l_autre_attache) {
        this.l_autre_attache = l_autre_attache;
    }
    @Override
    public boolean equals(Object o){
        return (o.getClass().equals(this.getClass()) && this.isType_attache()==((Attache_circle)o).isType_attache()
                && this.getList_own_line_relational()==((Attache_circle)o).getList_own_line_relational())? true:false;
    }
    @Override
    public int compareTo(Object o) {
        return (o.getClass().equals(this.getClass()) && this.equals(o))? 0:-1;
    }
    
}
