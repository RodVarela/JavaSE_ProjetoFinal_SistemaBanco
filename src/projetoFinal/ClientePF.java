package projetoFinal;

import java.util.Scanner;

public class ClientePF extends Cliente {

	private String nome;
	private int idade;
	private String sexo;

	Scanner s = new Scanner(System.in);
	boolean continua = true;

	// Construtor
	public ClientePF(){
		super();
		super.setTipoPessoa(CategoriaPessoa.PF);
		super.setTipoDocumento(TipoDocumento.CPF);
		super.setNumDocumento(super.getTipoDocumento().geraNumeroTeste());
		super.setChequeEspecial(true);
		super.setLimiteChequeEspecial(-1000);
	}

	@Override
	public void inserirDadosCliente() throws Exception {
		digitarNome();
		digitarIdade();
		digitarSexo();	
	}
	
	//Método Nome
	public void digitarNome() {
		continua = true;
		System.out.println("Digitar nome: ");
		do {
			this.nome = s.nextLine();
			if (this.nome.length() < 5) {
				System.out.println("Nome muito curto! Digite novamente.");
			} else if (this.nome.length() > 30) {
				System.out.println("Nome muito longo! Digite novamente.");
			} else {
				if (!this.nome.contains(" ")) {
					System.out.println("Precisa ter um sobrenome. Digite novamente.");
				} else {
					continua = false;
				}
			}
		} while (continua);
	}

	// Lançar Exception se a condição no valor da idade digitado não for atendido.
	// Este método é usado no método "digitarIdade"
	public void testeIdade(Scanner idade) throws Exception {
		System.out.println("Digitar idade: ");
		this.idade = Integer.parseInt(idade.nextLine());

		if (this.idade < 1 || this.idade > 90) {
			throw new ValoresInvalidos("A idade deve ser entre 1 e 90.");
		}
	}

	// Método Idade
	public void digitarIdade() throws Exception {
		continua = true;
		do {
			try {
				testeIdade(s);
				continua = false;
			} catch (NumberFormatException e) {
				System.out.println("Valor incorreto! Digitar apenas números inteiros!");
			} catch (ValoresInvalidos e) {
				System.out.println(e.getMessage());
			}

		} while (continua);
	}

	//Método Sexo
	public void digitarSexo() {
		continua = true;
		System.out.println("Digitar sexo: ( M - Masculino || F - Feminino ) ");
		do {
			this.sexo = s.nextLine();
			if (this.sexo.equals("M") || this.sexo.equals("F")) {
				continua = false;
			} else {
				System.out.println("Valor incorrreto! Digitar apenas M ou F.");

			}
		} while (continua);
	}

	
	public void imprimirDados() {
		System.out.println("----------------------------------------------------------------");
		super.imprimirDados();
		System.out.println(this.nome + ", " + this.idade + " ano(s), " + "Sexo: " + this.sexo);
	}

	@Override
	public String toString() {
		return "ClientePF [nome=" + nome + ", idade=" + idade + ", sexo=" + sexo + ", tipoPessoa=" + tipoPessoa + ", "
				+ super.getTipoDocumento() + ": " + super.getNumDocumento() + " | " + Conta.getTipoConta() + " - " + Conta.getNumeroConta() + "]";
	}

	
	
	// METODOS GETTERS & SETTERS
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

}
