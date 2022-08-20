package com.example.hw_2_14;

import java.util.Arrays;
import java.util.Objects;

public class IntegerListImpl implements IntegerList {

    private static final int INITIAL_SIZE = 15;

    private Integer[] data;

    private int capacity;

    public IntegerListImpl() {
        data = new Integer[INITIAL_SIZE];
        capacity = 0;
    }

    public IntegerListImpl(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размер списка должен быть положительным!");
        }
        data = new Integer[n];
        capacity = 0;
    }

    @Override
    public Integer add(Integer item) {
        growIfNeeded();
        if (capacity >= data.length) {
            throw new IllegalArgumentException("Список полон!");
        }
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Нельзя добавлять null в список!");
        }
        return data[capacity++] = item;
    }

    @Override
    public Integer add(int index, Integer item) {
        growIfNeeded();
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
    public Integer set(int index, Integer item) {
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
    public Integer remove(Integer item) {
        int indexForRemoving = indexOf(item);
        if (indexForRemoving == -1) {
            throw new IllegalArgumentException("Элемент не найден!");
        }
        return remove(indexForRemoving);
    }

    @Override
    public Integer remove(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть неотрицательным!");
        }
        if (index >= capacity) {
            throw new IllegalArgumentException("Индекс: " + index + ", Размер: " + capacity);
        }
        Integer removed = data[index];
        if (capacity - 1 - index >= 0) {
            System.arraycopy(data, index + 1, data, index, capacity - 1 - index);
        }
        data[--capacity] = null;
        return removed;
    }

    @Override
    public boolean contains(Integer item) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Нельзя добавлять null в список!");
        }
        sort();
        int min = 0;
        int max = capacity - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item.equals(data[mid])) {
                return true;
            }
            if (item < data[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
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
    public int lastIndexOf(Integer item) {
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
    public Integer get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть неотрицательным!");
        }
        if (index >= capacity) {
            throw new IllegalArgumentException("Индекс: " + index + ", Размер: " + capacity);
        }
        return data[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
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
    public Integer[] toArray() {
        Integer[] result = new Integer[capacity];
        System.arraycopy(data, 0, result, 0, capacity);
        return result;
    }

    private void sort() {
        quickSort(data, 0, data.length - 1);
    }

    private void quickSort(Integer[] data, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(data, begin, end);

            quickSort(data, begin, partitionIndex - 1);
            quickSort(data, partitionIndex + 1, end);
        }
    }

    private int partition(Integer[] data, int begin, int end) {
        int pivot = data[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (data[j] <= pivot) {
                i++;

                swapElements(data, i, j);
            }
        }

        swapElements(data, i + 1, end);
        return i + 1;
    }

    private void swapElements(Integer[] data, int i1, int i2) {
        int temp = data[i1];
        data[i1] = data[i2];
        data[i2] = temp;
    }


    private void growIfNeeded() {
        if (capacity == data.length) {
            grow();
        }
    }

    private void grow() {
        data = Arrays.copyOf(data, capacity + capacity / 2);
    }

}
