package caixeiroviajante;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class HeuristicasConstrutivas {

	private final float Matriz[][];
	private final HeuristicasDeRefinamento refinamento;

	public HeuristicasConstrutivas(float Matriz[][]) {
		this.Matriz = Matriz;
		refinamento = new HeuristicasDeRefinamento(Matriz);
	}

	public void VizinhoMaisProximo() {
		long inicio = System.currentTimeMillis();
		ArrayList<Integer> Solucao = new ArrayList<Integer>();
		ArrayList<Boolean> Visitado = new ArrayList<Boolean>();
		for (float[] Matriz1 : Matriz) {
			Visitado.add(false);
		}

		Solucao.add(0);
		Visitado.set(0, true);
		int i = 0, index = 0;
		while (Solucao.size() != Matriz.length) {
			float aux = Float.MAX_VALUE;
			for (int j = 0; j < Matriz[i].length; j++) {
				if (i == j || Visitado.get(j) == true) {

				} else if (Matriz[i][j] < aux) {
					aux = Matriz[i][j];
					index = j;
				}
			}
			Solucao.add(index);
			Visitado.set(index, true);
			i = index;
		}
		Solucao.add(0);
		
		System.out.println("Solucao n�o  refinada");
		calculaFuncaoObjetivo(Solucao);
		
		int userChoice = -1;
		Scanner Entrada = new Scanner(System.in);
		
		while(userChoice != 1 && userChoice != 2 && userChoice != 3) {
			System.out.println("Escolha uma heuristica de refinamento");
			System.out.println("1-Metodo da descida");
			System.out.println("2-Metodo da primeira melhora");
			System.out.println("3-Metodo Aleatorio");
			
			userChoice = Entrada.nextInt();
			
			switch(userChoice) {
			case 1:
				Solucao = refinamento.metodoDaDescida(Solucao);
				break;
			case 2:
				Solucao = refinamento.metodoDaPrimeiraMelhora(Solucao);
				break;
			case 3:
				int iteracoes;
				System.out.println("Numero de iteracoes");
				iteracoes = Entrada.nextInt();
				Solucao = refinamento.metodoAleatorio(Solucao, iteracoes);
				break;
			default:
				System.out.println("Entrada invalida");
			}
		}
		
		Solucao = refinamento.metodoDaDescida(Solucao); //refinamento
		
		long fim = System.currentTimeMillis();

		System.out.println("\nSolução do algoritmo do vizinho mais próximo\n");
		for (int k = 0; k < Solucao.size(); k++) {
			if (k == Solucao.size() - 1) {
				System.out.print(Solucao.get(k) + "\n\n");
			} else {
				System.out.print(Solucao.get(k) + " --> ");
			}

		}
		calculaFuncaoObjetivo(Solucao);
		System.out.println("Tempo de execução: " + (fim - inicio) + "\n");
	}

	public void ParcialmenteVizinhoMaisProximo(float Alpha) {
		long inicio = System.currentTimeMillis();
		Random Gerador = new Random();
		ArrayList<Integer> Solucao = new ArrayList<Integer>();
		ArrayList<Boolean> Visitado = new ArrayList<Boolean>();
		ArrayList<Integer> Auxiliar = new ArrayList<Integer>();
		for (float[] Matriz1 : Matriz) {
			Visitado.add(false);
		}

		Solucao.add(0);
		Visitado.set(0, true);

		int i = 0, index = 0;
		while (Solucao.size() != Matriz.length) {
			int TamanhoLRC = Math.round(1 + (Alpha * ((Matriz.length - Solucao.size()) - 1)));
			for (int k = 0; k < TamanhoLRC; k++) {
				float aux = Float.MAX_VALUE;
				for (int j = 0; j < Matriz[i].length; j++) {
					if (i == j || Visitado.get(j) == true || Auxiliar.contains(j)) {

					} else if (Matriz[i][j] < aux) {
						aux = Matriz[i][j];
						index = j;
					}
				}
				Auxiliar.add(index);
			}
			int cidade_escolhida = Gerador.nextInt(Auxiliar.size());

			Solucao.add(Auxiliar.get(cidade_escolhida));
			Visitado.set(Auxiliar.get(cidade_escolhida), true);
			i = Auxiliar.get(cidade_escolhida);
			Auxiliar.clear();
		}
		Solucao.add(0);
		
		System.out.println("Solucao n�o  refinada");
		calculaFuncaoObjetivo(Solucao);
		
		int userChoice = -1;
		Scanner Entrada = new Scanner(System.in);
		
		while(userChoice != 1 && userChoice != 2 && userChoice != 3) {
			System.out.println("Escolha uma heuristica de refinamento");
			System.out.println("1-Metodo da descida");
			System.out.println("2-Metodo da primeira melhora");
			System.out.println("3-Metodo Aleatorio");
			
			userChoice = Entrada.nextInt();
			
			switch(userChoice) {
			case 1:
				Solucao = refinamento.metodoDaDescida(Solucao);
				break;
			case 2:
				Solucao = refinamento.metodoDaPrimeiraMelhora(Solucao);
				break;
			case 3:
				int iteracoes;
				System.out.println("Numero de iteracoes");
				iteracoes = Entrada.nextInt();
				Solucao = refinamento.metodoAleatorio(Solucao, iteracoes);
				break;
			default:
				System.out.println("Entrada invalida");
			}
		}
		
		
		
		long fim = System.currentTimeMillis();

		System.out.println("\nSolução do algoritmo do vizinho mais próximo\n");
		for (int k = 0; k < Solucao.size(); k++) {
			if (k == Solucao.size() - 1) {
				System.out.print(Solucao.get(k) + "\n\n");
			} else {
				System.out.print(Solucao.get(k) + " --> ");
			}

		}
		calculaFuncaoObjetivo(Solucao);
		System.out.println("Tempo de execução: " + (fim - inicio) + "\n");
	}

	public void InsercaoMaisBarata() {
		long inicio = System.currentTimeMillis();
		Random Gerador = new Random();
		ArrayList<Integer> Solucao = new ArrayList();

		// Gera solu��o inicial aleatoria
		for (int i = 0; i < 3; i++) {
			int num = Gerador.nextInt(Matriz.length);
			if (!Solucao.contains(num)) {
				Solucao.add(num);
			}
		}
		Solucao.add(Solucao.get(0));

		// calcula solu��o
		while (Solucao.size() != Matriz.length + 1) {

			int cidadeMaisBarata = -1;
			int arestaParaInsercao = -1;
			float insercaoMaisBarata = Float.MAX_VALUE;

			// percorre todas as cidades
			for (int cidadeAtual = 0; cidadeAtual < Matriz.length; cidadeAtual++) {
				// se a posi��o atual n�o esta na solu��o verifica a sua inser��o
				if (!Solucao.contains(cidadeAtual)) {
					// Verifica as arestas
					for (int aux = 0; aux < Solucao.size() - 1; aux++) {
						// compara valor da inser��o na aresta
						if ((Matriz[Solucao.get(aux)][cidadeAtual] + Matriz[cidadeAtual][Solucao.get(aux + 1)]
								- Matriz[Solucao.get(aux)][Solucao.get(aux + 1)]) < insercaoMaisBarata) {
							insercaoMaisBarata = Matriz[Solucao.get(aux)][cidadeAtual]
									+ Matriz[cidadeAtual][Solucao.get(aux + 1)]
									- Matriz[Solucao.get(aux)][Solucao.get(aux + 1)];
							cidadeMaisBarata = cidadeAtual;
							arestaParaInsercao = aux;
						}
					}
				}
			}
			// Insere a cidade no percurso
			Solucao.add(arestaParaInsercao + 1, cidadeMaisBarata);
		}
		
		System.out.println("Solucao n�o  refinada");
		calculaFuncaoObjetivo(Solucao);
		
		int userChoice = -1;
		Scanner Entrada = new Scanner(System.in);
		
		while(userChoice != 1 && userChoice != 2 && userChoice != 3) {
			System.out.println("Escolha uma heuristica de refinamento");
			System.out.println("1-Metodo da descida");
			System.out.println("2-Metodo da primeira melhora");
			System.out.println("3-Metodo Aleatorio");
			
			userChoice = Entrada.nextInt();
			
			switch(userChoice) {
			case 1:
				Solucao = refinamento.metodoDaDescida(Solucao);
				break;
			case 2:
				Solucao = refinamento.metodoDaPrimeiraMelhora(Solucao);
				break;
			case 3:
				int iteracoes;
				System.out.println("Numero de iteracoes");
				iteracoes = Entrada.nextInt();
				Solucao = refinamento.metodoAleatorio(Solucao, iteracoes);
				break;
			default:
				System.out.println("Entrada invalida");
			}
		}
		
		

		long fim = System.currentTimeMillis();

		// Imprime a solu��o
		System.out.println("\nSolução do algoritmo com inser��o mais barata\n");
		for (int k = 0; k < Solucao.size(); k++) {
			if (k == Solucao.size() - 1) {
				System.out.print(Solucao.get(k) + "\n\n");
			} else {
				System.out.print(Solucao.get(k) + " --> ");
			}

		}

		calculaFuncaoObjetivo(Solucao);
		System.out.println("Tempo de execução: " + (fim - inicio) + "\n");
	}

	public void InsercaoMaisBarataParcialmenteGuloso(float alfa) {
		long inicio = System.currentTimeMillis();
		Random Gerador = new Random();
		ArrayList<Integer> Solucao = new ArrayList();
		ArrayList<Insercao> insercoes;

		// Gera solu��o inicial aleatoria
		for (int i = 0; i < 3; i++) {
			int num = Gerador.nextInt(Matriz.length);
			if (!Solucao.contains(num)) {
				Solucao.add(num);
			}
		}
		Solucao.add(Solucao.get(0));

		// calcula solu��o
		while (Solucao.size() != Matriz.length + 1) {

			insercoes = new ArrayList<Insercao>();

			// percorre todas as cidades
			for (int cidadeAtual = 0; cidadeAtual < Matriz.length; cidadeAtual++) {
				// se a posi��o atual n�o esta na solu��o verifica a sua inser��o
				if (!Solucao.contains(cidadeAtual)) {
					// Verifica as arestas e adiciona os resultados no array
					for (int aux = 0; aux < Solucao.size() - 1; aux++) {
						Insercao ins = new Insercao();
						ins.setCusto(Matriz[Solucao.get(aux)][cidadeAtual] + Matriz[cidadeAtual][Solucao.get(aux + 1)]
								- Matriz[Solucao.get(aux)][Solucao.get(aux + 1)]);
						ins.setCidade(cidadeAtual);
						ins.setAresta(aux);
						insercoes.add(ins);
					}
				}
			}
			// ordena o array
			Collections.sort(insercoes);
			// Insere a cidade no percurso
			int LRC = Math.round(1 + (alfa * (insercoes.size() - 1)));
			Insercao insercaoEscolhida = insercoes.get(Gerador.nextInt(LRC));
			Solucao.add(insercaoEscolhida.getAresta() + 1, insercaoEscolhida.getCidade());
		}
		
		System.out.println("Solucao n�o  refinada");
		calculaFuncaoObjetivo(Solucao);
		
		int userChoice = -1;
		Scanner Entrada = new Scanner(System.in);
		
		while(userChoice != 1 && userChoice != 2 && userChoice != 3) {
			System.out.println("Escolha uma heuristica de refinamento");
			System.out.println("1-Metodo da descida");
			System.out.println("2-Metodo da primeira melhora");
			System.out.println("3-Metodo Aleatorio");
			
			userChoice = Entrada.nextInt();
			
			switch(userChoice) {
			case 1:
				Solucao = refinamento.metodoDaDescida(Solucao);
				break;
			case 2:
				Solucao = refinamento.metodoDaPrimeiraMelhora(Solucao);
				break;
			case 3:
				int iteracoes;
				System.out.println("Numero de iteracoes");
				iteracoes = Entrada.nextInt();
				Solucao = refinamento.metodoAleatorio(Solucao, iteracoes);
				break;
			default:
				System.out.println("Entrada invalida");
			}
		}
		
		
		
		long fim = System.currentTimeMillis();

		// Imprime a solu��o
		System.out.println("\nSolução do algoritmo com inser��o mais barata\n");
		for (int k = 0; k < Solucao.size(); k++) {
			if (k == Solucao.size() - 1) {
				System.out.print(Solucao.get(k) + "\n\n");
			} else {
				System.out.print(Solucao.get(k) + " --> ");
			}

		}

		calculaFuncaoObjetivo(Solucao);
		System.out.println("Tempo de execução: " + (fim - inicio) + "\n");
	}

	public void Aleatorio() {
		long inicio = System.currentTimeMillis();
		Random Gerador = new Random();
		ArrayList<Integer> Solucao = new ArrayList();
		ArrayList<Boolean> Visitado = new ArrayList();
		for (float[] Matriz1 : Matriz) {
			Visitado.add(false);
		}

		Solucao.add(0);
		Visitado.set(0, true);
		int i = 0;
		while (Solucao.size() != Matriz.length) {
			int num = Gerador.nextInt(Matriz.length);
			if (!Solucao.contains(num) && Visitado.get(num) == false) {
				Solucao.add(num);
				Visitado.set(num, true);
			}
		}
		Solucao.add(0);

		System.out.println("Solucao n�o  refinada");
		calculaFuncaoObjetivo(Solucao);
		
		int userChoice = -1;
		Scanner Entrada = new Scanner(System.in);
		
		while(userChoice != 1 && userChoice != 2 && userChoice != 3) {
			System.out.println("Escolha uma heuristica de refinamento");
			System.out.println("1-Metodo da descida");
			System.out.println("2-Metodo da primeira melhora");
			System.out.println("3-Metodo Aleatorio");
			
			userChoice = Entrada.nextInt();
			
			switch(userChoice) {
			case 1:
				Solucao = refinamento.metodoDaDescida(Solucao);
				break;
			case 2:
				Solucao = refinamento.metodoDaPrimeiraMelhora(Solucao);
				break;
			case 3:
				int iteracoes;
				System.out.println("Numero de iteracoes");
				iteracoes = Entrada.nextInt();
				Solucao = refinamento.metodoAleatorio(Solucao, iteracoes);
				break;
			default:
				System.out.println("Entrada invalida");
			}
		}
		
		
		
		long fim = System.currentTimeMillis();

		System.out.println("\nSolução do algoritmo com solução aleatória\n");
		for (int k = 0; k < Solucao.size(); k++) {
			if (k == Solucao.size() - 1) {
				System.out.print(Solucao.get(k) + "\n\n");
			} else {
				System.out.print(Solucao.get(k) + " --> ");
			}

		}

		calculaFuncaoObjetivo(Solucao);
		System.out.println("Tempo de execução: " + (fim - inicio) + "\n");
	}

	public void calculaFuncaoObjetivo(ArrayList<Integer> Solucao) {
		float Distancia = 0;
		for (int i = 0; i < Matriz.length - 1; i++) {
			Distancia += Matriz[(int) Solucao.get(i)][(int) Solucao.get(i + 1)];
		}
		System.out.println("Função Objetiva: " + Distancia + "\n");
	}

}
