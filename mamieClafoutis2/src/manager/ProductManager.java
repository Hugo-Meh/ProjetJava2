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
	private static String queryByName = "select * from produit where nom like \"%?%\" ";
	private static String queryById = "select * from produit where id=?";
	private static String queryInsert = "insert into produit ('nom','categorie_id','reference','description','prix','url_Image','estvisible','poid','unite') values(?,?,?,?,?,?,?,?,?)";
	private static String queryUpdate = "update produit set nom=? ,categorie_id=?, description=?,prix=?, url_Image=?,estvisible=? where id=?";
	private static String queryByIdOrder = "select P.id as id, P.nom as nom,P.categorie_id as categorie_id,P.description as description,P.prix as prix, "
			+ "P.url_image as url_image,P.reference as reference,LC.quantite as quantite, from produit as P inner join ligne_de_commande as LC on P.id=LC.produit_idproduit"
			+ "inner join commande as C on LC.commande_id=C.id where C.id=?";

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
				p.setUrlImage(result.getString("url_Image"));
				p.setVisible(result.getBoolean("estvisible"));
				p.setRef(result.getString("reference"));
				p.setIdCategroy(result.getInt("categorie_id"));
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
				p.setUrlImage(result.getString("url_Image"));
				p.setVisible(result.getBoolean("estvisible"));
				p.setRef(result.getString("reference"));
				p.setIdCategroy(result.getInt("categorie_id"));

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
				p.setUrlImage(result.getString("url_Image"));
				p.setVisible(result.getBoolean("estvisible"));
				p.setRef(result.getString("reference"));
				p.setIdCategroy(result.getInt("categorie_id"));
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
					product.setUrlImage(result.getString("url_Image"));
					product.setVisible(result.getBoolean("estvisible"));
					product.setRef(result.getString("reference"));
					product.setIdCategroy(result.getInt("categorie_id"));

				}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}

		return product;
	}

	public static int Insert(Product item) {
		int retour = -1;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryInsert);
			ps.setString(1, item.getName());
			ps.setInt(2, item.getIdCategroy());
			ps.setString(3, (item.getRef() != null ? item.getRef() : ""));
			ps.setString(4, (item.getDescription() != null ? item.getDescription() : ""));
			ps.setFloat(5, (item.getPrice() >= 0 ? item.getPrice() : 0));
			ps.setString(6, (item.getUrlImage() != null ? item.getUrlImage() : ""));
			ps.setBoolean(7, item.isVisible());
			if (ps.executeUpdate() > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next())
					retour = rs.getInt(1);
			}
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
			ps.setBoolean(6, item.isVisible());
			ps.setInt(7, item.getId());
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

	public static boolean delete(int id) {
		boolean retour = false;
		return retour;
	}

	public static ArrayList<Product> getByIdOrder(int idOrder) {
		ArrayList<Product> products = null;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryByIdOrder);
			ps.setInt(1, idOrder);
			ResultSet rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				products = new ArrayList<>();
				while (rs.next()) {
					Product p = new Product();
					p.setId(rs.getInt("id"));
					p.setName(rs.getString("nom"));
					p.setIdCategroy(rs.getInt("categorie_id"));
					p.setDescription(rs.getString("description"));
					p.setPrice(rs.getFloat("prix"));
					p.setUrlImage(rs.getString("url_image"));
					p.setRef(rs.getString("reference"));
					p.setQuantity(rs.getInt("quantite"));
					products.add(p);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}

		return products;
	}
}
