package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Adress;
import service.ConnexionBDD;

public class AdressManager {
	private static String queryAll = "select * from adresse";
	private static String queryByIdEtab = "select * from adresse as A inner join etablissement as E "
			+ "on  A.id=E.Adresse_id where E.id=?";
	private static String insert = "insert into adresse ('numero_rue','nom_rue','ville','province','code_postal','pays')"
			+ "values(?,?,?,?,?,?)";
	private static String update = "update adresse set numero_rue=?, nom_rue=?,ville=?,province=?, code_postal=?pays=?";

	public static ArrayList<Adress> getAll() {
		ArrayList<Adress> adress = null;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryAll);
			ResultSet rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				adress = new ArrayList<>();
				while (rs.next()) {
					Adress a = new Adress();
					a.setId(rs.getInt("id"));
					a.setStreetNumber(rs.getInt("numero_rue"));
					a.setName(rs.getString("nom_rue"));
					a.setCity(rs.getString("ville"));
					a.setProvince(rs.getString("province"));
					a.setCP(rs.getString("code_postal"));
					a.setCountry(rs.getString("pays"));
					adress.add(a);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}

		return adress;
	}

	public static Adress getByIdEstablishment(int id) {
		Adress adress = null;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryByIdEtab);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				adress = new Adress();
				if (rs.next()) {

					adress.setId(rs.getInt("id"));
					adress.setStreetNumber(rs.getInt("numero_rue"));
					adress.setName(rs.getString("nom_rue"));
					adress.setCity(rs.getString("ville"));
					adress.setProvince(rs.getString("province"));
					adress.setCP(rs.getString("code_postal"));
					adress.setCountry(rs.getString("pays"));

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}

		return adress;
	}

	public static int insert(Adress adress) {
		int retour = -1;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(insert);
			ps.setInt(1, adress.getStreetNumber());
			ps.setString(2, adress.getName());
			ps.setString(3, adress.getCity());
			ps.setString(4, adress.getProvince());
			ps.setString(5, adress.getCP());
			ps.setString(6, adress.getCountry());
			if (ps.executeUpdate() > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next())
					retour = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retour;
	}

	public static boolean update(Adress adress) {
		boolean retour = false;
		try {

			PreparedStatement ps = ConnexionBDD.getPs(update);
			ps.setInt(1, adress.getStreetNumber());
			ps.setString(2, adress.getName());
			ps.setString(3, adress.getCity());
			ps.setString(4, adress.getProvince());
			ps.setString(5, adress.getCP());
			ps.setString(6, adress.getCountry());
			if (ps.executeUpdate() > 0)
				retour = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retour;

	}
}
