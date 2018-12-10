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
  ProdConsBuffer buffer = new ProdConsBuffer(BufSz,ProdTime,ConsTime);
  new Producteur(1, Mavg, buffer);
  new Consommateur(buffer,Mavg);
  new Consommateur(buffer,Mavg);
  new Producteur(2, Mavg, buffer);
  new Consommateur(buffer,Mavg);
  new Producteur(3, Mavg, buffer);
  new Producteur(4, Mavg, buffer);
}
	
	public static void main(String[] args){
	  TestProdCons test = new TestProdCons();
	  test.start();
	}
}
