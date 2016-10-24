package com.cloudbees.groovy.cps.impl;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.HashMap;

class Locals extends HashMap<String,Object> implements Externalizable {
    public Locals() {
        super();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(size());
        for (Entry<String, Object> entry : entrySet()) {
            out.writeObject(entry.getKey());
            try {
                out.writeObject(entry.getValue());
            } catch (IOException e) {
                throw new FieldSerializationException(entry.getKey(), e);
            }
        }
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        long size = (Integer)in.readObject();
        for (int i = 0; i < size; i++) {
            String key = (String)in.readObject();
            Object value = in.readObject();
            put(key, value);
        }
    }
}
