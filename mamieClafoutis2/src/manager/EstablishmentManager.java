package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entities.Establishment;
import service.ConnexionBDD;

public class EstablishmentManager {
	private static String queryAll = "select * from etablissement";
	private static String queryByIdUser = "select E.id as id,E.name as name,E.type_id as type_id,E.tel as tel, E.Adresse_id as Adresse_id "
			+ " from utilisateur as U  inner join etablissement as E on U.Etablissement_id=E.id where U.id = ?";
	private static String queryType = "select * from etablissement where type_id = (select id from type where denomination = ?)";
	private static String queryInsert = "insert into etablissement ( 'name', 'Adresse_id', 'tel', 'type_id') values(?,?,?,?)";
	private static String queryUpdate = "update etablissement set name = ?, Adresse_id = ?, tel = ?, type_id = ? where id = ?";

	public static ArrayList<Establishment> getAll() {
		ArrayList<Establishment> establishment = null;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryAll);
			ResultSet result = ps.executeQuery();

			if (result.isBeforeFirst()) {
				establishment = new ArrayList<>();
				while (result.next()) {
					Establishment e = new Establishment();
					e.setId(result.getInt("id"));
					e.setName(result.getString("name"));
					e.setIdAdress(result.getInt("Adresse_id"));
					e.setTel(result.getString("tel"));
					e.setType(result.getInt("type_id"));
					establishment.add(e);
				}
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			ConnexionBDD.closeConnection();
		}

		return establishment;
	}

	// Retourne l'etablissement ou travaille un employ�
	public static Establishment getByIdUser(int idUser) {
		Establishment establishment = null;

		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryByIdUser);
			ps.setInt(1, idUser);
			ResultSet result = ps.executeQuery();

			if (result.isBeforeFirst())
				establishment = new Establishment();
			if (result.next()) {
				establishment.setId(result.getInt("id"));
				establishment.setName(result.getString("name"));
				establishment.setIdAdress(result.getInt("Adresse_id"));
				establishment.setTel(result.getString("tel"));
				establishment.setType(result.getInt("type_id"));
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			ConnexionBDD.closeConnection();
		}

		return establishment;
	}

	// Retourne les etablissements d'un type
	public static ArrayList<Establishment> getBytype(String type) {
		ArrayList<Establishment> establishment = null;

		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryType);
			ps.setString(1, type);
			ResultSet result = ps.executeQuery();

			if (result != null)
				establishment = new ArrayList<>();

			while (result.next()) {
				Establishment e = new Establishment();
				e.setId(result.getInt("id"));
				e.setName(result.getString("name"));
				e.setIdAdress(result.getInt("Adresse_id"));
				e.setTel(result.getString("tel"));
				e.setType(result.getInt("type_id"));
				establishment.add(e);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			ConnexionBDD.closeConnection();
		}

		return establishment;
	}

	// En cas d'ajout d'un nouveau client restaurateur franchise ou corporate
	public static boolean Insert(Establishment etab) {
		boolean retour = false;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryInsert);
			ps.setInt(1, etab.getId());
			ps.setString(2, etab.getName());
			ps.setInt(3, etab.getIdAdress());
			ps.setString(4, etab.getTel());
			ps.setInt(5, etab.getType());
			int nbretour = ps.executeUpdate();
			if (nbretour > 0) {
				retour = true;
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

	// Dans le cas ou un franchise devient corporate ou inverse
	public static boolean Update(Establishment etab) {
		int nbrUpdate = 0;
		boolean retour = false;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryUpdate);
			ps.setInt(1, etab.getId());
			ps.setString(2, etab.getName());
			ps.setInt(3, etab.getIdAdress());
			ps.setString(4, etab.getTel());
			ps.setInt(5, etab.getType());
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

}