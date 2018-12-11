package jus.poc.prodcons.v1;

public class Consommateur extends Thread{
	int delay;
	public ProdConsBuffer buffer;
	public int NbreMessCons;
	
	Consommateur(int delay, ProdConsBuffer buff,int nM) {
	  this.delay = delay;
		this.buffer = buff;
		NbreMessCons = (int)(((float) 2*nM)*Math.random());
	}
	
	public void run() {
	   System.out.println(" * lancement Consommateur ID: " + this.getId() + " ; nombre message à consommée " + NbreMessCons);
		// TODO Auto-generated method stub	
	 for(int i =0; i != NbreMessCons;i++) {
	   System.out.println(" - Consommateur ID: " + this.getId() + " get numéro " + i);
	    try {
        Message m = buffer.get();
        m.setConsommateur(this);
        System.out.println(m.toString());
        this.sleep(delay);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
	  }
	 System.out.println(" ! Consommateur ID: " + this.getId() + " à fini sa tâche!");
	}
	
}
