package io.underflowers.underification.repositories.projections;

public interface PairProjection<T, U> {
    T getFirst();
    U getSecond();
}
