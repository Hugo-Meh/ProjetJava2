package entities;

public class User {

	private int id;
	private String lastName;
	private String firstName;
	private int establishmentId;
	private int roleId;
	private String username;
	private boolean isValid;
	private String token;
	private String pwd;

	public User() {

	}

	public User(String lastName, String firstName, int establishmentId,
			int roleId, String username, String token,
			String pwd) {

		this.lastName = lastName;
		this.firstName = firstName;
		this.establishmentId = establishmentId;
		this.roleId = roleId;
		this.username = username;
		this.token = token;
		this.pwd = pwd;
	}

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

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}