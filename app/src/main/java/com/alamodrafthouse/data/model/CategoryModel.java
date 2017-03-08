package com.alamodrafthouse.data.model;

public class CategoryModel {
  private String apiurl;

  private String id;

  private String name;

  private String url;

  public String getApiurl() {
    return apiurl;
  }

  public void setApiurl(String apiurl) {
    this.apiurl = apiurl;
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

  @Override public String toString() {
    return "ClassPojo [apiurl = " + apiurl
        + ", id = "
        + id
        + ", name = "
        + name
        + ", url = "
        + url
        + "]";
  }
}

