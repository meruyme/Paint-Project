package formas;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**Classe mãe Forma (abstrata) 
 * @author Mellany Linhares
 */

public abstract class Forma implements Serializable{
	
	protected int posicaoX;
	protected int posicaoY;
	protected Color cor;
	protected boolean isPreenchido;
	
	
	/**
	 * Construtor base para a criação de uma forma
	 * @param px int - Posição inicial X da forma 
	 * @param py int - Posição inicial Y da forma
	 * @param cor Color - Cor da forma
	 * @param preenchido boolean - Informa se a forma está preenchida ou não 
	 */
	public Forma(int px, int py, Color cor, boolean preenchido) { 
		this.setCor(cor);
		this.setPosicaoX(px);
		this.setPosicaoY(py);
		this.setPreenchido(preenchido);
	}
	
	/**
	 * Método para retorno do atributo boolean que informa se a forma está preenchida ou não 
	 * @return boolean - forma preenchida (true) ou forma não preenchida (false)
	 */
	public boolean isPreenchido() {
		return isPreenchido;
	}

	/**
	 * Método para setar o valor do atributo preenchido
	 * @param isPreenchido boolean - informa se a forma está preenchida ou não 
	 */
	public void setPreenchido(boolean isPreenchido) {
		this.isPreenchido = isPreenchido;
	}

	/**
	 * Método para retorno da posição inicial X da forma
	 * @return int - posição inicial X da forma
	 */
	public int getPosicaoX() {
		return posicaoX;
	}

	/**
	 * Método para setar o valor da posição inicial X
	 * @param posicaoX int - posição inicial X da forma
	 */
	public void setPosicaoX(int posicaoX) {
		this.posicaoX = posicaoX;
	}

	/**
	 * Método para retorno da posição inicial Y da forma
	 * @return int - posição inicial Y da forma
	 */
	public int getPosicaoY() {
		return posicaoY;
	}

	/**
	 * Método para setar o valor da posição inicial Y
	 * @param posicaoY int - posição inicial Y da forma
	 */
	public void setPosicaoY(int posicaoY) {
		this.posicaoY = posicaoY;
	}

	/**
	 * Método para retorno da cor da forma
	 * @return Color - cor da forma
	 */
	public Color getCor() {
		return cor;
	}

	/**
	 * Método para setar a cor
	 * @param cor Color - cor da forma
	 */
	public void setCor(Color cor) {
		this.cor = cor;
	}

	/**
	 * Método abstrato para desenhar uma forma na tela
	 * @param g Graphics - Objeto necessário para desenhar a forma na tela
	 */
	public abstract void desenhar(Graphics g);
	
	/**
	 * Método abstrato que sobrescreve o método toString()
	 * @return String - uma String que representa a forma 
	 */
	public abstract String toString();
	
	/**
	 * Método abstrato que sobrescreve o método equals() para a comparação de duas formas
	 * @param forma Forma - um objeto do tipo Forma para ser comparado com o outro objeto que chama esse método
	 * @return boolean - true se for igual, false se for diferente
	 */
	public abstract boolean equals(Forma forma); 
		
	/**
	 * Método para converter a cor RGB em hexadecimal para exibição no JList
	 * @return String - o hexadecimal correspondente à cor da forma
	 */
	public String toHexString() throws NullPointerException{ 
		String hex = Integer.toHexString(cor.getRGB() & 0xffffff);
		
		if(hex.length() < 6) {
			hex = "000000".substring(0, 6-hex.length()) + hex;
		}
		
		
		return "#"+hex;
	}
	

}
