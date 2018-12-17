package jus.poc.prodcons.v1;

public class ProdConsBuffer implements IProdConsBuffer{

  int prodTime;       //temps de production d'un message
  int consTime;       //temps de consommation d'un message
  Message[] buffer;   //buffer de message
  int tete;           //premier élément contenu dans le buffer
  int queue;          //dernier élément contenu dans le buffer
  int nbmessage;      //nombre de message dans le buffer
  int consoCompte;    //nombre de message consommé

  ProdConsBuffer (int sizeB) {
    buffer = new Message[sizeB];
    tete = 0;
    queue = -1;
    consoCompte = 0;
    nbmessage = 0;
    System.out.println("  ***** Le buffer est de taille "+ sizeB + " ***** ");
  }

  public synchronized void put(Message m) throws InterruptedException {
    while(nmsg() == buffer.length ) {   //garde
      System.out.println("\n  ### Producteur "+ Thread.currentThread().getId()+" en attente ! Le buffer contient " + nmsg() + "message(s) sur " + buffer.length);
      this.wait();
    }
    add(m);       //action
    System.out.println("\n  +++ Producteur " + Thread.currentThread().getId() + " a ajouté le message '" + m.message +"'. Le buffer contient " + nmsg() + " message(s) sur " + buffer.length);
    this.notifyAll();
  }

  public synchronized Message get() throws InterruptedException {
    while(nmsg() == 0) {          //garde
      System.out.println("\n  ### Consommateur "+ Thread.currentThread().getId() +" en attente ! Nombre de message consommé : " + consoCompte);
      this.wait();
    }
    Message m = remove();         //action
    System.out.println("\n  --- Consommateur " + Thread.currentThread().getId() +" a retiré le message '"+m.message +"' ajouté par le producteur " + m.prod.getId() +". Nombre de message consommé : " + (consoCompte+1) 
        + ".\n      Le buffer contient " + nmsg() + " messsage(s) sur " + buffer.length);
    this.notifyAll();
    return m;
  }

  @Override
  public int nmsg() {     //nombre de message contenu dans le buffer
    return nbmessage;
  }

  //ajout d'un message dans le buffer
  public void add(Message m) {
    queue = (queue +1) % buffer.length;
    buffer[queue] = m;
    nbmessage++;
  }

  //on enlève un message du buffer
  public Message remove() {
    Message m = buffer[tete];
    buffer[tete] =null;
    tete = (tete +1) % buffer.length;
    nbmessage--;
    return m;
  }
}
