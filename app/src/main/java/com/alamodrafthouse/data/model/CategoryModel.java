package com.alamodrafthouse.data.model;

public class CategoryModel {
    private String api_url;

    private String id;

    private String name;

    private String url;

    public String getApi_url() {
        return api_url;
    }

    public void setApi_url(String api_url) {
        this.api_url = api_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ClassPojo [api_url = " + api_url + ", id = " + id + ", name = " + name + ", url = " + url + "]";
    }
}

