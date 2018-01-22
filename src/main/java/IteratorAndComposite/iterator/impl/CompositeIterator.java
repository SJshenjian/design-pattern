package IteratorAndComposite.iterator.impl;

import IteratorAndComposite.iterator.MenuComponent;

import java.util.Iterator;
import java.util.Stack;

public class CompositeIterator<T> implements Iterator {
    private Stack stack = new Stack();

    public CompositeIterator(Iterator iterator) {
        stack.push(iterator);
    }

    @Override
    public T next() {
        Iterator iterator = (Iterator) stack.peek();
        MenuComponent component = (MenuComponent) iterator.next();
        if (component instanceof Menu) {
            stack.push(component.createIterator());
        }
        return (T) component;
    }

    @Override
    public boolean hasNext() {
        if (stack.empty()) {
            return false;
        }
        Iterator iterator = (Iterator) stack.peek();
        if (!iterator.hasNext()) {
            stack.pop();
            return hasNext();
        }
        return true;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}