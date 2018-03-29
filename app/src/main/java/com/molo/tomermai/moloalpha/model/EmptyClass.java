package com.molo.tomermai.moloalpha.model;


import java.io.Serializable;

public class EmptyClass implements Serializable {
    private String m_className;
    private int m_classSoundLevel;
    private int m_classPopulation;

    public EmptyClass(String className, int classSoundLevel, int classPopulation) {
        this.m_className = className;
        this.m_classSoundLevel = classSoundLevel;
        this.m_classPopulation = classPopulation;
    }

    public String getClassName() {
        return m_className;
    }

    public int getClassSoundLevel() {
        return m_classSoundLevel;
    }

    public int getClassPopulation() {
        return m_classPopulation;
    }
}
