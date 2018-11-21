package esercizio_jdbc;

import java.util.Date;

public class Automobile {
	private int id;
	private String marca;
	private String modello;
	private float cilindrata;
	private Date data_immatricolazione;
	private int numero_posti;

	public Automobile() {
		super();
	}

	public Automobile(int id, String marca, String modello, float cilindrata, Date data_immatricolazione,
			int numero_posti) {
		super();
		this.id = id;
		this.marca = marca;
		this.modello = modello;
		this.cilindrata = cilindrata;
		this.data_immatricolazione = data_immatricolazione;
		this.numero_posti = numero_posti;
	}

	public int getId() {
		return id;
	}

	public String getMarca() {
		return marca;
	}

	public String getModello() {
		return modello;
	}

	public float getCilindrata() {
		return cilindrata;
	}

	public Date getData_immatricolazione() {
		return data_immatricolazione;
	}

	public int getNumero_posti() {
		return numero_posti;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public void setCilindrata(float cilindrata) {
		this.cilindrata = cilindrata;
	}

	public void setData_immatricolazione(Date data_immatricolazione) {
		this.data_immatricolazione = data_immatricolazione;
	}

	public void setNumero_posti(int numero_posti) {
		this.numero_posti = numero_posti;
	}

}
