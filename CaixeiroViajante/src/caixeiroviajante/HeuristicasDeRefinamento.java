package caixeiroviajante;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class HeuristicasDeRefinamento {
	private float matriz[][];

	public HeuristicasDeRefinamento(float matriz[][]) {
		this.matriz = matriz;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Integer> metodoDaDescida(ArrayList<Integer> solucao) {
		System.out.println("refinando...");
		ArrayList<Integer> solucaoAlternativa;
		ArrayList<Integer> menorSolucao = null;
		float menorDistancia = calculaFuncaoObjetivo(solucao);
		boolean ocorreuMudanca = false;

		for (int aux = 0; aux < solucao.size(); aux++) {
			for (int aux2 = aux + 1; aux2 < solucao.size(); aux2++) {
				solucaoAlternativa = (ArrayList<Integer>) solucao.clone();
				Collections.swap(solucaoAlternativa, aux, aux2);
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
		System.out.println("refinando...");
		ArrayList<Integer> solucaoAlternativa;
		ArrayList<Integer> menorSolucao = null;
		float menorDistancia = calculaFuncaoObjetivo(solucao);
		boolean ocorreuMudanca = false;

		for (int aux = 0; aux < solucao.size(); aux++) {
			for (int aux2 = aux + 1; aux2 < solucao.size(); aux2++) {
				solucaoAlternativa = (ArrayList<Integer>) solucao.clone();
				Collections.swap(solucaoAlternativa, aux, aux2);
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
		System.out.println("refinando...");
		ArrayList<Integer> solucaoAlternativa;
		ArrayList<Integer> menorSolucao = (ArrayList<Integer>) solucao.clone();
		float menorDistancia = calculaFuncaoObjetivo(solucao);
		Random Gerador = new Random();
		
		for ( ; iteracoes>0; iteracoes--) {
			int rand1 = Gerador.nextInt(solucao.size());
			int rand2 = Gerador.nextInt(solucao.size());
			
			while(rand1==rand2) {
				rand2 = Gerador.nextInt(solucao.size());
			}
			
			solucaoAlternativa = (ArrayList<Integer>) menorSolucao.clone();
			Collections.swap(solucaoAlternativa, rand1, rand2);
			
			if (menorDistancia > calculaFuncaoObjetivo(solucaoAlternativa)) {
				menorSolucao = (ArrayList<Integer>) solucaoAlternativa.clone();
				menorDistancia = calculaFuncaoObjetivo(solucaoAlternativa);
			}
		}
		return menorSolucao;
	}

	public float calculaFuncaoObjetivo(ArrayList<Integer> Solucao) {
		float distancia = 0;
		for (int i = 0; i < matriz.length - 1; i++) {
			distancia += matriz[(int) Solucao.get(i)][(int) Solucao.get(i + 1)];
		}
		return distancia;
	}
}