package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Role;

import service.ConnexionBDD;

public class RoleManager {

	private static String queryAll = "select * from role";
	private static String insert = "insert into role denomination=?";
	private static String queryByName = "select * from role where denomination=?";
	private static String queryUpdate = "update role set denomination=? where id=?";

	// recuperer tout les type
	public static ArrayList<Role> getAll() {
		ArrayList<Role> role = null;

		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryAll);
			ResultSet rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				role = new ArrayList<>();
				while (rs.next()) {
					Role r = new Role();
					r.setId(rs.getInt("id"));
					r.setName(rs.getString("denomination"));
					role.add(r);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}

		return role;
	}

	public static boolean insert(String denomination) {
		boolean retour = false;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(insert);
			ps.setString(1, denomination);
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

	public static boolean update(Role role) {
		boolean retour = false;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryUpdate);
			ps.setString(1, role.getName());
			ps.setInt(2, role.getId());
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

	public static boolean existingName(String denomination) {
		boolean retour = false;

		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryByName);
			ResultSet rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				if (rs.next()) {
					retour = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}
		return retour;
	}

}
