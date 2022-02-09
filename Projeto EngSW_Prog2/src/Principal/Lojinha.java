package Principal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.BorderLayout;
import java.sql.*;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JSeparator;
import java.awt.FlowLayout;

public class Lojinha {

	private JFrame frame;
	private final JInternalFrame Tela_Login = new JInternalFrame("Login - EG Commerce");
	private final JInternalFrame tela_Cadastro = new JInternalFrame("Cadastro - EG Commerce");
	private final JInternalFrame tela_CadastroProduto = new JInternalFrame("Cadastrar Produto - EG Commerce");
	private final JInternalFrame tela_produtos = new JInternalFrame("Produtos - EG Commerce");
	private final JInternalFrame tela_FinalPedido = new JInternalFrame("Finaliza\u00E7\u00E3o de pedido");
	private final JInternalFrame tela_MeusPedidos = new JInternalFrame("Meus Pedidos");
	private JTextField txtBuscar;
	private JTextField txtEmail;
	private JPasswordField txtSenha;
	private JPasswordField txtPermissao;
	private JLabel lblEsqueceu;
	private JButton btnEntrar;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private JTextField txtEndereco;
	private JTextField txtN;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtComplemento;
	private JTextField txtCep;
	private JTextField txtNumero;
	private JTextField txtValidade;
	private JTextField txtCvv;
	private JTextField txtNomeCartao;
	private JTextField txtEmailCadastro;
	private JTextField txtSenhaCadastro;
	private JTextField txtConfirmarSenha;
	private JTextField txtPermissoGerada;
	private static Random rand = new Random();
	private static Random PedidoRand = new Random();
	private static char[] letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
	private static char [] nPedidos = "0123456789".toCharArray();
	private JRadioButton radioCartaoSim;
	private JRadioButton radioCartaoNao;
	private JTextField txtCpf;
	private JTextField txtNomeProduto;
	private JTextField txtPrecoProduto;
	private JTextField txtPermissaoCadastrarProduto;
	private JTextField txtAddProduto;
	private JTextField txtRemover;
	private JRadioButton rdbtnCartao;
	private JTextField txtValorTotal;
	private JTextField txtParcelas;
	private JTextField txtNomeCliente;
	private JTextField txtNomeClienteFinalizar;
	private JTextField txtNumPedidoGerado;
	private JTextField txtNumPedido;
	private JMenuItem menuVerCarrinho;
	private JTextField txtNumCartaoPedido;
	private JTextField txtNomeCartaoPedido;
	private JTextField txtCvvCartaoPedido;
	private JTextField txtValidadeCartaoPedido;
	

	/**
	 * Launch the application.
	 * @throws Exception 
	 */
	public static void main(String[] args) {
		BancoDeDados bancoDeDados = new BancoDeDados();
		bancoDeDados.conectar();
		
		if(bancoDeDados.estaConectado())
			System.out.println("Está conectado!!!");
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lojinha window = new Lojinha();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public Lojinha() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		float total=0;
		float parcela;
		
		//CRIANDO A JANELA PRINCIPAL
		
		frame = new JFrame();
		frame.setBounds(100, 100, 807, 675);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblBemVindo = new JLabel("Fa\u00E7a seu login");
		lblBemVindo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBemVindo.setFont(new Font("Arial", Font.ITALIC, 15));
		lblBemVindo.setBounds(524, 22, 259, 22);
		frame.getContentPane().add(lblBemVindo);
		
		txtBuscar = new JTextField();
		txtBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
		txtBuscar.setBounds(548, 580, 235, 22);
		frame.getContentPane().add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnBuscar.setBounds(698, 606, 85, 22);
		frame.getContentPane().add(btnBuscar);
		
		//----------------------------------------------
		
		//BUSCAR PRODUTO 
		
		JInternalFrame produtoBuscado = new JInternalFrame("Produto Buscado");
		produtoBuscado.setClosable(true);
		produtoBuscado.setBounds(186, 70, 322, 175);
		frame.getContentPane().add(produtoBuscado);
		produtoBuscado.getContentPane().setLayout(null);
		
		JLabel lblNomeProdutoBuscado = new JLabel("Nome");
		lblNomeProdutoBuscado.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNomeProdutoBuscado.setBounds(10, 10, 290, 24);
		produtoBuscado.getContentPane().add(lblNomeProdutoBuscado);
		
		JLabel lblPrecoProdutoBuscado = new JLabel("Pre\u00E7o:");
		lblPrecoProdutoBuscado.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPrecoProdutoBuscado.setBounds(10, 44, 290, 24);
		produtoBuscado.getContentPane().add(lblPrecoProdutoBuscado);
		
		JLabel lblCodigoProdutoBuscado = new JLabel("C\u00F3digo:");
		lblCodigoProdutoBuscado.setFont(new Font("Arial", Font.PLAIN, 20));
		lblCodigoProdutoBuscado.setBounds(10, 78, 290, 24);
		produtoBuscado.getContentPane().add(lblCodigoProdutoBuscado);
		
		JButton btnOkProdutoBuscado = new JButton("OK");
		btnOkProdutoBuscado.setFont(new Font("Arial", Font.PLAIN, 20));
		btnOkProdutoBuscado.setBounds(215, 112, 85, 24);
		produtoBuscado.getContentPane().add(btnOkProdutoBuscado);
		
		//----------------------------------------------
		
		//CRIANDO A TELA DE LOGIN
	
		Tela_Login.setClosable(true);
		Tela_Login.setBounds(199, 152, 420, 239);
		frame.getContentPane().add(Tela_Login);
		Tela_Login.getContentPane().setLayout(null);
		
		txtEmail = new JTextField();
		txtEmail.setForeground(Color.GRAY);
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 20));
		txtEmail.setBounds(10, 21, 388, 30);
		Tela_Login.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		JRadioButton radioAdmin = new JRadioButton("admin");
		radioAdmin.setForeground(Color.GRAY);
		radioAdmin.setFont(new Font("Arial", Font.PLAIN, 20));
		radioAdmin.setBounds(10, 136, 81, 21);
		Tela_Login.getContentPane().add(radioAdmin);
		
		txtPermissao = new JPasswordField();
		txtPermissao.setFont(new Font("Arial", Font.PLAIN, 20));
		txtPermissao.setForeground(Color.GRAY);
		txtPermissao.setBounds(203, 131, 195, 30);
		txtPermissao.setVisible(false);
		Tela_Login.getContentPane().add(txtPermissao);
		
		lblEsqueceu = new JLabel("esqueceu a senha?");
		lblEsqueceu.setForeground(Color.GRAY);
		lblEsqueceu.setFont(new Font("Arial", Font.PLAIN, 15));
		lblEsqueceu.setBounds(265, 98, 133, 13);
		Tela_Login.getContentPane().add(lblEsqueceu);
		
		JRadioButton radioUsuario = new JRadioButton("usu\u00E1rio");
		radioUsuario.setForeground(Color.GRAY);
		radioUsuario.setFont(new Font("Arial", Font.PLAIN, 20));
		radioUsuario.setBounds(89, 136, 104, 21);
		Tela_Login.getContentPane().add(radioUsuario);
		radioUsuario.setSelected(true);
		
		ButtonGroup G = new ButtonGroup();
		G.add(radioUsuario);
		G.add(radioAdmin);
		
		JLabel lblNewLabel = new JLabel("e-mail");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 10));
		lblNewLabel.setBounds(10, 10, 45, 7);
		Tela_Login.getContentPane().add(lblNewLabel);
		
		JLabel lblSenha = new JLabel("senha");
		lblSenha.setForeground(Color.GRAY);
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 10));
		lblSenha.setBounds(10, 54, 45, 7);
		Tela_Login.getContentPane().add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(10, 65, 388, 30);
		Tela_Login.getContentPane().add(txtSenha);
		txtSenha.setForeground(Color.GRAY);
		txtSenha.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JLabel lblPermissao = new JLabel("permiss\u00E3o");
		lblPermissao.setForeground(Color.GRAY);
		lblPermissao.setFont(new Font("Arial", Font.PLAIN, 10));
		lblPermissao.setBounds(203, 118, 70, 13);
		lblPermissao.setVisible(false);
		Tela_Login.getContentPane().add(lblPermissao);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(284, 171, 114, 30);
		Tela_Login.getContentPane().add(btnEntrar);
		btnEntrar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnEntrar.setForeground(Color.GRAY);
		
		Tela_Login.setVisible(false);
	
		//----------------------------------------------
		
		//CRIANDO E ADICIONANDO ITENS AO MENU
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.GRAY);
		menuBar.setFont(new Font("Arial", Font.PLAIN, 20));
		menuBar.setBounds(0, 0, 793, 22);
		frame.getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Conta");
		menuBar.add(mnNewMenu);
		
		JMenuItem menuEntrar = new JMenuItem("Entrar");
		mnNewMenu.add(menuEntrar);
		
		JMenuItem menuCadastrar = new JMenuItem("Cadastrar");
		mnNewMenu.add(menuCadastrar);
		
		JMenuItem menuSair = new JMenuItem("Sair");
		
		mnNewMenu.add(menuSair);
		
		JMenu menuProdutos = new JMenu("Produtos");
		menuBar.add(menuProdutos);
		
		JMenuItem menuNotebooks = new JMenuItem("Notebooks");
		menuProdutos.add(menuNotebooks);
		
		JMenuItem menuPCs = new JMenuItem("PCs");
		menuProdutos.add(menuPCs);
		
		JMenuItem menuAcessorios = new JMenuItem("Acess\u00F3rios");
		menuProdutos.add(menuAcessorios);
		
		JMenuItem menuCadastroProdutos = new JMenuItem("Cadastrar Produtos");
		menuProdutos.add(menuCadastroProdutos);
		
		JMenu menuCarrinho = new JMenu("Carrinho");
		menuBar.add(menuCarrinho);		
		
		menuVerCarrinho = new JMenuItem("Ver carrinho");
		JMenuItem menuMeusPedidos = new JMenuItem("Meus pedidos");
		
		menuCarrinho.add(menuVerCarrinho);
		menuCarrinho.add(menuMeusPedidos);
		
		tela_produtos.setClosable(true);

		//----------------------------------------------
		
		//CRIANDO TELA DE LISTAGEM DE PRODUTOS
		
		tela_produtos.setBounds(43, 88, 693, 451);
		frame.getContentPane().add(tela_produtos);
		tela_produtos.getContentPane().setLayout(null);
		
		DefaultListModel model = new DefaultListModel();
		DefaultListModel model2 = new DefaultListModel();
		DefaultListModel model3 = new DefaultListModel();
		DefaultListModel model4 = new DefaultListModel();
		DefaultListModel model5 = new DefaultListModel();
		DefaultListModel model6 = new DefaultListModel();
		
		JLabel lblAddProduto = new JLabel("C\u00F3digo do produto:");
		lblAddProduto.setForeground(Color.GRAY);
		lblAddProduto.setFont(new Font("Arial", Font.PLAIN, 20));
		lblAddProduto.setBounds(85, 10, 181, 29);
		tela_produtos.getContentPane().add(lblAddProduto);
		
		txtAddProduto = new JTextField();
		txtAddProduto.setForeground(Color.GRAY);
		txtAddProduto.setFont(new Font("Arial", Font.PLAIN, 20));
		txtAddProduto.setBounds(263, 9, 170, 30);
		tela_produtos.getContentPane().add(txtAddProduto);
		txtAddProduto.setColumns(10);
		
		JButton btnAddProduto = new JButton("Adicionar ao carrinho");
		
		btnAddProduto.setForeground(Color.GRAY);
		btnAddProduto.setFont(new Font("Arial", Font.PLAIN, 20));
		btnAddProduto.setBounds(443, 10, 234, 29);
		tela_produtos.getContentPane().add(btnAddProduto);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 55, 667, 306);
		tela_produtos.getContentPane().add(tabbedPane);
		
		JScrollPane scrollListaNotebooks = new JScrollPane();
		tabbedPane.addTab("Notebooks", null, scrollListaNotebooks, null);
		
		JScrollPane scrollListaPCs = new JScrollPane();
		tabbedPane.addTab("PCs", null, scrollListaPCs, null);
		
		JScrollPane scrollListaAcessorios = new JScrollPane();
		tabbedPane.addTab("Acess\u00F3rios", null, scrollListaAcessorios, null);
		
		JList listaNotebooks = new JList();
		listaNotebooks.setModel(model);
		scrollListaNotebooks.setViewportView(listaNotebooks);
		
		JList listaPCs = new JList();
		listaPCs.setModel(model2);
		scrollListaPCs.setViewportView(listaPCs);
		
		JList listaAcessorios = new JList();
		listaAcessorios.setModel(model3);
		scrollListaAcessorios.setViewportView(listaAcessorios);
		

		tela_produtos.setVisible(false);
		
		//----------------------------------------------
		
		//CRIANDO TELA DE CADASTRO DE PRODUTOS
		
		int idProduto=0;
		
		tela_CadastroProduto.setClosable(true);
		tela_CadastroProduto.setBounds(126, 31, 411, 597);
		frame.getContentPane().add(tela_CadastroProduto);
		tela_CadastroProduto.getContentPane().setLayout(null);
		
		txtNomeProduto = new JTextField();
		txtNomeProduto.setText("Nome do produto");
		txtNomeProduto.setForeground(Color.GRAY);
		txtNomeProduto.setFont(new Font("Arial", Font.PLAIN, 20));
		txtNomeProduto.setColumns(10);
		txtNomeProduto.setBounds(10, 10, 382, 35);
		tela_CadastroProduto.getContentPane().add(txtNomeProduto);
		
		txtPrecoProduto = new JTextField();
		txtPrecoProduto.setForeground(Color.GRAY);
		txtPrecoProduto.setFont(new Font("Arial", Font.PLAIN, 20));
		txtPrecoProduto.setColumns(10);
		txtPrecoProduto.setBounds(44, 55, 348, 35);
		tela_CadastroProduto.getContentPane().add(txtPrecoProduto);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 100, 382, 323);
		tela_CadastroProduto.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextArea txtDescricaoProduto = new JTextArea();
		txtDescricaoProduto.setForeground(Color.GRAY);
		txtDescricaoProduto.setFont(new Font("Arial", Font.PLAIN, 20));
		txtDescricaoProduto.setText("Descri\u00E7\u00E3o...");
		txtDescricaoProduto.setLineWrap(true);
		txtDescricaoProduto.setWrapStyleWord(true);
		txtDescricaoProduto.setBounds(0, 0, 382, 323);
		panel.add(txtDescricaoProduto);
		
		JComboBox categoriaBox = new JComboBox();
		categoriaBox.setForeground(Color.GRAY);
		categoriaBox.setFont(new Font("Arial", Font.PLAIN, 20));
		categoriaBox.setModel(new DefaultComboBoxModel(new String[] {"Notebooks", "PCs", "Acess\u00F3rios"}));
		categoriaBox.setBounds(10, 433, 382, 35);
		tela_CadastroProduto.getContentPane().add(categoriaBox);
		
		JLabel lblPermissaoCadastrarProduto = new JLabel("Permiss\u00E3o:");
		lblPermissaoCadastrarProduto.setForeground(Color.GRAY);
		lblPermissaoCadastrarProduto.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPermissaoCadastrarProduto.setBounds(10, 478, 109, 35);
		tela_CadastroProduto.getContentPane().add(lblPermissaoCadastrarProduto);
		
		txtPermissaoCadastrarProduto = new JTextField();
		txtPermissaoCadastrarProduto.setForeground(Color.GRAY);
		txtPermissaoCadastrarProduto.setFont(new Font("Arial", Font.PLAIN, 20));
		txtPermissaoCadastrarProduto.setColumns(10);
		txtPermissaoCadastrarProduto.setBounds(116, 478, 276, 35);
		tela_CadastroProduto.getContentPane().add(txtPermissaoCadastrarProduto);
		
		JButton btnCadastrarProduto = new JButton("Cadastrar");
		btnCadastrarProduto.setForeground(Color.GRAY);
		btnCadastrarProduto.setFont(new Font("Arial", Font.PLAIN, 20));
		btnCadastrarProduto.setBounds(264, 523, 131, 34);
		tela_CadastroProduto.getContentPane().add(btnCadastrarProduto);
		
		JLabel lblRS = new JLabel("R$");
		lblRS.setForeground(Color.GRAY);
		lblRS.setFont(new Font("Arial", Font.PLAIN, 20));
		lblRS.setBounds(10, 55, 26, 35);
		tela_CadastroProduto.getContentPane().add(lblRS);
		
		tela_CadastroProduto.setVisible(false);
		
		//-------------------------------------
		
		//CRIANDO TELA DE CADASTRO
		
		tela_Cadastro.setClosable(true);
		tela_Cadastro.setBounds(67, 27, 637, 482);
		frame.getContentPane().add(tela_Cadastro);
		
		//CARRINHO
		JInternalFrame carrinho = new JInternalFrame("Carrinho");
		carrinho.setClosable(true);
		carrinho.setBounds(60, 99, 602, 470);
		frame.getContentPane().add(carrinho);
		
		//FINALIZAR PEDIDO
		JInternalFrame FinalizarPedido = new JInternalFrame("Finalizar Pedido");
		FinalizarPedido.setBounds(37, 99, 647, 480);
		frame.getContentPane().add(FinalizarPedido);
		
		JInternalFrame FrameNumPedido = new JInternalFrame("N\u00FAmero pedido");
		FrameNumPedido.setBounds(136, 116, 346, 211);
		frame.getContentPane().add(FrameNumPedido);
		FrameNumPedido.setClosable(true);
		FrameNumPedido.getContentPane().setLayout(null);
		
		JLabel lblNumPedidoRadio = new JLabel("Quer gerar um n\u00FAmero de pedido?");
		lblNumPedidoRadio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumPedidoRadio.setBounds(8, 10, 237, 13);
		FrameNumPedido.getContentPane().add(lblNumPedidoRadio);
		
		JRadioButton rdbtnPedidoSim = new JRadioButton("Sim");
		rdbtnPedidoSim.setBounds(18, 29, 52, 21);
		FrameNumPedido.getContentPane().add(rdbtnPedidoSim);
		
		JRadioButton rdbtnPedidoNao = new JRadioButton("N\u00E3o");
		rdbtnPedidoNao.setBounds(128, 29, 52, 21);
		FrameNumPedido.getContentPane().add(rdbtnPedidoNao);
		
		txtNumPedidoGerado = new JTextField();
		txtNumPedidoGerado.setEditable(false);
		txtNumPedidoGerado.setBounds(8, 66, 126, 28);
		FrameNumPedido.getContentPane().add(txtNumPedidoGerado);
		txtNumPedidoGerado.setColumns(10);
		
		JLabel lblNumeroPedido = new JLabel("Inserir n\u00FAmero do pedido:");
		lblNumeroPedido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumeroPedido.setBounds(8, 104, 172, 13);
		FrameNumPedido.getContentPane().add(lblNumeroPedido);
		
		txtNumPedido = new JTextField();
		txtNumPedido.setBounds(188, 103, 126, 19);
		FrameNumPedido.getContentPane().add(txtNumPedido);
		txtNumPedido.setColumns(10);
		
		JButton btnOkNumPedido = new JButton("OK");
		btnOkNumPedido.setBounds(234, 146, 98, 35);
		FrameNumPedido.getContentPane().add(btnOkNumPedido);
		FinalizarPedido.getContentPane().setLayout(null);
		
		JLabel lblPagamento = new JLabel("Forma de pagamento:");
		lblPagamento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPagamento.setBounds(18, 10, 218, 13);
		FinalizarPedido.getContentPane().add(lblPagamento);
		
		JRadioButton rdbtnBoleto = new JRadioButton("Boleto");
		rdbtnBoleto.setBounds(18, 29, 69, 21);
		FinalizarPedido.getContentPane().add(rdbtnBoleto);
		
		rdbtnCartao = new JRadioButton("Cart\u00E3o de cr\u00E9dito");
		rdbtnCartao.setBounds(87, 29, 138, 21);
		FinalizarPedido.getContentPane().add(rdbtnCartao);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTotal.setBounds(395, 338, 69, 27);
		FinalizarPedido.getContentPane().add(lblTotal);
		
		JButton btnConcluir = new JButton("Concluir compra");
		btnConcluir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnConcluir.setBounds(488, 413, 137, 27);
		FinalizarPedido.getContentPane().add(btnConcluir);
		btnConcluir.setEnabled(false);
		
		txtValorTotal = new JTextField();
		txtValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtValorTotal.setEditable(false);
		txtValorTotal.setBounds(472, 343, 128, 19);
		FinalizarPedido.getContentPane().add(txtValorTotal);
		txtValorTotal.setColumns(10);
		txtValorTotal.setText("0");
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(8, 56, 617, 2);
		FinalizarPedido.getContentPane().add(separator_1);
		
		JLabel lblProd = new JLabel("Produtos adicionados ao carrinho:");
		lblProd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProd.setBounds(18, 68, 218, 13);
		FinalizarPedido.getContentPane().add(lblProd);
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(18, 415, 97, 25);
		FinalizarPedido.getContentPane().add(btnVoltar);
		
		txtParcelas = new JTextField();
		txtParcelas.setEditable(false);
		txtParcelas.setBounds(497, 372, 128, 19);
		FinalizarPedido.getContentPane().add(txtParcelas);
		txtParcelas.setColumns(10);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(18, 401, 607, 2);
		FinalizarPedido.getContentPane().add(separator_2);
		
		String [] parcelas = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		
		JComboBox comboBoxParcelas = new JComboBox(parcelas);
		comboBoxParcelas.setToolTipText("1\r\n2\r\n3\r\n4\r\n5\r\n6\r\n7\r\n8\r\n9\r\n10\r\n11\r\n12");
		
		comboBoxParcelas.setMaximumRowCount(12);
		comboBoxParcelas.setEnabled(false);
		comboBoxParcelas.setBounds(229, 29, 45, 21);
		FinalizarPedido.getContentPane().add(comboBoxParcelas);
		
		txtNumCartaoPedido = new JTextField();
		txtNumCartaoPedido.setColumns(10);
		txtNumCartaoPedido.setBounds(119, 317, 144, 19);
		FinalizarPedido.getContentPane().add(txtNumCartaoPedido);
		
		JLabel lblNumCartaoPedido = new JLabel("N\u00FAmero cart\u00E3o:");
		lblNumCartaoPedido.setBounds(18, 320, 97, 13);
		FinalizarPedido.getContentPane().add(lblNumCartaoPedido);
		
		JLabel lblNomeCartaoPedido = new JLabel("Nome cart\u00E3o:");
		lblNomeCartaoPedido.setBounds(273, 320, 97, 13);
		FinalizarPedido.getContentPane().add(lblNomeCartaoPedido);
		
		txtNomeCartaoPedido = new JTextField();
		txtNomeCartaoPedido.setColumns(10);
		txtNomeCartaoPedido.setBounds(382, 317, 172, 19);
		FinalizarPedido.getContentPane().add(txtNomeCartaoPedido);
		
		JLabel lblCvvCartaoPedido = new JLabel("CVV:");
		lblCvvCartaoPedido.setBounds(18, 348, 97, 13);
		FinalizarPedido.getContentPane().add(lblCvvCartaoPedido);
		
		txtCvvCartaoPedido = new JTextField();
		txtCvvCartaoPedido.setColumns(10);
		txtCvvCartaoPedido.setBounds(119, 345, 69, 19);
		FinalizarPedido.getContentPane().add(txtCvvCartaoPedido);
		
		JLabel lblValidadeCartaoPedido = new JLabel("Validade:");
		lblValidadeCartaoPedido.setBounds(18, 375, 97, 13);
		FinalizarPedido.getContentPane().add(lblValidadeCartaoPedido);
		
		txtValidadeCartaoPedido = new JTextField();
		txtValidadeCartaoPedido.setColumns(10);
		txtValidadeCartaoPedido.setBounds(119, 372, 69, 19);
		FinalizarPedido.getContentPane().add(txtValidadeCartaoPedido);
		
		JButton btnVerificar = new JButton("Verificar");
		btnVerificar.setBounds(194, 371, 97, 20);
		FinalizarPedido.getContentPane().add(btnVerificar);
		FinalizarPedido.setBounds(57, 25, 647, 480);
		frame.getContentPane().add(FinalizarPedido);
		
		JLabel lblNomeClienteFinalizar = new JLabel("Nome Cliente:");
		lblNomeClienteFinalizar.setBounds(18, 294, 97, 13);
		FinalizarPedido.getContentPane().add(lblNomeClienteFinalizar);
		
		txtNomeClienteFinalizar = new JTextField();
		txtNomeClienteFinalizar.setEditable(false);
		txtNomeClienteFinalizar.setBounds(119, 291, 226, 19);
		FinalizarPedido.getContentPane().add(txtNomeClienteFinalizar);
		txtNomeClienteFinalizar.setColumns(10);
		
		JTabbedPane Finalizar_produtos = new JTabbedPane(JTabbedPane.TOP);
		Finalizar_produtos.setBounds(18, 98, 607, 186);
		FinalizarPedido.getContentPane().add(Finalizar_produtos);
		
		JScrollPane scrollFinalizar = new JScrollPane();
		Finalizar_produtos.addTab("Produtos", null, scrollFinalizar, null);
		
		JList listFinalizar = new JList();
		scrollFinalizar.setViewportView(listFinalizar);
		listFinalizar.setModel(model5);
		
		JLabel lblFrete = new JLabel("Frete(valor \u00FAnico para todo o Brasil): R$ 50,00");
		lblFrete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFrete.setBounds(330, 10, 295, 13);
		FinalizarPedido.getContentPane().add(lblFrete);
		
		JLabel lblFreteGratis = new JLabel("Frete gr\u00E1tis acima de R$ 1000,00");
		lblFreteGratis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFreteGratis.setBounds(330, 33, 295, 13);
		FinalizarPedido.getContentPane().add(lblFreteGratis);
		FinalizarPedido.setBounds(45, 40, 647, 480);
		frame.getContentPane().add(FinalizarPedido);
		
		carrinho.getContentPane().setLayout(null);
		
		JButton btnFinalizar = new JButton("Finalizar pedido");
		btnFinalizar.setBounds(398, 397, 169, 33);
		carrinho.getContentPane().add(btnFinalizar);
		
		JLabel lblRemover = new JLabel("Deseja remover algum produto?");
		lblRemover.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRemover.setBounds(22, 10, 192, 33);
		carrinho.getContentPane().add(lblRemover);
		
		JRadioButton rdbtnSimRemover = new JRadioButton("Sim");
		rdbtnSimRemover.setBounds(22, 56, 51, 21);
		carrinho.getContentPane().add(rdbtnSimRemover);
		
		JRadioButton rdbtnNaoRemover = new JRadioButton("N\u00E3o");
		rdbtnNaoRemover.setBounds(109, 56, 51, 21);
		carrinho.getContentPane().add(rdbtnNaoRemover);
		
		txtRemover = new JTextField();
		txtRemover.setEditable(false);
		txtRemover.setEnabled(false);
		txtRemover.setBounds(271, 50, 109, 33);
		carrinho.getContentPane().add(txtRemover);
		txtRemover.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setEnabled(false);
		btnConfirmar.setBounds(426, 50, 109, 27);
		carrinho.getContentPane().add(btnConfirmar);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(250, 10, 28, 75);
		carrinho.getContentPane().add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Digite o c\u00F3digo do produto:");
		lblNewLabel_1.setEnabled(false);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(271, 21, 192, 13);
		carrinho.getContentPane().add(lblNewLabel_1);
		tela_Cadastro.getContentPane().setLayout(null);
		
		JLabel lblDadosPessoais = new JLabel("Dados pessoais");
		lblDadosPessoais.setForeground(Color.GRAY);
		lblDadosPessoais.setFont(new Font("Arial", Font.BOLD, 15));
		lblDadosPessoais.setBounds(10, 10, 134, 13);
		tela_Cadastro.getContentPane().add(lblDadosPessoais);
		
		txtNome = new JTextField();
		txtNome.setForeground(Color.GRAY);
		txtNome.setText("Nome");
		txtNome.setFont(new Font("Arial", Font.PLAIN, 20));
		txtNome.setBounds(10, 25, 289, 30);
		tela_Cadastro.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtSobrenome = new JTextField();
		txtSobrenome.setText("Sobrenome");
		txtSobrenome.setForeground(Color.GRAY);
		txtSobrenome.setFont(new Font("Arial", Font.PLAIN, 20));
		txtSobrenome.setColumns(10);
		txtSobrenome.setBounds(10, 65, 289, 30);
		tela_Cadastro.getContentPane().add(txtSobrenome);
		
		txtEndereco = new JTextField();
		txtEndereco.setText("Endere\u00E7o");
		txtEndereco.setForeground(Color.GRAY);
		txtEndereco.setFont(new Font("Arial", Font.PLAIN, 20));
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(10, 105, 289, 30);
		tela_Cadastro.getContentPane().add(txtEndereco);
		
		txtN = new JTextField();
		txtN.setText("n\u00BA");
		txtN.setForeground(Color.GRAY);
		txtN.setFont(new Font("Arial", Font.PLAIN, 20));
		txtN.setColumns(10);
		txtN.setBounds(10, 145, 63, 30);
		tela_Cadastro.getContentPane().add(txtN);
		
		txtBairro = new JTextField();
		txtBairro.setText("Bairro");
		txtBairro.setForeground(Color.GRAY);
		txtBairro.setFont(new Font("Arial", Font.PLAIN, 20));
		txtBairro.setColumns(10);
		txtBairro.setBounds(83, 145, 216, 30);
		tela_Cadastro.getContentPane().add(txtBairro);
		
		txtCidade = new JTextField();
		txtCidade.setText("Cidade");
		txtCidade.setForeground(Color.GRAY);
		txtCidade.setFont(new Font("Arial", Font.PLAIN, 20));
		txtCidade.setColumns(10);
		txtCidade.setBounds(10, 185, 199, 30);
		tela_Cadastro.getContentPane().add(txtCidade);
		
		JComboBox estadoBox = new JComboBox();
		estadoBox.setForeground(Color.GRAY);
		estadoBox.setFont(new Font("Arial", Font.PLAIN, 20));
		estadoBox.setMaximumRowCount(5);
		estadoBox.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		estadoBox.setSelectedIndex(23);
		estadoBox.setBounds(219, 185, 80, 30);
		tela_Cadastro.getContentPane().add(estadoBox);
		
		txtComplemento = new JTextField();
		txtComplemento.setText("Complemento");
		txtComplemento.setForeground(Color.GRAY);
		txtComplemento.setFont(new Font("Arial", Font.PLAIN, 20));
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(10, 225, 289, 30);
		tela_Cadastro.getContentPane().add(txtComplemento);
		
		txtCep = new JTextField();
		txtCep.setText("CEP");
		txtCep.setForeground(Color.GRAY);
		txtCep.setFont(new Font("Arial", Font.PLAIN, 20));
		txtCep.setColumns(10);
		txtCep.setBounds(10, 265, 289, 30);
		tela_Cadastro.getContentPane().add(txtCep);
		
		JLabel lblCartão = new JLabel("Possui cart\u00E3o ?");
		lblCartão.setForeground(Color.GRAY);
		lblCartão.setFont(new Font("Arial", Font.BOLD, 15));
		lblCartão.setBounds(10, 307, 119, 13);
		tela_Cadastro.getContentPane().add(lblCartão);
		
		txtNumero = new JTextField();
		txtNumero.setText("N\u00FAmero");
		txtNumero.setForeground(Color.GRAY);
		txtNumero.setFont(new Font("Arial", Font.PLAIN, 20));
		txtNumero.setColumns(10);
		txtNumero.setBounds(10, 330, 289, 30);
		txtNumero.setVisible(false);
		tela_Cadastro.getContentPane().add(txtNumero);
		
		txtValidade = new JTextField();
		txtValidade.setText("Validade");
		txtValidade.setForeground(Color.GRAY);
		txtValidade.setFont(new Font("Arial", Font.PLAIN, 20));
		txtValidade.setColumns(10);
		txtValidade.setBounds(10, 370, 179, 30);
		txtValidade.setVisible(false);
		tela_Cadastro.getContentPane().add(txtValidade);
		
		txtCvv = new JTextField();
		txtCvv.setText("CVV");
		txtCvv.setForeground(Color.GRAY);
		txtCvv.setFont(new Font("Arial", Font.PLAIN, 20));
		txtCvv.setColumns(10);
		txtCvv.setBounds(199, 370, 100, 30);
		txtCvv.setVisible(false);
		tela_Cadastro.getContentPane().add(txtCvv);
		
		txtNomeCartao = new JTextField();
		txtNomeCartao.setText("Nome (como impresso)");
		txtNomeCartao.setForeground(Color.GRAY);
		txtNomeCartao.setFont(new Font("Arial", Font.PLAIN, 20));
		txtNomeCartao.setColumns(10);
		txtNomeCartao.setBounds(10, 410, 289, 30);
		txtNomeCartao.setVisible(false);
		tela_Cadastro.getContentPane().add(txtNomeCartao);
		
		JLabel lblDadosUsuario = new JLabel("Dados de usu\u00E1rio");
		lblDadosUsuario.setForeground(Color.GRAY);
		lblDadosUsuario.setFont(new Font("Arial", Font.BOLD, 15));
		lblDadosUsuario.setBounds(332, 10, 134, 13);
		tela_Cadastro.getContentPane().add(lblDadosUsuario);
		
		txtEmailCadastro = new JTextField();
		txtEmailCadastro.setText("e-mail");
		txtEmailCadastro.setForeground(Color.GRAY);
		txtEmailCadastro.setFont(new Font("Arial", Font.PLAIN, 20));
		txtEmailCadastro.setColumns(10);
		txtEmailCadastro.setBounds(332, 25, 289, 30);
		tela_Cadastro.getContentPane().add(txtEmailCadastro);
		
		txtSenhaCadastro = new JTextField();
		txtSenhaCadastro.setText("Senha");
		txtSenhaCadastro.setForeground(Color.GRAY);
		txtSenhaCadastro.setFont(new Font("Arial", Font.PLAIN, 20));
		txtSenhaCadastro.setColumns(10);
		txtSenhaCadastro.setBounds(332, 105, 289, 30);
		tela_Cadastro.getContentPane().add(txtSenhaCadastro);
		
		txtConfirmarSenha = new JTextField();
		txtConfirmarSenha.setText("Confirmar senha");
		txtConfirmarSenha.setForeground(Color.GRAY);
		txtConfirmarSenha.setFont(new Font("Arial", Font.PLAIN, 20));
		txtConfirmarSenha.setColumns(10);
		txtConfirmarSenha.setBounds(332, 145, 289, 30);
		tela_Cadastro.getContentPane().add(txtConfirmarSenha);
		
		JRadioButton radioAdminCadastro = new JRadioButton("admin");
		radioAdminCadastro.setForeground(Color.GRAY);
		radioAdminCadastro.setFont(new Font("Arial", Font.PLAIN, 20));
		radioAdminCadastro.setBounds(332, 190, 81, 21);
		tela_Cadastro.getContentPane().add(radioAdminCadastro);
		
		JRadioButton radioUsuarioCadastro = new JRadioButton("usu\u00E1rio");
		radioUsuarioCadastro.setForeground(Color.GRAY);
		radioUsuarioCadastro.setFont(new Font("Arial", Font.PLAIN, 20));
		radioUsuarioCadastro.setBounds(424, 190, 104, 21);
		tela_Cadastro.getContentPane().add(radioUsuarioCadastro);
		
		txtPermissoGerada = new JTextField();
		txtPermissoGerada.setForeground(Color.GRAY);
		txtPermissoGerada.setFont(new Font("Arial", Font.PLAIN, 20));
		txtPermissoGerada.setColumns(10);
		txtPermissoGerada.setBounds(505, 225, 110, 30);
		txtPermissoGerada.setVisible(false);
		tela_Cadastro.getContentPane().add(txtPermissoGerada);
		
		JLabel lblPermissaoGerada = new JLabel("Permiss\u00E3o Gerada");
		lblPermissaoGerada.setForeground(Color.GRAY);
		lblPermissaoGerada.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPermissaoGerada.setBounds(322, 225, 179, 30);
		lblPermissaoGerada.setVisible(false);
		tela_Cadastro.getContentPane().add(lblPermissaoGerada);
		
		JButton btnCadastrarUsuario = new JButton("Cadastrar");
		btnCadastrarUsuario.setForeground(Color.GRAY);
		btnCadastrarUsuario.setFont(new Font("Arial", Font.PLAIN, 20));
		btnCadastrarUsuario.setBounds(487, 410, 134, 30);
		tela_Cadastro.getContentPane().add(btnCadastrarUsuario);
		
		radioCartaoSim = new JRadioButton("Sim");
		radioCartaoSim.setBounds(154, 301, 55, 21);
		tela_Cadastro.getContentPane().add(radioCartaoSim);
		
		radioCartaoNao = new JRadioButton("N\u00E3o");
		radioCartaoNao.setBounds(244, 301, 55, 21);
		tela_Cadastro.getContentPane().add(radioCartaoNao);
		
		
		ButtonGroup H = new ButtonGroup();
		H.add(radioUsuarioCadastro);
		H.add(radioAdminCadastro);
		
		ButtonGroup C = new ButtonGroup();
		C.add(radioCartaoNao);
		C.add(radioCartaoSim);
		
		ButtonGroup Remover = new ButtonGroup();
		Remover.add(rdbtnSimRemover);
		Remover.add(rdbtnNaoRemover);
		
		ButtonGroup numPedido = new ButtonGroup();
		numPedido.add(rdbtnPedidoSim);
		numPedido.add(rdbtnPedidoNao);
		
		ButtonGroup finalizar = new ButtonGroup();
		finalizar.add(rdbtnCartao);
		finalizar.add(rdbtnBoleto);
		
		JLabel lblNomeCliente = new JLabel("Nome cliente:");
		lblNomeCliente.setBounds(22, 330, 103, 13);
		carrinho.getContentPane().add(lblNomeCliente);
		
		txtNomeCliente = new JTextField();
		txtNomeCliente.setEditable(false);
		txtNomeCliente.setBounds(133, 327, 207, 19);
		carrinho.getContentPane().add(txtNomeCliente);
		txtNomeCliente.setColumns(10);
		
		JTabbedPane paneCarrinho = new JTabbedPane(JTabbedPane.TOP);
		paneCarrinho.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		paneCarrinho.setBounds(9, 105, 571, 215);
		carrinho.getContentPane().add(paneCarrinho);
		
		JScrollPane scrollCarrinho = new JScrollPane();
		paneCarrinho.addTab("Produtos carrinho", null, scrollCarrinho, null);
		
		JList listCarrinho = new JList();
		listCarrinho.setModel(model4);
		scrollCarrinho.setViewportView(listCarrinho);
		
		txtCpf = new JTextField();
		txtCpf.setText("CPF");
		txtCpf.setForeground(Color.GRAY);
		txtCpf.setFont(new Font("Arial", Font.PLAIN, 20));
		txtCpf.setColumns(10);
		txtCpf.setBounds(332, 65, 289, 30);
		tela_Cadastro.getContentPane().add(txtCpf);
		
		tela_Cadastro.setVisible(false);
		
		//----------------------------------------------
		
		//ÚLTIMA TELA DO PEDIDO
		
		tela_FinalPedido.setBounds(53, 101, 678, 291);
		frame.getContentPane().add(tela_FinalPedido);
		tela_FinalPedido.getContentPane().setLayout(null);
		
		JLabel lblNomeFinalPedido = new JLabel("Nome");
		lblNomeFinalPedido.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNomeFinalPedido.setBounds(10, 10, 646, 29);
		tela_FinalPedido.getContentPane().add(lblNomeFinalPedido);
		
		JLabel lblNFinalPedido = new JLabel("N");
		lblNFinalPedido.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNFinalPedido.setBounds(10, 49, 646, 29);
		tela_FinalPedido.getContentPane().add(lblNFinalPedido);
		
		JLabel lblFormaFinalPedido = new JLabel("Forma de pagamento");
		lblFormaFinalPedido.setFont(new Font("Arial", Font.PLAIN, 20));
		lblFormaFinalPedido.setBounds(10, 88, 646, 29);
		tela_FinalPedido.getContentPane().add(lblFormaFinalPedido);
		
		JLabel lblValorFinalPedido = new JLabel("Valor");
		lblValorFinalPedido.setFont(new Font("Arial", Font.PLAIN, 20));
		lblValorFinalPedido.setBounds(10, 127, 646, 29);
		tela_FinalPedido.getContentPane().add(lblValorFinalPedido);
		
		JButton btnFinalPedido = new JButton("OK");
		btnFinalPedido.setFont(new Font("Arial", Font.PLAIN, 20));
		btnFinalPedido.setBounds(10, 223, 646, 29);
		tela_FinalPedido.getContentPane().add(btnFinalPedido);
		tela_FinalPedido.setVisible(false);
		
		//----------------------------------------------
		
		//TELA MEUS PEDIDOS
		
		tela_MeusPedidos.setClosable(true);
		tela_MeusPedidos.setBounds(202, 131, 402, 308);
		frame.getContentPane().add(tela_MeusPedidos);
		tela_MeusPedidos.getContentPane().setLayout(null);
		
		JLabel lblNomeClientePedidos = new JLabel("Cliente");
		lblNomeClientePedidos.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNomeClientePedidos.setBounds(10, 10, 154, 24);
		tela_MeusPedidos.getContentPane().add(lblNomeClientePedidos);
		
		JPanel panelMeusPedidos = new JPanel();
		panelMeusPedidos.setBounds(10, 44, 370, 225);
		tela_MeusPedidos.getContentPane().add(panelMeusPedidos);
		panelMeusPedidos.setLayout(null);
		
		JScrollPane scrollPaneMeusPedidos = new JScrollPane();
		scrollPaneMeusPedidos.setBounds(0, 0, 370, 225);
		panelMeusPedidos.add(scrollPaneMeusPedidos);
		
		JList listMeusPedidos = new JList();
		scrollPaneMeusPedidos.setViewportView(listMeusPedidos);
		listMeusPedidos.setModel(model6);
		tela_MeusPedidos.setVisible(false);
		
		//----------------------------------------------
		
		//ACTION LISTENERS
		
		//Action Listeners Menu
		
		menuEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela_Login.setVisible(true);
			}
		});
		
		menuCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tela_Cadastro.setVisible(true);
			}
		});
		
		menuCadastroProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tela_CadastroProduto.setVisible(true);
			}
		});
		
		menuSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		menuVerCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				model4.removeAllElements();
				String servidor = "jdbc:mysql://localhost:3306/lojita?useTimezone=true&serverTimezone=GMT";
				String usuario = "root";
				String senha = "gabe2507";
				Produto p = new Produto();
				ResultSet rs = null;
				ResultSet n = null;
				try 
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection(servidor,usuario,senha);
					PreparedStatement ic = conn.prepareStatement("select produto.* from pedido, produto where pedido.Produto_idProduto = produto.idProduto and numPedido = '"+txtNumPedido.getText()+"';");
							
								
					rs=ic.executeQuery();
					int i=0;
					model.clear();
					
					while(rs.next()) {
						p.setIdProduto(rs.getInt("idProduto"));
						p.setNome(rs.getString("nome"));
						p.setDescricao(rs.getString("descricao"));
						p.setPreco(rs.getString("preco"));
						p.setCategoria(rs.getString("categoria"));
						
						model4.add(i, p.getNome());
						i++;
						
						model4.add(i, "Preço R$: "+p.getPreco());
						i++;
						
						model4.add(i, p.getDescricao());
						i++;
						
						model4.add(i, "Código do Produto: "+p.getIdProduto());
						i++;
						
						model4.add(i, "------------------------------------------------------------------------------------------------------");
						i++;
					}
					

					Statement nc = conn.createStatement();
					ResultSet result = nc.executeQuery ("select nome,sobrenome from pessoa where email = '"+txtEmail.getText()+"';");
					result.next();
					String nameCliente = result.getString("nome")+" "+result.getString("sobrenome");
					System.out.println(nameCliente);
					
					txtNomeCliente.setText(nameCliente);
					conn.close();
					listCarrinho.removeAll();
					
				} catch(Exception b)
				{
					System.out.println("Erro: "+b);
				}
				
				carrinho.setVisible(true);
			}
		});
		
		menuMeusPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String servidor = "jdbc:mysql://localhost:3306/lojita?useTimezone=true&serverTimezone=GMT";
				String usuario = "root";
				String senha = "gabe2507";
				ResultSet rs = null;
				try 
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection(servidor,usuario,senha);

					PreparedStatement ic = conn.prepareStatement("select distinct numPedido, nome, sobrenome from pedido join cliente join pessoa where email ='"+txtEmail.getText()+"'and Cliente_Pessoa_CPF=Pessoa_CPF and Pessoa_CPF=CPF;");
					rs=ic.executeQuery();
					int i=0;

					while(rs.next()) {
						
						model6.add(i, rs.getString("numPedido"));
						i++;
						
						lblNomeClientePedidos.setText(rs.getString("nome")+" "+rs.getString("sobrenome"));
					}
					
				} catch(Exception b)
				{
					System.out.println("Erro: "+b);
				}
				
				tela_MeusPedidos.setVisible(true);
			}
		});
		
		menuNotebooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String servidor = "jdbc:mysql://localhost:3306/lojita?useTimezone=true&serverTimezone=GMT";
				String usuario = "root";
				String senha = "gabe2507";
				Produto p = new Produto();
				ResultSet rs = null;
				try 
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection(servidor,usuario,senha);

					PreparedStatement ic = conn.prepareStatement("select * from produto where categoria = 'Notebooks';");
					rs=ic.executeQuery();
					int i=0;
					model.clear();
					
					while(rs.next()) {
						p.setIdProduto(rs.getInt("idProduto"));
						p.setNome(rs.getString("nome"));
						p.setDescricao(rs.getString("descricao"));
						p.setPreco(rs.getString("preco"));
						p.setCategoria(rs.getString("categoria"));
						
						model.add(i, p.getNome());
						i++;
						
						model.add(i, "Preço R$: "+p.getPreco());
						i++;
						
						model.add(i, p.getDescricao());
						i++;
						
						model.add(i, "Código do Produto: "+p.getIdProduto());
						i++;
						
						model.add(i, "------------------------------------------------------------------------------------------------------");
						i++;
					}
				} catch(Exception b)
				{
					System.out.println("Erro: "+b);
				}
				
				tabbedPane.setSelectedIndex(0);
				tela_produtos.setVisible(true);
			}
		});
		
		tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(tabbedPane.getSelectedIndex()==0) {
                	String servidor = "jdbc:mysql://localhost:3306/lojita?useTimezone=true&serverTimezone=GMT";
    				String usuario = "root";
    				String senha = "gabe2507";
    				Produto p = new Produto();
    				ResultSet rs = null;
    				
    				try 
    				{
    					Class.forName("com.mysql.cj.jdbc.Driver");
    					Connection conn = DriverManager.getConnection(servidor,usuario,senha);

    					PreparedStatement ic = conn.prepareStatement("select * from produto where categoria = 'Notebooks';");
    					rs=ic.executeQuery();
    					int ii=0;
    					model.clear();
    					
    					while(rs.next()) {
    						p.setIdProduto(rs.getInt("idProduto"));
    						p.setNome(rs.getString("nome"));
    						p.setDescricao(rs.getString("descricao"));
    						p.setPreco(rs.getString("preco"));
    						p.setCategoria(rs.getString("categoria"));
    						
    						model.add(ii, p.getNome());
    						ii++;
    						
    						model.add(ii, "Preço R$: "+p.getPreco());
    						ii++;
    						
    						model.add(ii, p.getDescricao());
    						ii++;
    						
    						model.add(ii, "Código do Produto: "+p.getIdProduto());
    						ii++;
    						
    						model.add(ii, "------------------------------------------------------------------------------------------------------");
    						ii++;
    					}
    				} catch(Exception b)
    				{
    					System.out.println("Erro: "+b);
    				}
    				
                }
                
                if(tabbedPane.getSelectedIndex()==1) {
                	String servidor = "jdbc:mysql://localhost:3306/lojita?useTimezone=true&serverTimezone=GMT";
    				String usuario = "root";
    				String senha = "gabe2507";
    				Produto p = new Produto();
    				ResultSet rs = null;
    				
    				try 
    				{
    					Class.forName("com.mysql.cj.jdbc.Driver");
    					Connection conn = DriverManager.getConnection(servidor,usuario,senha);

    					PreparedStatement ic = conn.prepareStatement("select * from produto where categoria = 'PCs';");
    					rs=ic.executeQuery();
    					int jj=0;
    					model2.clear();
    					
    					while(rs.next()) {
    						p.setIdProduto(rs.getInt("idProduto"));
    						p.setNome(rs.getString("nome"));
    						p.setDescricao(rs.getString("descricao"));
    						p.setPreco(rs.getString("preco"));
    						p.setCategoria(rs.getString("categoria"));
    						
    						model2.add(jj, p.getNome());
    						jj++;
    						
    						model2.add(jj, "Preço R$: "+p.getPreco());
    						jj++;
    						
    						model2.add(jj, p.getDescricao());
    						jj++;
    						
    						model2.add(jj, "Código do Produto: "+p.getIdProduto());
    						jj++;
    						
    						model2.add(jj, "------------------------------------------------------------------------------------------------------");
    						jj++;
    					}
    				} catch(Exception b)
    				{
    					System.out.println("Erro: "+b);
    				}
    				
                }
                
                if(tabbedPane.getSelectedIndex()==1) {
                	String servidor = "jdbc:mysql://localhost:3306/lojita?useTimezone=true&serverTimezone=GMT";
    				String usuario = "root";
    				String senha = "gabe2507";
    				Produto p = new Produto();
    				ResultSet rs = null;
    				
    				try 
    				{
    					Class.forName("com.mysql.cj.jdbc.Driver");
    					Connection conn = DriverManager.getConnection(servidor,usuario,senha);

    					PreparedStatement ic = conn.prepareStatement("select * from produto where categoria = 'Acessórios';");
    					rs=ic.executeQuery();
    					int kk=0;
    					model3.clear();
    					
    					while(rs.next()) {
    						p.setIdProduto(rs.getInt("idProduto"));
    						p.setNome(rs.getString("nome"));
    						p.setDescricao(rs.getString("descricao"));
    						p.setPreco(rs.getString("preco"));
    						p.setCategoria(rs.getString("categoria"));
    						
    						model3.add(kk, p.getNome());
    						kk++;
    						
    						model3.add(kk, "Preço R$: "+p.getPreco());
    						kk++;
    						
    						model3.add(kk, p.getDescricao());
    						kk++;
    						
    						model3.add(kk, "Código do Produto: "+p.getIdProduto());
    						kk++;
    						
    						model3.add(kk, "------------------------------------------------------------------------------------------------------");
    						kk++;
    					}
    				} catch(Exception b)
    				{
    					System.out.println("Erro: "+b);
    				}
    				
                }
            }
        });
		
		menuPCs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String servidor = "jdbc:mysql://localhost:3306/lojita?useTimezone=true&serverTimezone=GMT";
				String usuario = "root";
				String senha = "gabe2507";
				Produto p = new Produto();
				PreparedStatement ic = null;
				ResultSet rs = null;
				try 
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection(servidor,usuario,senha);

					ic = conn.prepareStatement("select * from produto where categoria = 'PCs';");
					rs=ic.executeQuery();
					int j=0;
					model2.clear();
					
					while(rs.next()) {
						p.setIdProduto(rs.getInt("idProduto"));
						p.setNome(rs.getString("nome"));
						p.setDescricao(rs.getString("descricao"));
						p.setPreco(rs.getString("preco"));
						p.setCategoria(rs.getString("categoria"));
						
						model2.add(j, p.getNome());
						j++;
						
						model2.add(j, "Preço: "+p.getPreco());
						j++;
						
						model2.add(j, p.getDescricao());
						j++;
						
						model2.add(j, "Código do Produto: "+p.getIdProduto());
						j++;
						
						model2.add(j, "------------------------------------------------------------------------------------------------------");
						j++;
					}
				} catch(Exception b)
				{
					System.out.println("Erro: "+b);
				}
				
				tabbedPane.setSelectedIndex(1);
				tela_produtos.setVisible(true);
			}
		});
		
		menuAcessorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String servidor = "jdbc:mysql://localhost:3306/lojita?useTimezone=true&serverTimezone=GMT";
				String usuario = "root";
				String senha = "gabe2507";
				Produto p = new Produto();
				PreparedStatement ic = null;
				ResultSet rs = null;
				try 
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection(servidor,usuario,senha);

					ic = conn.prepareStatement("select * from produto where categoria = 'Acessórios';");
					rs=ic.executeQuery();
					int k=0;
					model3.clear();
					
					while(rs.next()) {
						p.setIdProduto(rs.getInt("idProduto"));
						p.setNome(rs.getString("nome"));
						p.setDescricao(rs.getString("descricao"));
						p.setPreco(rs.getString("preco"));
						p.setCategoria(rs.getString("categoria"));
						
						model3.add(k, p.getNome());
						k++;
						
						model3.add(k, "Preço: "+p.getPreco());
						k++;
						
						model3.add(k, p.getDescricao());
						k++;
						
						model3.add(k, "Código do Produto: "+p.getIdProduto());
						k++;
						
						model3.add(k, "------------------------------------------------------------------------------------------------------");
						k++;
					}
				} catch(Exception b)
				{
					System.out.println("Erro: "+b);
				}
				
				tabbedPane.setSelectedIndex(2);
				tela_produtos.setVisible(true);
			}
		});
		
		//-----------------------
		
		//Action Listeners Login
		
		radioAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPermissao.setVisible(true);
				lblPermissao.setVisible(true);
			}
		});
		
		radioUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPermissao.setVisible(false);
				lblPermissao.setVisible(false);
			}
		});
		
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				String servidor = "jdbc:mysql://localhost:3306/lojita?useTimezone=true&serverTimezone=GMT";
				String usuario = "root";
				String senha = "gabe2507";
				ResultSet rs = null;
				String pass = String.valueOf(txtSenha.getPassword());
				String perm = String.valueOf(txtPermissao.getPassword());
				try 
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection(servidor,usuario,senha);

					PreparedStatement ic = conn.prepareStatement("select * from Pessoa join Cliente where email='"+txtEmail.getText()+"'and CPF=Pessoa_CPF");
					PreparedStatement ic2 = conn.prepareStatement("select * from Pessoa join Funcionario where email='"+txtEmail.getText()+"'and CPF=Pessoa_CPF");
					if(radioAdmin.isSelected()==false) {
						rs=ic.executeQuery();
						if(rs != null && rs.next()){
							if(rs.getString("senha").equals(pass)) {
								System.out.println("Bem-vindo!");
								Tela_Login.dispose();
								lblBemVindo.setText("Bem-vindo(a), "+rs.getString("nome")+" "+rs.getString("sobrenome"));
							}
							else
								System.out.println("Cai fora!");
						}
					}
					else {
						rs=ic2.executeQuery();
						if(rs != null && rs.next()){
							if(rs.getString("senha").equals(pass)&&rs.getString("codAdmin").equals(perm)) {
								System.out.println("Bem-vindo!");
								Tela_Login.dispose();
								lblBemVindo.setText("Bem-vindo(a), "+rs.getString("nome")+" "+rs.getString("sobrenome"));
							}
							else
								System.out.println("Cai fora!");
						}
					}
					
				} catch(Exception b)
				{
					System.out.println("Erro: "+b);
				}
			}
		});
		
		menuCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String servidor = "jdbc:mysql://localhost:3306/lojita?useTimezone=true&serverTimezone=GMT";
				String usuario = "root";
				String senha = "gabe2507";
				Produto p = new Produto();
				ResultSet rs = null;
				try 
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection(servidor,usuario,senha);
					PreparedStatement ic = conn.prepareStatement("select produto.* from pedido, produto where '"+txtAddProduto.getText()+"' = produto.idProduto and numPedido = '"+txtNumPedido.getText()+"';");
							
								
					rs=ic.executeQuery();
					int i=0;
					model.clear();
					
					while(rs.next()) {
						p.setIdProduto(rs.getInt("idProduto"));
						p.setNome(rs.getString("nome"));
						p.setDescricao(rs.getString("descricao"));
						p.setPreco(rs.getString("preco"));
						p.setCategoria(rs.getString("categoria"));
						
						model.add(i, p.getNome());
						i++;
						
						model.add(i, "Preço R$: "+p.getPreco());
						i++;
						
						model.add(i, p.getDescricao());
						i++;
						
						model.add(i, "Código do Produto: "+p.getIdProduto());
						i++;
						
						model.add(i, "------------------------------------------------------------------------------------------------------");
						i++;
					}
				} catch(Exception b)
				{
					System.out.println("Erro: "+b);
				}
				
				tabbedPane.setSelectedIndex(0);
				carrinho.setVisible(true);
			}
		});
		
		JButton btnAbrirCarrinho = new JButton("Abir carrinho");
		btnAbrirCarrinho.setBounds(561, 381, 116, 40);
		tela_produtos.getContentPane().add(btnAbrirCarrinho);
		
		btnAbrirCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carrinho.setVisible(true);
				tela_produtos.setVisible(false);
				
				model4.removeAllElements();
				String servidor = "jdbc:mysql://localhost:3306/lojita?useTimezone=true&serverTimezone=GMT";
				String usuario = "root";
				String senha = "gabe2507";
				Produto p = new Produto();
				ResultSet rs = null;
				ResultSet n = null;
				try 
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection(servidor,usuario,senha);
					PreparedStatement ic = conn.prepareStatement("select produto.* from pedido, produto where pedido.Produto_idProduto = produto.idProduto and numPedido = '"+txtNumPedido.getText()+"';");
							
								
					rs=ic.executeQuery();
					int i=0;
					model.clear();
					
					while(rs.next()) {
						p.setIdProduto(rs.getInt("idProduto"));
						p.setNome(rs.getString("nome"));
						p.setDescricao(rs.getString("descricao"));
						p.setPreco(rs.getString("preco"));
						p.setCategoria(rs.getString("categoria"));
						
						model4.add(i, p.getNome());
						i++;
						
						model4.add(i, "Preço R$: "+p.getPreco());
						i++;
						
						model4.add(i, p.getDescricao());
						i++;
						
						model4.add(i, "Código do Produto: "+p.getIdProduto());
						i++;
						
						model4.add(i, "------------------------------------------------------------------------------------------------------");
						i++;
					}
					

					Statement nc = conn.createStatement();
					ResultSet result = nc.executeQuery ("select nome,sobrenome from pessoa where email = '"+txtEmail.getText()+"';");
					result.next();
					String nameCliente = result.getString("nome")+" "+result.getString("sobrenome");
					System.out.println(nameCliente);
					
					txtNomeCliente.setText(nameCliente);
					conn.close();
					listCarrinho.removeAll();
					
				} catch(Exception b)
				{
					System.out.println("Erro: "+b);
				}
				
			}
		});
		
		
		
		
		btnAddProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String servidor = "jdbc:mysql://localhost:3306/lojita?useTimezone=true&serverTimezone=GMT";
				String usuario = "root";
				String senha = "gabe2507";
				
				try 
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection(servidor,usuario,senha);
					//adicionando dados de pessoa ao banco de dados
					
					FrameNumPedido.setVisible(true);
					tela_produtos.setVisible(false);
					
					
					//versao anterior sem programar aqui
					rdbtnPedidoSim.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							txtNumPedidoGerado.setText(numPedidoAleatorio());
							txtNumPedidoGerado.setVisible(true);
							
						}});
					
					//versao anterior sem programar aqui
					rdbtnPedidoNao.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							txtNumPedidoGerado.setVisible(false);
							
						}});
					
					
					}
				
				
				catch (Exception a)
				{
					System.out.println("Erro: " + a);
				}
					
			
		}});
		
		
		btnOkNumPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String servidor = "jdbc:mysql://localhost:3306/lojita?useTimezone=true&serverTimezone=GMT";
				String usuario = "root";
				String senha = "gabe2507";
				
				
				
				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection(servidor,usuario,senha);
					
					Statement cpf = conn.createStatement();
					String cp = txtEmail.getText();
					ResultSet result = cpf.executeQuery("select CPF from pessoa where email = '"+cp+"'");
					result.next();
					String codPessoa = result.getString("CPF");
					System.out.println("CPF do cliente: "+result.getString("CPF"));
					
					ResultSet result2 = cpf.executeQuery("select * from Produto where idProduto = '"+txtAddProduto.getText()+"'");
					result2.next();
					String precoString = result2.getString("preco").replace(",", ".").replace("R$ ", "");
					float valor = Float.parseFloat(precoString)+Float.parseFloat(txtValorTotal.getText().replace(",", ".").replace("R$ ", ""));
					
					txtValorTotal.setText("R$ "+String.valueOf(valor));
					
					
				PreparedStatement ap = conn.prepareStatement("insert into pedido (numPedido,Cliente_Pessoa_CPF,Produto_idProduto) values (?,?,?)");
				ap.setString(1, txtNumPedido.getText());
				ap.setString(2,codPessoa);
				ap.setString(3, txtAddProduto.getText());
				
				int b = ap.executeUpdate();
				
				if (b>0)
				{
					System.out.println("Produto adicionado com sucesso");
					FrameNumPedido.setVisible(false);
					tela_produtos.setVisible(true);
				}
				
				else
				{
					System.out.println("Produto não foi adicionado");
				}
					}
			catch (Exception ex)
				{
				System.out.println("Erro: "+ex);
				}
				
				txtNumPedidoGerado.setText("");
				//txtNumPedido.setText("");
				rdbtnPedidoSim.setSelected(false);
				rdbtnPedidoNao.setSelected(false);
				txtAddProduto.setText("");
			}});
		
		//-----------------------
		
		//Action Listeners Cadastro
		
		btnCadastrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//verificar seleção para salvar dados do usuario
				if(radioUsuarioCadastro.isSelected())
				{
				String servidor = "jdbc:mysql://localhost:3306/lojita?useTimezone=true&serverTimezone=GMT";
				String usuario = "root";
				String senha = "gabe2507";
				try 
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection(servidor,usuario,senha);
					//adicionando dados de pessoa ao banco de dados
					PreparedStatement ip = conn.prepareStatement("insert into pessoa (CPF,senha,email,nome,sobrenome) values (?,?,?,?,?)");
					ip.setString(1,txtCpf.getText());
					ip.setString(2,txtSenhaCadastro.getText());
					ip.setString(3,txtEmailCadastro.getText());
					ip.setString(4,txtNome.getText());
					ip.setString(5,txtSobrenome.getText());
					
					int z = ip.executeUpdate();
					if (z>0)
					{
						System.out.println("Registro pessoa feito com sucesso!");
					}
					else 
					{
						System.out.println("Processo de registro pessoa falhou!");
					}
					
					//adicionando dados do cliente ao banco de dados
					if(radioCartaoSim.isSelected()==true) {
					PreparedStatement ic = conn.prepareStatement("insert into cliente (Pessoa_CPF,numCard,validade,nomeCard,codSeg) values (?,?,?,?,?) ");
					ic.setString(1, txtCpf.getText());
					ic.setString(2, txtNumero.getText());
					ic.setString(3, txtValidade.getText());
					ic.setString(4, txtNomeCartao.getText());
					ic.setString(5, txtCvv.getText());
					
					int x = ic.executeUpdate();
					if (x>0)
					{
						System.out.println("Registro cliente feito com sucesso!");
					}
					else 
					{
						System.out.println("Processo de registro cliente falhou!");
					}
					
					}
					else
					{
						PreparedStatement ic = conn.prepareStatement("insert into cliente (Pessoa_CPF,numCard,validade,nomeCard,codSeg) values (?,?,?,?,?) ");
						ic.setString(1, txtCpf.getText());
						ic.setString(2, null);
						ic.setString(3, null);
						ic.setString(4, null);
						ic.setString(5, null);
						
						int x = ic.executeUpdate();
						if (x>0)
						{
							System.out.println("Registro cliente feito com sucesso!");
						}
						else 
						{
							System.out.println("Processo de registro cliente falhou!");
						}
					}
					//adicionando dados do endereco
					PreparedStatement ie = conn.prepareStatement("insert into endereco (CEP,Rua,Bairro,numero,complemento,cidade,estado,Cliente_Pessoa_CPF) values (?,?,?,?,?,?,?,?)");
					ie.setString(1, txtCep.getText());
					ie.setString(2, txtEndereco.getText());
					ie.setString(3, txtBairro.getText());
					ie.setString(4, txtN.getText());
					ie.setString(5, txtComplemento.getText());
					ie.setString(6, txtCidade.getText());
					ie.setString(7,(String) estadoBox.getSelectedItem());
					ie.setString(8, txtCpf.getText());
					
					int y = ie.executeUpdate();
					if (y>0)
					{
						System.out.println("Registro cliente endereço feito com sucesso!");
					}
					else 
					{
						System.out.println("Processo de registro do cliente endereço falhou!");
					}
				} catch(Exception b)
				{
					System.out.println("Erro: "+b);
				}
				}
				
				//verificar radio button para salvar cadastro admin
				if (radioAdminCadastro.isSelected())
				{
					String servidor = "jdbc:mysql://localhost:3306/lojita?useTimezone=true&serverTimezone=GMT";
					String usuario = "root";
					String senha = "gabe2507";
					try 
					{ 
						//adicionando dados da pessoa funcionário ao BD
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn1 = DriverManager.getConnection(servidor,usuario,senha);
						PreparedStatement ipf = conn1.prepareStatement("insert into pessoa (CPF,senha,email,nome,sobrenome) values (?,?,?,?,?)");
						ipf.setString(1,txtCpf.getText());
						ipf.setString(2,txtSenhaCadastro.getText());
						ipf.setString(3,txtEmailCadastro.getText());
						ipf.setString(4,txtNome.getText());
						ipf.setString(5,txtSobrenome.getText());
						
						int b = ipf.executeUpdate();
						if (b>0)
						{
							System.out.println("Registro pessoa funcionário feito com sucesso!");
						}
						else 
						{
							System.out.println("Processo de registro pessoa funcionário falhou!");
						}
						//adionando dados para funcionário ao BD
						PreparedStatement iff = conn1.prepareStatement("insert into funcionario (codAdmin,Pessoa_CPF) values (?,?)");
						iff.setString(1,txtPermissoGerada.getText());
						iff.setString(2,txtCpf.getText());
						
						int c = iff.executeUpdate();
						if (c>0)
						{
							System.out.println("Registro funcionário funcionário feito com sucesso!");
						}
						else 
						{
							System.out.println("Processo de registro funcionário funcionário falhou!");
						}
					} catch(Exception c)
					{
						System.out.println("Erro: "+c);
					}
				}
				tela_Cadastro.setVisible(false);
			}
		});
		
		radioAdminCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblPermissaoGerada.setVisible(true);
				txtPermissoGerada.setVisible(true);
				txtPermissoGerada.setText(nomeAleatorio());
				txtBairro.setVisible(false);
				lblCartão.setVisible(false);
				txtCep.setVisible(false);
				txtCidade.setVisible(false);
				txtComplemento.setVisible(false);
				txtCvv.setVisible(false);
				txtEndereco.setVisible(false);
				txtNomeCartao.setVisible(false);
				txtNumero.setVisible(false);
				txtValidade.setVisible(false);
				txtN.setVisible(false);
				estadoBox.setVisible(false);
				radioCartaoNao.setVisible(false);
				radioCartaoSim.setVisible(false);
				
			}
		});
		
		radioUsuarioCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblPermissaoGerada.setVisible(false);
				txtPermissoGerada.setVisible(false);
				txtBairro.setVisible(true);
				lblCartão.setVisible(true);
				txtCep.setVisible(true);
				txtCidade.setVisible(true);
				txtComplemento.setVisible(true);
				txtEndereco.setVisible(true);
				estadoBox.setVisible(true);
				radioCartaoNao.setVisible(true);
				txtN.setVisible(true);
				radioCartaoSim.setVisible(true);
				if(radioCartaoSim.isSelected()){
					txtNomeCartao.setVisible(true);
					txtNumero.setVisible(true);
					txtValidade.setVisible(true);
					txtCvv.setVisible(true);
				}
			}
		});
		
		txtNome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtNome.getText().equals("Nome"))
					txtNome.setText("");
			}
		});
		
		txtNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtNome.getText().equals(""))
					txtNome.setText("Nome");
			}
		});
		
		txtSobrenome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtSobrenome.getText().equals("Sobrenome"))
					txtSobrenome.setText("");
			}
		});
		
		txtSobrenome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtSobrenome.getText().equals(""))
					txtSobrenome.setText("Sobrenome");
			}
		});
		
		txtEndereco.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtEndereco.getText().equals("Endereço"))
					txtEndereco.setText("");
			}
		});
		
		txtEndereco.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtEndereco.getText().equals(""))
					txtEndereco.setText("Endereço");
			}
		});
		
		txtN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtN.getText().equals("nº"))
					txtN.setText("");
			}
		});
		
		txtN.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtN.getText().equals(""))
					txtN.setText("nº");
			}
		});
		
		txtBairro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtBairro.getText().equals("Bairro"))
					txtBairro.setText("");
			}
		});
		
		txtBairro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtBairro.getText().equals(""))
					txtBairro.setText("Bairro");
			}
		});
		
		txtCidade.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtCidade.getText().equals("Cidade"))
					txtCidade.setText("");
			}
		});
		
		txtCidade.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtCidade.getText().equals(""))
					txtCidade.setText("Cidade");
			}
		});
		
		txtComplemento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtComplemento.getText().equals("Complemento"))
					txtComplemento.setText("");
			}
		});
		
		txtComplemento.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtComplemento.getText().equals(""))
					txtComplemento.setText("Complemento");
			}
		});
		
		txtCep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtCep.getText().equals("CEP"))
					txtCep.setText("");
			}
		});
		
		txtCep.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtCep.getText().equals(""))
					txtCep.setText("CEP");
			}
		});
		
		txtNumero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtNumero.getText().equals("Número"))
					txtNumero.setText("");
			}
		});
		
		txtNumero.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtNumero.getText().equals(""))
					txtNumero.setText("Número");
			}
		});
		
		txtValidade.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtValidade.getText().equals("Validade"))
					txtValidade.setText("");
			}
		});
		
		txtValidade.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtValidade.getText().equals(""))
					txtValidade.setText("Validade");
			}
		});
		
		txtCvv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtCvv.getText().equals("CVV"))
					txtCvv.setText("");
			}
		});
		
		txtCvv.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtCvv.getText().equals(""))
					txtCvv.setText("CVV");
			}
		});
		
		txtNomeCartao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtNomeCartao.getText().equals("Nome (como impresso)"))
					txtNomeCartao.setText("");
			}
		});
		
		txtNomeCartao.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtNomeCartao.getText().equals(""))
					txtNomeCartao.setText("Nome (como impresso)");
			}
		});
		
		txtEmailCadastro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtEmailCadastro.getText().equals("e-mail"))
					txtEmailCadastro.setText("");
			}
		});
		
		txtEmailCadastro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtEmailCadastro.getText().equals(""))
					txtEmailCadastro.setText("e-mail");
			}
		});
		
		txtCpf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtCpf.getText().equals("CPF"))
					txtCpf.setText("");
			}
		});
		
		txtCpf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtCpf.getText().equals(""))
					txtCpf.setText("CPF");
			}
		});
		
		txtSenhaCadastro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtSenhaCadastro.getText().equals("Senha"))
					txtSenhaCadastro.setText("");
			}
		});
		
		txtSenhaCadastro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtSenhaCadastro.getText().equals(""))
					txtSenhaCadastro.setText("Senha");
			}
		});
		
		txtConfirmarSenha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtConfirmarSenha.getText().equals("Confirmar senha"))
					txtConfirmarSenha.setText("");
			}
		});
		
		txtConfirmarSenha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtConfirmarSenha.getText().equals(""))
					txtConfirmarSenha.setText("Confirmar senha");
			}
		});
		
		
		radioCartaoNao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCvv.setVisible(false);
				txtNomeCartao.setVisible(false);
				txtValidade.setVisible(false);
				txtNumero.setVisible(false);
				
			}
		});	
		
		radioCartaoSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCvv.setVisible(true);
				txtNomeCartao.setVisible(true);
				txtValidade.setVisible(true);
				txtNumero.setVisible(true);
				
			}
		});	
		
		//-----------------------
		
		
		//Action Listeners Cadastro de Produtos
		
		btnCadastrarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String servidor = "jdbc:mysql://localhost:3306/lojita?useTimezone=true&serverTimezone=GMT";
				String usuario = "root";
				String senha = "gabe2507";
				try 
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection(servidor,usuario,senha);
					
					//adicionando dados do produto ao banco de dados
					
					PreparedStatement ic = conn.prepareStatement("insert into produto (idProduto,nome,descricao,preco,categoria,Funcionario_Pessoa_CPF) values (?,?,?,?,?,?) ");
					ic.setInt(1, rand.nextInt(24)*rand.nextInt(82)+rand.nextInt(51)*rand.nextInt(15)+rand.nextInt(25));
					ic.setString(2, txtNomeProduto.getText());
					ic.setString(3, txtDescricaoProduto.getText());
					ic.setString(4, "R$ "+txtPrecoProduto.getText());
					ic.setString(5, categoriaBox.getSelectedItem().toString());
					
					String valida_CadastroProduto="";
					
					try{  
						String q = "select * from funcionario join pessoa where codAdmin='"+txtPermissaoCadastrarProduto.getText()+"'"; 
						ResultSet rs = ic.executeQuery(q);
			            if(rs != null && rs.next()){
			            	valida_CadastroProduto = rs.getString("Pessoa_CPF");
			            }
			            
			        }  
			        catch(SQLException ex){  
			            ex.printStackTrace();  
			        }  
					
					ic.setString(6, valida_CadastroProduto);
					
					int x = ic.executeUpdate();
					if (x>0)
					{
						System.out.println("Registro produto feito com sucesso!");
					}
					else 
					{
						System.out.println("Processo de registro produto falhou!");
					}
					
					
				
				} catch(Exception b)
				{
					System.out.println("Erro: "+b);
				}
				
				tela_CadastroProduto.setVisible(false);
			}
		});
		
		 txtNomeProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtNomeProduto.getText().equals("Nome do produto"))
					txtNomeProduto.setText("");
			}
		});
		
		 txtNomeProduto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtNomeProduto.getText().equals(""))
					txtNomeProduto.setText("Nome do produto");
			}
		});
		 
		 txtDescricaoProduto.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(txtDescricaoProduto.getText().equals("Descrição..."))
						txtDescricaoProduto.setText("");
				}
			});
			
		 txtDescricaoProduto.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					if(txtDescricaoProduto.getText().equals(""))
						txtDescricaoProduto.setText("Descrição...");
				}
			});
		 
		 
		 btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					String servidor = "jdbc:mysql://localhost:3306/lojita?useTimezone=true&serverTimezone=GMT";
					String usuario = "root";
					String senha = "gabe2507";
					model4.removeAllElements();
					try 
					{
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn = DriverManager.getConnection(servidor,usuario,senha);
						Statement stmt = conn.createStatement();
						String sql = "delete from pedido where numPedido = '"+txtNumPedido.getText()+"' and Produto_idProduto = '"+txtRemover.getText()+"'; ";
						int deleteCount = stmt.executeUpdate(sql);
								
						PreparedStatement car = conn.prepareStatement(sql);
						deleteCount =  car.executeUpdate();
						System.out.println("Produto removido com sucesso!");

						PreparedStatement ic = conn.prepareStatement("select produto.* from pedido, produto where pedido.Produto_idProduto = produto.idProduto and numPedido = '"+txtNumPedido.getText()+"';");
						
						
						ResultSet rs=ic.executeQuery();
						Produto p = new Produto();
						int i=0;
						model.clear();
						
						while(rs.next()) {
							p.setIdProduto(rs.getInt("idProduto"));
							p.setNome(rs.getString("nome"));
							p.setDescricao(rs.getString("descricao"));
							p.setPreco(rs.getString("preco"));
							p.setCategoria(rs.getString("categoria"));
							
							model4.add(i, p.getNome());
							i++;
							
							model4.add(i, "Preço R$: "+p.getPreco());
							i++;
							
							model4.add(i, p.getDescricao());
							i++;
							
							model4.add(i, "Código do Produto: "+p.getIdProduto());
							i++;
							
							model4.add(i, "------------------------------------------------------------------------------------------------------");
							i++;
						}
						//Atualizar a list do carrinho.
						
					} catch(Exception b)
					{
						System.out.println("Erro: "+b);
					}
					
					listCarrinho.removeAll();
					
				}
			});
				
		 
		 
		 //Radio Button Sim para remover algum produto do carrinho
		 rdbtnSimRemover.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
		 
					txtRemover.enable(true);
					txtRemover.setEditable(true);
					txtRemover.setVisible(true);
					btnConfirmar.enable(true);
					btnConfirmar.setEnabled(true);
					btnConfirmar.setVisible(true);
					
				}});			
		 
		 
		
		 //Radio Button Não para não remover nenhum produto do carrinho
		 rdbtnNaoRemover.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
		 
					txtRemover.enable(false);
					txtRemover.setEditable(false);
					txtRemover.setVisible(false);
					btnConfirmar.enable(false);
					btnConfirmar.setEnabled(false);
					btnConfirmar.setVisible(false);
					
					
				}});		
				
		 btnFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					FinalizarPedido.setVisible(true);
					carrinho.setVisible(false);
					model5.removeAllElements();
					String servidor = "jdbc:mysql://localhost:3306/lojita?useTimezone=true&serverTimezone=GMT";
					String usuario = "root";
					String senha = "gabe2507";
					Produto p = new Produto();
					ResultSet rs = null;
					try 
					{
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn = DriverManager.getConnection(servidor,usuario,senha);
						PreparedStatement ic = conn.prepareStatement("select produto.* from pedido, produto where pedido.Produto_idProduto = produto.idProduto and numPedido = '"+txtNumPedido.getText()+"';");
								
									
						rs=ic.executeQuery();
						int i=0;
						model.clear();
						
						while(rs.next()) {
							p.setIdProduto(rs.getInt("idProduto"));
							p.setNome(rs.getString("nome"));
							p.setDescricao(rs.getString("descricao"));
							p.setPreco(rs.getString("preco"));
							p.setCategoria(rs.getString("categoria"));
							
							model5.add(i, p.getNome());
							i++;
							
							model5.add(i, "Preço R$: "+p.getPreco());
							i++;
							
							model5.add(i, p.getDescricao());
							i++;
							
							model5.add(i, "Código do Produto: "+p.getIdProduto());
							i++;
							
							model5.add(i, "------------------------------------------------------------------------------------------------------");
							i++;
						}
						
						Statement nc = conn.createStatement();
						ResultSet resultado = nc.executeQuery ("select nome,sobrenome from pessoa where email = '"+txtEmail.getText()+"';");
						resultado.next();
						String nameCliente = resultado.getString("nome")+" "+resultado.getString("sobrenome");
						System.out.println(nameCliente);
						txtNomeClienteFinalizar.setText(nameCliente);
						listFinalizar.removeAll();
						
					} catch(Exception b)
					{
						System.out.println("Erro: "+b);
					}
					
					float valor = Float.parseFloat(txtValorTotal.getText().replace("R$ ", "").replace(",", "."));
					
					if(valor<=1000)
						valor=valor+50;
					
					txtValorTotal.setText(valor+"");
					
					btnVerificar.setVisible(false);
					txtValidadeCartaoPedido.setVisible(false);
					lblValidadeCartaoPedido.setVisible(false);
					txtCvvCartaoPedido.setVisible(false);
					lblCvvCartaoPedido.setVisible(false);
					txtNomeCartaoPedido.setVisible(false);
					lblNomeCartaoPedido.setVisible(false);
					lblNumCartaoPedido.setVisible(false);
					txtNumCartaoPedido.setVisible(false);
				}
			});
		 
		 //programando radio button da tela de finalizar para opção de cartão
		 rdbtnCartao.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnConcluir.setEnabled(false);
					comboBoxParcelas.setEnabled(true);
					comboBoxParcelas.setVisible(true);
					btnVerificar.setVisible(true);
					txtValidadeCartaoPedido.setVisible(true);
					lblValidadeCartaoPedido.setVisible(true);
					txtCvvCartaoPedido.setVisible(true);
					lblCvvCartaoPedido.setVisible(true);
					txtNomeCartaoPedido.setVisible(true);
					lblNomeCartaoPedido.setVisible(true);
					lblNumCartaoPedido.setVisible(true);
					txtNumCartaoPedido.setVisible(true);
				}});
		 
		 //programando radio button para tela de finalizar para opção de boleto
		 rdbtnBoleto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtParcelas.setText("---");
					btnConcluir.setEnabled(true);
					comboBoxParcelas.setEnabled(false);
					comboBoxParcelas.setVisible(false);
					btnVerificar.setVisible(false);
					txtValidadeCartaoPedido.setVisible(false);
					lblValidadeCartaoPedido.setVisible(false);
					txtCvvCartaoPedido.setVisible(false);
					lblCvvCartaoPedido.setVisible(false);
					txtNomeCartaoPedido.setVisible(false);
					lblNomeCartaoPedido.setVisible(false);
					lblNumCartaoPedido.setVisible(false);
					txtNumCartaoPedido.setVisible(false);
				}});
		 
		 //botão voltar presente na tela finalizar para voltar a tela do carrinho
		 btnVoltar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					FinalizarPedido.setVisible(false);
					carrinho.setVisible(true);
					model4.removeAllElements();
					String servidor = "jdbc:mysql://localhost:3306/lojita?useTimezone=true&serverTimezone=GMT";
					String usuario = "root";
					String senha = "gabe2507";
					Produto p = new Produto();
					ResultSet rs = null;
					ResultSet n = null;
					model.clear();
					try 
					{
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn = DriverManager.getConnection(servidor,usuario,senha);
						PreparedStatement ic = conn.prepareStatement("select produto.* from pedido, produto where pedido.Produto_idProduto = produto.idProduto and numPedido = '"+txtNumPedido.getText()+"';");
								
									
						rs=ic.executeQuery();
						int i=0;
						model.clear();
						
						while(rs.next()) {
							p.setIdProduto(rs.getInt("idProduto"));
							p.setNome(rs.getString("nome"));
							p.setDescricao(rs.getString("descricao"));
							p.setPreco(rs.getString("preco"));
							p.setCategoria(rs.getString("categoria"));
							
							model4.add(i, p.getNome());
							i++;
							
							model4.add(i, "Preço R$: "+p.getPreco());
							i++;
							
							model4.add(i, p.getDescricao());
							i++;
							
							model4.add(i, "Código do Produto: "+p.getIdProduto());
							i++;
							
							model4.add(i, "------------------------------------------------------------------------------------------------------");
							i++;
						}
						

						Statement nc = conn.createStatement();
						ResultSet result = nc.executeQuery ("select nome,sobrenome from pessoa where email = '"+txtEmail.getText()+"';");
						result.next();
						String nameCliente = result.getString("nome")+" "+result.getString("sobrenome");
						System.out.println(nameCliente);
						
						txtNomeCliente.setText(nameCliente);
						conn.close();
						listCarrinho.removeAll();
						
					} catch(Exception b)
					{
						System.out.println("Erro: "+b);
					}
					
					float valor = Float.parseFloat(txtValorTotal.getText().replace("R$ ", "").replace(",", "."));
					
					if(valor<=1000)
						valor=valor-50;
					
					txtValorTotal.setText(valor+"");
				}
			});
		//-----------------------
		
		//----------------------------------------------
		 comboBoxParcelas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					float valor = Float.parseFloat(txtValorTotal.getText().replace("R$ ", ""));
					int parcelamento = Integer.parseInt(comboBoxParcelas.getSelectedItem().toString());
					float valorParcela=valor/parcelamento;
					txtParcelas.setText(parcelamento+"X de R$ "+valorParcela);
				}
			});
		 
		 btnVerificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String servidor = "jdbc:mysql://localhost:3306/lojita?useTimezone=true&serverTimezone=GMT";
					String usuario = "root";
					String senha = "gabe2507";
					ResultSet rs = null;
					try 
					{
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn = DriverManager.getConnection(servidor,usuario,senha);
						PreparedStatement ic = conn.prepareStatement("select * from Cliente join Pessoa where email = '"+txtEmail.getText()+"' and Pessoa_CPF = CPF");
						rs = ic.executeQuery();
						rs.next();
						
						String num = rs.getString("numCard");
						String nom = rs.getString("nomeCard");
						String cvv = rs.getString("codSeg");
						String val = rs.getString("validade");
					
						if(num.equals(txtNumCartaoPedido.getText())&&nom.equals(txtNomeCartaoPedido.getText())&&cvv.equals(txtCvvCartaoPedido.getText())&&val.equals(txtValidadeCartaoPedido.getText()))
							btnConcluir.setEnabled(true);
						else
							btnConcluir.setEnabled(false);	
					} catch(Exception b)
					{
						System.out.println("Erro: "+b);
					}
				}
			});
		 
		 btnConcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String servidor = "jdbc:mysql://localhost:3306/lojita?useTimezone=true&serverTimezone=GMT";
					String usuario = "root";
					String senha = "gabe2507";
					ResultSet rs = null;
					String forma;
					try 
					{
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn = DriverManager.getConnection(servidor,usuario,senha);
						PreparedStatement ic = conn.prepareStatement("select * from Pedido join Cliente join Pessoa where numPedido='"+txtNumPedido.getText()+"'and Cliente_Pessoa_CPF=Pessoa_CPF limit 1;");
						rs = ic.executeQuery();
						rs.next();
						
						String nome = "Nome do cliente: "+rs.getString("nome")+" "+rs.getString("sobrenome");
						String num = "Número do pedido: "+rs.getString("numPedido");
						if(rdbtnBoleto.isSelected()) 
							forma = "Forma de pagamento: Boleto bancário";
						else
							forma = "Forma de pagamento: Cartão de crédito";
						
						String valor = "Valor total: "+txtValorTotal.getText();
						
						
						lblNomeFinalPedido.setText(nome);
						lblNFinalPedido.setText(num);
						lblFormaFinalPedido.setText(forma);
						lblValorFinalPedido.setText(valor);
						
						tela_FinalPedido.setVisible(true);
						FinalizarPedido.setVisible(false);
					
						
					} catch(Exception b)
					{
						System.out.println("Erro: "+b);
					}
					
				}
			});
		 
		 btnFinalPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tela_FinalPedido.setVisible(false);
				}
			});
		 
		 btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String servidor = "jdbc:mysql://localhost:3306/lojita?useTimezone=true&serverTimezone=GMT";
					String usuario = "root";
					String senha = "gabe2507";
					ResultSet rs = null;
	
					try 
					{
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn = DriverManager.getConnection(servidor,usuario,senha);
						PreparedStatement ic = conn.prepareStatement("select * from Produto where nome='"+txtBuscar.getText()+"';");
						rs = ic.executeQuery();
						rs.next();
						
						String nome = rs.getString("nome");
						String preco = "R$ "+rs.getString("preco");
						String cod = "Código: "+rs.getString("idProduto");

						
						
						lblNomeProdutoBuscado.setText(nome);
						lblPrecoProdutoBuscado.setText(preco);
						lblCodigoProdutoBuscado.setText(cod);
					
						produtoBuscado.setVisible(true);
						
					} catch(Exception b)
					{
						System.out.println("Erro: "+b);
					}
				
				}
			});
		 
		 btnOkProdutoBuscado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					lblNomeProdutoBuscado.setText("Nome");
					lblPrecoProdutoBuscado.setText("Preço: ");
					lblCodigoProdutoBuscado.setText("Código: ");
					
					produtoBuscado.setVisible(false);
				}
			});
	}
	
	public static String nomeAleatorio () {
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i<7; i++) {
	        int ch = rand.nextInt (letras.length);
	        sb.append (letras [ch]);
	    }    
	    return sb.toString();    
	}
	
	public static String numPedidoAleatorio () {
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i<10; i++) {
	        int ch = rand.nextInt (nPedidos.length);
	        sb.append (nPedidos [ch]);
	    }    
	    return sb.toString();    
	}
}