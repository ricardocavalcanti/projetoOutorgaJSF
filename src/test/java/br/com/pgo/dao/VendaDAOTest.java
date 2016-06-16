package br.com.pgo.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import br.com.pgo.domain.Outorgante;
import br.com.pgo.domain.Usuario;
import br.com.pgo.domain.Venda;

public class VendaDAOTest {

	@Test
	public void salvar() {

		Venda venda = new Venda(); // Pegar a data do sistema

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(17L);

		OutorganteDAO outorganteDAO = new OutorganteDAO();
		Outorgante processo = outorganteDAO.buscar(4L);

		venda.setData(new Date());
		venda.setProcesso(processo);
		venda.setUsuario(usuario);

		System.out.println("Venda salva com sucesso!");
	}

	public void buscar() {

		VendaDAO vendaDAO = new VendaDAO();
		Long codigo = 1L;
		Venda venda = vendaDAO.buscar(codigo);

		if (venda == null) {
			System.out.println("Venda não encontrada!");
		} else {
			System.out.println(venda.getData());
			System.out.println(venda.getProcesso());
			System.out.println(venda.getUsuario());
		}

	}

	public void excluir() {

		VendaDAO vendaDAO = new VendaDAO();
		Long codigo = 1L;
		Venda venda = vendaDAO.buscar(codigo);

		if (venda == null) {
			System.out.println("Venda não encontrada!");
		} else {

			System.out.println("Venda excluida!");

		}

	}

	public void listar() {

		VendaDAO vendaDAO = new VendaDAO();

		List<Venda> resultado = vendaDAO.listar();

		for (Venda venda : resultado) {

			System.out.println(venda.getData());
			System.out.println(venda.getProcesso());
			System.out.println(venda.getUsuario());
		}

	}

}