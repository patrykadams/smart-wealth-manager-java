package transactionproj.com.wealth.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import transactionproj.com.wealth.model.Transaction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles Data Persistence using Modern Java I/O (NIO) and Jackson.
 * * COMPETENCY CHECK:
 * - Uses 'Path' and 'Files' (Modern NIO) instead of legacy 'File'.
 * - Uses Dependency Injection principles for the filename.
 * - Handles Checked Exceptions explicitly (a key difference from C#).
 */
public class JsonRepository {
    private final Path filePath;
    private final ObjectMapper mapper;

    public JsonRepository(String filename) {
        this.filePath = Paths.get(filename);
        this.mapper = new ObjectMapper();
        
        // Register the JavaTimeModule to handle LocalDateTime correctly in JSON
        this.mapper.registerModule(new JavaTimeModule());
        
        // Enable pretty-printing for human-readable JSON files
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Saves a list of transactions to disk.
     * Uses NIO 'writeString' for efficient I/O operations.
     */
    public void save(List<Transaction> transactions) {
        try {
            // Ensure the directory exists before writing
            if (filePath.getParent() != null) {
                Files.createDirectories(filePath.getParent());
            }
            
            // Convert Object -> JSON String
            String json = mapper.writeValueAsString(transactions);
            
            // Write to file: Create if missing, Truncate if exists
            Files.writeString(filePath, json, 
                StandardOpenOption.CREATE, 
                StandardOpenOption.TRUNCATE_EXISTING);
                
            System.out.println("LOG: Data successfully saved to " + filePath.toAbsolutePath());
            
        } catch (IOException e) {
            
            throw new RuntimeException("CRITICAL: Failed to save transaction data.", e);
        }
    }

    /**
     * Loads transactions from disk.
     * Returns an empty list if the file does not exist yet.
     */
    public List<Transaction> load() {
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }

        try {
            // Read file content
            String json = Files.readString(filePath);
            
            // Convert JSON String -> List<Transaction>
            // We use TypeReference to handle the Generic List type erasure
            return mapper.readValue(json, new TypeReference<List<Transaction>>() {});
            
        } catch (IOException e) {
            throw new RuntimeException("CRITICAL: Failed to load transaction data.", e);
        }
    }
}