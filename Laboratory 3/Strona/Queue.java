package Strona;

public class Queue<E> {
    private class Element{
        private E value;
        private Element next;

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }

        Element(E data) {
            this.value = data;
        }
    }

    Element head = null;

    public Queue() {
    }

    public int size() {
        int pos = 0;
        Element actElem = head;
        while (actElem != null) {
            pos++;
            actElem = actElem.getNext();
        }
        return pos;
    }


    private Element getElement(int index) {
        if (index < 0) throw new IndexOutOfBoundsException();
        Element actElem = head;
        while (index > 0 && actElem != null) {
            index--;
            actElem = actElem.getNext();
        }
        if (actElem == null)
            throw new IndexOutOfBoundsException();
        return actElem;
    }

    public boolean enqueue(E e) {
        Element newElem = new Element(e);
        if (head == null) {
            head = newElem;
            return true;
        }
        Element tail = head;
        while (tail.getNext() != null)
            tail = tail.getNext();
        tail.setNext(newElem);
        return true;
    }

    public E get(int index) {
        Element actElem = getElement(index);
        return actElem.getValue();
    }


    public E dequeue() {
        if (head == null) throw new IndexOutOfBoundsException();
        E retValue = head.getValue();
        head = head.getNext();
        return retValue;
    }
}
