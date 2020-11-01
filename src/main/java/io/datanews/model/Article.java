package io.datanews.model;

import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.List;

import lombok.NonNull;
import lombok.Value;

@Value
public class Article {
  @NonNull
  String url;

  @NonNull
  String source;

  @NonNull
  List<String> authors;

  @NonNull
  String title;

  @NonNull
  Date pubDate;

  @Nullable
  Country country;

  @Nullable
  Language language;

  @NonNull
  String description;

  // There is a bug in the back-end that may cause this value to be null.
  @Nullable
  String imageUrl;

  @NonNull
  String content;
}

