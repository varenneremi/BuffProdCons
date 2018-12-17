package jus.poc.prodcons.v3;

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
        Message m = buffer.get();
        System.out.println(m.toString());
        buffer.consoCompte++;
        this.sleep(delay);
      } catch (InterruptedException e) {}
    }
  }
}
