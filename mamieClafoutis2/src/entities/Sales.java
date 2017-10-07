package entities;

import java.util.Date;

public class Sales {
	
	private Date date;
	private int quantity;
	private int userId;
	
	private int productId;	
	// ajouter par momo
	private String username;
	private String EstablishmentName;
	private String productName;
	public Sales(){
		
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEstablishmentName() {
		return EstablishmentName;
	}

	public void setEstablishmentName(String establishmentName) {
		EstablishmentName = establishmentName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
}
