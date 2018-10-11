package caixeiroviajante;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class LeituraArquivo {
    
    private final RandomAccessFile Raf;
    
    public LeituraArquivo() throws FileNotFoundException{
        Raf = new RandomAccessFile(".\\C50.txt","r");
    }
    
    public float[][] LeArquivo() throws IOException{
        //Leitura de arquivo colocando em dois arrays (ponto x e ponto y)
        ArrayList<Integer> x = new ArrayList();
        ArrayList<Integer> y = new ArrayList();
        String aux;
        Raf.seek(0);
        while(Raf.getFilePointer() < Raf.length()){
            aux = Raf.readLine();
            aux = aux.replace("   ", " ");
            aux = aux.replace("  ", " ");
            if(aux.charAt(0) == ' '){
                aux = aux.substring(1);
            }
            String[] Resultado = aux.split(" ");
            x.add(Integer.parseInt(Resultado[1]));
            y.add(Integer.parseInt(Resultado[2]));
        }

        //Criação da matriz, já calculando a distância euclidiana dos pontos
        float Matriz [] [] = new float [x.size()][y.size()];
        for(int i = 0 ; i < x.size() ; i++){
            Matriz [i][i] = 0;
            for(int j = i+1 ; j < y.size() ; j++){
                Matriz [i][j] = (float) Math.sqrt( Math.pow((x.get(i) - x.get(j)) ,2) + Math.pow ((y.get(i) - y.get(j)),2));
                Matriz [j][i] = Matriz [i][j];
            }
        }
        return Matriz;
    }
}
