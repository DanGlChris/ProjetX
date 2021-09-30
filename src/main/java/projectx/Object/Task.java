
package projectx.Object;

import projectx.Object.Design_object.Attache_circle;
import java.util.List;
import projectx.Object.feature.Task_feature;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
/**
 *
 * @author DanGlChris
 */
public class Task implements Task_feature{
    private StringProperty name = new SimpleStringProperty();
    private List<Task> Tasks;
    private boolean state = false;
    private double pourcentatge_Color;//il va falloir creer un dataBase pour acceder a  toute les tache, pour calculer le pourcentage de couleur
    private Color task_color;
    private Timer_Alarm task_timer;
    private StringProperty memo =  new SimpleStringProperty();//il va falloir revoir les manipulation des fichier text 
    //ajouter d'autre variable d'image et de video
    //
    public Task(String name, List<Task> Tasks){
        this.name.set(name);
        this.Tasks = this.Tasks;
    }
    public Task(String name){
        this.name.set(name);
    }

    public StringProperty getNameProprety() {
        return name;
    }
    
    public void setNameProprety(StringProperty str){
        this.name = str;
    }
    public void setName(String name) {
        this.name.set(name);
    }

    public List<Task> getTasks() {
        return Tasks;
    }

    public void setTasks(List<Task> Tasks) {
        this.Tasks = Tasks;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public double getPourcentatge_Color() {
        return pourcentatge_Color;
    }

    public void setPourcentatge_Color(double pourcentatge_Color) {
        this.pourcentatge_Color = pourcentatge_Color;
    }

    public Color getTask_color() {
        return task_color;
    }

    public void setTask_color(Color task_color) {
        this.task_color = task_color;
    }

    public Timer_Alarm getTask_timer() {
        return task_timer;
    }

    public void setTask_timer(Timer_Alarm task_timer) {
        this.task_timer = task_timer;
    }

    public StringProperty getMemoProprety() {
        return memo;
    }

    public void setMemoProprety(StringProperty memo) {
        this.memo = memo;
    }
    public void setMemo(String str){
        this.memo.set(str);
    }

    @Override
    public void Check_and_set_line(Attache_circle attache_circle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
