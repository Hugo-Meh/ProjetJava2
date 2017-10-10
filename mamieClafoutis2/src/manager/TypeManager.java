package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Type;
import service.ConnexionBDD;

public class TypeManager {
	private static String queryAll = "select * from type";
	private static String insert = "insert into type denomination=?";
	private static String queryByName = "select * from type where denomination=?";
	private static String queryUpdate = "update type set denomination=? where id=?";

	// recuperer tout les type
	public static ArrayList<Type> getAll() {
		ArrayList<Type> types = null;

		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryAll);
			ResultSet rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				types = new ArrayList<>();
				while (rs.next()) {
					Type t = new Type();
					t.setId(rs.getInt("id"));
					t.setName(rs.getString("denomination"));
					types.add(t);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}

		return types;
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

	public static boolean update(Type type) {
		boolean retour = false;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryUpdate);
			ps.setString(1, type.getName());
			ps.setInt(2, type.getId());
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
