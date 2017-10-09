package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Ingredient;
import service.ConnexionBDD;

public class IngredientManager {
	private static String queryIngredientByIdProduct = "select I.id as id,I.denomination as nom,LI.qantite as qte,I.ref as ref,LI.unite as unite from produit as P inner join ligne_ingredient as LI on P.id=LI.produit_id inner join ingredient I on LI.ingredient_id=I.id where p.id=?";
	private static String queryUpdateIngredientByIDProduct = "update ligne_ingredient set quantite=?,unite=? where produit_id=?";
	private static String queryDeleteLineIngedientByIDProduct = "delete from ligne_ingredient where product_id=? and ingredient_id=?";
	private static String queryInsertListIngredientByIDProduct = "insert into ligne_ingredient ('produit_id','ingredient_id','quantite','unite') values (?,?,?,?)";

	public static ArrayList<Ingredient> getAll() {
		return null;
	}

	public static boolean Insert(Ingredient ingerdient) {
		return false;
	}

	public static boolean Update(Ingredient ingredient) {
		return false;
	}

	public static ArrayList<Ingredient> getAllIngredientByIdProduct(int idProduct) {
		ArrayList<Ingredient> retour = null;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryIngredientByIdProduct);
			ps.setInt(1, idProduct);
			ResultSet result = ps.executeQuery();
			if (result != null)
				retour = new ArrayList<>();
			while (result.next()) {
				Ingredient I = new Ingredient();
				I.setId(result.getInt("id"));
				I.setName(result.getString("nom"));
				// I.setQty(result.getFloat("qte"));
				// I.setRef(result.getString("ref"));
				// I.setUnity(result.getString("unite"));
				retour.add(I);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}
		return retour;
	}

	// modifier les quantites des ingredients d'un produit par unite
	public static boolean UpdateIngeredientByIdProduct(int idProduct, Ingredient ingerdient) {
		boolean retour = false;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryUpdateIngredientByIDProduct);
			ps.setFloat(1, ingerdient.getQauntity());
			ps.setString(2, ingerdient.getUnity());
			ps.setInt(3, idProduct);
			int nbUpdate = ps.executeUpdate();
			if (nbUpdate > 0)
				retour = true;

			ConnexionBDD.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}
		return retour;
	}

	// supprimer un ingredient de la table ingredient des produits
	public static boolean removeIngredientByIdProduct(int idProduct, int idIngredient) {
		boolean retour = false;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryDeleteLineIngedientByIDProduct);
			ps.setInt(1, idProduct);
			ps.setInt(2, idIngredient);
			int nbUpdate = ps.executeUpdate();
			if (nbUpdate > 0)
				retour = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}
		return retour;
	}

	// inserer un ingredient de la table ingredient des produits
	public static boolean InsertIngredientByIdProduct(int idProduct, ArrayList<Ingredient> ingeredients) {
		boolean retour = false;
		int nbInsert = 0;
		try {
			for (Ingredient i : ingeredients) {
				PreparedStatement ps = ConnexionBDD.getPs(queryInsertListIngredientByIDProduct);
				ps.setInt(1, idProduct);
				ps.setInt(2, i.getId());
				// ps.setFloat(3, i.getQte());
				// ps.setString(4, i.getUnite());
				nbInsert += ps.executeUpdate();
			}
			if (nbInsert == ingeredients.size())
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
