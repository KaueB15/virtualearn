package br.com.fafic.virtualearn.interfaces;

import br.com.fafic.virtualearn.model.Rating;

public interface FileProcessor {
    void generateFile(Rating rating, String filePath);
}
