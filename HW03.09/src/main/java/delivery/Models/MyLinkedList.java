package delivery.Models;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {
    // i need to implement iterable in order to loop the elements and dont break old code
    // when using the new class

    private Node<T> head;
    private int size;

    // nested class
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    // inner class
    private class myIterator implements Iterator<T> {
        private Node<T> aux = head;

        @Override
        public boolean hasNext() {
            return aux != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = aux.data;
            aux = aux.next;
            return data;
        }
    }

    public MyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> aux = head;
            while (aux.next != null) {
                aux = aux.next;
            }
            aux.next = newNode;
        }
        this.size++;
    }

    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> aux = head;
            // 0 based?
            for (int i = 0; i < index - 1; i++) {
                aux = aux.next;
            }
            newNode.next = aux.next;
            aux.next = newNode;
        }
        size++;
    }

    //change in place
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<T> aux = head;
        for (int i = 0; i < index; i++) {
            aux = aux.next;
        }
        T oldElement = aux.data;
        aux.data = element;
        return oldElement;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<T> aux = head;
        for (int i = 0; i < index; i++) {
            aux = aux.next;
        }
        return aux.data;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        T removedData;
        if (index == 0) {
            removedData = head.data;
            head = head.next;
        } else {
            Node<T> aux = head;
            for (int i = 0; i < index - 1; i++) {
                aux = aux.next;
            }
            removedData = aux.next.data;
            aux.next = aux.next.next;
        }
        size--;
        return removedData;
    }

    public boolean remove(Object o) {
        if (head == null) {
            return false;
        }

        if (head.data.equals(o)) {
            head = head.next;
            size--;
            return true;
        }

        Node<T> aux = head;
        Node<T> prev = null;
        while (aux != null) {
            if (aux.data.equals(o)) {
                prev.next = aux.next;
                size--;
                return true;
            }
            prev = aux;
            aux = aux.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void clear() {
        this.head = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new myIterator();
    }

}