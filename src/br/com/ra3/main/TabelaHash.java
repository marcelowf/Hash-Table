package br.com.ra3.main;

public class TabelaHash {
	private int tamanho;
	private ListaEncadeada[] tabela;

	public TabelaHash(int tamanho) {
		this.tamanho = tamanho;
		tabela = new ListaEncadeada[tamanho];
		for (int i = 0; i < tamanho; i++) {
			tabela[i] = new ListaEncadeada();
		}
	}

	public boolean inserir(Registro registro, int indice) {
		if (tabela[indice].buscar(registro.getCodigo()) == null) {
			tabela[indice].inserir(registro);
			return false;
		} else {
			tabela[indice].inserir(registro);
			return true;
		}
	}

	public Registro buscar(String codigo, int indice) {
		return tabela[indice].buscar(codigo);
	}

	int calcularIndiceResto(String codigo) {
		int valorHash = Integer.parseInt(codigo);
		return valorHash % tamanho;
	}

	int calcularIndiceMultiplicacao(String codigo) {
		int valorHash = Integer.parseInt(codigo);
		double A = (Math.sqrt(5) - 1) / 2;
		return (int) (tamanho * ((valorHash * A) % 1));
	}

	int calcularIndiceDobramento(String codigo) {
		int valorHash = Integer.parseInt(codigo);
		int soma = 0;
		while (valorHash > 0) {
			soma += valorHash % 1000;
			valorHash /= 1000;
		}
		return soma % tamanho;
	}
}
