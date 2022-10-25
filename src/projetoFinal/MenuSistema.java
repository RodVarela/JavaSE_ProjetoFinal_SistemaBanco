package projetoFinal;

import java.util.Scanner;

public class MenuSistema {

	Scanner entrada = new Scanner(System.in);
	int opcao;
	
	// Menu Inicial
	public void menuInicial() throws Exception {
		System.out.println("-------------------BANCO RV-------------------");
		System.out.println("----------------------------------------------");
		System.out.println("\t Escolha a opção desejada:");
		System.out.println("1 - Interface Banco");
		System.out.println("2 - Interface Cliente");
		System.out.println("0 - Sair");
		System.out.println("----------------------------------------------");

		do {
			try {
				opcao = Integer.parseInt(entrada.nextLine());
				switch (opcao) {
				case 1:
					menuBanco();
					break;
				case 2:
					Conta.acessarConta();
					if (Conta.getAcesso() == true) {
						menuCliente();
					} else {
						menuInicial();
					}
					break;
				case 0:
					System.out.println("Até a próxima! Obrigado!!!!");
					break;

				default:
					System.out.println("Opção incorreta!");
					menuInicial();
				}
			} catch (NumberFormatException e) {
				System.out.println("Valor incorreto. Apenas números.");
				menuInicial();
			}
		} while (opcao != 0);
	}

	// Menu Banco
	public void menuBanco() throws Exception {
		System.out.println("----------------------------------------------");
		System.out.println("\t Sistema Interno do Banco RV");
		System.out.println("----------------------------------------------");
		System.out.println("\t Escolha a opção desejada:");
		System.out.println("1 - Cadastrar / Abrir Conta");
		System.out.println("2 - Lista de Contato Geral");
		System.out.println("3 - Lista de Contato Categoria Cliente");
		System.out.println("0 - Sair");
		System.out.println("----------------------------------------------");

		do {
			try {
				opcao = Integer.parseInt(entrada.nextLine());
				switch (opcao) {
				case 1:
					menuTipoCliente();
					break;
				case 2:
					Dados.imprimirListaContato();
					menuBanco();
					break;
				case 3:
					menuImprimirTipoPessoa();
					menuBanco();
					break;
				case 0:
					menuInicial();
					break;
				default:
					System.out.println("Opção incorreta!");
				}
			} catch (NumberFormatException e) {
				System.out.println("Valor incorreto. Apenas números.");
				menuBanco();
			}
		} while (opcao != 0);
	}

	// Menu tipo cliente pertence ao menu Banco
	public void menuTipoCliente() throws Exception {
		System.out.println("----------------------------------------------");
		System.out.println("\t Escolha a opção desejada:");
		System.out.println("1 - Pessoa Física");
		System.out.println("2 - Pessoa Jurídica");
		System.out.println("0 - Sair");
		System.out.println("----------------------------------------------");

		do {
			try {
				opcao = Integer.parseInt(entrada.nextLine());
				switch (opcao) {
				case 1:
					ClientePF pf = new ClientePF();
					pf.inserirDadosCliente();
					pf.imprimirDados();
					Dados.cadastrarContato(pf);
					Conta.abrirConta(pf);
					menuBanco();
					entrada.nextLine();
					break;

				case 2:
					ClientePJ pj = new ClientePJ();
					pj.inserirDadosCliente();
					pj.imprimirDados();
					Dados.cadastrarContato(pj);
					Conta.abrirConta(pj);
					menuBanco();
					entrada.nextLine();
					break;
				case 0:
					System.out.println("Saindo!!.");
					menuBanco();
					break;
				default:
					System.out.println("Opção incorreta!");
				}
			} catch (NumberFormatException e) {
				System.out.println("Valor incorreto. Apenas números.");
				menuTipoCliente();
			}
		} while (opcao != 0);
	}

	// Menu imprimir tipo Pessoa pertence ao menu Banco
	public void menuImprimirTipoPessoa() throws Exception {
		System.out.println("----------------------------------------------");
		System.out.println("\t Escolha a opção desejada:");
		System.out.println("1 - Pessoa Física");
		System.out.println("2 - Pessoa Jurídica");
		System.out.println("0 - Sair");
		System.out.println("----------------------------------------------");

		do {
			try {
				opcao = Integer.parseInt(entrada.nextLine());
				switch (opcao) {
				case 1:
					Dados.imprimirListaContatoPF();
					menuImprimirTipoPessoa();
					break;

				case 2:
					Dados.imprimirListaContatoPJ();
					menuImprimirTipoPessoa();
					break;
				case 0:
					menuBanco();
					break;
				default:
					System.out.println("Opção incorreta!");
				}
			} catch (NumberFormatException e) {
				System.out.println("Valor incorreto. Apenas números.");
				menuImprimirTipoPessoa();
			}
		} while (opcao != 0);
	}

	// Menu modulo cliente
	public void menuCliente() throws Exception {

		System.out.println("----------------------------------------------");
		System.out.println("\t Escolha a opção desejada:");
		System.out.println("1 - Consultar Saldo");
		System.out.println("2 - Consultar Extrato");
		System.out.println("3 - Depositar");
		System.out.println("4 - Sacar");
		System.out.println("5 - Transferir");
		System.out.println("0 - Sair");

		do {
			try {
				opcao = Integer.parseInt(entrada.nextLine());
				switch (opcao) {
				case 1:
					Conta.consultarSaldo();
					break;
				case 2:
					Transacoes.consultarHistorico();
					menuCliente();	
					break;
				case 3:
					Transacoes.depositar();
					menuCliente();	
					break;
				case 4:
					Transacoes.sacar();
					menuCliente();	
					break;
				case 5:
					Transacoes.transferir();
					menuCliente();
					break;
				case 0:
					Conta.setAcesso(false);
					System.out.println("----------------------------------------------");
					System.out.println("Logout realizado!");
					//entrada.nextLine();
					menuInicial();
					break;
				default:
					System.out.println("Opção incorreta!");
				}
			} catch (NumberFormatException e) {
				System.out.println("Valor incorreto. Apenas números.");
				menuCliente();
			}
		} while (opcao != 0);

	}


}
