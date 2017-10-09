package entities;

public class Establishment {
	private int id;
	private String name;
	private int idAdress;
	private String tel;
	private int type;

	// Getters
	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	
	public String getTel() {
		return this.tel;
	}

	public int getType() {
		return this.type;
	}

	// Setters
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String nom) {
		this.name = nom;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIdAdress() {
		return idAdress;
	}

	public void setIdAdress(int idAdress) {
		this.idAdress = idAdress;
	}
}