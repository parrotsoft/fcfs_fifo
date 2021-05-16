/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fifo;

/**
 *
 * @author mlope
 */
public class Proceso implements Comparable<Proceso>{
    
    private String proceso;
    public int tll;
    private int tRaf;

    public Proceso(String proceso, int tll, int tRaf) {
        this.proceso = proceso;
        this.tll = tll;
        this.tRaf = tRaf;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public int getTll() {
        return tll;
    }

    public void setTll(int tll) {
        this.tll = tll;
    }

    public int gettRaf() {
        return tRaf;
    }

    public void settRaf(int tRaf) {
        this.tRaf = tRaf;
    }

    @Override
    public int compareTo(Proceso t) {
        if (tll < t.tll) {
                return -1;
            }
            if (tll > t.tll) {
                return 1;
            }
            return 0;
    }
    
}
