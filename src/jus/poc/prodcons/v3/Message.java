package jus.poc.prodcons.v3;

public class Message {

	Producteur prod;
	String message;
	int nbExemplaire;
	
	Message (Producteur p,String message,int nbconso) {
		this.prod =p;
		this.message = message;
		this.nbExemplaire =  1 + (int)(Math.random() * (nbconso-1));
	}
	
	public String toString(){
		return ("\n  @ Message : '" + message +"' consommé par le consommateur " + Thread.currentThread().getId() +" et créé par le producteur "+ prod.getId() + " et dont il reste " + nbExemplaire +" exemplaire(s) @");
	}
}
