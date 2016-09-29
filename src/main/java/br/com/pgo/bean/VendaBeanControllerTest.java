package br.com.pgo.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
//import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.pgo.dao.OutorganteDAO;
import br.com.pgo.dao.UsuarioDAO;
import br.com.pgo.dao.VendaDAO;
import br.com.pgo.domain.Outorgante;
import br.com.pgo.domain.Ua;
import br.com.pgo.domain.Usuario;
import br.com.pgo.domain.Venda;
import br.com.pgo.util.InterpolarUaCalc;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VendaBeanControllerTest implements Serializable {

	private List<Ua> listaUas;
	private BigDecimal areaUa;
	private BigDecimal garantiaJan, garantiaFev, garantiaMar, garantiaAbr, garantiaMai, garantiaJun, garantiaJul,
			garantiaAgo, garantiaSet, garantiaOut, garantiaNov, garantiaDez;
	private BigDecimal areaDrenagem;
	private Venda venda; // processoAPAC //Vencimento // Captação - Itens que vem do venda.xhtml 
	private int num;
	private HashSet<Venda> listaVenda;
	private int processoMontante;
	private int processoJusante;
	private List<Outorgante> listaOutorgantes;
	private List<Usuario> listaUsuarios;	

	public List<Ua> getListaUas() {
		return listaUas;
	}

	public void setListaUas(List<Ua> listaUas) {
		this.listaUas = listaUas;
	}

	public BigDecimal getAreaUa() {
		return areaUa;
	}

	public void setAreaUa(BigDecimal areaUa) {
		this.areaUa = areaUa;
	}

	public BigDecimal getGarantiaJan() {
		return garantiaJan;
	}

	public void setGarantiaJan(BigDecimal garantiaJan) {
		this.garantiaJan = garantiaJan;
	}

	public BigDecimal getGarantiaFev() {
		return garantiaFev;
	}

	public void setGarantiaFev(BigDecimal garantiaFev) {
		this.garantiaFev = garantiaFev;
	}

	public BigDecimal getGarantiaMar() {
		return garantiaMar;
	}

	public void setGarantiaMar(BigDecimal garantiaMar) {
		this.garantiaMar = garantiaMar;
	}

	public BigDecimal getGarantiaAbr() {
		return garantiaAbr;
	}

	public void setGarantiaAbr(BigDecimal garantiaAbr) {
		this.garantiaAbr = garantiaAbr;
	}

	public BigDecimal getGarantiaMai() {
		return garantiaMai;
	}

	public void setGarantiaMai(BigDecimal garantiaMai) {
		this.garantiaMai = garantiaMai;
	}

	public BigDecimal getGarantiaJun() {
		return garantiaJun;
	}

	public void setGarantiaJun(BigDecimal garantiaJun) {
		this.garantiaJun = garantiaJun;
	}

	public BigDecimal getGarantiaJul() {
		return garantiaJul;
	}

	public void setGarantiaJul(BigDecimal garantiaJul) {
		this.garantiaJul = garantiaJul;
	}

	public BigDecimal getGarantiaAgo() {
		return garantiaAgo;
	}

	public void setGarantiaAgo(BigDecimal garantiaAgo) {
		this.garantiaAgo = garantiaAgo;
	}

	public BigDecimal getGarantiaSet() {
		return garantiaSet;
	}

	public void setGarantiaSet(BigDecimal garantiaSet) {
		this.garantiaSet = garantiaSet;
	}

	public BigDecimal getGarantiaOut() {
		return garantiaOut;
	}

	public void setGarantiaOut(BigDecimal garantiaOut) {
		this.garantiaOut = garantiaOut;
	}

	public BigDecimal getGarantiaNov() {
		return garantiaNov;
	}

	public void setGarantiaNov(BigDecimal garantiaNov) {
		this.garantiaNov = garantiaNov;
	}

	public BigDecimal getGarantiaDez() {
		return garantiaDez;
	}

	public void setGarantiaDez(BigDecimal garantiaDez) {
		this.garantiaDez = garantiaDez;
	}

	public BigDecimal getAreaDrenagem() {
		return areaDrenagem;
	}

	public void setAreaDrenagem(BigDecimal areaDrenagem) {
		this.areaDrenagem = areaDrenagem;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public HashSet<Venda> getListaVenda() {
		return listaVenda;
	}

	public void setListaVenda(HashSet<Venda> listaVenda) {
		this.listaVenda = listaVenda;
	}

	public int getProcessoMontante() {
		return processoMontante;
	}

	public void setProcessoMontante(int processoMontante) {
		this.processoMontante = processoMontante;
	}

	public int getProcessoJusante() {
		return processoJusante;
	}

	public void setProcessoJusante(int processoJusante) {
		this.processoJusante = processoJusante;
	}

	public List<Outorgante> getListaOutorgantes() {
		return listaOutorgantes;
	}

	public void setListaOutorgantes(List<Outorgante> listaOutorgantes) {
		this.listaOutorgantes = listaOutorgantes;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	// Instancia elementos para serem trabalhados na tela.
	@PostConstruct
	public void novo() {
		listar();
		venda = new Venda();
		listaVenda = new HashSet<Venda>();

		}

	// Listar usuários e outorgante para serem usando no
	// venda.xhtml (<p:outputLabel>)
	public void listar() {

		try {

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			listaUsuarios = usuarioDAO.listar("login");

			OutorganteDAO outorganteDAO = new OutorganteDAO();
			listaOutorgantes = outorganteDAO.listar("processoApac");

		} catch (RuntimeException erro) {

			Messages.addGlobalError("Ocorreu um erro ao criar processo");
			erro.printStackTrace();

		}

	}

	// CONSULTAR!
	// Dados serão consultados e lançados novamente na tela dentro das mesmas
	// variáveis
	public void consultar() {

		Iterator<Venda> posicao = listaVenda.iterator();

		while (posicao.hasNext()) {

			Venda atual = posicao.next();

			if (atual.getOutorgante().getProcessoApac() == venda.getOutorgante().getProcessoApac()) {

				venda.setJan(atual.getJan());
				venda.setJan(atual.getFev());
				venda.setJan(atual.getMar());
				venda.setJan(atual.getAbr());
				venda.setJan(atual.getMai());
				venda.setJan(atual.getJun());
				venda.setJan(atual.getJul());
				venda.setJan(atual.getAgo());
				venda.setJan(atual.getSet());
				venda.setJan(atual.getOut());
				venda.setJan(atual.getNov());
				venda.setJan(atual.getDez());

			}

		}
	}
	// ADICIONAR!

	// Adiconar Ua selecionada pelo usuário
	public void adicionar() {

		// Pesquisa Ua pelo número informado pelo usuário em venda.xhtml e
		// adiciona
		// na "listaUas" para ser usada no metódo para calcular interpolação das
		// Uas
		VendaDAO BuscarUA = new VendaDAO();
		listaUas = BuscarUA.buscarUa(num);
		areaUa = listaUas.get(1).getAreaUa();
		num = listaUas.get(1).getNumeroUa();

		if (listaUas.isEmpty()) {

			System.out.println("Nenhum registro encontrado");

		} else {
			int i = 0;
			for (Ua ua : listaUas) {
				i++;

				System.out.println(ua.getNumeroUa());
				System.out.println("Ano da UA: " + ua.getAno());

				venda.setNumeroUa(num);
				venda.setAreaUa(areaUa);
			}

			System.out.println("Quantidade Ua encontrada: " + i);
		}

		System.out.println(num);
	}

	// SIMULAR
	public void simular() {

		adicionar();
		interpolarMeses();
		encaixar();
		cacularDisponibilidade();
		consultar();

	}

	// INTERPOLAR!

	// Calculo de interpolação de todos os meses Jan - Dez da Ua selecionada
	// pelo usuário	
	public void interpolarMeses() {

		List<BigDecimal> ListaJan = new ArrayList<BigDecimal>();		
		for (Ua ua : listaUas) {
			ListaJan.add(ua.getJan());			
		}
		// Receber List de Janeiro que foi setada dentro do listUas
		InterpolarUaCalc interpolarJan = new InterpolarUaCalc();
		venda.setJan(interpolarJan.vendaInterpolar(ListaJan, areaUa, garantiaJan, areaDrenagem));
		
		

		List<BigDecimal> ListaFev = new ArrayList<BigDecimal>();
		for (Ua ua : listaUas) {

			ListaFev.add(ua.getFev());

		}
		// Receber List de Fevereiro que foi setada dentro do listUas
		InterpolarUaCalc interpolarFev = new InterpolarUaCalc();
		venda.setFev(interpolarFev.vendaInterpolar(ListaFev, areaUa, garantiaFev, areaDrenagem));

		List<BigDecimal> ListaMar = new ArrayList<BigDecimal>();
		for (Ua ua : listaUas) {

			ListaMar.add(ua.getMar());

		}

		// Receber List de Março que foi setada dentro do listUas
		InterpolarUaCalc interpolarMar = new InterpolarUaCalc();
		venda.setMar(interpolarMar.vendaInterpolar(ListaMar, areaUa, garantiaMar, areaDrenagem));

		List<BigDecimal> ListaAbr = new ArrayList<BigDecimal>();
		for (Ua ua : listaUas) {

			ListaAbr.add(ua.getAbr());

		}
		// Receber List de Abril que foi setada dentro do listUas
		InterpolarUaCalc interpolarAbr = new InterpolarUaCalc();
		venda.setAbr(interpolarAbr.vendaInterpolar(ListaAbr, areaUa, garantiaAbr, areaDrenagem));

		List<BigDecimal> ListaMai = new ArrayList<BigDecimal>();
		for (Ua ua : listaUas) {

			ListaMai.add(ua.getMai());

		}
		// Receber List de Maio que foi setada dentro do listUas
		InterpolarUaCalc interpolarMai = new InterpolarUaCalc();
		venda.setMai(interpolarMai.vendaInterpolar(ListaMai, areaUa, garantiaMai, areaDrenagem));

		List<BigDecimal> ListaJun = new ArrayList<BigDecimal>();
		for (Ua ua : listaUas) {

			ListaJun.add(ua.getJun());

		}
		// Receber List de Junho que foi setada dentro do listUas
		InterpolarUaCalc interpolarJun = new InterpolarUaCalc();
		venda.setJun(interpolarJun.vendaInterpolar(ListaJun, areaUa, garantiaJun, areaDrenagem));

		List<BigDecimal> ListaJul = new ArrayList<BigDecimal>();
		for (Ua ua : listaUas) {

			ListaJul.add(ua.getJul());

		}
		// Receber List de Julho que foi setada dentro do listUas
		InterpolarUaCalc interpolarJul = new InterpolarUaCalc();
		venda.setJul(interpolarJul.vendaInterpolar(ListaJul, areaUa, garantiaJul, areaDrenagem));

		List<BigDecimal> ListaAgo = new ArrayList<BigDecimal>();
		for (Ua ua : listaUas) {

			ListaAgo.add(ua.getAgo());

		}
		// Receber List de Agosto que foi setada dentro do listUas
		InterpolarUaCalc interpolarAgo = new InterpolarUaCalc();
		venda.setAgo(interpolarAgo.vendaInterpolar(ListaAgo, areaUa, garantiaAgo, areaDrenagem));

		List<BigDecimal> ListaSet = new ArrayList<BigDecimal>();
		for (Ua ua : listaUas) {

			ListaSet.add(ua.getSet());

		}
		// Receber List de Setembro que foi setada dentro do listUas
		InterpolarUaCalc interpolarSet = new InterpolarUaCalc();
		venda.setSet(interpolarSet.vendaInterpolar(ListaSet, areaUa, garantiaSet, areaDrenagem));

		List<BigDecimal> ListaOut = new ArrayList<BigDecimal>();
		for (Ua ua : listaUas) {

			ListaOut.add(ua.getOut());

		}
		// Receber List de Outubro que foi setada dentro do listUas
		InterpolarUaCalc interpolarOut = new InterpolarUaCalc();
		venda.setOut(interpolarOut.vendaInterpolar(ListaOut, areaUa, garantiaOut, areaDrenagem));

		List<BigDecimal> ListaNov = new ArrayList<BigDecimal>();
		for (Ua ua : listaUas) {

			ListaNov.add(ua.getNov());

		}
		// Receber List de Novembro que foi setada dentro do listUas
		InterpolarUaCalc interpolarNov = new InterpolarUaCalc();
		venda.setNov(interpolarNov.vendaInterpolar(ListaNov, areaUa, garantiaNov, areaDrenagem));

		List<BigDecimal> ListaDez = new ArrayList<BigDecimal>();
		for (Ua ua : listaUas) {

			ListaDez.add(ua.getDez());

		}
		// Receber List de Dezembro que foi setada dentro do listUas.
		InterpolarUaCalc interpolarDez = new InterpolarUaCalc();
		venda.setDez(interpolarDez.vendaInterpolar(ListaDez, areaUa, garantiaDez, areaDrenagem));

		// Amarrar controler a tela e testa interpolação!

	}

	// ENCAIXAR!

	// Faz consultado o BD e coloca na listaVenda e adicionar nova venda a
	// Lista.
	public void encaixar() {

		System.out.println("Metodo Encaixar!");
		try {

			VendaDAO vendaDAO = new VendaDAO();
			listaVenda = vendaDAO.listarVenda();
			listaVenda.add(venda);

			System.out.println("Tamanho lista ENCAIXAR: " + listaVenda.size());

		} catch (RuntimeException erro) {

			Messages.addGlobalInfo("Erro ao tentar criar 'Lista de Venda' ");
			erro.printStackTrace();

		}
		Iterator<Venda> posicao = listaVenda.iterator();

		// Encaixa novo elemento na lista, quando as duas condições foram
		// verdadeiras para o laço.
		while (posicao.hasNext()) {

			Venda atual = posicao.next();
			boolean condicao1 = false;
			boolean condicao2 = false;

			if (atual.getOutorgante().getProcessoApac() == processoJusante) {
				atual.setProcessoMotante(processoJusante);
				condicao1 = true;
			}
			if (atual.getOutorgante().getProcessoApac() == processoMontante) {
				atual.setProcessoJusante(processoMontante);
				condicao2 = true;
			}
			if (condicao1 == true && condicao2 == true) {
				break;
			}
		}

	}

	// Calcular disponibilidade
	public void cacularDisponibilidade() {

		Iterator<Venda> posicao = listaVenda.iterator();

		Venda jusante = null;

		while (posicao.hasNext()) {

			Venda atual = posicao.next();

			if (atual.getProcessoMotante() == 0) {
				// Primeiro processo do rio
				jusante = posicao.next();

				// Captação primeiro outorgante do rio

				// Disponibilidade Janeiro
				BigDecimal calcDispJanIni = new BigDecimal(String.valueOf(jusante.getCaptacao()))
						.subtract(new BigDecimal(String.valueOf(jusante.getJan())));
				jusante.setJan(calcDispJanIni);

				// Disponibilidade Fevereiro
				BigDecimal calcDispFevIni = new BigDecimal(String.valueOf(jusante.getCaptacao()))
						.subtract(new BigDecimal(String.valueOf(jusante.getFev())));
				jusante.setFev(calcDispFevIni);

				// Disponibilidade Março
				BigDecimal calcDispMarIni = new BigDecimal(String.valueOf(jusante.getCaptacao()))
						.subtract(new BigDecimal(String.valueOf(jusante.getMar())));
				jusante.setFev(calcDispMarIni);

				// Disponibilidade Abril
				BigDecimal calcDispAbrIni = new BigDecimal(String.valueOf(jusante.getCaptacao()))
						.subtract(new BigDecimal(String.valueOf(jusante.getAbr())));
				jusante.setFev(calcDispAbrIni);

				// Disponibilidade Maio
				BigDecimal calcDispMaiIni = new BigDecimal(String.valueOf(jusante.getCaptacao()))
						.subtract(new BigDecimal(String.valueOf(jusante.getMai())));
				jusante.setFev(calcDispMaiIni);

				// Disponibilidade Junho
				BigDecimal calcDispJunIni = new BigDecimal(String.valueOf(jusante.getCaptacao()))
						.subtract(new BigDecimal(String.valueOf(jusante.getJun())));
				jusante.setFev(calcDispJunIni);

				// Disponibilidade Julho
				BigDecimal calcDispJulIni = new BigDecimal(String.valueOf(jusante.getCaptacao()))
						.subtract(new BigDecimal(String.valueOf(jusante.getJul())));
				jusante.setFev(calcDispJulIni);

				// Disponibilidade Ago
				BigDecimal calcDispAgoIni = new BigDecimal(String.valueOf(jusante.getCaptacao()))
						.subtract(new BigDecimal(String.valueOf(jusante.getAgo())));
				jusante.setFev(calcDispAgoIni);

				// Disponibilidade Set
				BigDecimal calcDispSetIni = new BigDecimal(String.valueOf(jusante.getCaptacao()))
						.subtract(new BigDecimal(String.valueOf(jusante.getSet())));
				jusante.setFev(calcDispSetIni);

				// Disponibilidade Out
				BigDecimal calcDispOutIni = new BigDecimal(String.valueOf(jusante.getCaptacao()))
						.subtract(new BigDecimal(String.valueOf(jusante.getOut())));
				jusante.setFev(calcDispOutIni);

				// Disponibilidade Nov
				BigDecimal calcDispNovIni = new BigDecimal(String.valueOf(jusante.getCaptacao()))
						.subtract(new BigDecimal(String.valueOf(jusante.getNov())));
				jusante.setFev(calcDispNovIni);

				// Disponibilidade Dez
				BigDecimal calcDispDezIni = new BigDecimal(String.valueOf(jusante.getCaptacao()))
						.subtract(new BigDecimal(String.valueOf(jusante.getDez())));
				atual.setFev(calcDispDezIni);

				break;
			}
		}

		while (posicao.hasNext()) {

			Venda atual = posicao.next();

			while (posicao.hasNext()) {

				Venda proximo = posicao.next();

				if (atual.getOutorgante().getProcessoApac() == proximo.getProcessoMotante()) {

					BigDecimal captacao0 = atual.getCaptacao();
					BigDecimal captacao1 = proximo.getCaptacao();
					BigDecimal somaCaptacao = new BigDecimal(String.valueOf(captacao0))
							.add(new BigDecimal(String.valueOf(captacao1)));
					atual.setCaptacao(somaCaptacao);

					// Disponibilidade Janeiro
					BigDecimal calcDispJan = new BigDecimal(String.valueOf(atual.getCaptacao()))
							.subtract(new BigDecimal(String.valueOf(atual.getJan())));
					atual.setJan(calcDispJan);

					// Disponibilidade Fevereiro
					BigDecimal calcDispFev = new BigDecimal(String.valueOf(atual.getCaptacao()))
							.subtract(new BigDecimal(String.valueOf(atual.getFev())));
					atual.setFev(calcDispFev);

					// Disponibilidade Março
					BigDecimal calcDispMar = new BigDecimal(String.valueOf(atual.getCaptacao()))
							.subtract(new BigDecimal(String.valueOf(atual.getMar())));
					atual.setFev(calcDispMar);

					// Disponibilidade Abril
					BigDecimal calcDispAbr = new BigDecimal(String.valueOf(atual.getCaptacao()))
							.subtract(new BigDecimal(String.valueOf(atual.getAbr())));
					atual.setFev(calcDispAbr);

					// Disponibilidade Maio
					BigDecimal calcDispMai = new BigDecimal(String.valueOf(atual.getCaptacao()))
							.subtract(new BigDecimal(String.valueOf(atual.getMai())));
					atual.setFev(calcDispMai);

					// Disponibilidade Junho
					BigDecimal calcDispJun = new BigDecimal(String.valueOf(atual.getCaptacao()))
							.subtract(new BigDecimal(String.valueOf(atual.getJun())));
					atual.setFev(calcDispJun);

					// Disponibilidade Julho
					BigDecimal calcDispJul = new BigDecimal(String.valueOf(atual.getCaptacao()))
							.subtract(new BigDecimal(String.valueOf(atual.getJul())));
					atual.setFev(calcDispJul);

					// Disponibilidade Ago
					BigDecimal calcDispAgo = new BigDecimal(String.valueOf(atual.getCaptacao()))
							.subtract(new BigDecimal(String.valueOf(atual.getAgo())));
					atual.setFev(calcDispAgo);

					// Disponibilidade Set
					BigDecimal calcDispSet = new BigDecimal(String.valueOf(atual.getCaptacao()))
							.subtract(new BigDecimal(String.valueOf(atual.getSet())));
					atual.setFev(calcDispSet);

					// Disponibilidade Out
					BigDecimal calcDispOut = new BigDecimal(String.valueOf(atual.getCaptacao()))
							.subtract(new BigDecimal(String.valueOf(atual.getOut())));
					atual.setFev(calcDispOut);

					// Disponibilidade Nov
					BigDecimal calcDispNov = new BigDecimal(String.valueOf(atual.getCaptacao()))
							.subtract(new BigDecimal(String.valueOf(atual.getNov())));
					atual.setFev(calcDispNov);

					// Disponibilidade Dez
					BigDecimal calcDispDez = new BigDecimal(String.valueOf(atual.getCaptacao()))
							.subtract(new BigDecimal(String.valueOf(atual.getDez())));
					atual.setFev(calcDispDez);

				}
			}
		}

	}

	// SALVAR

	public void salvar() {

		VendaDAO salvarProcesso = new VendaDAO();

		Iterator<Venda> atual = listaVenda.iterator();

		while (atual.hasNext()) {
			salvarProcesso.mergeVenda(atual);

		}
	}
}
