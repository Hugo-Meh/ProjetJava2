package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Ingredient;
import service.ConnexionBDD;

public class IngredientManager {
	// requete pour la table ingredient
	private static String queryAll = "select * from ingredient";
	private static String queryInsert = "insert into ingredient ('denomination','quantite','unite_ingredient') values (?,?,?)";
	private static String queryUpdate = "update ingredient set denomination=?, quantite=?, unite_ingredient=?";
	// requette pour la table ligne_ingredient
	private static String queryIngredientByIdProduct = "select I.id as id,I.denomination as nom,LI.qantite as qte,I.ref as ref,LI.unite as unite from produit as P inner join ligne_ingredient as LI on P.id=LI.produit_id inner join ingredient I on LI.ingredient_id=I.id where p.id=?";
	private static String queryUpdateIngredientByIDProduct = "update ligne_ingredient set quantite=?,unite=? where produit_id=? and ingredient_id=?";
	private static String queryDeleteLineIngedientByIDProduct = "delete from ligne_ingredient where produit_id=? and ingredient_id=?";
	private static String queryInsertListIngredientByIDProduct = "insert into ligne_ingredient ('produit_id','ingredient_id','quantite','unite') values (?,?,?,?)";

	public static ArrayList<Ingredient> getAll() {
		ArrayList<Ingredient> ingeredients = null;

		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryAll);
			ResultSet rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				ingeredients = new ArrayList<>();
				if (rs.next()) {
					Ingredient i = new Ingredient();
					i.setId(rs.getInt("id"));
					i.setName(rs.getString("denomination"));
					i.setQauntity(rs.getFloat("quantite"));
					i.setUnity(rs.getString("unite_ingredient"));
					ingeredients.add(i);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}
		return ingeredients;
	}

	public static boolean Insert(Ingredient ingredient) {
		boolean retour = false;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryInsert);
			ps.setString(1, ingredient.getName());
			ps.setFloat(2, ingredient.getQauntity());
			ps.setString(3, ingredient.getUnity());
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

	public static boolean Update(Ingredient ingredient) {
		boolean retour = false;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryUpdate);

			ps.setString(1, ingredient.getName());
			ps.setFloat(2, ingredient.getQauntity());
			ps.setString(3, ingredient.getUnity());
			if (ps.executeUpdate() > 0)
				retour = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnexionBDD.closeConnection();
		}

		return retour;
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
				 I.setQauntity(result.getFloat("quantite"));
				 I.setUnity(result.getString("unite"));
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
	public static boolean UpdateIngeredientByIdProduct(int idProduct, Ingredient ingredient) {
		boolean retour = false;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryUpdateIngredientByIDProduct);
			ps.setFloat(1, ingredient.getQauntity());
			ps.setString(2, ingredient.getUnity());
			ps.setInt(3,idProduct);
			ps.setInt(4, ingredient.getId());
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
