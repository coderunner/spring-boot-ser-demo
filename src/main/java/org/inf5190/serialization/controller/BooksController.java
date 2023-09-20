package org.inf5190.serialization.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.inf5190.serialization.model.Book;
import org.inf5190.serialization.model.BookList;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@RestController
public class BooksController {
    static public final List<Book> BOOKS = new ArrayList<>(Arrays.asList(
            new Book("Le Hobbit", "J.R.R. Tolkien", 1937),
            new Book("La communauté de l'anneau", "J.R.R. Tolkien", 1954),
            new Book("Les deux tours", "J.R.R. Tolkien", 1954),
            new Book("Le retour du roi", "J.R.R. Tolkien", 1955)));

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getBooksJson() {
        try {
            ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = objectWriter.writeValueAsString(new BookList(BOOKS));

            final HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<String>(json, httpHeaders, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getBooksXml() {
        try {
            XmlMapper mapper = new XmlMapper();
            String xml = mapper.writeValueAsString(new BookList(BOOKS));

            final HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_XML);
            return new ResponseEntity<String>(xml, httpHeaders, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postBooksJson(@RequestBody Book book) {
        try {
            BOOKS.add(book);
            ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = objectWriter.writeValueAsString(book);

            final HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<String>(json, httpHeaders, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/books", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> postBooksXml(@RequestBody Book book) {
        try {
            BOOKS.add(book);
            XmlMapper mapper = new XmlMapper();
            String xml = mapper.writeValueAsString(book);

            final HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_XML);
            return new ResponseEntity<String>(xml, httpHeaders, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
