package formas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import exceptions.CampoVazio;
import exceptions.FigurasIguais;
import exceptions.ValoresInvalidos;

/**
 * Classe Retângulo, que extende a classe Forma, para a criação de objetos do tipo Retângulo
 * @author Mellany Linhares
 */
public class Retangulo extends Forma{
	
	private int base;
	private int altura;
	
	/**
	 * Construtor para a criação de um retângulo
	 * @param px int - Posição X inicial do retângulo
	 * @param py int - Posição Y inicial do retângulo
	 * @param cor Color - Cor do retângulo
	 * @param preenchido boolean - Informa se o retângulo está preenchido ou não 
	 * @param base String - Base do retângulo
	 * @param altura String - Altura do retângulo
	 * @throws CampoVazio - Verifica se a base, a altura ou a cor estão vazios
	 * @throws ValoresInvalidos - Verifica se a base ou altura possuem valores menores ou iguais a 0
	 * @throws NumberFormatException - Verifica se há algum valor não numérico na base ou na altura
	 */
	public Retangulo(int px, int py,Color cor,boolean preenchido, String base, String altura) { 
		super(px,py,cor,preenchido);
		try {
			
			if((base.equals(""))||(altura.equals(""))||(cor == null)) { 
				throw new CampoVazio(base,altura,cor);
			}
			
			if((Integer.parseInt(base) <= 0)||(Integer.parseInt(altura) <=0)) { 
				throw new ValoresInvalidos(Integer.parseInt(base),Integer.parseInt(altura));
			}
			
			this.setBase(Integer.parseInt(base)); 
			this.setAltura(Integer.parseInt(altura));
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
	 * Método para retorno da base do retângulo
	 * @return int - base do retângulo
	 */
	public int getBase() {
		return base;
	}

	/**
	 * Método para setar o valor da base do retângulo
	 * @param base int - base do retângulo
	 */
	public void setBase(int base) {
		this.base = base;
	}

	/**
	 * Método para retorno da altura do retângulo
	 * @return int - altura do retângulo
	 */
	public int getAltura() {
		return altura;
	}

	/**
	 * Método para setar o valor da altura do retângulo
	 * @param altura int - altura do retângulo
	 */
	public void setAltura(int altura) {
		this.altura = altura;
	}

	/**
	 * Método para desenhar um retângulo na tela. Caso ele esteja preenchido, usa o método fillRect para desenhar. Caso contrário,
	 * utiliza-se o drawRect.
	 * @param g Graphics - Objeto necessário para desenhar o retângulo na tela
	 */

	@Override
	public void desenhar(Graphics g) { 
		g.setColor(cor);
		if(isPreenchido) {
			g.fillRect(posicaoX, posicaoY, base, altura);		
		}
		else {
			g.drawRect(posicaoX, posicaoY, base, altura);
		}
	}
	
	/**
	 * Método que sobrescreve o método toString() para a exibição das informações do retângulo no JList. A cor é exibida
	 * em hexadecimal.
	 * @return s String - uma String que representa, em forma de texto, o retângulo
	 */
	public String toString() { 
		
		StringBuffer s = new StringBuffer();
		
		s.append("<html>Retângulo - ");
		s.append("<br>Posição X: "+posicaoX+"px - ");
		s.append("Posição Y: "+posicaoY+"px -<br>");
		if(isPreenchido) {
			s.append("Preenchido: Sim - ");
		}
		else {
			s.append("Preenchido: Não - ");
		}
		s.append("Cor: "+toHexString()+" - ");
		s.append("<br>Base: "+base+"px - ");
		s.append("Altura: "+altura+"px.</html>");
		
		return s.toString();
	}
	
	/**
	 * Método que sobrescreve o método equals() para a comparação de um retângulo e uma forma. 
	 * O método retorna true caso todas as informações sejam iguais e a forma passada como parâmetro seja um retângulo. Caso contrário, retorna false.
	 * @param forma Forma - um objeto do tipo Forma para ser comparado com o retângulo que chama esse método
	 * @return boolean - true se for igual, false se for diferente
	 */
	public boolean equals(Forma forma) { 
		if(forma instanceof Retangulo) {
			
			Retangulo rect = (Retangulo) forma;
			
			if((posicaoX == rect.getPosicaoX())&&(posicaoY == rect.getPosicaoY())&&(cor.equals(rect.getCor()))&&(isPreenchido == rect.isPreenchido())&&(base == rect.getBase())&&(altura == rect.getAltura())) {
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
	 * Método para a edição do retângulo. 
	 * Utiliza-se um ArrayList auxiliar para verificar se a forma editada é igual a alguma já existente no ArrayList de formas.
	 * Por isso, retira-se a forma selecionada do ArrayList auxiliar
	 * @param x int - nova posição X inicial do retângulo
	 * @param y int - nova posição Y inicial do retângulo
	 * @param cor Color - nova cor do retângulo
	 * @param preenchido boolean - novo valor booleano para verificar se o retângulo é preenchido ou não
	 * @param base String - nova base do retângulo
	 * @param altura String - nova altura do retângulo
	 * @param formas Formas - objeto Formas, que gerencia todas as formas contidas na tela
	 * @param formaSelecionada int - posição da forma que será editada
	 * @throws CampoVazio - Verifica se a base, a altura, a posição X/Y ou a cor estão vazios
	 * @throws ValoresInvalidos - Verifica se a base ou a altura tem valores menores ou iguais a 0 ou a posição X/Y tem valores negativos
	 * @throws FigurasIguais - Verifica se já existe uma figura igual a essa no array de figuras
	 * @throws NumberFormatException - Verifica se há algum valor não numérico na base, na altura ou na posição X/Y
	 * @return boolean para verificar se a edição foi bem sucedida ou não
	 */
	public boolean editarRetangulo(String x, String y, Color cor, boolean preenchido, String base, String altura, Formas formas, int formaSelecionada) {
		try {
			
			ArrayList<Forma> f = new ArrayList();
			Retangulo rect;
			
			for(Forma forma: formas.getFormas()) {
				f.add(forma);
			}
			
			f.remove(formaSelecionada); 
			
			if((altura.equals(""))||(base.equals(""))||(x.equals(""))||(y.equals(""))||(cor == null)) { 
				throw new CampoVazio(x,y,base,altura,cor);
			}
			
			if((Integer.parseInt(altura) <= 0)||(Integer.parseInt(base) <= 0)||(Integer.parseInt(x) < 0)||(Integer.parseInt(y) < 0)) {
				throw new ValoresInvalidos(Integer.parseInt(x),Integer.parseInt(y),Integer.parseInt(base),(Integer.parseInt(altura)));
			}
			
			
			for(Forma forma : f) {
				if(forma instanceof Retangulo) {
					rect = (Retangulo) forma;
					if((rect.getPosicaoX() == Integer.parseInt(x))&&(rect.getPosicaoY() == Integer.parseInt(y))&&(rect.getCor().equals(cor))&&(rect.isPreenchido() == preenchido)&&(rect.getBase() == Integer.parseInt(base))&&(rect.getAltura() == Integer.parseInt(altura))) {  
						throw new FigurasIguais();
					}
				}
				
			}
			
			
			this.setPosicaoX(Integer.parseInt(x));
			this.setPosicaoY(Integer.parseInt(y));
			this.setCor(cor);
			this.setPreenchido(preenchido);
			this.setBase(Integer.parseInt(base));
			this.setAltura(Integer.parseInt(altura));

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
