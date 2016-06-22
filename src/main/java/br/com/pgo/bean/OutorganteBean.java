package br.com.pgo.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.pgo.dao.OutorganteDAO;
import br.com.pgo.dao.UaDAO;
import br.com.pgo.domain.Outorgante;
import br.com.pgo.domain.Ua;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class OutorganteBean implements Serializable {

	private Outorgante outorgante;
	private List<Outorgante> listaOutorgante;
	private List<Ua> listaNumeroUa;

	public Outorgante getOutorgante() {
		return outorgante;
	}

	public void setOutorgante(Outorgante outorgante) {
		this.outorgante = outorgante;
	}

	public List<Outorgante> getListaOutorgante() {
		return listaOutorgante;
	}

	public void setListaOutorgante(List<Outorgante> listaOutorgante) {
		this.listaOutorgante = listaOutorgante;
	}

	public List<Ua> getListaNumeroUa() {
		return listaNumeroUa;
	}

	public void setListaNumeroUa(List<Ua> listaNumeroUa) {
		this.listaNumeroUa = listaNumeroUa;
	}

	public void novo() {

		try {

			outorgante = new Outorgante();
			UaDAO uaDAO = new UaDAO();
			listaNumeroUa = uaDAO.listar();

		} catch (RuntimeException erro) {

			Messages.addGlobalInfo("Erro ao gerar numero UA !");
			erro.printStackTrace();

		}
	}

	public void salvar() {

		try {

			OutorganteDAO outorganteDAO = new OutorganteDAO();
			outorganteDAO.salvar(outorgante);

			novo();
			Messages.addFlashGlobalInfo("Outorgante salvo com sucesso!");

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalInfo("Outorgante salvo com sucesso!");
			erro.printStackTrace();
		}

	}

	@PostConstruct
	public void listar() {

		try {

			OutorganteDAO outorganteDAO = new OutorganteDAO();
			listaOutorgante = outorganteDAO.listar();

		} catch (RuntimeException erro) {

			Messages.addGlobalInfo("Erro ao listar Outorgante !");
			erro.printStackTrace();
		}
	}
}
