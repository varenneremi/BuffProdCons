package jus.poc.prodcons.v3;

import java.util.Random;

public class Producteur extends Thread{
  int delay;
  Message message;
  int nbreMess;
  private ProdConsBuffer buffer;
  int nbexemplaire;
  
  Producteur (int delay,int nbM,ProdConsBuffer buffer, int nbexemplaire) {
    this.delay = delay;
    nbreMess = nbM;
    this.buffer = buffer;
    this.nbexemplaire = nbexemplaire;
  }

  @Override
  public void run() {
    System.out.println("\n ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n"
        + "  +++++ Lancement du producteur ayant comme ID : " + this.getId() + " et qui doit produire "+ nbreMess + " message(s) +++++"
        + "\n ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

    for(int i=0;i != nbreMess;i++) {
      try {
        buffer.put(new Message(this,"numero "+i,nbexemplaire));
        this.sleep(delay);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("\n  !!! Producteur " + this.getId() + " a fini sa tâche!");
  }
}
