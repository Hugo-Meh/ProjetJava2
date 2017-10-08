package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Recipe;
import service.ConnexionBDD;

public class RecipeManager {
	private static String queryRecipeById = "select * from recette where produit_id=?";
	private static String queryInsert = "insert into recette ('produit_id','description') values (?,?) ";
	private static String queryUpdate="update recette set description =?, date_modif=NOW() where produit_id=?";

	// dans le cas ou la recette est un textarea on retourne un String si l
	// recette est composé de plusieurs lignes on retourne Al<Receipe> ou on
	// ouvre un fichier editable
	public static String getRecipeById(int idProduct) {
		String recette = null;

		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryRecipeById);
			ps.setInt(1, idProduct);
			ResultSet result = ps.executeQuery();
			if (result.isBeforeFirst())
				if (result.next())
					recette = result.getString("url_recette");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}
		return recette;
	}

	// insertion d'une recette
	public static boolean insert(Recipe recipe) {
		boolean retour = false;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryInsert);
			ps.setInt(1, recipe.getIdProduct());
			ps.setString(2, recipe.getDescription());
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
	
	public static boolean update(Recipe recipe){
		boolean retour=false;
		try {
			PreparedStatement ps= ConnexionBDD.getPs(queryUpdate);
			ps.setString(1, recipe.getDescription());
			ps.setInt(2, recipe.getIdProduct());
			if(ps.executeUpdate()>0)
				retour=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}

		
		return retour;
	}
}
