package br.univel.jshare.servidor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import br.univel.jshare.comum.Arquivo;
import br.univel.jshare.comum.Cliente;
import br.univel.jshare.comum.IServer;
import br.univel.jshare.comum.TipoFiltro;

public class Servidor extends Thread implements Runnable, IServer {
	
	
	public static final String NOME = "JShare";
	private static final int PORTA_TCPIP = 1818;
	
	private List<Cliente> listClientes;	
	private Map<Cliente, List<Arquivo>> mapa = new HashMap<Cliente, List<Arquivo>>();
	
	public Servidor() throws RemoteException {
		super();

		new Thread(this).start();
	}
	
	public static void main(String[] args) {
		try {
			new Servidor();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {	
		System.out.println("Iniciando Servidor...");
		try {
			Registry registry = LocateRegistry.createRegistry(PORTA_TCPIP);
			registry.rebind(IServer.NOME_SERVICO, this);
			System.out.println("Servidor Iniciado !");

		} catch (RemoteException e) {
			System.err.println("\n\n-------------------------------------------------------\n"
					+ "ERRO: VERIFIQUE SE O SERVIDOR JÁ NÃO HAVIA SIDO INICIADO"
					+ " OU SE A PORTA NÃO ESTÁ OCUPADA POR OUTRO PROGRAMA.\n"
					+ "-------------------------------------------------------------------\n\n");
			e.printStackTrace();
		}
	}

	@Override
	public void registrarCliente(Cliente c) throws RemoteException {
		listClientes.add(c);
		System.out.println("Cliente " + c.getNome() + "(IP:" + c.getIp() + ") conectou-se");
		// trocar por msg na tela qdo conseguir acertar a tela preta
		
	}

	@Override
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista) throws RemoteException {
		mapa.put(c, lista);
		
	}

	@Override
	public Map<Cliente, List<Arquivo>> procurarArquivo(String query, TipoFiltro tipoFiltro, String filtro)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] baixarArquivo(Cliente cli, Arquivo arq) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void desconectar(Cliente c) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
