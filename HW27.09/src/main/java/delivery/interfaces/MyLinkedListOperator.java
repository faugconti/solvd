package delivery.interfaces;

import delivery.Models.MyLinkedList;

@FunctionalInterface
public interface MyLinkedListOperator<T,R> {
    R operate(MyLinkedList<T> list);
}
