package jus.poc.prodcons.v1;

public class Producteur extends Thread{
  int delay;
	Message message;
	int nbreMess;
	private ProdConsBuffer buffer;
	
	Producteur (int delay,int nbM,ProdConsBuffer buffer) {
	  this.delay = delay;
	  nbreMess = (int)(((float) 2*nbM)*Math.random());
		this.buffer = buffer;
		
	}
	
	@Override
	public void run() {
	  System.out.println(" * lancement Producteur ID: " + this.getId() + " nombre message à créé: "+ nbreMess);
		// TODO Auto-generated method stub
		for(float i=0;i != nbreMess;i++) {
		  try {
        buffer.put(new Message(this,"numero "+i));
        this.sleep(delay);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
		}
		System.out.println(" * Producteur ID: " + this.getId() + " a fini sa tâche!");
	}
}
