/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx.Interface;

import projectx.Object.Design_object.Attache_circle;
import projectx.Object.Design_object.Line_Relational;
import projectx.Object.Design_object.Task_design;
import projectx.Object.Design_object.Couple_attache_circle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.AnchorPane;
import projectx.Object.Design_object.Cursor;

/**
 *
 * @author DanGlChris
 */
public interface General_data {
    public String defaut_name_task = "default_task";
    public StringProperty Current_Action = new SimpleStringProperty("");
    public DoubleProperty Zoom_value = new SimpleDoubleProperty(50);
    public DoubleProperty task_obj_prefX = new SimpleDoubleProperty(90);
    public DoubleProperty task_obj_prefY = new SimpleDoubleProperty(130);
    public DoubleProperty Circle_zize = new SimpleDoubleProperty(4);
    public DoubleProperty Square_size = new SimpleDoubleProperty(90);
    public AnchorPane pane_principale = new AnchorPane();
    public List<Attache_circle> attache_circle_relational_tempo = new ArrayList<Attache_circle>();
    public List<Couple_attache_circle> attache_Relational = new ArrayList<Couple_attache_circle>();
    public List<Line_Relational> list_line_relational = new ArrayList<Line_Relational>();
    public List<Line_Relational> list_line_relational_tempo = new ArrayList<Line_Relational>();
    public Set<Line_Relational> list_line_relational_task_attache = new HashSet<Line_Relational>();
    public Map<Attache_circle, Task_design> list_attache_taskD = new HashMap<>();
    public List<Task_design> list_task_design_tempo = new ArrayList<>();
    // trouver une solution pour que le ligne soient toujours en avant plan
    public Cursor cursor = new Cursor();
    public DoubleProperty PosCursorX = new SimpleDoubleProperty(0);
    public DoubleProperty PosCursorY = new SimpleDoubleProperty(0);
}
