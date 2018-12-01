
public class ProdConsBuffer implements IProdConsBuffer{
	
	int nbreMess;
	
	ProdConsBuffer (int nbre) {
		this.nbreMess = nbre;
	}
	
	public void put(Message m) throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	public Message get() throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	public int nmsg() {
		// TODO Auto-generated method stub
		return 0;
	}

}
