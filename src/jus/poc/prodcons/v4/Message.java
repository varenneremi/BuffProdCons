package jus.poc.prodcons.v4;

public class Message {

	Producteur prod;
	Consommateur cons;
	String message;

	Message (Producteur p,String message) {
		this.prod =p;
		this.message = message;
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
