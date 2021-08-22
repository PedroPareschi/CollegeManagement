package br.com.pedropareschi.collegemanagement.services;

import br.com.pedropareschi.collegemanagement.domain.ClassTerm;
import br.com.pedropareschi.collegemanagement.domain.College;
import br.com.pedropareschi.collegemanagement.domain.Student;
import br.com.pedropareschi.collegemanagement.domain.Term;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.stream.Stream;

public class PdfService {

    private static Document document = new Document();

    public static void academicRecord(Student student) throws FileNotFoundException, DocumentException, ParseException {
        PdfWriter.getInstance(document, new FileOutputStream("histórico-escolar.pdf"));
        document.open();
        College college = student.getProgram().getCampus().getCollege();
        Chunk collegeInfo = new Chunk();
        collegeInfo.append(college.getName().toUpperCase(Locale.ROOT))
                .append("\n").append(student.getProgram().getCampus().getName())
                .append("\nCNPJ: ").append(college.getCnpj());
        collegeInfo.setLineHeight(15);

       Paragraph paragraph = new Paragraph();
       paragraph.add(collegeInfo);
       paragraph.setAlignment(Element.ALIGN_CENTER);
       document.add(paragraph);

        Chunk studentInfo = new Chunk();
        studentInfo.append("\n\n\n\nNome: ").append(student.getName())
                .append("\nCPF: ").append(student.getCpf())
                .append("\nRG: ").append(student.getRg())
                .append("\nNascimento: ").append(new SimpleDateFormat("dd/MM/yyyy").format(student.getBirthdate()))
                .append("\nCurso: ").append(student.getProgram().getName())
                .append("\nCR: ").append(String.format("%.2f", student.getGpa()))
                .append("\n\nDisciplinas cursadas\n\n");
        studentInfo.setLineHeight(15);
        document.add(studentInfo);

        PdfPTable table = new PdfPTable(new float[]{2, 5, 1, 1, 1, 2});
        table.setWidthPercentage(100);
        addTableHeader(table);
        addRows(table, student);
        document.add(table);

        Chunk chunk = new Chunk("CRED - Créditos, CH - Carga Horária, MF - Média Final, SF - Situação Final");
        Font font = new Font();
        font.setSize(10);
        chunk.setFont(font);
        document.add(chunk);

        document.close();
    }

    private static void addTableHeader(PdfPTable table) {
        Stream.of("Período", "Disciplina", "CRED", "CH", "MF", "SF")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private static void addRows(PdfPTable table, Student student) {
        for(Term term: student.getTerms()){
            for (ClassTerm classTerm: term.getClasses()){
                StringBuilder sb = new StringBuilder();
                sb.append(classTerm.getaClass().getYear())
                        .append(".")
                        .append(classTerm.getaClass().getSemester().getCod());
                table.addCell(sb.toString());
                table.addCell(classTerm.getaClass().getCourse().getName());
                table.addCell(classTerm.getaClass().getCourse().getCredits().toString());
                table.addCell(classTerm.getaClass().getCourse().getTotalHours().toString());
                table.addCell(String.format("%.2f", classTerm.getFinalGrade()));
                table.addCell(classTerm.getSituation().getDescription());
            }
        }
    }
}
