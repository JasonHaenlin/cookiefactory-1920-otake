package fr.unice.polytech.si4.otake.cookiefactory.product;

/**
 * PackSize
 */
public class PackType {
    public enum PackSize {
        SMALL, MEDIUM, BIG
    }

    private final int size;
    private final int price;
    private final PackSize type;

    PackType(PackSize type, int size, int price) {
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
     * @return the type
     */
    public PackType getType() {
        return type;
    }

}
