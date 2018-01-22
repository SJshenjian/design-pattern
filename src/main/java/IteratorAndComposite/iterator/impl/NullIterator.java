package IteratorAndComposite.iterator.impl;

import java.util.Iterator;

public class NullIterator<T> implements Iterator {

    @Override
    public T next() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}