package com.example.studytracker;

public class uploadPDF {

    public String uploadName;
    public String url;

    public uploadPDF() {

    }

    public uploadPDF(String uploadName, String url) {
        this.uploadName = uploadName;
        this.url = url;
    }

    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
