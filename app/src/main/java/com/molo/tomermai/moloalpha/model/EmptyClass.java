package com.molo.tomermai.moloalpha.model;


import java.io.Serializable;

public class EmptyClass implements Serializable {
    private String m_className;
    private int m_noise;
    private int m_population;
    private int m_timeLeft;
    private String m_imageURL;

    public EmptyClass(String className,
                      int classSoundLevel,
                      int classPopulation,
                      int timeleft,
                      String imageURL) {

        this.m_className = className;
        this.m_noise = classSoundLevel;
        this.m_population = classPopulation;
        this.m_timeLeft = timeleft;
        this.m_imageURL = imageURL;
    }

    public String getClassName() {
        return m_className;
    }

    public int getClassSoundLevel() {
        return m_noise;
    }

    public int getClassPopulation() {
        return m_population;
    }

    public int getTimeLeft() {
        return m_timeLeft;
    }

    public String getImageURL() {
        return m_imageURL;
    }

    public void setM_className(String m_className) {
        this.m_className = m_className;
    }

    public void setM_noise(int m_noise) {
        this.m_noise = m_noise;
    }

    public void setM_population(int m_population) {
        this.m_population = m_population;
    }

    public void setM_timeLeft(int m_timeLeft) {
        this.m_timeLeft = m_timeLeft;
    }

    public void setM_imageURL(String m_imageURL) {
        this.m_imageURL = m_imageURL;
    }
}
