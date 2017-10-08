package entities;

public class Ingredient {
	private int id;
	private String name;
	private float qauntity;
	private String unity;
	
	public Ingredient (){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getQauntity() {
		return qauntity;
	}

	public void setQauntity(float qauntity) {
		this.qauntity = qauntity;
	}

	public String getUnity() {
		return unity;
	}

	public void setUnity(String unity) {
		this.unity = unity;
	}
}
