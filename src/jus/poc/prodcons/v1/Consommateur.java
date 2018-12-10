package jus.poc.prodcons.v1;

public class Consommateur extends Thread{
	
	private ProdConsBuffer buffer;
	private int NbreMessCons;
	
	Consommateur(ProdConsBuffer buff,int nM) {
		this.buffer = buff;
		NbreMessCons = nM;    //faire de façon random
		System.out.println(" * new Consommateur ID: " + this.getId() + " nombre message à consommée " + NbreMessCons);
		
		this.start();
	}
	
	public void run() {
		// TODO Auto-generated method stub	
	 for(int i =0; i != NbreMessCons;i++) {
	   System.out.println(" - Consommateur ID: " + this.getId() + " get numéro " + i);
	    try {
        Message m = buffer.get();
        m.setConsommateur(this);
        System.out.println(m.toString());
        this.yield();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
	  }
	 System.out.println(" * Consommateur ID: " + this.getId() + " à fini sa tâche!");
	}
	
}
