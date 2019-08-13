package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import formas.Circulo;
import formas.Formas;
import formas.Retangulo;
import formas.Triangulo;
import telas.EditarCirculo;
import telas.EditarRetangulo;
import telas.EditarTriangulo;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;


import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import exceptions.NenhumItemSelecionado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ScrollPaneConstants;

/**
 * JFrame que contém a tela de desenho, a listagem para a exibição para o usuário e todos os botões necessários
 * para desenhar, editar e excluir uma forma.
 * @author Mellany Linhares
 *
 */

public class Paint extends JFrame {

	private JPanel contentPane;
	int selecaoBotao;
	Formas f = new Formas();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					Paint frame = new Paint();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Paint() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 978, 764);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 250));
		contentPane.setBorder(null);
		setTitle("Paint");
		setContentPane(contentPane);
		
		
		PaintJPanel panel = new PaintJPanel();
		//JPanel panel = new JPanel();
		
		DefaultListModel modelo = new DefaultListModel();
		JList listaFiguras = new JList(modelo);		
		listaFiguras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		panel.setInformacoes(f, panel,modelo);
		
		JScrollPane scrollPaint = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaint.setBackground(Color.white);
		panel.setBackground(Color.white);

		
		JPanel grupoBotoes = new JPanel();
		grupoBotoes.setBorder(null);
		
		JScrollPane tableFiguras = new JScrollPane(listaFiguras);
		tableFiguras.setBackground(Color.white);
		tableFiguras.setBorder(BorderFactory.createTitledBorder("Figuras"));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBackground(new Color(255, 250, 250));
		
		JPanel botoesTable = new JPanel();
		botoesTable.setBackground(new Color(255, 250, 250));
		botoesTable.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setOpaque(true);
		btnEditar.setBackground(Color.WHITE);
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 14));
		botoesTable.add(btnEditar,BorderLayout.EAST);
		
		/**
		 * Quando o usuário selecionar uma forma na JList e apertar o botão de editar, será aberta o JDialog correspondente a essa forma para a edição 
		 * da mesma. 
		 * @throws NenhumItemSelecionado - Verifica se o usuário selecionou alguma forma.
		 */
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if(listaFiguras.getSelectedIndex() < 0) {
						throw new NenhumItemSelecionado("Selecione uma forma antes de continuar.");
					}
			
					if(f.getFormas().get(listaFiguras.getSelectedIndex()) instanceof Circulo) {
						EditarCirculo editarCirculo = new EditarCirculo(f,panel,modelo,listaFiguras.getSelectedIndex());
						editarCirculo.setVisible(true);
					}
					if(f.getFormas().get(listaFiguras.getSelectedIndex()) instanceof Retangulo) {
						EditarRetangulo editarRetangulo = new EditarRetangulo(f,panel,modelo,listaFiguras.getSelectedIndex());
						editarRetangulo.setVisible(true);
					}
					if(f.getFormas().get(listaFiguras.getSelectedIndex()) instanceof Triangulo) {
						EditarTriangulo editarTriangulo = new EditarTriangulo(f,panel,modelo,listaFiguras.getSelectedIndex());
						editarTriangulo.setVisible(true);
					}
					
				}
				catch(NenhumItemSelecionado e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setOpaque(true);
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setFont(new Font("Arial", Font.PLAIN, 14));
		botoesTable.add(btnExcluir,BorderLayout.EAST);
		
		/**
		 * Caso o usuário selecione o botão de excluir forma, o método excluirFormas da classe Formas é acionado.
		 */
		btnExcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				f.excluirFormas(listaFiguras.getSelectedIndex(), modelo, panel);
			}
		});
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setOpaque(true);
		btnLimpar.setBackground(Color.WHITE);
		btnLimpar.setFont(new Font("Arial", Font.PLAIN, 14));
		botoesTable.add(btnLimpar,BorderLayout.EAST);
		
		/**
		 * Caso o usuário selecione o botão de limpar tela, o método limparTela da classe Formas é acionado.
		 */
		btnLimpar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				f.limparTela(modelo, panel);
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(scrollPaint, GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
					.addGap(0)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(tableFiguras, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
						.addComponent(grupoBotoes, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
						.addComponent(botoesTable, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(grupoBotoes, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tableFiguras, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(botoesTable, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addGap(0))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPaint, GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
							.addContainerGap())))
		);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 250, 250));
		toolBar.add(menuBar);
		
		JMenu menuArquivo = new JMenu("Arquivo");
		menuArquivo.setBackground(new Color(255, 250, 250));
		menuArquivo.setFont(new Font("Arial", Font.PLAIN, 17));
		menuBar.add(menuArquivo);
		
		/**
		 * Caso o usuário selecione a opção de carregar arquivos na toolbar, o método carregarFormas da classe Formas é acionado.
		 */
		JMenuItem menuItemCarregar = new JMenuItem("Carregar arquivos");
		menuItemCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				f.carregarFormas(modelo);
				panel.repaint();
				
			}
		});
		menuItemCarregar.setBackground(new Color(255, 255, 255));
		menuItemCarregar.setFont(new Font("Arial", Font.PLAIN, 15));
		menuArquivo.add(menuItemCarregar);
		
		/**
		 * Caso o usuário selecione a opção de salvar arquivos na toolbar, o método salvarFormas da classe Formas é acionado.
		 */
		JMenuItem menuItemSalvar = new JMenuItem("Salvar arquivos");
		menuItemSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				f.salvarFormas();

			}
		});
		menuItemSalvar.setBackground(new Color(255, 255, 255));
		menuItemSalvar.setFont(new Font("Arial", Font.PLAIN, 15));
		menuArquivo.add(menuItemSalvar);
		grupoBotoes.setLayout(new GridLayout(3,1));
		
		Icon iconRect = new ImageIcon(getClass().getResource("/imagens/rectangle3.png"));
		JButton btnRetangulo = new JButton(iconRect);
		btnRetangulo.setBackground(Color.white);
		grupoBotoes.add(btnRetangulo);
		
		Icon iconTriangle = new ImageIcon(getClass().getResource("/imagens/triangle.png"));
		JButton btnTriangulo = new JButton(iconTriangle);
		btnTriangulo.setBackground(Color.white);
		grupoBotoes.add(btnTriangulo);
		
		Icon iconCircle = new ImageIcon(getClass().getResource("/imagens/oval.png"));
		JButton btnCirculo = new JButton(iconCircle);
		btnCirculo.setBackground(Color.white);
		grupoBotoes.add(btnCirculo);
		contentPane.setLayout(gl_contentPane);
		
		/**
		 * Caso o usuário selecione o botão "retângulo", o botão em questão assume a cor cinza, enquanto os outros dois correspondentes ao círculo e ao
		 * triângulo assumem a cor branca, para que o usuário saiba qual botão foi selecionado. 
		 * Após isso, é setado no panel o botão selecionado (o int correspondente ao retângulo é 1).
		 */
		btnRetangulo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				btnRetangulo.setBackground(Color.LIGHT_GRAY);
				btnTriangulo.setBackground(Color.white);
				btnCirculo.setBackground(Color.white);
				
				selecaoBotao = 1;
				panel.setBotao(selecaoBotao);
				
			}
		});
		
		/**
		 * Caso o usuário selecione o botão "círculo", o botão em questão assume a cor cinza, enquanto os outros dois correspondentes ao retângulo e ao
		 * triângulo assumem a cor branca, para que o usuário saiba qual botão foi selecionado. 
		 * Após isso, é setado no panel o botão selecionado (o int correspondente ao círculo é 3).
		 */
		btnCirculo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				btnRetangulo.setBackground(Color.white);
				btnTriangulo.setBackground(Color.white);
				btnCirculo.setBackground(Color.LIGHT_GRAY);
				selecaoBotao = 3;
				panel.setBotao(selecaoBotao);
				
			}
		});
		
		/**
		 * Caso o usuário selecione o botão "triângulo", o botão em questão assume a cor cinza, enquanto os outros dois correspondentes ao círculo e ao
		 * retângulo assumem a cor branca, para que o usuário saiba qual botão foi selecionado. 
		 * Após isso, é setado no panel o botão selecionado (o int correspondente ao triângulo é 2).
		 */
		btnTriangulo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnRetangulo.setBackground(Color.white);
				btnTriangulo.setBackground(Color.LIGHT_GRAY);
				btnCirculo.setBackground(Color.white);
				selecaoBotao = 2;
				panel.setBotao(selecaoBotao);
				
			}
		});
		
	}
}
