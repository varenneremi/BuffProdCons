package jus.poc.prodcons.v1;

public class Consommateur extends Thread{
  int delay;
  public ProdConsBuffer buffer;

  Consommateur(int delay, ProdConsBuffer buff) {
    this.delay = delay;
    this.buffer = buff;
  }

  public void run() {
    System.out.println(" * lancement Consommateur ID: " + this.getId());
    while(true) {
      System.out.println(" - Consommateur ID: " + this.getId() + " get");
      try {
        Message m = buffer.get();
        m.setConsommateur(this);
        System.out.println(m.toString());
        this.sleep(delay);
        buffer.consoCompte++;
      } catch (InterruptedException e) {}
    }
  }
}
