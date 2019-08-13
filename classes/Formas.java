package formas;

import java.awt.Graphics;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import exceptions.ArquivoVazio;
import exceptions.FigurasIguais;
import exceptions.NenhumItemSelecionado;
import exceptions.TelaVazia;
import main.PaintJPanel;


/**
 * Classe Formas, que tem como função gerenciar as formas desenhadas na tela por meio de um ArrayList
 * @author Mellany Linhares
 */

public class Formas {
	
	private ArrayList<Forma> formas;
	
	/**
	 * Construtor da classe Formas, que instancia o ArrayList com as formas desenhadas pelo usuário
	 */
	public Formas() {
		formas = new ArrayList<Forma>();
	}
	
	/**
	 * Método para retorno do ArrayList de formas
	 * @return ArrayList<Formas> - ArrayList que armazena todas as formas desenhadas pelo usuário
	 */
	public ArrayList<Forma> getFormas(){
		return formas;
	}
	
	/**
	 * Método que adiciona uma forma desenhada pelo usuário no ArrayList de formas e na lista para a exibição no ArrayList.
	 * @param f Forma - Forma desenhada pelo usuário 
	 * @param modelo DefaultListModel - Listagem para exibição no JList de todas as formas desenhadas pelo usuário
	 * @throws FigurasIguais - Verifica se já existe uma forma igual no ArrayList de formas.
	 * @return boolean - Retorna true caso a forma seja adicionada na lista, e false caso não.
	 */
	public boolean addFormas(Forma f, DefaultListModel modelo) {
		try {
			for(Forma forma : formas) {
				if(forma.equals(f)) {
					throw new FigurasIguais(); 
				}
			}
			formas.add(f); 
			modelo.add(modelo.getSize(), f.toString()); 
			
			return true;
		}
		catch(FigurasIguais e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	/**
	 * Método que percorre o ArrayList de formas e desenha cada uma na tela de desenho.
	 * @param g Graphics - Objeto necessário para desenhar uma forma na tela.
	 */
	public void desenharFormas(Graphics g) { 
		for (Forma f : formas) {
			f.desenhar(g);
		}
	}
	
	/**
	 * Método para excluir uma forma da tela de desenho e da listagem. Exibe um JOptionPane para que o usuário possa
	 * confirmar a escolha.
	 * @param forma int - Posição da forma selecionada no ArrayList
	 * @param modelo DefaultListModel - Listagem para exibição no JList de todas as formas desenhadas pelo usuário	 
	 * @param panel PaintJPanel - Tela de desenho
	 * @throws NenhumItemSelecionado - Verifica se o usuário selecionou algum item na JList antes de selecionar o botão de excluir.
	 */
	public void excluirFormas(int forma,DefaultListModel modelo, PaintJPanel panel) { 
		try {
			if(forma < 0) {
				throw new NenhumItemSelecionado("Selecione uma forma antes de continuar.");
			}
			
			int opcao = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja deletar essa forma?", "DELETAR FORMA", JOptionPane.YES_NO_OPTION);
		
			if(opcao == JOptionPane.YES_OPTION) {
				formas.remove(forma);
				modelo.remove(forma);
				panel.repaint();
			}
		}
		catch(NenhumItemSelecionado e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Método para limpar a tela de desenho. Exibe um JOptionPane para o usuário poder confirmar a sua escolha.
	 * @param modelo DefaultListModel - Listagem para exibição no JList de todas as formas desenhadas pelo usuário	 
	 * @param panel PaintJPanel - Tela de desenho
	 * @throws TelaVazia - Verifica se a tela de desenho já está vazia quando o usuário seleciona o botão de limpar tela.
	 */
	public void limparTela(DefaultListModel modelo, PaintJPanel panel) {
		
		try {
			
			if(formas.isEmpty()) { 
				throw new TelaVazia("A tela já está vazia.");
			}
		
			int opcao = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja limpar a tela de desenho?", "LIMPAR TELA", JOptionPane.YES_NO_OPTION);
		
			if(opcao == JOptionPane.YES_OPTION) {
				formas.clear();
				modelo.clear();
				panel.repaint();
			}
		
		}
		catch(TelaVazia e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Método para salvar as formas em um arquivo do tipo .paint. Foi utilizado o componente JFileChooser para o usuário
	 * poder selecionar onde ele desenha salvar o arquivo. Foi habilitado um filtro para que aparecesse apenas arquivos 
	 * com a extensão .paint para o usuário.
	 * Além disso, ele verifica se, no nome do arquivo, o usuário já digitou a extensão .paint. Caso sim, ele não faz nada.
	 * Caso não, ele adiciona a extensão no arquivo.
	 * @throws ArquivoVazio - Verifica se a tela de desenho está vazia quando o usuário selecionar a opção de salvar arquivo.
	 */
	public void salvarFormas() {
		try {
			if(formas.isEmpty()) { 
				throw new ArquivoVazio("A tela de pintura está vazia. Desenhe algo antes de salvar em um arquivo.");
			}
			JFileChooser fileChooser = new JFileChooser();
			File arquivo = null;
			FileOutputStream file;
			
			 fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			 FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos PAINT (.paint)", new String[]{"paint"});   
			 fileChooser.setFileFilter(filter);    
			 fileChooser.setAcceptAllFileFilterUsed(false);  
			 fileChooser.setMultiSelectionEnabled(false); 
			 
	         int opcao = fileChooser.showSaveDialog(null); 
	         if (opcao!=1){
	        	 arquivo = fileChooser.getSelectedFile();
	         
	        	 if(arquivo.getName().contains(".paint")) { 
		        	 file = new FileOutputStream(arquivo.getPath());
	        	 }
	        	 else {
	        		 file = new FileOutputStream(arquivo.getPath()+".paint");
	        	 }
	        	 
	        	 ObjectOutputStream fileObj = new ObjectOutputStream(file);
        
	        	 for (Forma forma : formas) { 
	        		 fileObj.writeObject(forma);
	        	 }
	        	 
	        	 fileObj.close();
	        }
		}
		catch(ArquivoVazio e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		catch(IOException e) {
			
		}
	}
	
	/**
	 * Método para carregar as formas de um arquivo do tipo .paint para a tela de desenho. Foi utilizado o componente JFileChooser para o usuário
	 * poder selecionar o arquivo que ele deseja que seja carregado. Foi habilitado um filtro para que aparecesse apenas arquivos 
	 * com a extensão .paint para o usuário.
	 * Foi utilizado um ArrayList auxiliar para receber as formas contidas no arquivo e compará-las com as já contidas na tela de desenho.
	 * Caso houvesse alguma igual, a forma em questão não é adicionada na tela.
	 * @param modelo DefaultListModel - Listagem para exibição no JList de todas as formas desenhadas pelo usuário	 
	 */
	public void carregarFormas(DefaultListModel modelo) {
		int cont;
		ArrayList<Forma> formaarquivo = new ArrayList<Forma>();
		JFileChooser fileChooser = new JFileChooser();    
		fileChooser.setDialogTitle("Selecione o arquivo desejado");    
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);    

		FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos PAINT", new String[]{"paint"});    
		fileChooser.setFileFilter(filter);    
		fileChooser.setAcceptAllFileFilterUsed(false);  
		fileChooser.setMultiSelectionEnabled(false);    
		File arquivo = null;
		int opcao = fileChooser.showOpenDialog(null); 
		
        if (opcao!=1){
        	arquivo = fileChooser.getSelectedFile();
        
       	 	try (ObjectInputStream f = new ObjectInputStream(new FileInputStream(arquivo.getPath()))) {
       	 		
       	 		while(true) { 
       	 			formaarquivo.add((Forma) f.readObject());
       	 		}
       	 		
			}
        	catch(EOFException e){
        	
        	} 
			catch(Exception e) {
        		e.printStackTrace();
        	}
       	 	
       	 	
       	 for(Forma formasarquivo : formaarquivo){ 
	 			cont = 0;
	 			for (Forma forma : formas) {
	 				if(formasarquivo.equals(forma)) { 
	 					cont++;
	 				}
	 			}
	 			if(cont == 0) { 
		    		formas.add(formasarquivo);
	 			}
	    	}
       	 	
   	 		modelo.clear(); 
   	 		
       	 	for (Forma forma : formas) {
       	 		modelo.add(modelo.getSize(), forma.toString());
			}

        }
		
	}

}
