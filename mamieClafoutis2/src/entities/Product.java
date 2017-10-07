package entities;
import java.io.Serializable;

public class Product implements Serializable {
	
	private int id;
	private String name;
	private String description;
	private String urlImage;
	private float price;
	private int idCategroy;	
	
	// ajouter quantite momo 
	 private boolean isVisible;
	 private int quantity;
	 private String ref;
	public Product(){
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getIdCategroy() {
		return idCategroy;
	}

	public void setIdCategroy(int idCategroy) {
		this.idCategroy = idCategroy;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

}
