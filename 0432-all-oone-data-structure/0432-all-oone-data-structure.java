import java.util.*;

class AllOne {

    // Each Node represents one frequency/count.
    // It stores all keys having that count.
    class Node {
        int count;
        Set<String> keys;
        Node prev;
        Node next;

        Node(int count) {
            this.count = count;
            this.keys = new HashSet<>();
        }
    }

    // key -> node containing the key
    private Map<String, Node> map;

    // Dummy head and tail
    private Node head;
    private Node tail;

    public AllOne() {
        map = new HashMap<>();

        head = new Node(0);
        tail = new Node(0);

        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {

        // Key doesn't exist
        if (!map.containsKey(key)) {

            // Need a count=1 node
            Node first = head.next;

            if (first == tail || first.count != 1) {
                Node newNode = new Node(1);
                insertAfter(head, newNode);
                first = newNode;
            }

            first.keys.add(key);
            map.put(key, first);

        } else {

            // Key already exists
            Node current = map.get(key);
            Node next = current.next;

            // Need a node with count current.count + 1
            if (next == tail || next.count != current.count + 1) {
                Node newNode = new Node(current.count + 1);
                insertAfter(current, newNode);
                next = newNode;
            }

            // Move key to next count
            next.keys.add(key);
            map.put(key, next);

            // Remove key from old count
            current.keys.remove(key);

            // Remove empty node
            if (current.keys.isEmpty()) {
                removeNode(current);
            }
        }
    }

    public void dec(String key) {

        Node current = map.get(key);

        // Key has count 1
        if (current.count == 1) {

            // Remove completely
            current.keys.remove(key);
            map.remove(key);

            if (current.keys.isEmpty()) {
                removeNode(current);
            }

        } else {

            Node prev = current.prev;

            // Need a node with count current.count - 1
            if (prev == head || prev.count != current.count - 1) {
                Node newNode = new Node(current.count - 1);
                insertAfter(prev, newNode);
                prev = newNode;
            }

            // Move key to previous count
            prev.keys.add(key);
            map.put(key, prev);

            // Remove from current count
            current.keys.remove(key);

            // Remove empty node
            if (current.keys.isEmpty()) {
                removeNode(current);
            }
        }
    }

    public String getMaxKey() {

        if (tail.prev == head) {
            return "";
        }

        // Any key from maximum-count node
        return tail.prev.keys.iterator().next();
    }

    public String getMinKey() {

        if (head.next == tail) {
            return "";
        }

        // Any key from minimum-count node
        return head.next.keys.iterator().next();
    }

    // Insert newNode after prev
    private void insertAfter(Node prev, Node newNode) {

        newNode.next = prev.next;
        newNode.prev = prev;

        prev.next.prev = newNode;
        prev.next = newNode;
    }

    // Remove a node from the linked list
    private void removeNode(Node node) {

        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}