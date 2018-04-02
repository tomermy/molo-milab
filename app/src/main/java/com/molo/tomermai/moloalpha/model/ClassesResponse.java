package com.molo.tomermai.moloalpha.model;


import java.util.List;

public class ClassesResponse {
    private boolean isError;
    private boolean isEmpty;
    private int numOfClasses;
    private List<EmptyClass> emptyClasses;

    public ClassesResponse(boolean isError, boolean isEmpty, int numOfClasses, List<EmptyClass> emptyClasses) {
        this.isError = isError;
        this.isEmpty = isEmpty;
        this.numOfClasses = numOfClasses;
        this.emptyClasses = emptyClasses;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public void setNumOfClasses(int numOfClasses) {
        this.numOfClasses = numOfClasses;
    }

    public void setEmptyClasses(List<EmptyClass> emptyClasses) {
        this.emptyClasses = emptyClasses;
    }

    public boolean isError() {
        return isError;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public int getNumOfClasses() {
        return numOfClasses;
    }

    public List<EmptyClass> getEmptyClasses() {
        return emptyClasses;
    }
}
