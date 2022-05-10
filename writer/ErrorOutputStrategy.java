package edu.sjsu.mdani.writer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class ErrorOutputStrategy implements OutputFileWriter{
    @Override
    public void write(String message, String errorFilePath) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("Not processing the order; Invalid order received; refer output file for more details : " + errorFilePath);
        PrintWriter writer = new PrintWriter(errorFilePath, "UTF-8");
        writer.println(message);
        writer.close();
    }
}
