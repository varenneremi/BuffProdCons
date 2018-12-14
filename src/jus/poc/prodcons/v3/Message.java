package jus.poc.prodcons.v3;

public class Message {

	Producteur prod;
	Consommateur cons;
	String message;
	int nbExemplaire;
	
	Message (Producteur p,String message, int nbExemplaire) {
		this.prod =p;
		this.message = message;
		this.nbExemplaire = nbExemplaire;
	}
	
	public String toString(){
		return (" --- Message : '" + message +"' consommé par Consommateur " + this.cons.getId() +" ; créé par Producteur "+ prod.getId());
	}
	
	public Producteur getNumProducteur(){
		return prod;
	}
	
	public void setConsommateur(Consommateur cons) {
	  this.cons = cons;
	}
}
