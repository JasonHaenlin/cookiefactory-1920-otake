package fr.unice.polytech.si4.otake.cookiefactory.product;

public class Beverage extends Product {

    Beverage(String name, ProductType type, int price){
        super(name, type);
        this.price = price;
    }

    public double computePrice(){
        return this.price;
    }

    public int retrieveSize(){
        return 1;
    }

    public int getSize() {
        return 1;
    }

    public int hashCode(){
        return 0;
    }

    public boolean equals(Object obj){
        return obj == this;
    }
}
