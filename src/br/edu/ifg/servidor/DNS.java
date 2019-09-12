package br.edu.ifg.servidor;

import br.edu.ifg.interf.Constant;
import br.edu.ifg.interf.InterfaceDNS;
import br.edu.ifg.model.TabelaUsuarios;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class DNS implements InterfaceDNS {

	TabelaUsuarios tabelaUsuarios = new TabelaUsuarios();

	public DNS() {

	}

	// IMPLEMENTAR UM METODO PARA RETORNAR UM NICK DE IP
	public static void main(String args[]) throws RemoteException {

		try {
			DNS dns = new DNS();
			InterfaceDNS interfDNS = (InterfaceDNS) UnicastRemoteObject.exportObject(dns, 0);
			Registry registry = LocateRegistry.createRegistry(Constant.RMI_PORT);
			registry.bind(Constant.RMI_ID, interfDNS);

			System.out.println("Servidor rodando!");

		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

	public boolean autentica(String nick, String ip) throws RemoteException {
		tabelaUsuarios.adicionarUsuario(nick, ip);
		System.out.println("Tabela de usuarios online: " + tabelaUsuarios.toString());
		return true;
	}

	public String obterListaIPUsuarios(String nick) {
		HashMap<String, String> hash = new HashMap<>();
		hash = tabelaUsuarios.getListaUsuariosOnline();
		if (hash.containsKey(nick)) {
			return hash.get(nick);
		} else {
			System.out.println("Nao existe a chave dentro da lista");
		}
		return null;
	}

	public ArrayList<String> obterListaUsuariosOnline() throws RemoteException {
		HashMap<String, String> listaUsuariosOnline = new HashMap<String, String>();

		listaUsuariosOnline = tabelaUsuarios.getListaUsuariosOnline();
		ArrayList<String> lista = new ArrayList<>();

		for (String key : listaUsuariosOnline.keySet()) {
			// System.out.println(key);
			lista.add(key);
		}

		return lista;
	}
}