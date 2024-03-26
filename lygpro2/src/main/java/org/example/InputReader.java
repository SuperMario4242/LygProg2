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
            channel.setEnd(false);
            while (((line = reader.readLine()) != null)) {
                if (!line.trim().isEmpty()) {
                    //Thread.sleep(100);
                    int data = Integer.parseInt(line);
                    channel.sendData(data);
                }
                else break;

//                int data = Integer.parseInt(line);
//                channel.sendData(data);
            }
            channel.setEnd(true);
        } catch (IOException e) {
            e.fillInStackTrace();
        }
//        catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
    private int countLines(String fileName) {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) { // Ignore empty lines at the end of the file
                    count++;
                }
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
        return count;
    }

}