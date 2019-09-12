package br.edu.ifg.cliente;

import br.edu.ifg.interf.*;

import java.io.ObjectInputStream.GetField;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class AplicacaoChat {

	private AplicacaoChat() {

	}

	public static void main(String[] args) {
		try {

			Registry registry = LocateRegistry.getRegistry("localhost", Constant.RMI_PORT);
			final InterfaceDNS remote = (InterfaceDNS) registry.lookup(Constant.RMI_ID);
			InetAddress ip = InetAddress.getLocalHost();
			String hostname = ip.getHostAddress();

			String nick = JOptionPane.showInputDialog("Digite o nick");
			System.out.println(nick);

			if (remote.autentica(nick, hostname)) {
				JOptionPane.showMessageDialog(null, "Usuario adicionado com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "Erro ao adicionar!");
			}
			ArrayList<String> lista = new ArrayList<>();

			lista = remote.obterListaUsuariosOnline();
			for (String nomeOnline : lista) {
				System.out.println("Nome dos usuarios On line: " + nomeOnline);
			}

			System.out.println("O ip de: Jhonatan e : " + remote.obterListaIPUsuarios("jhonatan"));

		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}

	}

}