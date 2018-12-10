package jus.poc.prodcons.v1;

import java.util.ArrayList;

public class ProdConsBuffer implements IProdConsBuffer{
	
	int nbreMess;
	int sizeBuf;
	int prodTime;
	int consTime;
	ArrayList<Message> buffer;
	int consoCompte;
	
	ProdConsBuffer (int sizeB,int pT,int cT) {
		this.sizeBuf = sizeB;
		prodTime = pT;
		consTime = cT;
		buffer = new ArrayList<Message>(sizeB);
		consoCompte = 0;
		System.out.println(" *** ConsBuffer size "+sizeBuf + " ");
	}
	
	public synchronized void put(Message m) throws InterruptedException {
	    System.out.println(" + Producteur ID: " + m.prod.getId() + " put "+ m.message);
		  while(buffer.size() == sizeBuf ) {
		    try {
		      System.out.println(" ++ Prod en attente ! Buffer contient " + buffer.size() + " sur " + sizeBuf + " messages");
		      this.wait();
		    }catch(InterruptedException e) {}
		  }
		  /*try {
		    this.wait(1000);
		  }catch(InterruptedException e) {}*/
		  buffer.add(m);
		  System.out.println(" ++ Ajout ; buffer contient " + buffer.size() + " sur " + sizeBuf + " messages");
		  this.notifyAll();
	}

	public synchronized Message get() throws InterruptedException {
	    while(buffer.size() == 0) {
	       try {
	         System.out.println(" -- Conso en attente ! Consommés " + consoCompte);
	          this.wait();
	        }catch(InterruptedException e) {}
	    }
      //this.wait(1000);
	    consoCompte++;
      Message m = buffer.remove(0);
      System.out.println(" -- Consommés : " + consoCompte + " ; buffer contient " + buffer.size() + " sur " + sizeBuf + " messages");
      this.notifyAll();
      return m;
	}

	public int nmsg() {
		// TODO Auto-generated method stub
		return 0;
	}
}
