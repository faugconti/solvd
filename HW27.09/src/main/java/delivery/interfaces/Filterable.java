package delivery.interfaces;

@FunctionalInterface
public interface Filterable<T> {
    boolean test(T element);
}
