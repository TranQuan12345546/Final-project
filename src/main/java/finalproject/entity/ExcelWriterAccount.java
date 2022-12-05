package finalproject.entity;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class ExcelWriterAccount {

    public void writeExcel(List<Account> accounts) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("AccountSheet");
        int rowCount =0;
        Row firstRow = sheet.createRow(rowCount++);
        Cell firstCell = firstRow.createCell(0);
        firstCell.setCellValue("Stt");
        firstRow.createCell(1).setCellValue("Email");
        firstRow.createCell(2).setCellValue("Name");
        firstRow.createCell(3).setCellValue("Password");

        for (Account account: accounts){
            Row row = sheet.createRow(rowCount++);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(account.getId());
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(account.getEmail());
            Cell cell3 = row.createCell(2);
            cell3.setCellValue(account.getUsername());
            Cell cell4 = row.createCell(3);
            cell4.setCellValue(account.getPassword());
        }
        FileOutputStream fos = new FileOutputStream("Account.xlsx");
        workbook.write(fos);
        workbook.close();
    }

    public List<Account> readExcel(List<Account> accounts) throws IOException {
        FileInputStream excel = new FileInputStream(new File("Account.xlsx"));
        Workbook workbook = new XSSFWorkbook(excel);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();
        Row firstRow = iterator.next();
       // Cell firstCell = firstRow.getCell(0);
        while (iterator.hasNext()){
            Row currentRow = iterator.next();
            Account account = new Account(
                    currentRow.getCell(1).getStringCellValue(),currentRow.getCell(2).getStringCellValue(),currentRow.getCell(3).getStringCellValue());
            accounts.add(account);
        }
        return accounts;
    }

}
