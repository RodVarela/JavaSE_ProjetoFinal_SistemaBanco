package projetoFinal;

import java.util.ArrayList;


public class Dados{

	// Metodos estaticos podem ser acessados sem precisar instanciar objetos à
	// classe.

	static ArrayList<Cliente> contatos = new ArrayList<Cliente>();

	public static void cadastrarContato(Cliente cliente) {
		contatos.add(cliente);
		System.out.println("Contato cadastrado com sucesso!");
		
	}

	public static void imprimirListaContato() {
		System.out.println("### LISTA DE CLIENTES GERAL ###");
		for (Cliente cliente : contatos) {
			System.out.println(cliente);
		}
		System.out.println("Contatos Cadastrados: " + contatos.size());
	}

	
	//Imprimir lista de Contato Pessoa Fisica
	public static void imprimirListaContatoPF() {
		System.out.println("### LISTA DE CLIENTES - PF ###");
		int count = 0;
		for (Cliente cliente : contatos) {
			if (cliente.tipoPessoa == CategoriaPessoa.PF) {
				System.out.println(cliente);
				count = count + 1;
			}
		}
		System.out.println("Contatos Cadastrados: " + count);
	}

	//Imprimir lista de Contato Pessoa Juridica
		public static void imprimirListaContatoPJ() {
			System.out.println("### LISTA DE CLIENTES - PJ ###");
			int count = 0;
			for (Cliente cliente : contatos) {
				if (cliente.tipoPessoa == CategoriaPessoa.PJ) {
					System.out.println(cliente);
					count = count + 1;
				}
			}
			System.out.println("Contatos Cadastrados: " + count);
		}
		
}
