package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import service.ConnexionBDD;

public class RecipeManager {
	private static String queryRecipeById = "select * from recette where produit_id=?";
	
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

}
