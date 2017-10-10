package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import entities.Sales;
import service.ConnexionBDD;

public class SalesManager {

	// les attributs static private String query
	private static String queryAll = "select V.date_horaire as date,U.id as idUser,CONCAT(U.nom,\"  \",U.prenom) as nom,P.id as idProduit,"
			+ "V.quantite as quantite,E.name as nomEtab from ventes V inner join produit P on P.id=V.produit_id inner join utilisateur U on U.id=V.utilisateur_id"
			+ "inner join etablissement E on U.etablissement_id=E.id";
	private static String queryByIdEtab = "select V.date_horaire as date,U.id as idUser,CONCAT(U.nom,\"  \",U.prenom)  as nom,P.id as idProduit,"
			+ "V.quantite as quantite,E.name as nomEtab from ventes V inner join produit P on P.id=V.produit_id inner join utilisateur U on U.id=V.utilisateur_id"
			+ "inner join etablissement E on U.etablissement_id=E.id where E.id=? and V.date_horaire between ? and ?";

	private static String queryInsert = "insert into ventes ('utilisateur_id','quantite','produit_id') values(?,?,?)";
	private static String queryTest = "select * from ventes where date_horaire between DATE_SUB(NOW(),INTERVAL 1 HOUR) and NOW() and produit_id=? and utilisateur_id=?"
			+ "and produit_id=? and utilisateur_id=?";
	private static String queryUpdate = " update ventes set quantite=quantite + ? where date_horaire=?";

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
		} finally {
			ConnexionBDD.closeConnection();
		}
		return sales;
	}

	// on passe un tableau qui prend une valeur ou plus car on veut permettre a
	// l'admin d'afficher un ou plusieur etablissements
	// on peut ajouter les orderBy Date ou produit cette methode affiche toutes
	// les ventes de chaque jour
	public static ArrayList<Sales> getByIdEtablishement(int[] idEtablishement, Date dateFirst, Date dateEnd) {
		ArrayList<Sales> sales = null;

		try {
			for (int i = 0; i < idEtablishement.length; i++) {
				PreparedStatement ps = ConnexionBDD.getPs(queryByIdEtab);
				ps.setInt(1, idEtablishement[i]);
				ps.setDate(2, (java.sql.Date) dateFirst);
				ps.setDate(3, (java.sql.Date) dateEnd);
				ResultSet rs = ps.executeQuery();
				if (rs.isBeforeFirst()) {
					if (sales == null)
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
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}
		return sales;
	}

	// une methode qui affiche un groupement de vente pour un produit donn�e
	// durant une date donn�e
	// a voir apres j'ai oublie a quoi sert :)
	public static ArrayList<Sales> getStatByEtablishement(int[] idEtablishement, Date dateFirst, Date dateEnd) {

		return null;
	}

	// l'insertion des ventes des vendeur si le produit n'est pas existant
	// pendant l'heure qui precede
	public static boolean Insert(Sales sales) {
		boolean retour = false;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryInsert);
			ps.setInt(2, sales.getUserId());
			ps.setInt(3, sales.getQuantity());
			ps.setInt(4, sales.getProductId());
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

	// update d'une vente existante si le produit a �t� entrer par un
	// utilisateur donn�e pendant lheure qui pr�c�de

	public static boolean Update(Date date, Sales sales) {
		boolean retour = false;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryUpdate);
			ps.setInt(1, sales.getQuantity());
			ps.setTimestamp(2, (Timestamp) date);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}
		return retour;
	}

	// verifie si un produit donn�e a �t� ajouter a dans la derniere heure s'il
	// existe on appelle update si -1 on appelle insert
	public static Date isAddedInLastHour(int idProduct, int idUser) {
		Date date = null;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryTest);
			ps.setInt(1, idProduct);
			ps.setInt(1, idUser);
			ResultSet rs = ps.executeQuery();
			if (rs.isBeforeFirst() && rs.next())
				date = rs.getDate("date_horaire");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}
		return date;
	}
}
