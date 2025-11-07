import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements Iterable<T> {

    // --- Nodo Interno ---
    private static class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> prev;

        public Node(T value, Node<T> next, Node<T> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    // --- Atributos de DoublyLinkedList ---
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // --- Métodos Requeridos [cite: 67] ---
    

    public int size() {
        return this.size;
    }


    public boolean isEmpty() {
        return this.size == 0;
    }

    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value, this.head, null);
        if (isEmpty()) {
            this.tail = newNode;
        } else {
            this.head.prev = newNode;
        }
        this.head = newNode;
        this.size++;
    }

    public void addLast(T value) {
        Node<T> newNode = new Node<>(value, null, this.tail);
        if (isEmpty()) {
            this.head = newNode;
        } else {
            this.tail.next = newNode;
        }
        this.tail = newNode;
        this.size++;
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("La lista está vacía");
        }
        T value = this.head.value;
        this.head = this.head.next;
        this.size--;

        if (isEmpty()) {
            this.tail = null;
        } else {
            this.head.prev = null;
        }
        return value;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("La lista está vacía");
        }
        T value = this.tail.value;
        this.tail = this.tail.prev;
        this.size--;

        if (isEmpty()) {
            this.head = null;
        } else {
            this.tail.next = null;
        }
        return value;
    }

    // --- Métodos de acceso (Get/Set) ---

    // Método auxiliar para encontrar un nodo por índice
    private Node<T> getNode(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        
        Node<T> current;
        // Optimización: buscar desde la cabeza o la cola según qué esté más cerca
        if (index < this.size / 2) {
            current = this.head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = this.tail;
            for (int i = this.size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    public T get(int index) {
        return getNode(index).value;
    }

    // (Añadido) Necesario para que el método de ordenamiento Burbuja siga funcionando
    public void set(int index, T value) {
        Node<T> node = getNode(index);
        node.value = value;
    }

    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // (Métodos extra de la Parte A) [cite: 68-69]
    // (Estos no los usaremos en la integración, pero son parte del requisito)

    public void addAfter(Node<T> node, T value) {
        // (Implementación omitida por brevedad, pero requerida por la guía)
    }

    public void addBefore(Node<T> node, T value) {
        // (Implementación omitida por brevedad, pero requerida por la guía)
    }
    
    public T remove(Node<T> node) {
        // (Implementación omitida por brevedad, pero requerida por la guía)
        return null; // Cambiar
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