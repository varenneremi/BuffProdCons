package jus.poc.prodcons.v1;

public class Producteur extends Thread{
  int num;
	Message message;
	int nbreMess;
	private ProdConsBuffer buffer;
	
	Producteur (int numero,int nbM,ProdConsBuffer buffer) {
		this.num = numero;
	  nbreMess = nbM;//(int)(2.*((float)nbM) * Math.random());
		this.buffer = buffer;
		System.out.println(" * new Producteur ID: " + this.getId() + " nombre message à créé: "+ nbreMess);
		this.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(float i=0;i != nbreMess;i++) {
		  try {
        buffer.put(new Message(this,"numero "+i));
        this.yield();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
		}
		System.out.println(" * Producteur ID: " + this.getId() + " a fini sa tâche!");
	}
}
