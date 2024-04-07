package org.example;

import java.io.*;
import java.lang.*;

class InputReader extends Thread {
    private final DataChannel channel;
    private final String inputFile;

    public InputReader(DataChannel channel, String inputFile) {
        this.channel = channel;
        this.inputFile = inputFile;
    }


    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while (((line = reader.readLine()) != null)) {
                if (!line.trim().isEmpty()) {
                    int data = Integer.parseInt(line);
                    channel.sendData(data);
                }
                else break;

            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }

    }

}