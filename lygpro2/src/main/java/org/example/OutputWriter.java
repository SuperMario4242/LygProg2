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
            int data;
            while(true) {
                Thread.sleep(100);
                data = channel.receiveData();
                if(data == 0){
                    break;
                }
                writer.write(Integer.toString(data));
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}