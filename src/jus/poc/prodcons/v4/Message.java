package jus.poc.prodcons.v4;

public class Message {

  Producteur prod;
  String message;

  Message (Producteur p,String message) {
    this.prod =p;
    this.message = message;
  }

  public String toString(){
    return ("\n  @ Message : '" + message +"' consommé par le consommateur " + Thread.currentThread().getId() +" et créé par le producteur "+ prod.getId() + " @");
  }
}
