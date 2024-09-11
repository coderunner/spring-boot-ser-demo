package org.inf5190.serialization.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public record Book(
        @JacksonXmlProperty(localName = "Title") String title,
        @JacksonXmlProperty(localName = "Author") String author,
        @JacksonXmlProperty(localName = "Year") Integer year) {
}