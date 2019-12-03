package fr.unice.polytech.si4.otake.cookiefactory.product;

import java.util.Objects;

/**
 * PackSize
 */
public class PackType implements Comparable<PackType> {
    public enum TypeSize {
        SMALL, MEDIUM, BIG
    }

    private int size;
    private double price;
    private final TypeSize type;

    public PackType(TypeSize type, int size, double price) {
        this.type = type;
        this.size = size;
        this.price = price;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the type
     */
    public TypeSize getType() {
        return type;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int compareTo(PackType o) {
        return this.size - o.size;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PackType)) {
            return false;
        }
        PackType packtype = (PackType) obj;
        return this.hashCode() == packtype.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.type,this.size,this.price);
    }
}
