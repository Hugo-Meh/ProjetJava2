package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Category;
import service.ConnexionBDD;

public class CategoryManager {
	// les attribut Private static String Query
	private static String queryAll = "select * from categorie";
	private static String queryInsert = "insert into categorie ('denomination') values(?)";
	private static String queryNameCategoryByIdProduct = "select c.denomination as nomCategorie from produit as p inner join categorie as c on c.id=p.categorie_id where p.id=?";

	public static ArrayList<Category> getAll() {
		ArrayList<Category> categorie = null;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryAll);
			ResultSet result = ps.executeQuery();
			if (result.isBeforeFirst()) {
				categorie = new ArrayList<>();
				while (result.next()) {
					Category c = new Category();
					c.setId(result.getInt("id"));
					c.setNom(result.getString("denomination"));
					categorie.add(c);
				}
			}
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			ConnexionBDD.closeConnection();
		}
		return categorie;
	}

	public static boolean InsertCategory(String Category) {
		boolean retour = false;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryInsert);
			ps.setString(1, Category);
			int nbRetour = ps.executeUpdate();
			if (nbRetour > 0)
				retour = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}

		return retour;
	}

	// retourner le nom d'une categorie d'une produit de cle id
	public static String getCategoryNameByIdProduct(int id) {
		String retour = null;

		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryNameCategoryByIdProduct);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			if (result.isBeforeFirst())
				if (result.next()) {
					retour = result.getString("nomCategorie");
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
