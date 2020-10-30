package io.datanews.model;

import java.util.Date;
import java.util.List;

import lombok.NonNull;
import lombok.Value;

@Value
public class Article {
  @NonNull String url;
  @NonNull String source;
  @NonNull List<String> authors;
  @NonNull String title;
  @NonNull Date pubDate;
  @NonNull Country country;
  @NonNull Language language;
  @NonNull String description;
  @NonNull String imageUrl;
  @NonNull String content;
}
