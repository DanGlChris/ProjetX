/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx.Object.Design_object;

/**
 *
 * @author DanGlChris
 */
public class Couple_attache_circle implements Comparable{
    private Attache_circle tete, pied;
    public Couple_attache_circle(Attache_circle tete, Attache_circle pied){
        this.tete = tete;
        this.pied = pied;
    }   
    public boolean isEquals(Couple_attache_circle other){
        return getTete().equals(other.getTete()) && getPied().equals(other.getPied());
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
    @Override
    public String toString(){
        return "[tete: "+getTete()+"]\n[pied: "+getPied();
    }
    @Override
    public boolean equals(Object l_autre){
        return (l_autre.getClass().equals(this.getClass()) && ((Couple_attache_circle)l_autre).getTete().equals(getTete())
                && ((Couple_attache_circle)l_autre).getPied().equals(getPied()));
    }
    @Override
    public int compareTo(Object l_autre) {
        return (l_autre.getClass().equals(this.getClass()) && ((Couple_attache_circle)l_autre).getTete().equals(getTete())
                && ((Couple_attache_circle)l_autre).getPied().equals(getPied()))? 0:-1;
    }
}
