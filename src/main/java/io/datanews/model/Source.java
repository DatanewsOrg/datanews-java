package io.datanews.model;

import org.jetbrains.annotations.Nullable;

import lombok.NonNull;
import lombok.Value;

@Value
public class Source {
  @NonNull
  String description;

  @NonNull
  String url;

  @Nullable
  Country country;

  @Nullable
  Language language;
}
