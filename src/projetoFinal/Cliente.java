package projetoFinal;

import java.util.Random;
import java.util.Scanner;

public abstract class Cliente {
	
	Scanner entrada = new Scanner(System.in);
	Random geraNumero = new Random();
	
	CategoriaPessoa tipoPessoa;
	TipoDocumento tipoDocumento; // CPF ou CNPJ
	private String numDocumento; // Numero CPF ou CNPJ
	private boolean status;
	private boolean contaCorrente, contaPoupanca;
	//private int numeroCC, numeroCP;
	//private double saldo;
	private boolean chequeEspecial;
	private double limiteChequeEspecial;

	// Método Construtor
	public Cliente() {
		this.status = false;
		this.contaCorrente = false;
		this.contaPoupanca = false;
		this.chequeEspecial = false;
		//this.saldo = 0;
	}

	public abstract void inserirDadosCliente() throws Exception;
	//public abstract void mostrarDadosCliente();
	
	public void imprimirDados() {
		System.out.print(this.tipoPessoa + " | " + tipoDocumento + ": " + numDocumento + " - ");
	}

		
	// METODOS GETTERS & SETTERS
	public void setTipoPessoa(CategoriaPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public boolean getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(boolean contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	public boolean getContaPoupanca() {
		return contaPoupanca;
	}

	public void setContaPoupanca(boolean contaPoupanca) {
		this.contaPoupanca = contaPoupanca;
	}

//	public int getNumeroCC() {
//		return numeroCC;
//	}
//
//	public void setNumeroCC(int numeroCC) {
//		this.numeroCC = numeroCC;
//	}
//
//	public int getNumeroCP() {
//		return numeroCP;
//	}
//
//	public void setNumeroCP(int numeroCP) {
//		this.numeroCP = numeroCP;
//	}
//
//	public double getSaldo() {
//		return saldo;
//	}
//
//	public void setSaldo(double saldo) {
//		this.saldo = saldo;
//	}

	public boolean getChequeEspecial() {
		return chequeEspecial;
	}

	public void setChequeEspecial(boolean chequeEspecial) {
		this.chequeEspecial = chequeEspecial;
	}

	public double getLimiteChequeEspecial() {
		return limiteChequeEspecial;
	}

	public void setLimiteChequeEspecial(double limiteChequeEspecial) {
		this.limiteChequeEspecial = limiteChequeEspecial;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
