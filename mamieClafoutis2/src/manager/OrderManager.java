package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entities.Order;
import service.ConnexionBDD;

public class OrderManager {
	// les attribut Private static String Query
	private static String queryAll = "select * from commande";
	private static String queryByIntervalDate = "select * from commande where date between ? and ? and valide=true";
	private static String queryCostumerByIntervalDate = "select * from commande c inner join utilisateur u on u.id=c.utilisateur_id inner join etablissement e on u.etablissement_id=e.id  where c.date between ? and ?  and e.id =?";
	private static String queryByIdUserIntoDate = "select * from commande where date between ? and ?  and utilisateur_id=?";
	private static String queryFirstDate = "select min(date) from commande";
	private static String queryLastDate = "select top max(date) from commande";
	private static String UpdateAdminValidate = "Update commande set valide=? where id=?";
	private static String Insert = "insert into commande (utilisateur_id) values (?)";
	

	public static ArrayList<Order> getAll() {
		ArrayList<Order> order = null;

		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryAll);
			ResultSet result = ps.executeQuery();
			if (result.isBeforeFirst())
				order = new ArrayList<>();
			while (result.next()) {
				Order o = new Order();
				o.setId(result.getInt("id"));
				o.setUserID(result.getInt("utilisateur_id"));
				o.setDate(result.getDate("date"));
				o.setValide(result.getBoolean("valide"));
				order.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}
		return order;
	}

	// l'atelier peut voir les commandes valider par l'admin pour les preparer
	// au meme tps la commande doit etre invalide par l'atelier
	// si les date sont null on prendra en parametre datefirst et date end
	public static ArrayList<Order> getValidateByAdminIntoDate(Date dateStart, Date dateEnd) {
		ArrayList<Order> order = null;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(queryByIntervalDate);
			ps.setTimestamp(1, (java.sql.Timestamp) dateStart);
			ps.setTimestamp(2, (java.sql.Timestamp) dateEnd);
			ResultSet result = ps.executeQuery();
			if (result.isBeforeFirst())
				order = new ArrayList<>();
			while (result.next()) {
				Order o = new Order();
				o.setId(result.getInt("id"));
				o.setUserID(result.getInt("utilisateur_id"));
				o.setDate(result.getDate("date"));
				o.setValide(result.getBoolean("valide"));
				order.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}
		return order;
	}

	// retourner toutes les commandes valid�e par une succursale pour un temps
	// donne
	// une boucle sur laquery pour chaque valeur du tableau id_Etab
	// les costumers sera une seule valeur l'id de leur etablissement et ladmin
	// peut selectionner tout les etablissement ou choisir quelque uns ou
	// choisir un type
	// la methode appelante traduit le type en tableau int et le passe en
	// parametre

	public static ArrayList<Order> getByCostumersIntoDate(int[] id_etablishment, Date dateStart, Date dateEnd) {
		ArrayList<Order> order = null;
		try {
			for (int i : id_etablishment) {
				PreparedStatement ps = ConnexionBDD.getPs(queryCostumerByIntervalDate);
				ps.setTimestamp(1, (java.sql.Timestamp) dateStart);
				ps.setTimestamp(2, (java.sql.Timestamp) dateEnd);
				ps.setInt(3, i);
				ResultSet result = ps.executeQuery();
				if (result.isBeforeFirst())
					if (order == null)
						order = new ArrayList<>();
				while (result.next()) {
					Order o = new Order();
					o.setId(result.getInt("id"));
					o.setUserID(result.getInt("utilisateur_id"));
					o.setDate(result.getDate("date"));
					o.setValide(result.getBoolean("valide"));
					order.add(o);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}
		return order;
	}

	// retourn�e toutes les commandes preparer par l'atelier pour valider la
	// livraison
	// on peut mettre en parametre les date our filtrer ou directement afficher
	// que les commande de la semaine qui doivent etre preparer
	// si en java on a droit a la valeur par defaut on peut faire deux query
	// dans cette meme methode
	// commande a penser prochainement
	public static ArrayList<Order> getValidateByManufactureIntoDate(Date dateStart, Date dateEnd) {
		return null;
	}

	// retourne les commande d'un user donn�e pour un interval de temps donn�e
	// pour les costumers puissent voir leur commandes en cours pour pouvoir
	// modifier
	public static ArrayList<Order> getByIdUser(int user, Date dateStart, Date dateEnd) {
		ArrayList<Order> order = null;
		try {

			PreparedStatement ps = ConnexionBDD.getPs(queryByIdUserIntoDate);
			ps.setTimestamp(1, (java.sql.Timestamp) dateStart);
			ps.setTimestamp(2, (java.sql.Timestamp) dateEnd);
			ps.setInt(3, user);
			ResultSet result = ps.executeQuery();
			if (result.isBeforeFirst())
				order = new ArrayList<>();
			while (result.next()) {
				Order o = new Order();
				o.setId(result.getInt("id"));
				o.setUserID(result.getInt("utilisateur_id"));
				o.setDate(result.getDate("date"));
				o.setValide(result.getBoolean("valide"));
				order.add(o);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}
		return order;
	}

	// filtre date

	public static Date getFirstDate() {
		Date firstdate = null;
		try {

			PreparedStatement ps = ConnexionBDD.getPs(queryFirstDate);

			ResultSet result = ps.executeQuery();
			if (result.isBeforeFirst())

				if (result.next()) {
					firstdate = result.getDate("date");
				}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}
		return firstdate;
	}

	public static Date getLastDate() {
		Date lastDate = null;
		try {

			PreparedStatement ps = ConnexionBDD.getPs(queryLastDate);

			ResultSet result = ps.executeQuery();
			if (result.isBeforeFirst())

				if (result.next()) {
					lastDate = result.getDate("date");
				}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}
		return lastDate;

	}

	// ladmin valide la commande pour qu'elle soit visible a latelier pour
	// preparation
	public static boolean UpdateValidationByAdmin(int idOrder, boolean isvalide) {
		boolean retour = false;

		try {
			PreparedStatement ps = ConnexionBDD.getPs(UpdateAdminValidate);
			ps.setBoolean(1, isvalide);
			ps.setInt(2, idOrder);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionBDD.closeConnection();
		}
		return retour;
	}

	// prochainement on peut imaginer que l'atelier valide la preparation pour
	// livraison
	public static boolean UpdateValidationByManifacture(int idOrder, boolean isvalide) {
		return false;
	}

	// le costumer (franchise corporate ou autre) peut modifier sa commande si
	// elle n'est pas encore valider par l'admin
	// prochainement
	public static boolean UpdateByCostumers() {
		return false;
	}

	// insertion des commandes par les clients
	// retoune la valeur de l'id genere a linsertion
	public static int Insert(int idUser) {
		int retour = -1;
		try {
			PreparedStatement ps = ConnexionBDD.getPs(Insert);
			ps.setInt(1, idUser);
			if (ps.executeUpdate() > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next())
					retour = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnexionBDD.closeConnection();
		}
		return retour;
	}

	// methode qui retourne le dernier id de la commande inserer un id sera
	// utilise pour la table ligne de commande
	// la methode getGeneratedKeys a l'insertion retour l'id gerenre pour la
	// ligne inserer
	public static int getLastIdByUser(int idUser) {
		return 0;
	}

}
