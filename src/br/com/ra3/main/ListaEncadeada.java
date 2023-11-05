package br.com.ra3.main;

public class ListaEncadeada {
    private Node head;

    public ListaEncadeada() {
        head = null;
    }

    public void inserir(Registro registro) {
        if (head == null) {
            head = new Node(registro);
        } else {
            Node atual = head;
            while (atual.getNext() != null) {
                atual = atual.getNext();
            }
            atual.setNext(new Node(registro));
        }
    }


    public Registro buscar(String codigo) {
        Node current = head;
        while (current != null) {
            if (current.getData().getCodigo().equals(codigo)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }
}
