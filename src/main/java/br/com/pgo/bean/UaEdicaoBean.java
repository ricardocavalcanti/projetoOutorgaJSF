package br.com.pgo.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.pgo.dao.UaDAO;
import br.com.pgo.domain.Ua;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UaEdicaoBean implements Serializable {

	private Long codigoUa;
	private UaDAO uaDAO;
	private Ua ua;

	public Long getCodigoUa() {
		return codigoUa;
	}

	public void setCodigoUa(Long codigoUa) {
		this.codigoUa = codigoUa;
	}

	public Ua getUa() {
		return ua;
	}

	public void setUa(Ua ua) {
		this.ua = ua;
	}

	@PostConstruct
	public void iniciar() {

		uaDAO = new UaDAO();

	}

	public void carregarEdicao() {

		try {

			ua = uaDAO.buscar(codigoUa);

		} catch (RuntimeException erro) {

			Messages.addGlobalInfo("Erro ao carregar edição da Unidade de análise");

		}

	}

	public void salvarEdicao() {

		try {

			uaDAO.merge(ua);

			Messages.addFlashGlobalInfo("Unidade de análise editada com sucesso!");

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalInfo("Erro ao editar Unidade de análise!");
			erro.printStackTrace();
		}

	}

}
