package br.univel.jshare.cliente;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


import br.univel.jshare.comum.IServer;

public class ConexaoCliente {
	public static void main(String[] args) {
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
}
