package edu.sjsu.mdani.writer;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public interface OutputFileWriter {
    public void write(String message, String inputPath) throws FileNotFoundException, UnsupportedEncodingException;
}
