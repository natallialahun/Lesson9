package com.epam.lessson9;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Natallia_Lahun on 03/21/2017.
 */
public class MyLinkedList<T> implements Iterable<T> {

    private int size;
    private transient LinkedListElement<T> first;
    private transient LinkedListElement<T> last;
    /*
    * Реализуйте самостоятельно динамическую структуру Односвязный список.
     * Реализуйте методы добавления (в голову, хвост и произвольное место списка),
      * удаления и поиска объекта в списке.
      * Используйте параметризацию при описании класса.
      * (Условие не означает необходимости повторить все возможности класса LinkedList.
       * Возможности Java Collection Framework не использовать).*/

    public boolean addLast(T element){
        if (last == null) {
            last = first = new LinkedListElement<>(element);
        } else {
            LinkedListElement<T> currLast = last;
            last = new LinkedListElement<>(element);
            currLast.next = last;
        }
        this.size++;
        return true;
    }

    public boolean addFirst(T element){
        if (first == null) {
            last = first = new LinkedListElement<>(element);
        } else {
            LinkedListElement<T> currFirst = first;
            first = new LinkedListElement<>(element);
            first.next = currFirst;
        }
        this.size++;
        return true;
    }

    public boolean contains(T element){
        return getElement(element) != null;
    }


    public boolean removeFirst(){
        if (first == null) {
            return false;
        }
            LinkedListElement<T> currFirst = first;
            first = currFirst.next;
            first.next = currFirst;
            this.size--;
            return true;
    }

    public boolean removeLast(){
        if (last == null) {
            return false;
        }

            LinkedListElement<T> current = first;
            while(current.next != null){
                LinkedListElement<T> prev = current;
                current  = prev.next;
            }
            last = current;
            last.next = null;
            this.size--;
            return true;

    }

    public boolean addAfter(T after,T element) {
        if (last == null) {
            return false;
        }
        LinkedListElement<T> afterElement = getElement(after);
        if (afterElement == null){
                return false;
        }
        LinkedListElement<T> beforeElement = afterElement.next;
        afterElement.next = new LinkedListElement<>(element, beforeElement);
        this.size++;
        return true;
    }

    public boolean addBefore(T before,T element){
        if (last == null) {
          return false;
        }
        LinkedListElement<T> beforeElement = getPreviousElement(before);
        LinkedListElement<T> afterElement = beforeElement.next;
        beforeElement.next = new LinkedListElement<>(element, afterElement);
        this.size++;
        return true;

    }


    public boolean remove(T element) {
        if (last == null) {
            return false;
        }
        LinkedListElement<T> beforeElement = getPreviousElement(element);
        LinkedListElement<T> currentElement = getElement(element);
        if (beforeElement == null || currentElement == null){
            return false;
        }
        beforeElement.next = currentElement.next;
        this.size--;
        return true;
    }

    private LinkedListElement<T> getElement(T val){
        LinkedListElement<T> current = first;
        while(current.next != null){
            if(current.element.equals(val)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    private LinkedListElement<T> getPreviousElement(T val){
        LinkedListElement<T> current = first;
        LinkedListElement<T> prev = first;
        while(current != null && !current.element.equals(val)){
            prev = current;
            current = prev.next;
        }
        return prev;
    }

    public Iterator<T> iterator() {
        return new MySetIterator();
    }



    private class LinkedListElement<T>{
        private final T element;
        private LinkedListElement<T> next;

        LinkedListElement(T element){
            this.element  = element;
        }

        LinkedListElement(T element, LinkedListElement<T> next){
            this.element  = element;
            this.next = next;
        }
    }

    private class MySetIterator implements
            Iterator<T> {
        private LinkedListElement<T> cursor;
        private int idx;

        public MySetIterator() {
            this.idx = 0;
            this.cursor = first;
        }

        public boolean hasNext() {
            return this.idx < MyLinkedList.this.size ;
        }

        public T next() {
            if(this.hasNext()) {
                T current = cursor.element;
                this.idx ++;
                cursor = cursor.next;
                return current;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }



}
