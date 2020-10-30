package io.datanews.model;

import lombok.NonNull;
import lombok.Value;

@Value
public class Source {
  @NonNull String description;
  @NonNull String url;
  Country country;
  Language language;
}
