package com.example.hw_2_14;

import java.util.Objects;

public class StringListImpl implements StringList {

    private static final int INITIAL_SIZE = 15;

    private final String[] data;

    private int capacity;

    public StringListImpl() {
        data = new String[INITIAL_SIZE];
        capacity = 0;
    }

    public StringListImpl(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размер списка должен быть положительным!");
        }
        data = new String[n];
        capacity = 0;
    }

    @Override
    public String add(String item) {
        if (capacity >= data.length) {
            throw new IllegalArgumentException("Список полон!");
        }
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Нельзя добавлять null в список!");
        }
        return data[capacity++] = item;
    }

    @Override
    public String add(int index, String item) {
        if (capacity >= data.length) {
            throw new IllegalArgumentException("Список полон!");
        }
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Нельзя добавлять null в список!");
        }
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть неотрицательным!");
        }
        if (index > capacity) {
            throw new IllegalArgumentException("Индекс: " + index + ", Размер: " + capacity);
        }
        System.arraycopy(data, index, data, index + 1, capacity - index);
        data[index] = item;
        capacity++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Нельзя добавлять null в список!");
        }
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть неотрицательным!");
        }
        if (index >= capacity) {
            throw new IllegalArgumentException("Индекс: " + index + ", Размер: " + capacity);
        }
        return data[index] = item;
    }

    @Override
    public String remove(String item) {
        int indexForRemoving = indexOf(item);
        if (indexForRemoving == -1) {
            throw new IllegalArgumentException("Элемент не найден!");
        }
        return remove(indexForRemoving);
    }

    @Override
    public String remove(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть неотрицательным!");
        }
        if (index >= capacity) {
            throw new IllegalArgumentException("Индекс: " + index + ", Размер: " + capacity);
        }
        String removed = data[index];
        if (capacity - 1 - index >= 0) {
            System.arraycopy(data, index + 1, data, index, capacity - 1 - index);
        }
        data[--capacity] = null;
        return removed;
    }

    @Override
    public boolean contains(String item) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Нельзя добавлять null в список!");
        }
        for (int i = 0; i < capacity; i++) {
            if (data[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Нельзя добавлять null в список!");
        }
        int index = -1;
        for (int i = 0; i < capacity; i++) {
            if (data[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(String item) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Нельзя добавлять null в список!");
        }
        int index = -1;
        for (int i = capacity - 1; i >= 0; i--) {
            if (data[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public String get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть неотрицательным!");
        }
        if (index >= capacity) {
            throw new IllegalArgumentException("Индекс: " + index + ", Размер: " + capacity);
        }
        return data[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (size() != otherList.size()) {
            return false;
        }
        for (int i = 0; i < capacity; i++) {
            if (!data[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            data[i] = null;
        }
        capacity = 0;
    }

    @Override
    public String[] toArray() {
        String[] result = new String[capacity];
        System.arraycopy(data, 0, result, 0, capacity);
        return result;
    }

}
