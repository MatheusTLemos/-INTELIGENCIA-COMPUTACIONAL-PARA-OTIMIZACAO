package caixeiroviajante;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class HeuristicasDeRefinamento {

    private float matriz[][];

    public HeuristicasDeRefinamento(float matriz[][]) {
        this.matriz = matriz;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Integer> metodoDaDescida(ArrayList<Integer> solucao) {

        ArrayList<Integer> solucaoAlternativa;
        ArrayList<Integer> menorSolucao = null;
        float menorDistancia = calculaFuncaoObjetivo(solucao);
        boolean ocorreuMudanca = false;

        for (int aux = 0; aux < solucao.size() - 1; aux++) {
            for (int aux2 = aux + 1; aux2 < solucao.size() - 1; aux2++) {
                solucaoAlternativa = (ArrayList<Integer>) solucao.clone();
                Collections.swap(solucaoAlternativa, aux, aux2);
                if (aux == 0) {
                    solucaoAlternativa.set(solucaoAlternativa.size() - 1, solucaoAlternativa.get(0));
                }
                if (menorDistancia > calculaFuncaoObjetivo(solucaoAlternativa)) {
                    menorSolucao = (ArrayList<Integer>) solucaoAlternativa.clone();
                    menorDistancia = calculaFuncaoObjetivo(solucaoAlternativa);
                    ocorreuMudanca = true;
                }
            }
        }
        if (!ocorreuMudanca) {
            return solucao;
        }
        return metodoDaDescida(menorSolucao);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Integer> metodoDaPrimeiraMelhora(ArrayList<Integer> solucao) {

        ArrayList<Integer> solucaoAlternativa;
        ArrayList<Integer> menorSolucao = null;
        float menorDistancia = calculaFuncaoObjetivo(solucao);
        boolean ocorreuMudanca = false;

        for (int aux = 0; aux < solucao.size() - 1; aux++) {
            for (int aux2 = aux + 1; aux2 < solucao.size() - 1; aux2++) {
                solucaoAlternativa = (ArrayList<Integer>) solucao.clone();
                Collections.swap(solucaoAlternativa, aux, aux2);
                if (aux == 0) {
                    solucaoAlternativa.set(solucaoAlternativa.size() - 1, solucaoAlternativa.get(0));
                }
                if (menorDistancia > calculaFuncaoObjetivo(solucaoAlternativa)) {
                    menorSolucao = (ArrayList<Integer>) solucaoAlternativa.clone();
                    menorDistancia = calculaFuncaoObjetivo(solucaoAlternativa);
                    ocorreuMudanca = true;
                    break;
                }
            }
            if (ocorreuMudanca) {
                break;
            }
        }
        if (!ocorreuMudanca) {
            return solucao;
        }
        return metodoDaPrimeiraMelhora(menorSolucao);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Integer> metodoAleatorio(ArrayList<Integer> solucao, int iteracoes) {

        ArrayList<Integer> solucaoAlternativa;
        ArrayList<Integer> menorSolucao = (ArrayList<Integer>) solucao.clone();
        float menorDistancia = calculaFuncaoObjetivo(solucao);
        Random Gerador = new Random();

        for (int aux = 0; aux < iteracoes; aux++) {
            int rand1 = Gerador.nextInt(solucao.size());
            int rand2 = Gerador.nextInt(solucao.size());

            while (rand1 == rand2) {
                rand2 = Gerador.nextInt(solucao.size());
            }

            solucaoAlternativa = (ArrayList<Integer>) menorSolucao.clone();
            Collections.swap(solucaoAlternativa, rand1, rand2);

            if (menorDistancia > calculaFuncaoObjetivo(solucaoAlternativa)) {
                menorSolucao = (ArrayList<Integer>) solucaoAlternativa.clone();
                menorDistancia = calculaFuncaoObjetivo(solucaoAlternativa);
                aux = 0;
            }
        }
        return menorSolucao;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Integer> metodoVND(ArrayList<Integer> solucao) {
        Random Gerador = new Random();
        ArrayList<Integer> solucaoAlternativa;
        for (int i = 0; i < 5; i++) {
            solucaoAlternativa = (ArrayList<Integer>) solucao.clone();
            for (int j = 0; j <= i; j++) {
                int rand1 = Gerador.nextInt(solucao.size());
                int rand2 = Gerador.nextInt(solucao.size());

                while (rand1 == rand2) {
                    rand2 = Gerador.nextInt(solucao.size());
                }

                if ((rand1 == 0 || rand2 == 0) && (rand2 != solucao.size() - 1) || rand1 != solucao.size() - 1) {
                    Collections.swap(solucaoAlternativa, rand1, rand2);
                    solucaoAlternativa.set(solucaoAlternativa.size() - 1, solucaoAlternativa.get(0));

                } else if ((rand1 == solucao.size() - 1 || rand2 == solucao.size() - 1) && (rand2 != 0 || rand1 != 0)) {
                    Collections.swap(solucaoAlternativa, rand1, rand2);
                    solucaoAlternativa.set(0, solucaoAlternativa.get(solucaoAlternativa.size() - 1));
                } else {
                    Collections.swap(solucaoAlternativa, rand1, rand2);
                }
            }
            solucaoAlternativa = metodoDaDescida(solucaoAlternativa);

            if (calculaFuncaoObjetivo(solucao) > calculaFuncaoObjetivo(solucaoAlternativa)) {
                solucao = solucaoAlternativa;
                i = 0;
            }
        }
        return solucao;
    }

    @SuppressWarnings("unchecked")
	public ArrayList<Integer> metodoTabu(ArrayList<Integer> solucao, int BTmax) {
        int iter = 0;
        int melhorInt = 0;

        LinkedList<Object> tabu = new LinkedList<Object>();
        ArrayList<Integer> melhorSolucao = (ArrayList<Integer>) solucao.clone();

        ArrayList<Integer> solucaoAlternativa = (ArrayList<Integer>) solucao.clone();

        while (iter - melhorInt <= BTmax) {
            iter++;
            
            ArrayList<Integer> menorSolucao = (ArrayList<Integer>) solucaoAlternativa.clone();
            float menorDistancia = Float.MAX_VALUE;
            
            if (tabu.size() > 150) {
            	tabu.clear();
            }
            
            for (int aux = 0; aux < solucao.size() - 1; aux++) {
                for (int aux2 = aux + 1; aux2 < solucao.size() - 1; aux2++) {
                    solucaoAlternativa = (ArrayList<Integer>) menorSolucao.clone();
                    Collections.swap(solucaoAlternativa, aux, aux2);
                    if (aux == 0) {
                        solucaoAlternativa.set(solucaoAlternativa.size() - 1, solucaoAlternativa.get(0));
                    }
                    if (menorDistancia > calculaFuncaoObjetivo(solucaoAlternativa)) {
                    	if (!tabu.contains(solucaoAlternativa)) {
                    		menorSolucao = (ArrayList<Integer>) solucaoAlternativa.clone();
                        	menorDistancia = calculaFuncaoObjetivo(solucaoAlternativa);
                    	}
                    }
                }
            }

            tabu.add(menorSolucao);
            
            if (calculaFuncaoObjetivo(melhorSolucao) > calculaFuncaoObjetivo(menorSolucao)) {

                melhorInt = iter;
                melhorSolucao = menorSolucao;

            }
            
            solucaoAlternativa = (ArrayList<Integer>) menorSolucao.clone();
        }
        
        return melhorSolucao;
    }

    public float calculaFuncaoObjetivo(ArrayList<Integer> Solucao) {
        float distancia = 0;
        for (int i = 0; i < matriz.length - 1; i++) {
            distancia += matriz[(int) Solucao.get(i)][(int) Solucao.get(i + 1)];
        }
        return distancia;
    }
}
