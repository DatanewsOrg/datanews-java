package io.datanews.model;

import com.google.gson.annotations.SerializedName;

public enum Language {
  @SerializedName("en") EN,
  @SerializedName("de") DE,
  @SerializedName("it") IT,
  @SerializedName("fr") FR,
  @SerializedName("nl") NL,
  @SerializedName("sv") SV,
  @SerializedName("da") DA,
  @SerializedName("fi") FI,
  @SerializedName("hu") HU,
  @SerializedName("no") NO,
  @SerializedName("pl") PL,
  @SerializedName("ru") RU,
  @SerializedName("uk") UK,
  @SerializedName("pt") PT,
  @SerializedName("es") ES;

  public String getUrlName() {
    return name().toLowerCase();
  }
}
