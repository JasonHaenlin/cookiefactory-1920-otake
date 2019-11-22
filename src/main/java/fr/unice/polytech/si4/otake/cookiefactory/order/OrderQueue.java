package fr.unice.polytech.si4.otake.cookiefactory.order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * OrderQueue
 */
public class OrderQueue implements OrderObserver {

    private final Queue<Order> orders;
    private final Stack<Order> archivedOrders;
    private final List<Order> ordersToRetrieve;

    public OrderQueue() {
        this.orders = new PriorityQueue<>(10,
                (Order o1, Order o2) -> o1.getAppointmentDate().compareTo(o2.getAppointmentDate()));
        this.archivedOrders = new Stack<>();
        this.ordersToRetrieve = new ArrayList<>();

    }

    public int size() {
        return this.orders.size();
    }

    public Stack<Order> getArchive() {
        return this.archivedOrders;
    }

    public List<Order> toRetrieve() {
        return this.ordersToRetrieve;
    }

    public Order next() {
        Order o = this.orders.remove();
        o.updateStatus(Status.READY);
        this.ordersToRetrieve.add(o);
        return peek();
    }

    public boolean add(Order order) {
        if (order == null) {
            return false;
        }
        order.setObs(this);
        order.updateStatus(Status.WAITING);
        return this.orders.add(order);
    }

    public Order peek() {
        return this.orders.peek();
    }

    public boolean archive(Order orderToArchive) {
        if (orderToArchive == null) {
            return false;
        }
        orderToArchive.updateStatus(Status.RETRIEVED);
        if (this.orders.remove(orderToArchive) || this.ordersToRetrieve.remove(orderToArchive)) {
            this.archivedOrders.add(orderToArchive);
            return true;
        }
        return false;
    }

    @Override
    public boolean retrieved(Order order) {
        return archive(order);
    }

}
