package exceptions;

/**
 * Exception para verificar se já existe alguma figura igual no ArrayList antes de adicioná-la.
 * @author Mellany Linhares
 *
 */
public class FigurasIguais extends Exception{
	
	/**
	 * Construtor que será acionado ao lançar a exception.
	 */
	public FigurasIguais() {
		super("Não é possível adicionar formas iguais.");
	}

}
