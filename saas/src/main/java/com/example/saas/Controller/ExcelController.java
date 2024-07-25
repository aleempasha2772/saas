package com.example.saas.Controller;

import com.example.saas.Helper.ExcelHelper;
import com.example.saas.Model.YourEntity;
import com.example.saas.Service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {

    @Autowired
    ExcelService excelService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                excelService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @PostMapping("/read")
    public ResponseEntity<List<YourEntity>> readExcelData(@RequestParam("file") MultipartFile file) {
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                List<YourEntity> entities = excelService.readExcelData(file);
                return ResponseEntity.status(HttpStatus.OK).body(entities);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/get")
    public ResponseEntity<List<YourEntity>> getExcelData(){
        try{
            List<YourEntity> entity = excelService.getAllItems();
            if (entity.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok().body(entity);
        }catch (Exception E){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadExcel() {
        ByteArrayInputStream in = excelService.exportToExcel();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=entities.xlsx");

        return ResponseEntity.ok().headers(headers).body(in.readAllBytes());
    }
}
