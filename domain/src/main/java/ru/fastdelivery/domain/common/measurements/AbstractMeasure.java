package ru.fastdelivery.domain.common.measurements;

import java.math.BigInteger;

public interface AbstractMeasure<T> extends Comparable<T> {


    default BigInteger roundToMultipleOfFifty(BigInteger input){
        int remainder = input.intValue() % 50;
        return remainder == 0 ? input :
                input.add(BigInteger.valueOf(50 - remainder));

    }

    default boolean isLessThanZero(BigInteger measure) {
        return BigInteger.ZERO.compareTo(measure) > 0;
    }

   default boolean isMoreThanLimit(BigInteger measure){
        return BigInteger.valueOf(1500).compareTo(measure) > 0;
    }


    @Override
    int compareTo(T o);

    @Override
    boolean equals(Object obj);

}
