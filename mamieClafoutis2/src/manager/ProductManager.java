package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entities.Product;
import service.ConnexionBDD;

public class ProductManager {
	// les attribut Private static String Query
	private static String queryAll = "select * from produit";
	private static String queryByIdCategory = "select * from produit where categorie_id=?";
	private static String queryByName = "select * from produit where nom=?";
	private static String queryById = "select * from produit where id=?";
	private static String queryInsert = "insert into produit ('nom','categorie_id','ref','description','prix','urlImage') values(?,?,?,?,?,?)";
	private static String queryUpdate = "update produit set nom=? ,categorie_id=?, description=?,prix=?, urlImage=? where id=?";

	// retourner tout les produit de la table produit
	public static ArrayList<Product> getAll() {
		ArrayList<Product> retour = null;

		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryAll);
			ResultSet result = ps.executeQuery();
			if (result.isBeforeFirst())
				retour = new ArrayList<>();
			while (result.next()) {
				Product p = new Product();
				p.setId(result.getInt("id"));
				p.setName(result.getString("nom"));
				p.setDescription(result.getString("description"));
				p.setPrice(result.getFloat("prix"));
				p.setUrlImage(result.getString("urlImage"));
				retour.add(p);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}
		return retour;
	}

	// retourner tout les produit d'une categorie d'un id donnee
	public static ArrayList<Product> getByCategoryId(int id) {
		ArrayList<Product> retour = null;

		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryByIdCategory);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			if (result.isBeforeFirst())
				retour = new ArrayList<>();
			while (result.next()) {
				Product p = new Product();
				p.setId(result.getInt("id"));
				p.setName(result.getString("nom"));
				p.setDescription(result.getString("description"));
				p.setPrice(result.getFloat("prix"));
				p.setUrlImage(result.getString("urlImage"));
				retour.add(p);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}
		return retour;
	}

	// retourner tout les produit avec un nom "name"
	public static ArrayList<Product> getByName(String name) {
		ArrayList<Product> retour = null;

		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryByName);
			ps.setString(1, name);
			ResultSet result = ps.executeQuery();
			if (result.isBeforeFirst())
				retour = new ArrayList<>();
			while (result.next()) {
				Product p = new Product();
				p.setId(result.getInt("id"));
				p.setName(result.getString("nom"));
				p.setDescription(result.getString("description"));
				p.setPrice(result.getFloat("prix"));
				p.setUrlImage(result.getString("urlImage"));
				retour.add(p);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}

		return retour;

	}

	// retourner un produit
	public static Product getById(int id) {
		Product product = null;

		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryById);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			if (result.isBeforeFirst())
				if (result.next()) {
					product = new Product();
					product.setId(result.getInt("id"));
					product.setName(result.getString("nom"));
					product.setDescription(result.getString("description"));
					product.setPrice(result.getFloat("prix"));
					product.setUrlImage(result.getString("urlImage"));

				}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}

		return product;
	}

	public static boolean Insert(Product item) {
		boolean retour = false;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryInsert);
			ps.setString(1, item.getName());
			ps.setInt(2, item.getIdCategroy());
			// ps.setString(3, item.getRef());
			ps.setString(4, item.getDescription());
			ps.setFloat(5, item.getPrice());
			ps.setString(6, item.getUrlImage());
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

	// une methode qui utilise la meme query qui modifie tout les champs d'un
	// produit meme s'il y a une seule colonne à modifier
	public static boolean Update(Product item) {
		int nbrUpdate = 0;
		boolean retour = false;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryUpdate);
			ps.setString(1, item.getName());
			ps.setInt(2, item.getIdCategroy());
			ps.setString(3, item.getDescription());
			ps.setFloat(4, item.getPrice());
			ps.setString(5, item.getUrlImage());
			ps.setInt(6, item.getId());
			nbrUpdate = ps.executeUpdate();
			if (nbrUpdate > 0)
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
