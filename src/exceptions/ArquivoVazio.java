
package exceptions;

/**
 * Exception para verificar se o ArrayList está vazio antes de salvar o arquivo.
 * @author Mellany Linhares
 *
 */
public class ArquivoVazio extends Exception{
	
	/**
	 * Construtor para o lançamento da exception.
	 * @param message String - Mensagem de erro que será mostrada ao usuário.
	 */
	public ArquivoVazio(String message) {
		super(message);
	}

}
