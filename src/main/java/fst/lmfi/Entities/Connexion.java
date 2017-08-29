package fst.lmfi.Entities;

import org.springframework.data.annotation.Id;

public class Connexion {
	
	private String em;
	private String pw;

	public Connexion() {

	}

	public Connexion(String e, String p) {
		this.em = e;
		this.pw = p;
	}

	public String getEm() {
		return em;
	}

	public void setEm(String em) {
		this.em = em;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

}
