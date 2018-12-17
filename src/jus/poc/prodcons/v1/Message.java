package jus.poc.prodcons.v1;

public class Message {
	Producteur prod;   //producteur du message
	String message;    //message en lui même

	Message (Producteur p,String message) {
		this.prod =p;
		this.message = message;
	}
	
	//traitement du message
	public String toString(){
		return ("\n  @ Message : '" + message +"' consommé par le consommateur " + Thread.currentThread().getId() +" et créé par le producteur "+ prod.getId() + " @");
	}
}
