package exceptions;

/**
 * Exception usada para verificar se o usuário selecionou alguma figura antes de apertar nos botões de excluir/editar.
 * @author Mellany Linhares
 *
 */
public class NenhumItemSelecionado extends Exception{
	
	/**
	 * Construtor para o lançamento da exception.
	 * @param message String - Mensagem de erro que será mostrada ao usuário.
	 */
	public NenhumItemSelecionado(String message) {
		super(message);
	}

}
