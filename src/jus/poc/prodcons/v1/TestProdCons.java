package jus.poc.prodcons.v1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class TestProdCons {
		
  
Properties properties;
int nbP;
int nbC;
int BufSz;
int ProdTime;
int ConsTime;
int Mavg;
int nbMessACreeTotal;
int nbMessageAConsoTotal;

TestProdCons(){
  properties = new Properties();
}

private void start() {
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
  nbMessageAConsoTotal = 0;
  
  // plus propre à faire
  for(int i=0; i< (nbP + nbC);i++) {
    if((nbProd != nbP) && (Math.random() < 0.5F)) {
      Producteur p = new Producteur(ProdTime, Mavg, buffer);
      nbMessACreeTotal += p.nbreMess;
      nbProd += 1;
      p.start();
    }else if(nbConso != nbC){
      Consommateur c = new Consommateur(ConsTime,buffer,Mavg);
      nbMessageAConsoTotal += c.NbreMessCons;
      nbConso += 1;
      c.start();
    }
  }
  System.out.println(" *** RECAP : " + nbP + " producteurs ; "+nbC+" consommateurs ; "+nbMessACreeTotal+" messages à créer ; "+nbMessageAConsoTotal + " messages à consommer ; buffer de taille "+ BufSz );
 
}
	
	public static void main(String[] args){
	  TestProdCons test = new TestProdCons();
	  test.start();
	}
}
