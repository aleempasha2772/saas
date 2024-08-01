package com.example.file_ms.Service;

import com.example.file_ms.Model.FileDetailsDTO;
import com.example.file_ms.Model.FileDocument;
import com.example.file_ms.Repository.FileRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public FileDocument storeFile(MultipartFile file) throws IOException{
        FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName(file.getOriginalFilename());
        fileDocument.setFileType(file.getContentType());
        fileDocument.setData(file.getBytes());
        fileDocument.setDateAndTime(LocalDateTime.now());

        return fileRepository.save(fileDocument);
    }

    public FileDocument getFile(String id){
        return fileRepository.findById(id).orElse(null);
    }

    public void deleteFile(String id){
        fileRepository.deleteById(id);
    }


    public Map<Integer, String> getAllFileNames() {
        List<FileDocument> files = fileRepository.findAll();
        Map<Integer, String> fileNamesMap = new HashMap<>();
        for (int i = 0; i < files.size(); i++) {
            fileNamesMap.put(i + 1, files.get(i).getFileName());
        }
        return fileNamesMap;
    }

    /*
    public List<List<String>> readExcelFile(String id) throws IOException {
        FileDocument fileDocument = getFile(id);
        if (fileDocument == null || !fileDocument.getFileType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            throw new IllegalArgumentException("File not found or not an Excel file");
        }

        try (Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(fileDocument.getData()))) {
            List<List<String>> sheetData = new ArrayList<>();
            Sheet sheet = workbook.getSheetAt(0); // Assuming you want to read the first sheet

            for (Row row : sheet) {
                List<String> rowData = new ArrayList<>();
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            rowData.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                rowData.add(cell.getDateCellValue().toString());
                            } else {
                                rowData.add(String.valueOf(cell.getNumericCellValue()));
                            }
                            break;
                        case BOOLEAN:
                            rowData.add(String.valueOf(cell.getBooleanCellValue()));
                            break;
                        case FORMULA:
                            rowData.add(cell.getCellFormula());
                            break;
                        default:
                            rowData.add("");
                    }
                }
                sheetData.add(rowData);
            }
            return sheetData;
        }
    }


     */
    public Map<String, List<List<String>>> readExcelFile(String id) throws IOException {
        FileDocument fileDocument = getFile(id);
        if (fileDocument == null || !fileDocument.getFileType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            throw new IllegalArgumentException("File not found or not an Excel file");
        }

        try (Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(fileDocument.getData()))) {
            Map<String, List<List<String>>> allSheetData = new HashMap<>();

            for (Sheet sheet : workbook) {
                List<List<String>> sheetData = new ArrayList<>();
                for (Row row : sheet) {
                    List<String> rowData = new ArrayList<>();
                    for (Cell cell : row) {
                        switch (cell.getCellType()) {
                            case STRING:
                                rowData.add(cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    rowData.add(cell.getDateCellValue().toString());
                                } else {
                                    rowData.add(String.valueOf(cell.getNumericCellValue()));
                                }
                                break;
                            case BOOLEAN:
                                rowData.add(String.valueOf(cell.getBooleanCellValue()));
                                break;
                            case FORMULA:
                                rowData.add(cell.getCellFormula());
                                break;
                            default:
                                rowData.add("");
                        }
                    }
                    sheetData.add(rowData);
                }
                allSheetData.put(sheet.getSheetName(), sheetData);
            }
            return allSheetData;
        }
    }
    public List<List<String>> readCsvFile(String id) throws IOException {
        FileDocument fileDocument = getFile(id);
        if (fileDocument == null || !fileDocument.getFileType().equals("text/csv")) {
            throw new IllegalArgumentException("File not found or not a CSV file");
        }

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(new ByteArrayInputStream(fileDocument.getData())))) {
            List<List<String>> csvData = new ArrayList<>();
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                List<String> rowData = new ArrayList<>();
                for (String value : values) {
                    rowData.add(value);
                }
                csvData.add(rowData);
            }
            return csvData;
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public List<FileDetailsDTO> getAllFileDetais(){

        List<FileDocument> files = fileRepository.findAll();
        List<FileDetailsDTO> fileDetailsList= new ArrayList<>();
        for(int i=0;i<files.size();i++){
            FileDocument fileDocument = files.get(i);
            FileDetailsDTO fileDetailsDTO = new FileDetailsDTO();
            fileDetailsDTO.setId(i+1);
            fileDetailsDTO.setFileName(fileDocument.getFileName());
            fileDetailsDTO.setFileType(fileDocument.getFileType());
            fileDetailsDTO.setUploadDateTime(fileDocument.getDateAndTime());
            fileDetailsList.add(fileDetailsDTO);
        }
        return fileDetailsList;
    }


}
