package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entities.User;
import service.ConnexionBDD;

public class UserManager {
	private static String queryUser = "select * from utilisateur where userName = ? and password = ?";
	private static String queryById = "select * from utilisateur where id = ?";
	private static String queryInsert = "insert into utilisateur ('id', 'nom', 'prenom', 'etablisement_id', 'role_id', 'userName', 'password') values(?,?,?,?,?,?,?)";
	private static String queryUpdate = "update utilisateur set id = ?, nom = ?, prenom = ?, etablisement_id = ?, role_id = ?, userName = ?, password = ?";

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
					user.setEstablishmentId(result.getInt("etablisement_id"));
					user.setRoleId(result.getInt("role_id"));
					user.setUsername(result.getString("userName"));
					// u.setPassword(result.getString("password"));
					
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

	public boolean verifyUser(String login, String pwd) {
		if (getUser(login, pwd) != null)
			return true;

		return false;
	}

	public User getAllInformationById(int id) {
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
					user.setEstablishmentId(result.getInt("etablisement_id"));
					user.setRoleId(result.getInt("role_id"));
					user.setUsername(result.getString("userName"));
					// u.setPassword(result.getString("password"));
					
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
	public static boolean Insert(User newUser) {
		boolean retour = false;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryInsert);
			ps.setInt(1, newUser.getId());
			ps.setString(2, newUser.getLastName());
			ps.setString(3, newUser.getFirstName());
			ps.setInt(4, newUser.getEstablishmentId());
			ps.setInt(5, newUser.getRoleId());
			ps.setString(6, newUser.getUsername());
			ps.setString(7, newUser.getPassword());
			int nbretour = ps.executeUpdate();
			if(nbretour>0){
				retour=true;
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

	// mis a jour d'un employé type de fonction
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
			ps.setString(7, newUser.getPassword());
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
	//get all user
	//get user by role
}