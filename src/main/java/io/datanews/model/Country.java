package io.datanews.model;

import com.google.gson.annotations.SerializedName;

public enum Country {
  /** Australia */
  @SerializedName("at") AT,
  /** Austria */
  @SerializedName("au") AU,
  /** Brazil */
  @SerializedName("br") BR,
  /** Canada */
  @SerializedName("ca") CA,
  /** Denmark */
  @SerializedName("dk") DK,
  /** Finland */
  @SerializedName("fi") FI,
  /** France */
  @SerializedName("fr") FR,
  /** Germany */
  @SerializedName("de") DE,
  /** Great Britain */
  @SerializedName("gb") GB,
  /** Greece */
  @SerializedName("gr") GR,
  /** Hungary */
  @SerializedName("hu") HU,
  /** India */
  @SerializedName("in") IN,
  /** Ireland */
  @SerializedName("ie") IE,
  /** Italy */
  @SerializedName("it") IT,
  /** Mexico */
  @SerializedName("mx") MX,
  /** Morocco */
  @SerializedName("ma") MA,
  /** Netherlands */
  @SerializedName("nl") NL,
  /** New Zealand */
  @SerializedName("nz") NZ,
  /** Norway */
  @SerializedName("no") NO,
  /** Poland */
  @SerializedName("pl") PL,
  /** Portugal */
  @SerializedName("pt") PT,
  /** Russia */
  @SerializedName("ru") RU,
  /** Sweden */
  @SerializedName("se") SE,
  /** Switzerland */
  @SerializedName("ch") CH,
  /** Ukraine */
  @SerializedName("ua") UA,
  /** USA */
  @SerializedName("us") US,
  /** Venezuela */
  @SerializedName("ve") VE;

  public String getUrlName() {
    return name().toLowerCase();
  }
}
