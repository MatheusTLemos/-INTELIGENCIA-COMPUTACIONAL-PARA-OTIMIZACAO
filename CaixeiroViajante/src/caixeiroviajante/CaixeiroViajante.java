package caixeiroviajante;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CaixeiroViajante {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        Scanner Entrada = new Scanner(System.in);
        LeituraArquivo Leitura = new LeituraArquivo();
        float Matriz[][] = Leitura.LeArquivo();
        Algoritmos Algoritmo = new Algoritmos(Matriz);
        
        int i = 4;
        while(i != 0){
            System.out.println(
            "Comando |                     Descrição                                              |\n" +
            "   1    |  Algoritmo do vizinho mais próximo                                         |\n" +
            "   2    |  Algoritmo de inserção mais barata                                         |\n" +
            "   3    |  Algoritmo com solução de forma aleatória                                  |\n" +
            "   4    |  Algoritmo parcialmente guloso pelo método do vizinho mais próximo         |\n" +
            "   0    |  Sair do programa.                                                         |\n");
            
            i = Entrada.nextInt();
            switch(i){
                case 0:
                    System.out.println("Programa finalizado.\n");
                    Entrada.close();
                    break;
                case 1:
                    Algoritmo.VizinhoMaisProximo();
                    break;
                case 2:
                    Algoritmo.InsercaoMaisBarata();
                    break;
                case 3:
                    Algoritmo.Aleatorio();
                    break;
                case 4:
                    Algoritmo.ParcialmenteVizinhoMaisProximo((float) 0.2);
                    break;
                default:
                    System.out.println("Por favor entre com um tipo de dado válido.\n");
                    break;
            }
        }
        
    }
    
}
