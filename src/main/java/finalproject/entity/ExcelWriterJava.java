package finalproject.entity;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class ExcelWriterJava {
    public void writeExcel(List<QuesAnsDetail> quesAnsDetails) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("QuestionSheet");
        int rowCount =0;
        Row firstRow = sheet.createRow(rowCount++);
        Cell firstCell = firstRow.createCell(0);
        firstCell.setCellValue("Stt");
        firstRow.createCell(1).setCellValue("Question");
        firstRow.createCell(3).setCellValue("Topic");
        firstRow.createCell(5).setCellValue("Answer");
        firstRow.createCell(7).setCellValue("Status");

        for (QuesAnsDetail quesAnsDetail: quesAnsDetails){
            Row row = sheet.createRow(rowCount++);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(quesAnsDetail.getId());
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(quesAnsDetail.getQuestion().getContent());
            Cell cell3 = row.createCell(3);
            cell3.setCellValue(quesAnsDetail.getQuestion().getTopic());
            Cell cell4 = row.createCell(5);
            cell4.setCellValue(quesAnsDetail.getAnswer().getContent());
            Cell cell5 = row.createCell(7);
            cell5.setCellValue(quesAnsDetail.getStatus());
        }
        FileOutputStream fos = new FileOutputStream("Question.xlsx");
        workbook.write(fos);
        workbook.close();
    }

    public List<QuesAnsDetail> readExcel(List<QuesAnsDetail> quesAnsDetails) throws IOException {
        FileInputStream excel = new FileInputStream("Question.xlsx");
        Workbook workbook = new XSSFWorkbook(excel);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();
        Row firstRow = iterator.next();
        while (iterator.hasNext()){
            Row currentRow = iterator.next();
            Question question = new Question(currentRow.getCell(1).getStringCellValue(), currentRow.getCell(3).getStringCellValue());
            Answer answer = new Answer(currentRow.getCell(5).getStringCellValue());
            QuesAnsDetail quesAnsDetail = new QuesAnsDetail(question, answer, currentRow.getCell(7).getStringCellValue());
            quesAnsDetails.add(quesAnsDetail);
        }
        return quesAnsDetails;
    }

}
