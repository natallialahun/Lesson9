package com.epam.lessson9;

/**
 * Created by Natallia_Lahun on 03/22/2017.
 */
public class ClassRunner {
    public static void main (String[] args){
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);



        list.removeLast();


        list.addAfter(2,5);
        list.addAfter(2,0);
        list.addBefore(0,0);

        list.remove(2);

        list.remove(15);
        list.addAfter(15,3);

        for (int element: list) {
            System.out.println(element);
        }
    }
}
