package com.example.string.builder;

import jdk.internal.util.Preconditions;

import java.io.IOException;
import java.util.Arrays;

public class AbstractNewStringBuilder {

    /**
     * Хранилище символов
     */
    char[] value;

    /**
     * Количество фактически используемых символов
     */
    int count;

    /**
     * Максимальный объём массива
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;


    /**
     * Конструктор
     */
    public AbstractNewStringBuilder(int i) {
        value = new char[i];
        count = 0;
    }

    public AbstractNewStringBuilder append(String str) throws IOException {
        if (str == null) {
            return appendNull();
        }
        int len = str.length();

        ensureCapacityInternal(count + len);
        str.getChars(0, len, value, count);
        count += len;

        return this;
    }

    private void ensureCapacityInternal(int minimumCapacity) {
        if (minimumCapacity - value.length > 0) {

            value = Arrays.copyOf(value, newCapacity(minimumCapacity));
        }
    }

    private AbstractNewStringBuilder appendNull() {
        ensureCapacityInternal(count + 4);

        value[count++] = 'n';
        value[count++] = 'u';
        value[count++] = 'l';
        value[count++] = 'l';

        return this;
    }

    private int newCapacity(int minCapacity) {
        int newCapacity = (value.length << 1) + 2;

        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }

        return (newCapacity <= 0 || MAX_ARRAY_SIZE - newCapacity < 0)
                ? hugeCapacity(minCapacity)
                : newCapacity;
    }

    private int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }

        return (minCapacity > MAX_ARRAY_SIZE)
                ? Integer.MAX_VALUE
                : MAX_ARRAY_SIZE;
    }

    protected void delete(int start, int end) {
        int count = this.count;
        if (end > count) {
            end = count;
        }
        int len = end - start;
        if (len > 0) {
            this.count = count - len;
        }
    }

    @Override
    public String toString() {
        int length = this.length();

        this.value = Arrays.copyOfRange(this.value, 0, length);
        return new String(this.value);
    }

    public int length() {
        return this.count;
    }
}
