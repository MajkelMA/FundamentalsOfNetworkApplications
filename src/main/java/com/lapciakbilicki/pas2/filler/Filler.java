package com.lapciakbilicki.pas2.filler;

import java.util.List;

public interface Filler<T> {
    void autoFill(List<T> destination);
}
