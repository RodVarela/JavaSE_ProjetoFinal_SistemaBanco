package projetoFinal;

import java.util.Scanner;

public class ClientePJ extends Cliente {

	private String razaoSocial;
	private int tempoDeExistencia;
	
	Scanner in = new Scanner(System.in);

	public ClientePJ() {
		super();
		super.setTipoPessoa(CategoriaPessoa.PJ);
		super.setTipoDocumento(TipoDocumento.CNPJ);
		super.setNumDocumento(super.getTipoDocumento().geraNumeroTeste());
		super.setChequeEspecial(true);
		super.setLimiteChequeEspecial(-1000);
	}

	@Override
	public void inserirDadosCliente() throws Exception {
		System.out.println("Digitar Razão Social: ");
		this.razaoSocial = in.nextLine();
	
		// Tratamento de exception para o tempo da empresa
		System.out.println("Digitar Tempo de Existência da Empresa: ");
		boolean continua = true;
		do {
			try {
				this.tempoDeExistencia = Integer.parseInt(in.nextLine());
				continua = false;
			} catch (NumberFormatException e) {
				System.out.println("Valor incorreto! Digitar apenas números inteiros!");
			}
		} while (continua);
		
	}
	
	
	public void imprimirDados() {
		super.imprimirDados();
		System.out.println(this.razaoSocial + ", " + this.tempoDeExistencia + " ano(s)");
	}

	@Override
	public String toString() {
		return "ClientePJ [razaoSocial=" + razaoSocial + ", tempoDeExistencia=" + tempoDeExistencia + ", tipoPessoa="
				+ tipoPessoa + ", " + super.getTipoDocumento() + ": " + super.getNumDocumento() + " | " + Conta.getTipoConta() + " - " + Conta.getNumeroConta() + "]";
	}

	
	
	// METODOS GETTERS & SETTER
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public int getTempoDeExistencia() {
		return tempoDeExistencia;
	}

	public void setTempoDeExistencia(int tempoExistencia) {
		this.tempoDeExistencia = tempoExistencia;
	}

}
