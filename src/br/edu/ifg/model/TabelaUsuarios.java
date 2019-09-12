/**
 * @version 1.0 11/9/2019
 * @author JhonatanGSantos - IFG Câmpus Inhumas 
 * Todos os Direitos Reservados
 */

package br.edu.ifg.model;

import java.util.HashMap;

public class TabelaUsuarios {

	HashMap<String, String> listaUsuariosOnline = new HashMap<String, String>();

	public void adicionarUsuario(String nick, String ip) {
		this.listaUsuariosOnline.put(nick, ip);
	}

	public HashMap<String, String> getListaUsuariosOnline() {
		return listaUsuariosOnline;
	}

	public String ipUsuario(String nick) {
		return listaUsuariosOnline.get(nick);
	}

	public void setListaUsuariosOnline(HashMap<String, String> listaUsuariosOnline) {
		this.listaUsuariosOnline = listaUsuariosOnline;
	}

	public String toString() {
		return "TabelaUsuarios [listaUsuariosOnline=" + listaUsuariosOnline + "]";
	}

}
