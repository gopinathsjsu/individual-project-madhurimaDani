package edu.sjsu.mdani.writer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class SuccessOutputStrategy implements OutputFileWriter{
    @Override
    public void write(String message, String successFilePath) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("Processing the order; Refer output file for more details : "+ successFilePath);
        PrintWriter writer = new PrintWriter(successFilePath, "UTF-8");
        writer.println(message);
        writer.close();
    }
}
