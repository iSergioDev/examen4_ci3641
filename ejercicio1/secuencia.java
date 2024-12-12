import java.util.*;

// Interfaz Secuencia
interface Secuencia<T> {
    void agregar(T elemento);
    T remover();
    boolean vacio();
}

// Clase concreta Pila
class Pila<T> implements Secuencia<T> {
    private Stack<T> stack;

    public Pila() {
        this.stack = new Stack<>();
    }

    @Override
    public void agregar(T elemento) {
        stack.push(elemento);
    }

    @Override
    public T remover() {
        if (vacio()) {
            throw new NoSuchElementException("La pila está vacía.");
        }
        return stack.pop();
    }

    @Override
    public boolean vacio() {
        return stack.isEmpty();
    }
}

// Clase concreta Cola
class Cola<T> implements Secuencia<T> {
    private Queue<T> queue;

    public Cola() {
        this.queue = new LinkedList<>();
    }

    @Override
    public void agregar(T elemento) {
        queue.add(elemento);
    }

    @Override
    public T remover() {
        if (vacio()) {
            throw new NoSuchElementException("La cola está vacía.");
        }
        return queue.poll();
    }

    @Override
    public boolean vacio() {
        return queue.isEmpty();
    }
}