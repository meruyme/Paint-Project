package telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
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

import formas.Circulo;
import formas.Forma;
import formas.Formas;
import formas.Retangulo;
import main.PaintJPanel;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

/**
 * JDialog que ser� usado para o usu�rio editar as informa��es referentes a um c�rculo. 
 * @author Mellany Linhares
 *
 */
public class EditarCirculo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtRaio;
	private Color corSelecionada;
	private JTextField txtY;
	private JTextField txtX;
	
	/**
	 * Construtor para a cria��o do JDialog. 
	 * @param formas Formas - Objeto que gerencia o ArrayList de formas.
	 * @param panel PaintJPanel - Tela de desenho.
	 * @param modelo DefaultListModel - Listagem para exibi��o no JList de todas as formas desenhadas pelo usu�rio.
	 * @param formaSelecionada int - Posi��o da forma selecionada no ArrayList de formas.
	 */
	public EditarCirculo(Formas formas, PaintJPanel panel,DefaultListModel modelo, int formaSelecionada) {
	    
	    Circulo circle = (Circulo) formas.getFormas().get(formaSelecionada);
	    corSelecionada = circle.getCor();
	    
		setModalityType(DEFAULT_MODALITY_TYPE);
		setTitle("Editar c�rculo");
		setBounds(100, 100, 442, 256);
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
		
		JLabel lblNewLabel = new JLabel("Raio (px):");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(12, 69, 75, 18);
		contentPanel.add(lblNewLabel);
		
		txtRaio = new JTextField();
		txtRaio.setBounds(159, 69, 150, 22);
		txtRaio.setText(Integer.toString(circle.getRaio()));
		contentPanel.add(txtRaio);
		txtRaio.setColumns(10);
		{
			JLabel lblCor = new JLabel("Cor:");
			lblCor.setFont(new Font("Arial", Font.PLAIN, 14));
			lblCor.setBounds(12, 97, 75, 18);
			contentPanel.add(lblCor);
		}
		
		
			JLabel mostrarCor = new JLabel("");
			mostrarCor.setBounds(305, 97, 34, 24);
			contentPanel.add(mostrarCor);
			mostrarCor.setOpaque(true);
			mostrarCor.setBackground(corSelecionada);
		
		/**
		 * Quando o usu�rio selecionar o bot�o de selecionar cor, ser� aberto um JColorChooser para a sele��o da cor da forma. 
		 * Ap�s a sele��o, a cor selecionada ser� setada em um label para o usu�rio poder saber que cor foi escolhida.
		 */
		JButton btnSelecionarCor = new JButton("Selecionar cor");
		btnSelecionarCor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				corSelecionada = JColorChooser.showDialog(EditarCirculo.this,"Selecione uma cor", corSelecionada );
				mostrarCor.setOpaque(true);
				mostrarCor.setBackground(corSelecionada);
			}
		});
		btnSelecionarCor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSelecionarCor.setBounds(159, 97, 134, 25);
		btnSelecionarCor.setBackground(Color.white);
		contentPanel.add(btnSelecionarCor);
		
		JLabel lblSelecioneOTipo = new JLabel("Tipo de \r\nc\u00EDrculo:");
		lblSelecioneOTipo.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSelecioneOTipo.setBounds(12, 126, 108, 27);
		contentPanel.add(lblSelecioneOTipo);
		
		JComboBox cbPreenchido = new JComboBox();
		cbPreenchido.setOpaque(true);
		cbPreenchido.setBackground(Color.WHITE);
		cbPreenchido.setModel(new DefaultComboBoxModel(new String[] {"N�o Preenchido", "Preenchido"}));
		cbPreenchido.setBounds(159, 126, 150, 22);
		
		if(circle.isPreenchido()) {
			cbPreenchido.setSelectedItem("Preenchido");
		}
		else {
			cbPreenchido.setSelectedItem("N�o Preenchido");
		}
		
		contentPanel.add(cbPreenchido);
		
		JLabel lblPosioYpx = new JLabel("Posi\u00E7\u00E3o Y (px):");
		lblPosioYpx.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPosioYpx.setBounds(12, 41, 108, 18);
		contentPanel.add(lblPosioYpx);
		
		txtY = new JTextField();
		txtY.setColumns(10);
		txtY.setBounds(159, 41, 150, 22);
		txtY.setText(Integer.toString(circle.getPosicaoY()));
		contentPanel.add(txtY);
		
		JLabel lblPosioXpx = new JLabel("Posi\u00E7\u00E3o X (px):");
		lblPosioXpx.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPosioXpx.setBounds(12, 13, 108, 18);
		contentPanel.add(lblPosioXpx);
		
		txtX = new JTextField();
		txtX.setColumns(10);
		txtX.setBounds(159, 13, 150, 22);
		txtX.setText(Integer.toString(circle.getPosicaoX()));
		contentPanel.add(txtX);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.decode("#cce6ff"));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				/**
				 * Quando o usu�rio clicar no bot�o de editar, a forma ser� editada. Para isso, ser�o recolhidas as informa��es dadas pelo usu�rio no JDialog.
				 * Foi utilizada uma vari�vel de controle para saber se a forma foi editada ou n�o. A vari�vel de controle recebe o return do m�todo editarC�rculo.
				 * Caso ela tenha sido editada, a forma � atualizada no ArrayList de formas, o panel � repintado e o JDialog fecha.
				 */
				JButton btnDesenhar = new JButton("Editar");
				btnDesenhar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						boolean preenchido = true;
						boolean contrl = false;
						
						if(cbPreenchido.getSelectedItem().equals("N�o Preenchido")) {
							preenchido = false;
						}
						
						contrl = circle.editarCirculo(txtX.getText(), txtY.getText(), corSelecionada, preenchido, txtRaio.getText(),formas,formaSelecionada);
						
						if(contrl == true) {
							formas.getFormas().set(formaSelecionada, circle);
							modelo.set(formaSelecionada, circle.toString());
							panel.repaint();
							dispose();
						}
					}
				});
				btnDesenhar.setOpaque(true);
				btnDesenhar.setBackground(Color.WHITE);
				btnDesenhar.setFont(new Font("Arial", Font.PLAIN, 14));
				buttonPane.add(btnDesenhar);
				getRootPane().setDefaultButton(btnDesenhar);
			}
			{
				/**
				 * Caso o usu�rio aperte no bot�o de cancelar, a tela � fechada.
				 */
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						dispose();
						
					}
				});
				btnCancelar.setOpaque(true);
				btnCancelar.setBackground(Color.WHITE);
				btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		
	}
}
