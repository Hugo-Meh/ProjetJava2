package manager;

import java.util.ArrayList;
import java.util.Date;

import entities.Order;

public class OrderManager {

	// les attribut Private static String Query

	public static ArrayList<Order> getAll() {
		return null;
	}

	// l'atelier peut voir les commandes valider par l'admin pour les preparer
	// au meme tps la commande doit etre invalide par l'atelier
	// si les date sont null on prendra en parametre datefirst et date end
	public static ArrayList<Order> getValidateByAdminIntoDate(Date dateStart, Date dateEnd) {
		return null;
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
		return null;
	}

	// retourn�e toutes les commandes preparer par l'atelier pour valider la
	// livraison
	// on peut mettre en parametre les date our filtrer ou directement afficher
	// que les commande de la semaine qui doivent etre preparer
	// si en java on a droit a la valeur par defaut on peut faire deux query
	// dans cette meme methode

	public static ArrayList<Order> getValidateByManufactureIntoDate(Date dateStart, Date dateEnd) {
		return null;
	}

	// retourne les commande d'un user donn�e pour un interval de temps donn�e
	// pour les costumers puissent voir leur commandes en cours pour pouvoir
	// modifier
	public static ArrayList<Order> getByIdUser(int User, Date dateStart, Date dateEnd) {
		return null;
	}

	// filtre date

	public static Date getFirstDate() {
		return null;
	}

	public static Date getLastDate() {
		return null;
	}

	// ladmin valide la commande pour qu'elle soit visible a latelier pour
	// preparation
	public static boolean UpdateValidationByAdmin(int idOrder, boolean isvalide) {
		return false;
	}

	public static boolean UpdateValidationByManifacture(int idOrder, boolean isvalide) {
		return false;
	}

	// le costumer (franchise corporate ou autre) peut modifier sa commande si
	// elle n'est pas encore valider par l'admin
	public static boolean UpdateByCostumers() {
		return false;
	}

	// insertion des commandes par les clients
	
	public static boolean Insert(int idUser){
		return false;
	}
	
	// methode qui retourne le dernier id de la commande inserer un id sera utilise pour la table ligne de commande
	
	public static int getLastIdByUser(int idUser){
		return 0;
	}
	
}
