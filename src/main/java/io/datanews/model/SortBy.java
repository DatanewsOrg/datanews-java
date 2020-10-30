package io.datanews.model;

import com.google.gson.annotations.SerializedName;

public enum SortBy {
  @SerializedName("date")
  DATE,

  @SerializedName("relevance")
  RELEVANCE;

  public String getUrlName() {
    return name().toLowerCase();
  }
}
