package fr.unice.polytech.si4.otake.cookiefactory.product;

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
}
