package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.User;
import service.ConnexionBDD;

public class UserManager {
	private static String queryUser = "select * from utilisateur where userName = ? and password = ?";
	private static String queryById = "select * from utilisateur where id = ?";
	private static String queryInsert = "insert into utilisateur (nom, prenom, etablissement_id, role_id, userName,password,token) values(?,?,?,?,?,?,?)";
	private static String queryUpdate = "update utilisateur set id = ?, nom = ?, prenom = ?, etablissement_id = ?, role_id = ?, userName = ?, password = ?";
	private static String queryByNameUser = "select * from utilisateur where username=?";
	private static String queryValidateUser = "update utilisateur set isvalid=true where id=?";
	private static String queryTokenById = "select token from utilisateur where id=?";
	private static String qureyByIdEtab = "select * from utilisateur where etblissement_id=?";
	private static String queryByIdRole = " select * from utilisateur where role_id=?";
	private static String queryByName = "select * from utilisateur where nom like '%?%' or prenom like '%?%' or userName like '%?%'";
	private static String queryAll = "select * utilisateur";

	public static User getUser(String login, String pwd) {
		User user = null;

		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryUser);
			ps.setString(1, login);
			ps.setString(2, pwd);
			ResultSet result = ps.executeQuery();

			if (result.isBeforeFirst())
				if (result.next()) {
					user = new User();
					user.setId(result.getInt("id"));
					user.setLastName(result.getString("nom"));
					user.setFirstName(result.getString("prenom"));
					user.setEstablishmentId(result.getInt("etablissement_id"));
					user.setRoleId(result.getInt("role_id"));
					user.setUsername(result.getString("userName"));
					user.setValid(result.getBoolean("isvalid"));
					user.setToken(result.getString("token"));
				}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			ConnexionBDD.closeConnection();
		}

		return user;
	}

	static public boolean verifyUser(String login, String pwd) {
		if (getUser(login, pwd) != null)
			return true;

		return false;
	}

	static public User getAllInformationById(int id) {
		User user = null;

		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryById);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();

			if (result.isBeforeFirst())

				if (result.next()) {
					user = new User();
					user.setId(result.getInt("id"));
					user.setLastName(result.getString("nom"));
					user.setFirstName(result.getString("prenom"));
					user.setEstablishmentId(result.getInt("etablissement_id"));
					user.setRoleId(result.getInt("role_id"));
					user.setUsername(result.getString("userName"));
					user.setValid(result.getBoolean("isvalid"));
					user.setToken(result.getString("token"));

				}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			ConnexionBDD.closeConnection();
		}

		return user;
	}

	// a l'inscription on appelle insert user
	public static int Insert(User newUser) {
		int retour = -1;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryInsert);
			ps.setString(1, newUser.getLastName());
			ps.setString(2, newUser.getFirstName());
			ps.setInt(3, newUser.getEstablishmentId());
			ps.setInt(4, newUser.getRoleId());
			ps.setString(5, newUser.getUsername());
			ps.setString(6, newUser.getPwd());
			ps.setString(7, newUser.getToken());

			int nbretour = ps.executeUpdate();

			if (nbretour > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next())
					retour = rs.getInt(1);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			ConnexionBDD.closeConnection();
		}

		return retour;
	}

	// mis a jour d'un employ� type de fonction
	// ou retirer de la liste avec un boolean
	public static boolean Update(User newUser) {
		int nbrUpdate = 0;
		boolean retour = false;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryUpdate);
			ps.setInt(1, newUser.getId());
			ps.setString(2, newUser.getLastName());
			ps.setString(3, newUser.getFirstName());
			ps.setInt(4, newUser.getEstablishmentId());
			ps.setInt(5, newUser.getRoleId());
			ps.setString(6, newUser.getUsername());
			nbrUpdate = ps.executeUpdate();

			if (nbrUpdate > 0)
				retour = true;
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			ConnexionBDD.closeConnection();
		}

		return retour;
	}

	// get user by id etablishment
	public static ArrayList<User> getByEstablishmentId(int id) {
		ArrayList<User> users = null;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(qureyByIdEtab);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();

			if (result.isBeforeFirst())
				users = new ArrayList<>();
			while (result.next()) {
				User user = new User();
				user.setId(result.getInt("id"));
				user.setLastName(result.getString("nom"));
				user.setFirstName(result.getString("prenom"));
				user.setEstablishmentId(result.getInt("etablissement_id"));
				user.setRoleId(result.getInt("role_id"));
				user.setUsername(result.getString("userName"));
				user.setValid(result.getBoolean("isvalid"));
				user.setToken(result.getString("token"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			ConnexionBDD.closeConnection();
		}
		return users;
	}

	// get all user
	public static ArrayList<User> getAll(int id) {
		ArrayList<User> users = null;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryAll);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();

			if (result.isBeforeFirst())
				users = new ArrayList<>();
			while (result.next()) {
				User user = new User();
				user.setId(result.getInt("id"));
				user.setLastName(result.getString("nom"));
				user.setFirstName(result.getString("prenom"));
				user.setEstablishmentId(result.getInt("etablissement_id"));
				user.setRoleId(result.getInt("role_id"));
				user.setUsername(result.getString("userName"));
				user.setValid(result.getBoolean("isvalid"));
				user.setToken(result.getString("token"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			ConnexionBDD.closeConnection();
		}
		return users;
	}

	// get user by role
	public static ArrayList<User> getByRoleId(int id) {
		ArrayList<User> users = null;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryByIdRole);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();

			if (result.isBeforeFirst())
				users = new ArrayList<>();
			while (result.next()) {
				User user = new User();
				user.setId(result.getInt("id"));
				user.setLastName(result.getString("nom"));
				user.setFirstName(result.getString("prenom"));
				user.setEstablishmentId(result.getInt("etablissement_id"));
				user.setRoleId(result.getInt("role_id"));
				user.setUsername(result.getString("userName"));
				user.setValid(result.getBoolean("isvalid"));
				user.setToken(result.getString("token"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			ConnexionBDD.closeConnection();
		}
		return users;
	}

	// get user by name User
	public static ArrayList<User> getByName(String name) {
		ArrayList<User> users = null;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryByName);
			ps.setString(1, name);
			ResultSet result = ps.executeQuery();

			if (result.isBeforeFirst())
				users = new ArrayList<>();
			while (result.next()) {
				User user = new User();
				user.setId(result.getInt("id"));
				user.setLastName(result.getString("nom"));
				user.setFirstName(result.getString("prenom"));
				user.setEstablishmentId(result.getInt("etablissement_id"));
				user.setRoleId(result.getInt("role_id"));
				user.setUsername(result.getString("userName"));
				user.setValid(result.getBoolean("isvalid"));
				user.setToken(result.getString("token"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			ConnexionBDD.closeConnection();
		}
		return users;
	}

	// a l'inscription on test si l'email existe dans la base ou nn?

	static public boolean VerifyNameUser(String login) {
		boolean retour = true;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryByNameUser);
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			if (rs.isBeforeFirst())
				retour = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}
		return retour;
	}

	static public User getNameUser(String login) {
		User user = null;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryByNameUser);
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			if (rs.isBeforeFirst() && rs.next())
				user = new User();
			user.setId(rs.getInt("id"));
			user.setLastName(rs.getString("nom"));
			user.setFirstName(rs.getString("prenom"));
			user.setEstablishmentId(rs.getInt("etablissement_id"));
			user.setRoleId(rs.getInt("role_id"));
			user.setUsername(rs.getString("userName"));
			user.setValid(rs.getBoolean("isvalid"));
			user.setToken(rs.getString("token"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}
		return user;
	}

	// verifier un token s'il est correct ou nn
	public static boolean validateToken(int idUser, String token) {
		boolean retour = false;
		String localtoken = null;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryTokenById);
			ps.setInt(1, idUser);
			ResultSet rs = ps.executeQuery();
			if (rs.isBeforeFirst() && rs.next())
				localtoken = rs.getString("token");
			if (localtoken.equals(token))
				retour = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}

		return retour;
	}

	// valider un utilisateur apres verification du token
	public static boolean validateUser(int id) {
		boolean retour = false;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryValidateUser);
			ps.setInt(1, id);
			if (ps.executeUpdate() > 0)
				retour = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}
		return retour;
	}
}