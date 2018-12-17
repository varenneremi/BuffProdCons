package jus.poc.prodcons.v4;

public class Consommateur extends Thread{
  int delay;
  public ProdConsBuffer buffer;

  Consommateur(int delay, ProdConsBuffer buff) {
    this.delay = delay;
    this.buffer = buff;
  }

  public void run() {
    System.out.println("\n ------------------------------------------------------------ \n"
        + "  ----- Lancement du consommateur ayant comme ID : " + this.getId() + " -----"
        + "\n ------------------------------------------------------------ ");
    while(true) {
      try {
        buffer.sema_autorisation.acquire();
        for(int i=0;i != 20;i++) {
          Message m = buffer.get();
          System.out.println(m.toString());
          buffer.consoCompte++;
        }
        buffer.sema_autorisation.release();
        this.sleep(delay);
      } catch (InterruptedException e) {}
    }
  }
}
