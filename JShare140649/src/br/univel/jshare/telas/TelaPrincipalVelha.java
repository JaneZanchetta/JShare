package br.univel.jshare.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.univel.jshare.cliente.ConexaoCliente;
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
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.awt.event.ActionEvent;

public class TelaPrincipalVelha extends JFrame {

	private JPanel contentPane;
	private JTextField txfNome;
	private JTextField txfIP;
	private JTextField txfPorta;
	private ButtonGroup bg = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipalVelha frame = new TelaPrincipalVelha();
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
	public TelaPrincipalVelha() {
		setTitle("Pain Sharing");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.NORTH);
		
		JPanel panel_Escolha = new JPanel();
		tabbedPane.addTab("Conex\u00E3o", null, panel_Escolha, null);
		
		JRadioButton rdbtnServidor = new JRadioButton("Servidor");
		rdbtnServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conectarServidor();
			}
		});
		panel_Escolha.add(rdbtnServidor);
		
		JRadioButton rdbtnCliente = new JRadioButton("Cliente");
		rdbtnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conectarCliente();
			}
		});
		panel_Escolha.add(rdbtnCliente);
		
		JRadioButton rdbtnClientServer = new JRadioButton("Servidor e Cliente");
		panel_Escolha.add(rdbtnClientServer);
		bg.add(rdbtnClientServer);
		bg.add(rdbtnCliente);
		bg.add(rdbtnServidor);
	
		
		JPanel panel_Pesquisa = new JPanel();
		tabbedPane.addTab("Pesquisa", null, panel_Pesquisa, null);
		
		JPanel panel_Download = new JPanel();
		tabbedPane.addTab("Download", null, panel_Download, null);
		
		JPanel panel_Conexao = new JPanel();
		contentPane.add(panel_Conexao, BorderLayout.CENTER);
		GridBagLayout gbl_panel_Conexao = new GridBagLayout();
		gbl_panel_Conexao.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_Conexao.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_Conexao.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_Conexao.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_Conexao.setLayout(gbl_panel_Conexao);
		
		JLabel lblNome = new JLabel("Nome");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.gridx = 1;
		gbc_lblNome.gridy = 1;
		panel_Conexao.add(lblNome, gbc_lblNome);
		
		txfNome = new JTextField();
		GridBagConstraints gbc_txfNome = new GridBagConstraints();
		gbc_txfNome.gridwidth = 3;
		gbc_txfNome.anchor = GridBagConstraints.NORTH;
		gbc_txfNome.insets = new Insets(0, 0, 5, 5);
		gbc_txfNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txfNome.gridx = 2;
		gbc_txfNome.gridy = 1;
		panel_Conexao.add(txfNome, gbc_txfNome);
		txfNome.setColumns(10);
		
		JLabel lblEndereoIp = new JLabel("Endere\u00E7o IP");
		GridBagConstraints gbc_lblEndereoIp = new GridBagConstraints();
		gbc_lblEndereoIp.anchor = GridBagConstraints.EAST;
		gbc_lblEndereoIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndereoIp.gridx = 1;
		gbc_lblEndereoIp.gridy = 2;
		panel_Conexao.add(lblEndereoIp, gbc_lblEndereoIp);
		
		txfIP = new JTextField();
		GridBagConstraints gbc_txfIP = new GridBagConstraints();
		gbc_txfIP.gridwidth = 3;
		gbc_txfIP.insets = new Insets(0, 0, 5, 5);
		gbc_txfIP.fill = GridBagConstraints.HORIZONTAL;
		gbc_txfIP.gridx = 2;
		gbc_txfIP.gridy = 2;
		panel_Conexao.add(txfIP, gbc_txfIP);
		txfIP.setColumns(10);
		
		JLabel lblPorta = new JLabel("Porta");
		GridBagConstraints gbc_lblPorta = new GridBagConstraints();
		gbc_lblPorta.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorta.anchor = GridBagConstraints.EAST;
		gbc_lblPorta.gridx = 6;
		gbc_lblPorta.gridy = 2;
		panel_Conexao.add(lblPorta, gbc_lblPorta);
		
		txfPorta = new JTextField();
		GridBagConstraints gbc_txfPorta = new GridBagConstraints();
		gbc_txfPorta.insets = new Insets(0, 0, 5, 0);
		gbc_txfPorta.fill = GridBagConstraints.HORIZONTAL;
		gbc_txfPorta.gridx = 7;
		gbc_txfPorta.gridy = 2;
		panel_Conexao.add(txfPorta, gbc_txfPorta);
		txfPorta.setColumns(10);
		
		JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionConectar();
			}
		});
		GridBagConstraints gbc_btnConectar = new GridBagConstraints();
		gbc_btnConectar.insets = new Insets(0, 0, 0, 5);
		gbc_btnConectar.gridx = 5;
		gbc_btnConectar.gridy = 3;
		panel_Conexao.add(btnConectar, gbc_btnConectar);
		
		JButton btnDesconectar = new JButton("Desconectar");
		GridBagConstraints gbc_btnDesconectar = new GridBagConstraints();
		gbc_btnDesconectar.gridx = 7;
		gbc_btnDesconectar.gridy = 3;
		panel_Conexao.add(btnDesconectar, gbc_btnDesconectar);
	}

	protected void actionConectar() {
		
	}

	protected void conectarCliente() {
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry("127.0.0.1",1818);
			IServer servico = (IServer) registry.lookup(IServer.NOME_SERVICO);
			System.out.println("Vai pro Servidor");
			System.out.println("Voltou do Servidor");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	protected void conectarServidor() {
		try {
			System.out.println(bg.getSelection().getActionCommand());
			Servidor servidor = new Servidor();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
