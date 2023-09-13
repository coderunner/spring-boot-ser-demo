package org.inf5190.serialization.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class BookList {
    @JacksonXmlElementWrapper(localName = "Books")
    @JacksonXmlProperty(localName = "Book")
    public List<Book> books;

    public BookList(List<Book> books) {
        this.books = books;
    }

    @JsonProperty("count")
    public int getCount() {
        return books.size();
    }

}
