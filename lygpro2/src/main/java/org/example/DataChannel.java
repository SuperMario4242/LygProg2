package org.example;

import java.lang.*;

class DataChannel {
    private final int[] buffer;

    private final int size;
    private int in;
    private int out;

    private int count;
    private int elementCount;
    private boolean end;

    public DataChannel(int bufferSize) {
        size = bufferSize;
        buffer = new int[size];
        in = 0;
        out = 0;
        count = 0;
        elementCount = 0;
    }


    public synchronized void sendData(int data) {
        while (count == size) {
            try {
                wait();

            } catch (InterruptedException e) {
                e.fillInStackTrace();
            }
        }
        buffer[in] = data;
        in = (in + 1) % size;
        count++;
        elementCount++;
        notifyAll();
    }
    public int getElementCount(){
            return elementCount;
    }
    public synchronized int receiveData() {
        while (count == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.fillInStackTrace();
            }
        }
        int data = buffer[out];
        out = (out + 1) % size;
        count--;
        notifyAll();
        return data;
    }

    public boolean getEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}