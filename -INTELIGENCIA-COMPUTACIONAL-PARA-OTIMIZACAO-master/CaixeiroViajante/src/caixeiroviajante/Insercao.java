package caixeiroviajante;

public class Insercao implements Comparable<Insercao> {
	private int cidade;
	private int aresta;
	private float custo;

	public int getCidade() {
		return cidade;
	}

	public void setCidade(int cidade) {
		this.cidade = cidade;
	}

	public int getAresta() {
		return aresta;
	}

	public void setAresta(int aresta) {
		this.aresta = aresta;
	}

	public float getCusto() {
		return custo;
	}

	public void setCusto(float custo) {
		this.custo = custo;
	}

	@Override
	public int compareTo(Insercao ins) {
		return (this.getCusto() < ins.getCusto() ? -1 : (this.getCusto() == ins.getCusto() ? 0 : 1));
	}
}
