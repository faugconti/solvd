package delivery.interfaces;

@FunctionalInterface
public interface Reducer<T,R> {
    R reduce(R accumulator, T element);
}
