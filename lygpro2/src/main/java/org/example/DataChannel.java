package org.example;

import java.lang.*;

class DataChannel {
    private final int[] buffer;


    private final int size;
    private int in;
    private int out;
    private int count;

    public DataChannel(int bufferSize) {
        size = bufferSize;
        buffer = new int[size];
        in = 0;
        out = 0;
        count = 0;
    }

    public int getSize() {
        return size;
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
        System.out.println("Sent: " + data);
        in = (in + 1) % size;
        count++;
        notifyAll();
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
        System.out.println("Received: " + data);
        out = (out + 1) % size;
        count--;
        notifyAll();
        return data;
    }
}