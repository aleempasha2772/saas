package com.example.saas.Service;

import com.example.saas.Helper.ExcelHelper;
import com.example.saas.Model.YourEntity;
import com.example.saas.Repository.YourEntityRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    private YourEntityRepository repository;

    public void save(MultipartFile file) {
        try {
            List<YourEntity> entities = ExcelHelper.excelToEntities(file.getInputStream());
            repository.saveAll(entities);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store Excel data: " + e.getMessage());
        }
    }

    public List<YourEntity> readExcelData(MultipartFile file) {
        try {
            return ExcelHelper.excelToEntities(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read Excel data: " + e.getMessage());
        }
    }
    
    public List<YourEntity> getAllItems(){
        return repository.findAll();
    }

    public ByteArrayInputStream exportToExcel() {
        List<YourEntity> entities = repository.findAll();

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Entities");

            // Header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Column1");
            headerRow.createCell(1).setCellValue("Column2");
            // Add more headers as needed

            // Data
            int rowIdx = 1;
            for (YourEntity entity : entities) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(entity.getColumn1());
                row.createCell(1).setCellValue(entity.getColumn2());
                // Add more columns as needed
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to export data to Excel file: " + e.getMessage());
        }
    }
}
