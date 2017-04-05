package br.univel.jshare.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;

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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Conex\u00E3o", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Pesquisa", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Download", null, panel_2, null);
		
		JRadioButton rdbtnServidor = new JRadioButton("Servidor");
		panel_2.add(rdbtnServidor);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Cliente");
		panel_2.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Cliente/Servidor");
		panel_2.add(rdbtnNewRadioButton_1);
	}

}
