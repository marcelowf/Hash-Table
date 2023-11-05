package br.com.ra3.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print(
				"Escolha o tamanho do vetor da tabela hash\n\n1) 10\n2) 100\n3) 1000\n4) 10000\n5) 100000\n\nEscolha: ");
		int escolhaTamanhoDoVetorDaTabela = scanner.nextInt();
		int tamanhoDoVetor = 0;
		while (escolhaTamanhoDoVetorDaTabela < 1 || escolhaTamanhoDoVetorDaTabela > 5) {
			System.out.print("Tamanho não válido, escolha outro: ");
			escolhaTamanhoDoVetorDaTabela = scanner.nextInt();
		}
		if (escolhaTamanhoDoVetorDaTabela == 1) {
			tamanhoDoVetor = 10;
		} else if (escolhaTamanhoDoVetorDaTabela == 2) {
			tamanhoDoVetor = 100;
		} else if (escolhaTamanhoDoVetorDaTabela == 3) {
			tamanhoDoVetor = 1000;
		} else if (escolhaTamanhoDoVetorDaTabela == 4) {
			tamanhoDoVetor = 10000;
		} else if (escolhaTamanhoDoVetorDaTabela == 5) {
			tamanhoDoVetor = 100000;
		}
		System.out.println("Tamanho da tabela: " + tamanhoDoVetor);
		TabelaHash tabelaHash = new TabelaHash(tamanhoDoVetor);
		System.out.print(
				"Escolha o tamanho da entrada\n\n1) 20 mil\n2) 100 mil\n3) 500 mil\n4) 1 milhão\n5) 5 milhões\n\nEscolha: ");
		int escolhaTamanhoDaEntrada = scanner.nextInt();
		int tamanhoDaEntrada = 0;
		while (escolhaTamanhoDaEntrada < 1 || escolhaTamanhoDaEntrada > 5) {
			System.out.print("Tamanho não válido, escolha outro: ");
			escolhaTamanhoDaEntrada = scanner.nextInt();
		}
		if (escolhaTamanhoDaEntrada == 1) {
			tamanhoDaEntrada = 20000;
		} else if (escolhaTamanhoDaEntrada == 2) {
			tamanhoDaEntrada = 100000;
		} else if (escolhaTamanhoDaEntrada == 3) {
			tamanhoDaEntrada = 500000;
		} else if (escolhaTamanhoDaEntrada == 4) {
			tamanhoDaEntrada = 1000000;
		} else if (escolhaTamanhoDaEntrada == 5) {
			tamanhoDaEntrada = 5000000;
		}
		String arquivoDeTexto = "src\\dados\\dados_" + tamanhoDaEntrada + ".txt";
		System.out.println("Escolha a função de hash:");
		System.out.println("1) Resto da Divisão");
		System.out.println("2) Multiplicação");
		System.out.println("3) Dobramento");
		int escolhaFuncaoHash = scanner.nextInt();

		while (escolhaFuncaoHash < 1 || escolhaFuncaoHash > 3) {
			System.out.print("Opção inválida, escolha outra: ");
			escolhaFuncaoHash = scanner.nextInt();
		}
		long startTime = System.nanoTime(); // Marca o início da inserção
		int colisoes = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(arquivoDeTexto));
			String linha;
			while ((linha = reader.readLine()) != null) {
				String codigo = linha.trim();
				Registro registro = new Registro(codigo);
				int indice;
				switch (escolhaFuncaoHash) {
				case 1:
					indice = tabelaHash.calcularIndiceResto(codigo);
					break;
				case 2:
					indice = tabelaHash.calcularIndiceMultiplicacao(codigo);
					break;
				case 3:
					indice = tabelaHash.calcularIndiceDobramento(codigo);
					break;
				default:
					indice = tabelaHash.calcularIndiceResto(codigo);
					break;
				}
				if (tabelaHash.inserir(registro, indice)) {
					colisoes++;
				}
			}
			reader.close();
			long endTime = System.nanoTime();
			long elapsedTime = (endTime - startTime) / 1000;
			System.out.println("Tempo de inserção (μs): " + elapsedTime);
			System.out.println("Número de colisões: " + colisoes);
		} catch (IOException e) {
			System.err.println("Erro ao ler o arquivo: " + e.getMessage());
		}

		System.out.print("Digite o código do registro que deseja buscar ou 0 para sair: ");
		int codigo = scanner.nextInt();

		while (codigo < 0 || codigo > 999999999) {
			System.out.print("Opção inválida, escolha outro código: ");
			codigo = scanner.nextInt();
		}
		if (codigo == 0) {
			System.out.println("Saindo");
		} else {
			int indice;
			switch (escolhaFuncaoHash) {
			case 1:
				indice = tabelaHash.calcularIndiceResto(Integer.toString(codigo));
				break;
			case 2:
				indice = tabelaHash.calcularIndiceMultiplicacao(Integer.toString(codigo));
				break;
			case 3:
				indice = tabelaHash.calcularIndiceDobramento(Integer.toString(codigo));
				break;
			default:
				indice = tabelaHash.calcularIndiceResto(Integer.toString(codigo));
				break;
			}
			Registro registro = tabelaHash.buscar(Integer.toString(codigo), indice);
			if (registro != null) {
				System.out.println("Registro " + registro.getCodigo() + " encontrado no indice " + indice);
			} else {
				System.out.println("Registro não encontrado.");
			}
		}
		scanner.close();
	}
}