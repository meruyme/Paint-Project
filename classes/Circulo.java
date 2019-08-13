package formas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import exceptions.CampoVazio;
import exceptions.FigurasIguais;
import exceptions.ValoresInvalidos;

/**
 * Classe Círculo, que extende a classe Forma, para a criação de objetos do tipo Círculo
 * @author Mellany Linhares
 */

public class Circulo extends Forma{
	
	private int raio;
	
	/**
	 * Construtor para a criação de um círculo
	 * @param px int - Posição X do centro do círculo
	 * @param py int - Posição Y do centro do círculo
	 * @param cor Color - Cor do círculo
	 * @param preenchido boolean - Informa se o círculo está preenchido ou não 
	 * @param raio String - Raio do círculo
	 * @throws CampoVazio - Verifica se o raio ou a cor estão vazios
	 * @throws ValoresInvalidos - Verifica se o raio possui valor menor ou igual a 0
	 * @throws NumberFormatException - Verifica se há algum valor não numérico no raio
	 */
	public Circulo(int px, int py,Color cor,boolean preenchido, String raio) { 
		super(px,py,cor,preenchido);
			try {
			
			if((raio.equals(""))||cor == null) {
				throw new CampoVazio(raio,cor);
			}
			
			if((Integer.parseInt(raio) <= 0)) { 
				throw new ValoresInvalidos(Integer.parseInt(raio));
			}
			
			this.setRaio(Integer.parseInt(raio)); 
		}
		catch(CampoVazio e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		catch(ValoresInvalidos e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		catch(NumberFormatException e) { 
			JOptionPane.showMessageDialog(null, "Os campos de texto para desenho só aceitam números. Repita a operação.", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Método para retorno do raio do círculo
	 * @return int - raio do círculo
	 */
	public int getRaio() {
		return raio;
	}

	/**
	 * Método para setar o valor do raio do círculo
	 * @param raio int - raio do círculo
	 */
	public void setRaio(int raio) {
		this.raio = raio;
	}

	/**
	 * Método para desenhar um círculo na tela. Caso ele esteja preenchido, usa o método fillOval para desenhar. Caso contrário,
	 * utiliza-se o drawOval.
	 * @param g Graphics - Objeto necessário para desenhar o círculo na tela
	 */
	@Override
	public void desenhar(Graphics g) { 
		g.setColor(cor);
		if(isPreenchido) {
			g.fillOval(posicaoX-raio, posicaoY-raio, raio*2, raio*2);		
		}
		else {
			g.drawOval(posicaoX-raio, posicaoY-raio, raio*2, raio*2);		
		}
	}
	
	/**
	 * Método que sobrescreve o método equals() para a comparação de um círculo e uma forma. 
	 * O método retorna true caso todas as informações sejam iguais e a forma passada como parâmetro seja um círculo. Caso contrário, retorna false.
	 * @param forma Forma - um objeto do tipo Forma para ser comparado com o círculo que chama esse método
	 * @return boolean - true se for igual, false se for diferente
	 */
	public boolean equals(Forma forma) { 

		if(forma instanceof Circulo) {

			Circulo circle = (Circulo) forma;
			
			if((posicaoX == circle.getPosicaoX())&&(posicaoY == circle.getPosicaoY())&&(cor.equals(circle.getCor()))&&(isPreenchido == circle.isPreenchido())&&(raio == circle.getRaio())) {
				return true;
			}
			else {
				return false;
			}
			
		}
		else {
			return false;
		}		
	}
	
	/**
	 * Método que sobrescreve o método toString() para a exibição das informações do círculo no JList. A cor é exibida
	 * em hexadecimal.
	 * @return s String - uma String que representa, em forma de texto, o círculo
	 */
	public String toString() { 
		
		StringBuffer s = new StringBuffer();
		
		s.append("<html>Círculo - ");
		s.append("<br>Posição X: "+posicaoX+"px - ");
		s.append("Posição Y: "+posicaoY+"px -<br>");
		if(isPreenchido) {
			s.append("Preenchido: Sim - ");
		}
		else {
			s.append("Preenchido: Não - ");
		}
		s.append("Cor: "+toHexString()+" -<br>");
		s.append("Raio: "+raio+"px.</html>");
		
		return s.toString();
	}

	
	/**
	 * Método para a edição do círculo. 
	 * Utiliza-se um ArrayList auxiliar para verificar se a forma editada é igual a alguma já existente no ArrayList de formas.
	 * Por isso, retira-se a forma selecionada do ArrayList auxiliar
	 * @param x int - nova posição X do centro do círculo
	 * @param y int - nova posição Y do centro do círculo
	 * @param cor Color - nova cor do círculo
	 * @param preenchido boolean - novo valor booleano para verificar se o círculo é preenchido ou não
	 * @param raio String - novo raio do círculo
	 * @param formas Formas - objeto Formas, que gerencia todas as formas contidas na tela
	 * @param formaSelecionada int - posição da forma que será editada
	 * @throws CampoVazio - Verifica se o raio, a posição X/Y ou a cor estão vazios
	 * @throws ValoresInvalidos - Verifica se o raio tem valores menores ou iguais a 0 ou a posição X/Y tem valores negativos
	 * @throws FigurasIguais - Verifica se já existe uma figura igual a essa no array de figuras
	 * @throws NumberFormatException - Verifica se há algum valor não numérico no raio ou na posição X/Y
	 * @return boolean para verificar se a edição foi bem sucedida ou não
	 */
	public boolean editarCirculo(String x, String y, Color cor, boolean preenchido, String raio, Formas formas, int formaSelecionada) {
		try {
			
			ArrayList<Forma> f = new ArrayList();
			Circulo circle;
			
			for(Forma forma: formas.getFormas()) {
				f.add(forma);
			}
			
			f.remove(formaSelecionada); 
			
			if((raio.equals(""))||(x.equals(""))||(y.equals(""))||(cor == null)) { 
				throw new CampoVazio(x,y,raio,cor);
			}
			
			if((Integer.parseInt(raio) <= 0)||(Integer.parseInt(x) < 0)||(Integer.parseInt(y) < 0)) { 
				throw new ValoresInvalidos(Integer.parseInt(x),Integer.parseInt(y),Integer.parseInt(raio));
			}
			
			for(Forma forma : f) {
				if(forma instanceof Circulo) {
					circle = (Circulo) forma;
					if((circle.getPosicaoX() == Integer.parseInt(x))&&(circle.getPosicaoY() == Integer.parseInt(y))&&(circle.getCor().equals(cor))&&(circle.isPreenchido() == preenchido)&&(circle.getRaio() == Integer.parseInt(raio))) { 
						throw new FigurasIguais();
					}
				}
				
			}
			
			this.setPosicaoX(Integer.parseInt(x));
			this.setPosicaoY(Integer.parseInt(y));
			this.setCor(cor);
			this.setPreenchido(preenchido);
			this.setRaio(Integer.parseInt(raio));
			
			return true;
		}
		catch(CampoVazio e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		catch(ValoresInvalidos e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		catch(NumberFormatException e) { 
			JOptionPane.showMessageDialog(null, "Os campos de texto para desenho só aceitam números. Repita a operação.", "ERRO", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		catch(FigurasIguais e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}


}
