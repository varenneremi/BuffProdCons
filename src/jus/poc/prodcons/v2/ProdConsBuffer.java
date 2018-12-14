package jus.poc.prodcons.v2;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class ProdConsBuffer implements IProdConsBuffer{

  int nbreMess;
  int sizeBuf;
  int prodTime;
  int consTime;
  ArrayList<Message> buffer;
  int consoCompte;
  Semaphore sema_prod ;
  Semaphore sema_cons ;

  ProdConsBuffer
  (int sizeB) {
    this.sizeBuf = sizeB;
    buffer = new ArrayList<Message>(sizeB);
    consoCompte = 0;
    sema_prod = new Semaphore(sizeB);
    sema_cons = new Semaphore(0);
    System.out.println(" *** ConsBuffer size "+sizeBuf + " *** ");
  }

  public void put(Message m) throws InterruptedException {
    sema_prod.acquire();
    System.out.println(" + Producteur ID: " + m.prod.getId() + " put "+ m.message);
    buffer.add(m);
    System.out.println(" ++ Ajout ; buffer contient " + buffer.size() + " sur " + sizeBuf + " messages");
    sema_cons.release();

  }

  public Message get() throws InterruptedException {
    sema_cons.acquire();
    Message m;
    m = buffer.remove(0);
    System.out.println(" -- Nombre de message consommé : " + (consoCompte+1) + " ; buffer contient " + buffer.size() + " sur " + sizeBuf + " messages");
    sema_prod.release();
    return m;
  }

  @Override
  public int nmsg() {
    // TODO Auto-generated method stub
    return buffer.size();
  }
}
