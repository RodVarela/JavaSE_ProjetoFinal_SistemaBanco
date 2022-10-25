package projetoFinal;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class Transacoes extends Conta {
	
	static ArrayList<Double> historicoTransacoes = new ArrayList<Double>();
	
	// M�TODO DEPOSITAR
	public static void depositar() {
		boolean continua = true;
		do {
			try {
				double valor;
				do {
					System.out.println("Digite o valor do dep�sito:");
					valor = entrada.nextDouble();
					if(valor > 0) {
						continua = false;
					}else {
						System.out.println("Digitar valor positivo!");
					}
				} while (continua);
				continua = true;
				if (Conta.getStatus() == true) {
					Conta.setSaldoCC(Conta.getSaldoCC() + valor);
					System.out.println("--------------------------------------------------------------");
					System.out.println("Dep�sito no valor de " + valor + " realizado com sucesso!");
					System.out.println("SALDO DA CONTA: " + Conta.getSaldoCC());
					System.out.println("LIMITE TOTAL DISPON�VEL: " + (Conta.getSaldoCC() - Conta.getLimiteChequeEspecial()));
					System.out.println("--------------------------------------------------------------");
					historicoTransacoes.add(valor);
					continua = false;
				} else {
					System.out.println("Este cliente n�o possui conta aberta. Abrir conta para realizar esta opera��o!");
					continua = false;
				}
			} catch (InputMismatchException e) {
				System.out.println("Valor inv�lido! Digitar apenas n�meros!");
				entrada.nextLine();
			}
		} while (continua);
	}
		
	// M�TODO SACAR
	public static void sacar() {
		boolean continua = true;
		do {
			try {
				double valor;
				do {
					System.out.println("Digite o valor do saque:");
					valor = entrada.nextDouble();
					if (valor > 0) {
						continua = false;
					} else {
						System.out.println("Digitar valor positivo!");
					}
				} while (continua);
				continua = true;
				if (Conta.getStatus() == true) {
					if ((Conta.getSaldoCC() - valor) < Conta.getLimiteChequeEspecial()) {
						System.out.println("--------------------------------------------------------------");
						System.out.println("N�o � poss�vel sacar. Cheque especial no limite!");
						System.out.println("SALDO DA CONTA: " + Conta.getSaldoCC());
						System.out.println(
								"LIMITE DISPON�VEL: " + (Conta.getSaldoCC() - Conta.getLimiteChequeEspecial()));
						System.out.println("--------------------------------------------------------------");
					} else {
						if ((Conta.getSaldoCC() - valor) < 0) {
							Conta.setSaldoCC(Conta.getSaldoCC() - valor);
							System.out.println("--------------------------------------------------------------");
							System.out.println("Saque no valor de " + valor + " realizado com sucesso!");
							System.out.println("ATEN��O! Utilizando o Cheque Especial");
							System.out.println("SALDO DA CONTA: " + Conta.getSaldoCC());
							System.out.println(
									"LIMITE DISPON�VEL: " + (Conta.getSaldoCC() - Conta.getLimiteChequeEspecial()));
							System.out.println("--------------------------------------------------------------");
							historicoTransacoes.add(valor * -1);
						} else {
							System.out.println("--------------------------------------------------------------");
							Conta.setSaldoCC(Conta.getSaldoCC() - valor);
							System.out.println("Saque no valor de " + valor + " realizado com sucesso!");
							System.out.println("SALDO DA CONTA: " + Conta.getSaldoCC());
							System.out.println(
									"LIMITE DISPON�VEL: " + (Conta.getSaldoCC() - Conta.getLimiteChequeEspecial()));
							System.out.println("--------------------------------------------------------------");
							historicoTransacoes.add(valor * -1);
						}
						continua = false;
					}
				} else {
					System.out
							.println("Este cliente n�o possui conta aberta. Abrir conta para realizar esta opera��o!");
					continua = false;
				}
			} catch (InputMismatchException e) {
				System.out.println("Valor inv�lido! Digitar apenas n�meros!");
				entrada.nextLine();
			}
		} while (continua);
	}

	// M�TODO TRANSFERIR
	public static void transferir() {
		System.out.println("--------------------------------------------------------------");
		System.out.println("----------------Transferir de Conta Corrente------------------");
		int banco = 0;
		int agencia = 0;
		int conta = 0;
		boolean continua = true;

		do {
			try {
				System.out.println("Informe o n�mero do banco: ");
				banco = entrada.nextInt();
				continua = false;
			} catch (InputMismatchException e) {
				System.out.println("Digitar apenas n�meros inteiros!");
				entrada.nextLine();
			}
		} while (continua);

		continua = true;
		do {
			try {
				System.out.println("Informe o n�mero da ag�ncia: ");
				agencia = entrada.nextInt();
				continua = false;
			} catch (InputMismatchException e) {
				System.out.println("Digitar apenas n�meros inteiros! Sem o d�gito!");
				entrada.nextLine();
			}
		} while (continua);

		continua = true;
		do {
			try {
				System.out.println("Informe o n�mero da conta: ");
				conta = entrada.nextInt();
				continua = false;
			} catch (InputMismatchException e) {
				System.out.println("Digitar apenas n�meros inteiros!");
				entrada.nextLine();
			}
		} while (continua);

		continua = true;
		double valorTransferencia = 0;
		do {
			try {
				System.out.println("Digite o valor a ser transferido: ");
				valorTransferencia = entrada.nextDouble();
				if (valorTransferencia > 0) {
					continua = false;
				} else {
					System.out.println("Digitar valor positivo!");
				}
				// continua = false;
			} catch (InputMismatchException e) {
				System.out.println("Valor inv�lido! Digitar apenas n�meros!");
				entrada.nextLine();
			}
		} while (continua);

		if ((Conta.getSaldoCC() - valorTransferencia) < Conta.getLimiteChequeEspecial()) {
			System.out.println("--------------------------------------------------------------");
			System.out.println("N�o h� saldo dispon�vel para esta opera��o!");
			System.out.println("Valor solicitado: " + valorTransferencia);
			System.out.println("Total Dispon�vel: " + (Conta.getSaldoCC() - Conta.getLimiteChequeEspecial()));
		} else if ((Conta.getSaldoCC() - valorTransferencia) < 0) {
			Conta.setSaldoCC(Conta.getSaldoCC() - valorTransferencia);
			System.out.println("--------------------------------------------------------------");
			System.out.println("ATEN��O! Voc� est�ra utilizando o Cheque Especial");
			System.out.println("Transfer�ncia realizada com sucesso!");
			System.out.println("## Dados da conta destino ##");
			System.out.println("Banco: " + banco + " | Ag�ncia: " + agencia + " | Conta: " + conta);
			System.out.println("Valor Transferido: " + valorTransferencia);
			System.out.println("---------------------------Sua Conta---------------------------");
			System.out.println("SALDO ATUAL: " + Conta.getSaldoCC());
			System.out.println("LIMITE DISPON�VEL: " + (Conta.getSaldoCC() - Conta.getLimiteChequeEspecial()));
			System.out.println("--------------------------------------------------------------");
			historicoTransacoes.add(valorTransferencia * -1);
		} else {
			System.out.println("--------------------------------------------------------------");
			Conta.setSaldoCC(Conta.getSaldoCC() - valorTransferencia);
			System.out.println("Transfer�ncia realizada com sucesso!");
			System.out.println("## Dados da conta destino ##");
			System.out.println("Banco: " + banco + " | Ag�ncia: " + agencia + " | Conta: " + conta);
			System.out.println("Valor Transferido: " + valorTransferencia);
			System.out.println("---------------------------Sua Conta---------------------------");
			System.out.println("SALDO ATUAL: " + Conta.getSaldoCC());
			System.out.println("LIMITE DISPON�VEL: " + (Conta.getSaldoCC() - Conta.getLimiteChequeEspecial()));
			System.out.println("--------------------------------------------------------------");
			historicoTransacoes.add(valorTransferencia * -1);
		}

	}
	
	// M�TODO CONSULTAR HIST�RICO DE TRANSA��ES
	public static void consultarHistorico() {
		int cont = 0;
		double depositoInicial = Conta.getPrimeiroDeposito();
		
		System.out.println("----------------------------------------------");
		System.out.println("----------HIST�RIO DE MOVIMENTA��ES-----------");
		
		if(Conta.getTipoConta()=="Conta Corrente") {
			System.out.println("Saldo Inicial C/C: " + depositoInicial);
		}else if(Conta.getTipoConta() == "Conta Poupan�a") {
			System.out.println("Saldo Inicial C/P: " + depositoInicial);
		}
		
		for (Double v : historicoTransacoes) {
			if(v > 0) {
				System.out.println("Movimenta��o " + (cont + 1) + " - Dep�sito de: " + v);
			}else {
				System.out.println("Movimenta��o " + (cont + 1) + " - Saque de: " +  v);
			}
			cont++;
		}
		System.out.println("Total de Movimenta��es: " + cont);
		
		if(Conta.getTipoConta()=="Conta Corrente") {
			System.out.println("Saldo Final C/C: " + Conta.getSaldoCC());
		}else if(Conta.getTipoConta() == "Conta Poupan�a") {
			System.out.println("Saldo Final C/P: " + Conta.getSaldoCC());
		}
		
		System.out.println("----------------------------------------------");
	}
		
}
