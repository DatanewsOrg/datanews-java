package io.datanews.model;

import com.google.gson.annotations.SerializedName;

public enum Topic {
  @SerializedName("general")
  GENERAL,

  @SerializedName("business")
  BUSINESS,

  @SerializedName("tech")
  TECH,

  @SerializedName("entertainment")
  ENTERTAINMENT,

  @SerializedName("sports")
  SPORTS,

  @SerializedName("science")
  SCIENCE,

  @SerializedName("health")
  HEALTH;

  public String getUrlName() {
    return name().toLowerCase();
  }
}
