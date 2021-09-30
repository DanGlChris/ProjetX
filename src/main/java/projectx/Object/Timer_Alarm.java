/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx.Object;//il sera affilier dans la base de données pour gerer les alarm
import org.joda.time.*;
public class Timer_Alarm {
    private DateTime dateTime;//sera transferer dans le database
    public Timer_Alarm(){
    }
    public Timer_Alarm(DateTime dateTime){
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }
}
