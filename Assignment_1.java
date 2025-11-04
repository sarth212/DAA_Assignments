// Name:Sarthak Deshmukh
// PRN:124B2F004

import java.util.*;

class CustomerOrder {
    long timestamp;
    String orderId, customerName;

    CustomerOrder(long t, String id, String name) {
        timestamp = t;
        orderId = id;
        customerName = name;
    }

    public String toString() {
        return String.format("OrderID: %-5s | Timestamp: %-8d | Customer: %s",
                orderId, timestamp, customerName);
    }

    static void mergeSort(CustomerOrder[] a, int l, int r) {
        if (l >= r) return;
        int m = (l + r) / 2;
        mergeSort(a, l, m);
        mergeSort(a, m + 1, r);
        merge(a, l, m, r);
    }

    static void merge(CustomerOrder[] a, int l, int m, int r) {
        List<CustomerOrder> temp = new ArrayList<>();
        int i = l, j = m + 1;
        while (i <= m && j <= r)
            temp.add(a[i].timestamp <= a[j].timestamp ? a[i++] : a[j++]);
        while (i <= m) temp.add(a[i++]);
        while (j <= r) temp.add(a[j++]);
        for (int k = 0; k < temp.size(); k++) a[l + k] = temp.get(k);
    }

    public static void main(String[] args) {
        CustomerOrder[] orders = {
                new CustomerOrder(1726829300L, "ORD101", "Aditya"),
                new CustomerOrder(1726829200L, "ORD102", "Riya"),
                new CustomerOrder(1726829400L, "ORD103", "Karan"),
                new CustomerOrder(1726829100L, "ORD104", "Sakshi"),
                new CustomerOrder(1726829500L, "ORD105", "Neha")
        };

        System.out.println("Before Sorting:");
        for (CustomerOrder o : orders) System.out.println(o);

        mergeSort(orders, 0, orders.length - 1);

        System.out.println("\nAfter Sorting:");
        for (CustomerOrder o : orders) System.out.println(o);
    }
}

