package org.example;


public class Main {
    public static  void main(String[] args) {
            java.lang.String currentDir = System.getProperty("user.dir");
            java.lang.String path = "/lygpro2/src/main/java/org/example/";
            System.out.println("Dabartinis darbinis katalogas: " + currentDir);
            DataChannel channel = new DataChannel(5);
            InputReader inputReader = new InputReader(channel, currentDir+path + "input.txt");
            OutputWriter outputWriter = new OutputWriter(channel, currentDir+path +"output.txt");

            inputReader.start();
            outputWriter.start();

        try {
            inputReader.join();
            outputWriter.join();
        } catch (InterruptedException e) {
            e.fillInStackTrace();
        }
        System.out.println("Viskas atlikta. Programa baigia darbą.");
    }
}
