package br.com.pgo.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.pgo.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ImprimirBean implements Serializable {

	public void imprimir() {

		try {

			String caminho = Faces.getRealPath("/reports/Outorgante.jasper");
			Map<String, Object> parametros = new HashMap<>();
			Connection conexao = HibernateUtil.getConexao();

			JasperFillManager.fillReport(caminho, parametros, conexao);

		} catch (JRException erro) {
			Messages.addGlobalError("Erro ao tentar gerar relatório");
			erro.printStackTrace();

		}
	}

}
