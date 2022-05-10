package edu.sjsu.mdani.writer;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class ResultWriter {
    OutputFileWriter strategy;

    //Default strategy
    public ResultWriter() {
        this.strategy = new SuccessOutputStrategy();
    }

    public void write(String message, String inputPath) throws FileNotFoundException, UnsupportedEncodingException {
        this.strategy.write(message, inputPath);
    }

    public void changeStrategy(OutputFileWriter strategy) {
        this.strategy = strategy;
    }
}
