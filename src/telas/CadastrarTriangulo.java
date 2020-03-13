package telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import formas.Forma;
import formas.Formas;
import formas.Retangulo;
import formas.Triangulo;
import main.PaintJPanel;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * JDialog que será usado para o usuário selecionar as informações necessárias para desenhar um triângulo na tela. 
 * @author Mellany Linhares
 *
 */
public class CadastrarTriangulo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtBase;
	private JTextField txtAltura;
	private Color corSelecionada;
	
	 /**
	  * Construtor para a criação do JDialog.
	  * @param x int - Valor da posição X que o usuário clicou na tela.
	  * @param y int - Valor da posição Y que o usuário clicou na tela.
	  * @param formas Formas - Objeto que gerencia o ArrayList de formas.
	  * @param panel PaintJPanel - Tela de desenho.
	  * @param modelo DefaultListModel - Listagem para exibição no JList de todas as formas desenhadas pelo usuário
	  */

	public CadastrarTriangulo(int x, int y, Formas formas, PaintJPanel panel, DefaultListModel modelo) {
	
		setModalityType(DEFAULT_MODALITY_TYPE);
		setTitle("Desenhar triângulo");
		setBounds(100, 100, 382, 220);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.decode("#cce6ff"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("");
			label.setBounds(12, 24, 24, 0);
			contentPanel.add(label);
		}
		
		JLabel lblNewLabel = new JLabel("Base (px): ");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(12, 13, 75, 18);
		contentPanel.add(lblNewLabel);
		
		txtBase = new JTextField();
		txtBase.setBounds(159, 10, 150, 22);
		contentPanel.add(txtBase);
		txtBase.setColumns(10);
		{
			JLabel lblAlturapx = new JLabel("Altura (px): ");
			lblAlturapx.setFont(new Font("Arial", Font.PLAIN, 14));
			lblAlturapx.setBounds(12, 40, 75, 18);
			contentPanel.add(lblAlturapx);
		}
		{
			txtAltura = new JTextField();
			txtAltura.setBounds(159, 37, 150, 22);
			contentPanel.add(txtAltura);
			txtAltura.setColumns(10);
		}
		{
			JLabel lblCor = new JLabel("Cor:");
			lblCor.setFont(new Font("Arial", Font.PLAIN, 14));
			lblCor.setBounds(12, 69, 75, 18);
			contentPanel.add(lblCor);
		}
		
		
			JLabel mostrarCor = new JLabel("");
			mostrarCor.setBounds(307, 65, 34, 24);
			contentPanel.add(mostrarCor);
		
		/**
		 * Quando o usuário selecionar o botão de selecionar cor, será aberto um JColorChooser para a seleção da cor da forma. 
		 * Após a seleção, a cor selecionada será setada em um label para o usuário poder saber que cor foi escolhida.
		 */
		JButton btnSelecionarCor = new JButton("Selecionar cor");
		btnSelecionarCor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				corSelecionada = JColorChooser.showDialog( CadastrarTriangulo.this,"Selecione uma cor", corSelecionada );
				mostrarCor.setOpaque(true);
				mostrarCor.setBackground(corSelecionada); 
			}
		});
		btnSelecionarCor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSelecionarCor.setBounds(159, 65, 134, 25);
		btnSelecionarCor.setBackground(Color.white);
		contentPanel.add(btnSelecionarCor);
		
		{
			JLabel lblTipoDeRetngulo = new JLabel("Tipo de tri\u00E2ngulo:");
			lblTipoDeRetngulo.setFont(new Font("Arial", Font.PLAIN, 14));
			lblTipoDeRetngulo.setBounds(12, 98, 116, 18);
			contentPanel.add(lblTipoDeRetngulo);
		}
		
			JComboBox cbPreenchido = new JComboBox();
			cbPreenchido.setBackground(Color.WHITE);
			cbPreenchido.setModel(new DefaultComboBoxModel(new String[] {"Não Preenchido", "Preenchido"}));
			cbPreenchido.setBounds(159, 98, 150, 22);
			contentPanel.add(cbPreenchido);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.decode("#cce6ff"));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				/**
				 * Quando o usuário clicar no botão de desenhar, a forma será criada. Para isso, serão recolhidas as informações dadas pelo usuário no JDialog.
				 * Foi utilizada uma variável de controle para saber se a forma foi criada ou não. Caso ela não tenha sido criada, a variável recebe false.
				 * Caso ela tenha sido criada, a forma é adicionada no ArrayList de formas e, caso ela tenha sido adicionada no array, a tela de desenho é repintada e o JDialog é fechado.
				 */
				JButton btnDesenhar = new JButton("Desenhar");
				btnDesenhar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						boolean preenchido = true;
						boolean contrl = true;
						
						if(cbPreenchido.getSelectedItem().equals("Não Preenchido")) { 
							preenchido = false;
						}
						
						Triangulo triangle = new Triangulo(x,y,corSelecionada,preenchido,txtBase.getText(),txtAltura.getText()); 
						
						 if (triangle.getBase() == 0 && triangle.getAltura() == 0) { 
	                            contrl = false;   
						 } 
						 
						 if(contrl == true){ 
							 if(formas.addFormas(triangle,modelo)) { 
	                            	panel.repaint();
	                                dispose();
	                            }
	                        }
					}
				});
				btnDesenhar.setBackground(Color.WHITE);
				btnDesenhar.setFont(new Font("Arial", Font.PLAIN, 14));
				btnDesenhar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				buttonPane.add(btnDesenhar);
				getRootPane().setDefaultButton(btnDesenhar);
			}
			{
				/**
				 * Caso o usuário aperte no botão de cancelar, a tela é fechada.
				 */
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) { 
						
						dispose();
						
					}
				});
				btnCancelar.setBackground(Color.WHITE);
				btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
}
