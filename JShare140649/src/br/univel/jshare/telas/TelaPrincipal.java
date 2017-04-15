package br.univel.jshare.telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.univel.jshare.cliente.ConexaoCliente;
import br.univel.jshare.comum.Cliente;
import br.univel.jshare.comum.IServer;
import br.univel.jshare.servidor.Servidor;

import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import br.univel.jshare.util.*;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField txfNome;
	private JTextField txfIP;
	private JTextField txfPorta;
	private JTextField txfPasta;
	private boolean cliConectado = false;
	private boolean serverConectado = false;
	private Cliente cliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setTitle("Pain Sharing");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane_1, BorderLayout.NORTH);
		
		JPanel panelConexao = new JPanel();
		tabbedPane_1.addTab("Conex\u00E3o", null, panelConexao, null);
		
		JButton btnIniciarServidor = new JButton("Iniciar Servidor");
		btnIniciarServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciaServidor();
			}
		});
		panelConexao.add(btnIniciarServidor);
		
		JPanel panelPesquisa = new JPanel();
		tabbedPane_1.addTab("Pesquisa", null, panelPesquisa, null);
		
		JPanel panelResumo = new JPanel();
		tabbedPane_1.addTab("Resumo", null, panelResumo, null);
		
		JPanel panelCliente = new JPanel();
		contentPane.add(panelCliente, BorderLayout.CENTER);
		GridBagLayout gbl_panelCliente = new GridBagLayout();
		gbl_panelCliente.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelCliente.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelCliente.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelCliente.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelCliente.setLayout(gbl_panelCliente);
		
		JLabel lblNewLabel = new JLabel("Nome");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panelCliente.add(lblNewLabel, gbc_lblNewLabel);
		
		txfNome = new JTextField();
		txfNome.setText("Seu nome de usu\u00E1rio");
		GridBagConstraints gbc_txfNome = new GridBagConstraints();
		gbc_txfNome.insets = new Insets(0, 0, 5, 5);
		gbc_txfNome.anchor = GridBagConstraints.NORTH;
		gbc_txfNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txfNome.gridx = 1;
		gbc_txfNome.gridy = 0;
		panelCliente.add(txfNome, gbc_txfNome);
		txfNome.setColumns(10);
		
		JLabel lblEndereoIp = new JLabel("Endere\u00E7o IP");
		GridBagConstraints gbc_lblEndereoIp = new GridBagConstraints();
		gbc_lblEndereoIp.anchor = GridBagConstraints.EAST;
		gbc_lblEndereoIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndereoIp.gridx = 0;
		gbc_lblEndereoIp.gridy = 1;
		panelCliente.add(lblEndereoIp, gbc_lblEndereoIp);
		
		txfIP = new JTextField();
		txfIP.setText("IP do Servidor");
		GridBagConstraints gbc_txfIP = new GridBagConstraints();
		gbc_txfIP.insets = new Insets(0, 0, 5, 5);
		gbc_txfIP.anchor = GridBagConstraints.NORTH;
		gbc_txfIP.fill = GridBagConstraints.HORIZONTAL;
		gbc_txfIP.gridx = 1;
		gbc_txfIP.gridy = 1;
		panelCliente.add(txfIP, gbc_txfIP);
		txfIP.setColumns(10);
		
		JLabel lblPorta = new JLabel("Porta");
		GridBagConstraints gbc_lblPorta = new GridBagConstraints();
		gbc_lblPorta.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorta.anchor = GridBagConstraints.EAST;
		gbc_lblPorta.gridx = 5;
		gbc_lblPorta.gridy = 1;
		panelCliente.add(lblPorta, gbc_lblPorta);
		
		txfPorta = new JTextField();
		txfPorta.setText("Porta p/ conex\u00E3o");
		GridBagConstraints gbc_txfPorta = new GridBagConstraints();
		gbc_txfPorta.insets = new Insets(0, 0, 5, 0);
		gbc_txfPorta.anchor = GridBagConstraints.NORTH;
		gbc_txfPorta.fill = GridBagConstraints.HORIZONTAL;
		gbc_txfPorta.gridx = 6;
		gbc_txfPorta.gridy = 1;
		panelCliente.add(txfPorta, gbc_txfPorta);
		txfPorta.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Pasta");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		panelCliente.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		txfPasta = new JTextField();
		txfPasta.setText("(Path) Pasta aonde est\u00E3o arquivos a compartilhar");
		GridBagConstraints gbc_txfPasta = new GridBagConstraints();
		gbc_txfPasta.anchor = GridBagConstraints.NORTH;
		gbc_txfPasta.insets = new Insets(0, 0, 0, 5);
		gbc_txfPasta.fill = GridBagConstraints.HORIZONTAL;
		gbc_txfPasta.gridx = 1;
		gbc_txfPasta.gridy = 2;
		panelCliente.add(txfPasta, gbc_txfPasta);
		txfPasta.setColumns(10);
		
		JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conectarCliente();
			}
		});
		GridBagConstraints gbc_btnConectar = new GridBagConstraints();
		gbc_btnConectar.insets = new Insets(0, 0, 0, 5);
		gbc_btnConectar.gridx = 4;
		gbc_btnConectar.gridy = 2;
		panelCliente.add(btnConectar, gbc_btnConectar);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.SOUTH);
		
		JTextArea textArea = new JTextArea();
		textArea.setForeground(new Color(50, 205, 50));
		textArea.setBackground(Color.BLACK);
		scrollPane.setViewportView(textArea);
		
	}
/*  num tá dando certo!
	JTextArea textArea = new JTextArea();
	textArea.setForeground(new Color(50, 205, 50));
	textArea.setBackground(Color.BLACK);
 	scrollPane.setViewportView(textArea);
	splitPane.setDividerLocation(200);
	*/
	
	
	protected void conectarCliente() {
		LerIp lerIP = new LerIp();
		String IPString;
/*		IPString = ????(): lerip do metodo
 * 
 *      ver o que está errado para conseguir usar metodo do outro pacote
 *      acertar e tirar as linhas abaixo
 */
		
		InetAddress IP;
 
		try {
			IP = InetAddress.getLocalHost();
		    IPString = IP.getHostAddress();
		} catch (UnknownHostException e) {
			IPString = "99999999";
			e.printStackTrace();
		}
//   ** eliminar até aqui qdo conseguir usar o util	
		
		String nomeCli = txfNome.getText().trim(); 
		String endIP = txfIP.getText().trim();
		int porta = Integer.parseInt(txfPorta.getText().trim());
		String pastaCli = txfPasta.getText().trim();
		Registry registry;
	
		try {
			registry = LocateRegistry.getRegistry(endIP, porta);
			try {
				IServer servidor = (IServer) registry.lookup(IServer.NOME_SERVICO);
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cliente = new Cliente();
		cliente.setId(Long.valueOf((IPString.replace(".", ""))));
		cliente.setIp(IPString);
		cliente.setNome(nomeCli);
		cliente.setPorta(porta);
		
	}

	protected void iniciaServidor() {
		/*
		 *  acho que tenho que mudar a implementação do servidor pq
		 *  senão qdo dar o newservidor se for cliente vai dar B.O
		 *  testar local e depois arrumar
		 */
		
		
		if (serverConectado) {
			try {
			Servidor meuServidor = new Servidor();
			serverConectado = true;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
	

	}
}
