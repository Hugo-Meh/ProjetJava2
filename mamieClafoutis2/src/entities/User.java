package entities;

public class User {
	private int id;
	private String lastName;
	private String firstName;
	private int establishmentId;
	private int roleId;
	private String username;
	private String password;

	// Getters
	public int getId() {
		return this.id;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public int getEstablishmentId() {
		return this.establishmentId;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	// Setters
	public void setId(int id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setEstablishmentId(int establishmentId) {
		this.establishmentId = establishmentId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}