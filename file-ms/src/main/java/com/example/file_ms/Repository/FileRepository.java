package com.example.file_ms.Repository;

import com.example.file_ms.Model.FileDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepository extends MongoRepository<FileDocument,String> {

}
