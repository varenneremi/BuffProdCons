package jus.poc.prodcons.v3;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Random;

public class TestProdCons extends Thread{
  Properties properties;
  int nbP;
  int nbC;
  int BufSz;
  int ProdTime;
  int ConsTime;
  int Mavg;
  ArrayList<Producteur> producteurs;
  Random random;
  long time_start;

  TestProdCons(){
    time_start = System.currentTimeMillis();
    properties = new Properties();
    producteurs = new ArrayList<Producteur>();
    random = new Random();
  }

  public void start() {
    try {
      properties.loadFromXML(TestProdCons.class.getClassLoader().getResourceAsStream("jus/poc/prodcons/options.xml"));
      nbP = Integer.parseInt(properties.getProperty("nbP"));
      nbC = Integer.parseInt(properties.getProperty("nbC"));
      BufSz = Integer.parseInt(properties.getProperty("BufSz"));
      ProdTime = Integer.parseInt(properties.getProperty("ProdTime"));
      ConsTime = Integer.parseInt(properties.getProperty("ConsTime"));
      Mavg = Integer.parseInt(properties.getProperty("Mavg"));
    }catch(InvalidPropertiesFormatException e) {
      e.printStackTrace(); 
    }catch(IOException e) {
      e.printStackTrace();
    }
    ProdConsBuffer buffer = new ProdConsBuffer(BufSz);

    int nbProd = 0;
    int nbConso = 0;

    for(int i=0; i< (nbP + nbC);i++) {
      if((nbProd != nbP) && (Math.random() < 0.5F)) {
        Producteur p = new Producteur(ProdTime, (int) (random.nextGaussian() + Mavg), buffer,nbC);
        producteurs.add(p);
        nbProd += 1;
        p.setDaemon(true);
        p.start();
      }else if(nbConso != nbC){
        Consommateur c = new Consommateur(ConsTime,buffer);
        nbConso += 1;
        c.setDaemon(true);
        c.start();
      }
    }
    System.out.println("\n ************************************************************ \n"
        + "  ***** RECAPITULATIF : " + nbProd + " producteurs ; "+nbConso+" consommateurs ; buffer de taille "+ BufSz + " *****"
        + "\n ************************************************************ \n");
    
    producteurs.forEach(p -> {
      try {
        p.join();
      } catch (InterruptedException e) {}
    }); 

    while(buffer.consoCompte != buffer.nbMessCreeTotal) {
      this.yield();
    }

    System.out.println("\n ============================================================ \n"
        + "  ===== Le programme a fini en " + (System.currentTimeMillis() - time_start) +" millisecondes ====="
        + "\n ============================================================ \n");
    
    if(buffer.nmsg() != 0) {
      System.out.println("  !!!!! Il reste " + buffer.nmsg() + " message(s) dans le buffer !!!!! ");
    }
  }

  public static void main(String[] args){

    TestProdCons test = new TestProdCons();
    test.start();
    
  }
}
