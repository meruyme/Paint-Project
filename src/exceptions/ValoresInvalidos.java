
package exceptions;

/**
 * Exception para verificar os valores inválidos nas telas de desenho e edição.
 * @author Mellany Linhares
 *
 */
public class ValoresInvalidos extends Exception{
	
	private int base = 1, altura = 1, raio = 1, x = 1, y = 1; 
	

	/**
	 * Construtor usado para verificar valores inválidos na tela de desenho de triângulos e retângulos.
	 * @param base int - Base da forma.
	 * @param altura int - Altura da forma.
	 */
	public ValoresInvalidos(int base,int altura) { 
		super();		
		this.base = base;
		this.altura = altura;
	}
	
	/**
	 * Construtor usado para verificar valores inválidos na tela de edição de triângulos e retângulos.
	 * @param x int - Posição X da forma.
	 * @param y int - Posição Y da forma.
	 * @param base int - Base da forma.
	 * @param altura int - Altura da forma.
	 */
	public ValoresInvalidos(int x, int y, int base,int altura) { 
		super();		
		this.x = x;
		this.y = y;
		this.base = base;
		this.altura = altura;
	}
	
	/**
	 * Construtor usado para verificar valores inválidos na tela de desenho de círculos.
	 * @param raio int - Raio da forma.
	 */
	public ValoresInvalidos(int raio) {
		super();		
		this.raio = raio;
	}
	
	/**
	 * Construtor usado para verificar valores inválidos na tela de edição de círculos.
	 * @param x int - Posição X da forma.
	 * @param y int - Posição Y da forma.
	 * @param raio int - Raio da forma.
	 */
	public ValoresInvalidos(int x, int y, int raio) { 
		super();		
		this.x = x;
		this.y = y;
		this.raio = raio;
	}
	
	/**
	 * Método que sobrescreve o método getMessage para retornar uma mensagem personalizada para o usuário, informando-o que campos possuem valores inválidos.
	 * @return String - Mensagem de erro que será exibida ao usuário.
	 */
	public String getMessage() { 
		
		StringBuffer message = new StringBuffer();
		
		message.append("Os seguintes campos possuem valores inválidos (iguais ou menores que 0):");
		
		if(x < 0) {
			message.append(" - Posição X");
		}
		if(y < 0) {
			message.append(" - Posição Y");
		}
		if(base <= 0) {
			message.append(" - Base");
		}
		
		if(altura <= 0) {
			message.append(" - Altura");
		}
		
		if(raio <= 0) {
			message.append(" - Raio");
		}
		
		message.append(". Informe valores válidos antes de continuar.");
		
		return message.toString();
		
	}

}
