import java.io.*;

//We used the implementation of the list from the slides and added some methods to it that serves our program.
public class List<T> implements Serializable {
    private Node<T> firstNode;
    private Node<T> lastNode;
    private String name;

    public List() {
        this("list");
    }

    public List(String listName) {
        name = listName;
        firstNode = lastNode = null;
    }

    public void insertAtFront(T insertItem) {
        if (isEmpty())
            firstNode = lastNode = new Node<T>(insertItem);
        else
            firstNode = new Node<T>(insertItem, firstNode);
    }

    public void insertAtBack(T insertItem) {
        if (isEmpty())
            firstNode = lastNode = new Node<T>(insertItem);
        else
            lastNode = lastNode.nextNode = new Node<T>(insertItem);
    }

    public T removeFromFront() {
        if (isEmpty())
            return null;
        T removedItem = firstNode.data;
        if (firstNode == lastNode)
            firstNode = lastNode = null;
        else
            firstNode = firstNode.nextNode;
        return removedItem;
    }

    public T removeFromBack() {
        if (isEmpty())
            return null;
        T removedItem = lastNode.data;
        if (firstNode == lastNode)
            firstNode = lastNode = null;
        else {
            Node<T> current = firstNode;
            while (current.nextNode != lastNode)
                current = current.nextNode;
            lastNode = current;
            current.nextNode = null;
        }
        return removedItem;
    }

    public boolean isEmpty() {
        return firstNode == null;
    }

    // return object at the given index, it checks if the list is empty, then it
    // iterates through the list until it finds the object at the given index.
    public T get(int index) {
        if (isEmpty())
            return null;

        Node<T> current = firstNode;
        int count = 0;
        while (current != null) {
            if (count == index) {
                return current.data;
            }
            count++;
            current = current.nextNode;
        }
        return null;
    }

    // remove method, it checks if the list is empty, then it iterates through the
    // list until it finds the object to remove wherever that object was.
    public boolean remove(T itemToRemove) {
        if (isEmpty())
            return false;

        if (firstNode.data.equals(itemToRemove)) {
            removeFromFront();
            return true;
        }
        Node<T> previous = null;
        Node<T> current = firstNode;

        while (current != null) {
            if (current.data.equals(itemToRemove)) {
                previous.nextNode = current.nextNode;
                if (current == lastNode) {
                    lastNode = previous;
                }
                return true;
            }
            previous = current;
            current = current.nextNode;
        }

        return false;
    }

    public int size() {// used instead of counters, it iterates through the list and counts the number
                       // of nodes unntil it reached a null node.
        int count = 0;
        Node<T> current = firstNode;
        while (current != null) {
            count++;
            current = current.nextNode;
        }
        return count;
    }

}