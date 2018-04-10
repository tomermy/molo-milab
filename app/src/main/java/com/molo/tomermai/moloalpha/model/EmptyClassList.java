package com.molo.tomermai.moloalpha.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmptyClassList implements Serializable {
    private List<EmptyClass> classes = new ArrayList<>();

    public EmptyClassList(List<EmptyClass> classes) {
        this.classes = classes;
    }

    public List<EmptyClass> getClasses() {
        return classes;
    }
}
