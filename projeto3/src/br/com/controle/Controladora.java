package br.com.controle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Controladora implements Serializable {

	private String questao1;
	private String resposta1;

	private String questao2;
	private String resposta2;

	private String questao3;
	private String resposta3;

	private String questao4;
	private String resposta4;

	private String questao5;
	private String resposta5;

	public String getQuestao1() {
		return questao1;
	}

	public void setQuestao1(String questao1) {
		this.questao1 = questao1;
	}

	public String getResposta1() {
		if (questao1 != null && questao1.equals("A")) {
			resposta1 = "1) Acertou, Resposta correta: Letra 'A'";
		} else {
			resposta1 = "1) Errou, Resposta correta: Letra 'A'";
		}

		return resposta1;
	}

	public void setResposta1(String resposta1) {
		this.resposta1 = resposta1;
	}

	public String getQuestao2() {
		return questao2;
	}

	public void setQuestao2(String questao2) {
		this.questao2 = questao2;
	}

	public String getResposta2() {
		if (questao2 != null && questao2.equals("E")) {
			resposta2 = "2) Acertou, Resposta correta: Letra 'E'";
		} else {
			resposta2 = "2) Errou, Resposta correta: Letra 'E'";
		}
		return resposta2;
	}

	public void setResposta2(String resposta2) {
		this.resposta2 = resposta2;
	}

	public String getQuestao3() {
		return questao3;
	}

	public void setQuestao3(String questao3) {
		this.questao3 = questao3;
	}

	public String getResposta3() {
		if (questao3 != null && questao3.equals("A")) {
			resposta3 = "3) Acertou, Resposta correta: Letra 'A'";
		} else {
			resposta3 = "3) Errou, Resposta correta: Letra 'A'";

		}
		return resposta3;

	}

	public void setResposta3(String resposta3) {
		this.resposta3 = resposta3;
	}

	public String getQuestao4() {
		return questao4;
	}

	public void setQuestao4(String questao4) {
		this.questao4 = questao4;
	}

	public String getResposta4() {
		if (questao4 != null && questao4.equals("D")) {
			resposta4 = "4) Acertou, Resposta correta: Letra 'D'";
		} else {
			resposta4 = "4) Errou, Resposta correta: Letra 'A'";

		}
		return resposta4;
	}

	public void setResposta4(String resposta4) {
		this.resposta4 = resposta4;
	}

	public String getQuestao5() {
		return questao5;
	}

	public void setQuestao5(String questao5) {
		this.questao5 = questao5;
	}

	public String getResposta5() {
		if (questao5 != null && questao5.equals("B")) {
			resposta5 = "5) Acertou, Resposta correta: Letra 'D'";
		} else {
			resposta5 = "5) Errou, Resposta correta: Letra 'A'";

		}
		return resposta5;
	}

	public void setResposta5(String resposta5) {
		this.resposta5 = resposta5;
	}

}
