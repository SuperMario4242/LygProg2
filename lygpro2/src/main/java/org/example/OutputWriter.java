package org.example;


import java.io.*;


class OutputWriter extends Thread {
    private final DataChannel channel;
    private final String outputFile;

    public OutputWriter(DataChannel channel, String outputFile) {
        this.channel = channel;
        this.outputFile = outputFile;
    }

    @Override
    public void run() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            int i=0;
            while (i<channel.getSize()) {
                int data = channel.receiveData();
                writer.write("Received data " + i + " " + Integer.toString(data));
                writer.newLine();
                writer.flush();
                i++;
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }
}