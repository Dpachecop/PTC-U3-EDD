import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements Iterable<T> {

    // --- Nodo Interno ---
    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    // --- Atributos de SinglyLinkedList ---
    private Node<T> head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // --- Métodos Requeridos  ---

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void addFirst(T value) {
        this.head = new Node<>(value, this.head);
        this.size++;
    }

    public void addLast(T value) {
        if (isEmpty()) {
            addFirst(value);
            return;
        }
        Node<T> current = this.head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Node<>(value, null);
        this.size++;
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("La lista está vacía");
        }
        T value = this.head.value;
        this.head = this.head.next;
        this.size--;
        return value;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("La lista está vacía");
        }
        if (this.size == 1) {
            return removeFirst();
        }

        Node<T> current = this.head;
        while (current.next.next != null) {
            current = current.next;
        }
        
        T value = current.next.value;
        current.next = null;
        this.size--;
        return value;
    }

    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        Node<T> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    public void clear() {
        this.head = null;
        this.size = 0;
    }

    // --- Iterador (para usar en bucles for-each) ---
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = current.value;
                current = current.next;
                return value;
            }
        };
    }
}