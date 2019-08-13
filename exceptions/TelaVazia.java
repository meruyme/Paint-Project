package exceptions;

/**
 * Exception para verificar se a tela está vazia quando o usuário selecionar limpar a tela.
 * @author Mellany Linhares
 *
 */

public class TelaVazia extends Exception{
	
	/**
	 * Construtor para o lançamento da exception.
	 * @param message String - Mensagem de erro que será mostrada ao usuário.
	 */
	public TelaVazia(String message) {
		super(message);
	}

}
