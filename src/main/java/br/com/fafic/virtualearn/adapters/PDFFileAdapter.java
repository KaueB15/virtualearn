package br.com.fafic.virtualearn.adapters;

import br.com.fafic.virtualearn.interfaces.FileProcessor;
import br.com.fafic.virtualearn.model.Rating;
import br.com.fafic.virtualearn.model.Student;
import br.com.fafic.virtualearn.model.Teacher;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class PDFFileAdapter implements FileProcessor {

    @Override
    public void generateFile(Rating rating, String filePath, Teacher teacher, Student student) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(50, 750);

            contentStream.showText("Professor: " + teacher.getName());
            contentStream.newLine();
            contentStream.showText("Notas do Aluno: " + student.getName());
            contentStream.newLine();
            contentStream.showText("Nota 1: " + rating.getR1());
            contentStream.newLine();
            contentStream.showText("Nota 2: " + rating.getR2());
            contentStream.newLine();
            contentStream.showText("Nota 3: " + rating.getR3());
            contentStream.newLine();
            contentStream.showText("Média Final: " + rating.getFinalRating());
            contentStream.newLine();

            contentStream.endText();
            contentStream.close();

            document.save(filePath);
            System.out.println("PDF gerado com sucesso em: " + filePath);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao gerar o arquivo PDF: " + e.getMessage(), e);
        }
    }


}
