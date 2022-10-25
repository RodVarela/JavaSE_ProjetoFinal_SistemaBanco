package projetoFinal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Conta {

	static Scanner entrada = new Scanner(System.in);
	static Random geraNumero = new Random();
	private static boolean status = false;
	private static double saldoCC, saldoCP;
	private static double limiteChequeEspecial;
	private static boolean acesso = false;
	static String tipoConta;
	private static int numeroConta;
	private static Double primeiroDeposito;

	static ArrayList<Integer> contasC = new ArrayList<Integer>();
	static ArrayList<Integer> contasP = new ArrayList<Integer>();

	/// MÉTODO PARA ABRIR CONTA CORRENTE COM SWITCH
	public static void abrirConta(Cliente cliente) throws IOException {
		System.out.println("------------------------------------------------------");
		System.out.println("\t ABERTURA DE CONTA");
		System.out.println("Digite 1 para Conta Corrente ou 2 para Conta Poupança.");
		int opcao = Integer.parseInt(entrada.nextLine());
		switch (opcao) {
		case 1:
			if (cliente.getStatus() != true && cliente.getContaCorrente() != true) {
				cliente.setStatus(true);
				cliente.setContaCorrente(true);
				Conta.setNumeroConta(1 + geraNumero.nextInt(99999));
				contasC.add(Conta.getNumeroConta());
				Conta.setSaldoCC(0);
				Conta.setLimiteChequeEspecial(-1000);
				Conta.setTipoConta("Conta Corrente");
				depositoInicial();
				System.out.println("----------------------------------------------");
				System.out.print("Conta Corrente aberta com sucesso! ");
				System.out.println("C/C " + Conta.getNumeroConta());
				System.out.println("Saldo Inicial: " + Conta.getSaldoCC());
				System.out.println("Cheque Especial autorizado: " + (Conta.getLimiteChequeEspecial() * -1));
				System.out.println("----------------------------------------------");
				Conta.gravarArquivo(cliente);
				Conta.lerArquivo();
				System.out.println("----------------------------------------------");
				break;
			} else {
				System.out.println("Erro! Conta Corrente para este cliente já existe!");
			}
		case 2:
			if (cliente.getStatus() != true || cliente.getContaPoupanca() != true) {
				cliente.setStatus(true);
				cliente.setContaPoupanca(true);
				Conta.setNumeroConta(1 + geraNumero.nextInt(99999));
				contasP.add(Conta.getNumeroConta());
				Conta.setSaldoCP(0);
				Conta.setTipoConta("Conta Poupança");
				depositoInicial();
				System.out.println("----------------------------------------------");
				System.out.print("Conta Poupança aberta com sucesso! ");
				System.out.println("C/P " + Conta.getNumeroConta());
				System.out.println("Saldo Inicial: " + Conta.getSaldoCP());
				System.out.println("----------------------------------------------");
				Conta.gravarArquivo(cliente);
				Conta.lerArquivo();
				System.out.println("----------------------------------------------");
				break;
			} else {
				System.out.println("Erro! Conta Poupança para este cliente já existe!");
			}

		default:
			System.out.println("Opção inválida! Digite 1 para Conta Corrente ou 2 para Conta Poupança.");
		}

		Conta.setStatus(true);
	}

	// Método para gravar dados em um arquivo 
	public static void gravarArquivo(Cliente cliente) throws IOException {

		try {
			FileOutputStream gravarArquivo = new FileOutputStream("pasta/arquivo.txt");
			PrintWriter pr = new PrintWriter(gravarArquivo);

			pr.println(cliente.tipoPessoa + " | " + Conta.getTipoConta() + " | " + Conta.getNumeroConta());
			pr.close();
			gravarArquivo.close();
			System.out.println("Arquivo gravado com sucesso!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Método para ler dados do arquivo
	public static void lerArquivo() throws IOException {

		FileInputStream ler = new FileInputStream("pasta/arquivo.txt");
		InputStreamReader input = new InputStreamReader(ler);
		BufferedReader br = new BufferedReader(input);
		String linha;
		do {
			linha = br.readLine();
			if (linha != null) {
				System.out.println("Valor gravado = " + linha);
			}
		} while (linha != null);

		br.close();
	}

	
	// Método depósito Inicial
	private static void depositoInicial() {
		Conta.setPrimeiroDeposito(0d);
		
		do {
			try {
				System.out.println("Digite o valor inicial do depósito:");
				Conta.setPrimeiroDeposito(entrada.nextDouble());
			} catch (InputMismatchException e) {
				System.out.println("Dados inválidos! Digite apenas números!");
				entrada.nextLine();
			} 
		} while (Conta.getPrimeiroDeposito() < 0);
		
		if(Conta.getTipoConta().equals("Conta Corrente")) {
			Conta.setSaldoCC(primeiroDeposito);
		}else if(Conta.getTipoConta().equals("Conta Poupança")) {
			Conta.setSaldoCP(primeiroDeposito);
		}
		
		entrada.nextLine();
	}
		
	
	// Método para acessar o sistema
	public static void acessarConta() {
		int sair = 1;
		do {
			try {
				System.out.println("Digite o número da conta para Login ou 0 (zero) para sair:");
				int numero = Integer.parseInt(entrada.nextLine());
				
				if(numero == 0) {
					sair = 0;
				} else if (Conta.contasC.contains(numero)) {
					Conta.setAcesso(true);
					System.out.println("----------------------------------------------");
					System.out.println("Bem vindo ao Banco RV");
					System.out.println("Status: Logado - C/C " + numero);
					sair = 0;
				} else if (Conta.contasP.contains(numero)) {
					Conta.setAcesso(true);
					System.out.println("----------------------------------------------");
					System.out.println("Bem vindo ao Banco RV");
					System.out.println("Status: Logado - C/Poupança " + numero);
					sair = 0;
				} else if(numero != 0) {
					System.out.println("----------------------------------------------");
					Conta.setAcesso(false);
					System.out.println("Conta não encontrada!");
				}
			} catch (NumberFormatException e) {
				System.out.println("----------------------------------------------");
				System.out.println("Dados incorretos. Digitar apenas números!");
			} 
		} while (sair != 0);
		
	}
	

	//Método Consultar Saldo
	public static void consultarSaldo() {
		System.out.println("--------------------------------------------------------------");
		if(Conta.getTipoConta().equals("Conta Corrente")) {
			System.out.println("C/C: " + Conta.getNumeroConta());
		}else if(Conta.getTipoConta().equals("Conta Poupança")) {
			System.out.println("C/P: " + Conta.getNumeroConta());
		}		
		System.out.println("SALDO DA CONTA: " + Conta.getSaldoCC());
		System.out.println("LIMITE DISPONÍVEL: " + (Conta.getSaldoCC() - Conta.getLimiteChequeEspecial()));
		System.out.println("--------------------------------------------------------------");
	
	}
	
	
	
	// GETTERS AND SETTERS
	public static boolean getStatus() {
		return status;
	}

	public static void setStatus(boolean status) {
		Conta.status = status;
	}

//	public static double getSaldo() {
//		return saldo;
//	}
//
//	public static void setSaldo(double saldo) {
//		Conta.saldo = saldo;
//	}

	public static double getLimiteChequeEspecial() {
		return limiteChequeEspecial;
	}

	public static void setLimiteChequeEspecial(double limiteChequeEspecial) {
		Conta.limiteChequeEspecial = limiteChequeEspecial;
	}

	public static boolean getAcesso() {
		return acesso;
	}

	public static void setAcesso(boolean acesso) {
		Conta.acesso = acesso;
	}
	
	public static String getTipoConta() {
		return tipoConta;
	}

	public static void setTipoConta(String tipoConta) {
		Conta.tipoConta = tipoConta;
	}
	
//	public static int getNumeroCC() {
//		return numeroCC;
//	}
//
//	public static void setNumeroCC(int numeroCC) {
//		Conta.numeroCC = numeroCC;
//	}
//
//	public static int getNumeroCP() {
//		return numeroCP;
//	}
//
//	public static void setNumeroCP(int numeroCP) {
//		Conta.numeroCP = numeroCP;
//	}

	public static double getSaldoCP() {
		return saldoCP;
	}

	public static void setSaldoCP(double saldoCP) {
		Conta.saldoCP = saldoCP;
	}

	public static double getSaldoCC() {
		return saldoCC;
	}

	public static void setSaldoCC(double saldoCC) {
		Conta.saldoCC = saldoCC;
	}

	public static int getNumeroConta() {
		return numeroConta;
	}

	public static void setNumeroConta(int numeroConta) {
		Conta.numeroConta = numeroConta;
	}

	public static Double getPrimeiroDeposito() {
		return primeiroDeposito;
	}

	public static void setPrimeiroDeposito(Double primeiroDeposito) {
		Conta.primeiroDeposito = primeiroDeposito;
	}
}
