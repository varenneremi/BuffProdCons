package jus.poc.prodcons.v1;

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

    while(true) {     //boucle de consommation
      try {
        Message m = buffer.get();
        System.out.println(m.toString()); //traitement du message
        buffer.consoCompte++;             //on incremente le compteur de message consommé
        this.sleep(delay);                //délai de traitement

      } catch (InterruptedException e) {}
    }
  }
}
