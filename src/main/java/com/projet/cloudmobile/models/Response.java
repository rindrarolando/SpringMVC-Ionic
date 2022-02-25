package com.projet.cloudmobile.models;

public class Response {
    String status;
    String message;
    String datas;

    public Response(String status, String message, String datas) {
        this.status = status;
        this.message = message;
        this.datas = datas;
    }

    public Response() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getDatas() {
        return datas;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }
}
