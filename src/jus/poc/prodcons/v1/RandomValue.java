package jus.poc.prodcons.v1;

public class RandomValue {

	int nombre; 
	
	RandomValue (int nbreMax) {
		this.nombre = (int)Math.random()*nbreMax;
	}
	
}
