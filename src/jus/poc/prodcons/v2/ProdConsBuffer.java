package jus.poc.prodcons.v2;

import java.util.concurrent.Semaphore;

public class ProdConsBuffer implements IProdConsBuffer{
        
  int prodTime;         //temps de production d'un message   
  int consTime;         //temps de consommation d'un message
  Message[] buffer;     //buffer de message
  int tete;             //premier élément contenu dans le buffer
  int queue;            //dernier élément contenu dans le buffer
  int nbmessage;        //nombre de message dans le buffer
  int consoCompte;      //nombre de message consommé
  Semaphore sema_prod ; //semaphore producteur
  Semaphore sema_cons ; //semaphore consommateur
 
  ProdConsBuffer
  (int sizeB) {
    buffer = new Message[sizeB];
    tete = 0;
    queue = -1;
    nbmessage = 0;
    consoCompte = 0;
    sema_prod = new Semaphore(sizeB);
    sema_cons = new Semaphore(0);
    System.out.println("  ***** Le buffer est de taille "+ sizeB + " ***** ");
  }

  public void put(Message m) throws InterruptedException {
    sema_prod.acquire();
    add(m);
    System.out.println("\n  +++ Producteur " + Thread.currentThread().getId() + " a ajouté le message '" + m.message +"'. Le buffer contient " + nmsg() + " message(s) sur " + buffer.length);
    sema_cons.release();

  }

  public Message get() throws InterruptedException {
    sema_cons.acquire();
    Message m;
    m = remove();
    System.out.println("\n  --- Consommateur " + Thread.currentThread().getId() +" a retiré le message '"+m.message +"' ajouté par le producteur " + m.prod.getId() +". Nombre de message consommé : " + (consoCompte+1) 
        + ".\n      Le buffer contient " + nmsg() + " messsage(s) sur " + buffer.length);
    sema_prod.release();
    return m;
  }

  @Override 
  public int nmsg() {   //nombre de message contenu dans le buffer
    return nbmessage;
  }

  //ajout d'un message dans le buffer
  private void add(Message m) {
    queue = (queue +1) % buffer.length;
    buffer[queue] = m;
    nbmessage++;
  }

  //on enlève un message du buffer
  private Message remove() {
    Message m = buffer[tete];
    buffer[tete] =null;
    tete = (tete +1) % buffer.length;
    nbmessage--;
    return m;
  }
}
