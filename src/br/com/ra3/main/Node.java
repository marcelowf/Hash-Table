package br.com.ra3.main;

public class Node {
    private Registro data;
    private Node next;

    public Node(Registro data) {
        this.data = data;
        this.next = null;
    }

    public Registro getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
