package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entities.Sales;
import service.ConnexionBDD;

public class SalesManager {

	// les attributs static private String query
	private static String queryAll = "select V.date_horaire as date,U.id as idUser,U.nom+ ' 'U.prenom as nom,P.id as idProduit,"
			+ "V.quantite as quantite,E.nom as nomEtab from ventes V inner join produit P on P.id=V.produit.id inner join utilisateur U on U.id=V.utilisateur_id"
			+ "inner join etablissement E on U.etablissement_id=E.id";

	public static ArrayList<Sales> getAll() {
		ArrayList<Sales> sales = null;

		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryAll);
			ResultSet rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				sales = new ArrayList<>();
				while (rs.next()) {
					Sales s = new Sales();
					s.setDate(rs.getDate("date"));
					s.setUserId(rs.getInt("idUser"));
					s.setUsername(rs.getString("nom"));
					s.setProductId(rs.getInt("idProduit"));
					s.setQuantity(rs.getInt("quantite"));
					s.setEstablishmentName(rs.getString("nomEtab"));
					sales.add(s);
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sales;
	}

	// on passe un tableau qui prend une valeur ou plus car on veut permettre a
	// l'admin d'afficher un ou plusieur etablissements
	// on peut ajouter les orderBy Date ou produit cette methode affiche toutes
	// les ventes de chaque jour
	public static ArrayList<Sales> getByIdEtablishement(int[] idEtablishement, Date dateFirst, Date dateEnd) {

		return null;
	}

	// une methode qui affiche un groupement de vente pour un produit donnée
	// durant une date donnée
	public static ArrayList<Sales> getStatByEtablishement(int[] idEtablishement, Date dateFirst, Date dateEnd) {

		return null;
	}

	// l'insertion des ventes des vendeur si le produit n'est pas existant
	// pendant l'heure qui precede
	public static boolean Insert(int idProduct, Sales sales) {
		return false;
	}

	// update d'une vente existante si le produit a été entrer par un
	// utilisateur donnée pendant lheure qui précède

	public static boolean Update(int idVente, Sales sales) {
		return false;
	}

	// verifie si un produit donnée a été ajouter a dans la derniere heure s'il
	// existe on appelle update si -1 on appelle insert
	public static int isAddedInLastHour(int idProduct, int idUser) {
		int idVente = -1;
		return idVente;
	}

}
