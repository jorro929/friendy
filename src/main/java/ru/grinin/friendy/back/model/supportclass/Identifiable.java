package ru.grinin.friendy.back.model.supportclass;

import java.io.Serializable;

public interface Identifiable<I extends Comparable<I>>
        extends Serializable {
    I getId();

    void setId(I id);
}

