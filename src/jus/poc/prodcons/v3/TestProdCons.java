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
  int nbMessACreeTotal;
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
    nbMessACreeTotal = 0;

    for(int i=0; i< (nbP + nbC);i++) {
      if((nbProd != nbP) && (Math.random() < 0.5F)) {
        Producteur p = new Producteur(ProdTime, (int) (random.nextGaussian() + Mavg), buffer);
        producteurs.add(p);
        nbMessACreeTotal += p.nbreMess*5;
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
    System.out.println(" *** RECAP : " + nbProd + " producteurs ; "+nbConso+" consommateurs ; "+nbMessACreeTotal+" messages à créer ; "+ " buffer de taille "+ BufSz);

    producteurs.forEach(p -> {
      try {
        p.join();
      } catch (InterruptedException e) {}
    }); 

    //a corriger je pense
    while(buffer.consoCompte != nbMessACreeTotal) {
      this.yield();
      try {
        this.sleep(1000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      System.out.println(" nb message : "+buffer.nbreMess + " consoCompte : " + buffer.consoCompte + " NbTotale : " + nbMessACreeTotal);
    }

    System.out.println(" *** TERMINATE ***");
    System.out.println("temps de traitement total : " + (System.currentTimeMillis() - time_start));
  }

  public static void main(String[] args){

    TestProdCons test = new TestProdCons();
    test.start();

    System.out.println(" *** TERMINATE TOTALE***");
    System.out.flush();
    
  }
}