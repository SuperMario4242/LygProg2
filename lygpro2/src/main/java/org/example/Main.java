package org.example;
//2 uzduotis
//6 variantas
//Rafal Paskevic 3 kursas 2 grupe
public class Main {
    public static  void main(String[] args) {
        int bufferSize = 5;
        String currentDir = System.getProperty("user.dir");
        String path = "/lygpro2/src/main/java/org/example/";
        DataChannel channel = new DataChannel(bufferSize);
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
    }
}
