/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fifo;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author mlope
 */
public class Fifo {

    static Scanner teclado = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        int nProcesos = 0;
        int op = 0;
        
        System.out.println("Digite la Cantidad de procesos :");
        nProcesos = teclado.nextInt();
        Proceso[] procesos = new Proceso[nProcesos];
                    
        do {
            System.out.println("\n\nSOLUCIONA => FCFS o FIFO");
            System.out.println("--- MENU ---");
            System.out.println("1. Digita procesos");
            System.out.println("2. Ver Tabla de procesos");
            System.out.println("3. Ordenar por tiempo de llegada");
            System.out.println("4. Ver Diagrama de Gantt");
            System.out.println("5. Ver Distribución");
            System.out.println("6. Salir");
            System.out.println("Seleccione una opcion : ");
            op = teclado.nextInt();
            
            switch(op) {
                case 1:
                    llenarDatos(procesos);
                    break;
                case 2:
                    imprimirTabla(procesos);
                    break;
                case 3:
                    Arrays.sort(procesos);
                    break;
                case 4:
                    diagramaGantt(procesos);
                    break;
                case 5:
                    distribucion(procesos);
                    break;
                case 6:
                    System.out.println("Lo esperamos pronto....");
                    break;
                default:
                    System.out.println("Debe seleccionar una opcion valida!");
            }
        }while(op != 6);
        
    }
    
    static void llenarDatos(Proceso[] procesos) {
        for (int i = 1; i <= procesos.length; i++) {
            int tll = 0; // Tiempo de llegada
            int tRaf = 0; // Rafaga de CPU
            
            System.err.println("Proceso "+i);
            System.out.println("Tiempo de llegada : ");
            tll = teclado.nextInt();
            System.out.println("Rafaga de CPU : ");
            tRaf = teclado.nextInt();
            
            Proceso proc = new Proceso("P"+i, tll, tRaf);
            procesos[i-1] = proc;
            System.out.println("-----------------------------");
        }
    }
    
    static void imprimirTabla(Proceso[] procesos) {
        System.out.println("TADOS TABULADORS");
        System.out.println("PROCESO\t TIEMPO DE LLEGADA\t RAFAGA DE CPU");
        for (int i = 0; i < procesos.length; i++) {
            System.out.println(procesos[i].getProceso()+"\t\t"+procesos[i].getTll()+"\t\t\t"+procesos[i].gettRaf());
        }
    }
    
    static void diagramaGantt(Proceso[] procesos) {
        System.out.println("\nDIAGRAMA DE GANTT");
        String cadenaHeader = "";
        String cadenaBody = "";
        int dg = 0;
        for (int i = 0; i < procesos.length; i++) {
            cadenaHeader += procesos[i].getProceso() + "\t";
            dg += procesos[i].gettRaf();
            if (i == 0) {
                cadenaBody += "0:"+dg+"\t";
            } else {
                cadenaBody += dg+"\t";
            }
        }
        System.out.println(cadenaHeader);
        System.out.println(cadenaBody);
    }
    
    static void distribucion(Proceso[] procesos) {
        System.out.println("\nDISTRIBUCION");
        System.out.println("PROCESO\t TE\t TR");
        
        int dg = 0; // Acomulador para las rafagas
        double totalE = 0; // Total de espera 
        double totalR = 0; // Total de espera
        double tPromedioE = 0; // Tiempo promedio de espera
        double tPromedioR = 0; // Tiempo promedio de respuesta
        
        for (int i = 0; i < procesos.length; i++) {
            int te = dg-procesos[i].getTll();
            int tr = dg+procesos[i].gettRaf();
            totalE += te; //Sumamos cada uno de los tiempos de espera
            totalR += tr; //Sumamos cada una de los tiempos de respuesta
            dg += procesos[i].gettRaf(); // Ingrementamos las rafagas
            System.out.println(procesos[i].getProceso()+"\t"+te+"\t"+tr);
        }
        
        tPromedioE = totalE/procesos.length;
        tPromedioR = totalR/procesos.length;
        
        System.out.println("----------------------------");
        System.out.println("TPromE: "+tPromedioE+" m/s");
        System.out.println("TPromR: "+tPromedioR+" m/s");
    }
    
}
