package com.cloudbees.groovy.cps.impl;

import java.io.IOException;

public class FieldSerializationException extends IOException {
    private final String field;

    public FieldSerializationException(String field, IOException cause) {
        super("Failed to serialize field " + field, cause);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
