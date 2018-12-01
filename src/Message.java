
public class Message {

	Producteur prod;
	String message;
	int idThread;
	
	Message (int id, Producteur p) {
		this.idThread = id;
		this.prod =p;
	}
	
	public String toString(){
		return ("Message : '" + message +"' numero : " + this.getID() +" créer par "+ prod);
	}
		
	public int getID() {
		return this.idThread;
	}
	
	public void setID(int compteur) {
		this.idThread = compteur;
	}
	
	public Producteur getNumProducteur(){
		return prod;
	}
}
