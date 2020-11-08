package io.datanews.search;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import io.datanews.model.Country;
import io.datanews.model.Language;
import io.datanews.model.SortBy;
import io.datanews.model.Topic;
import lombok.NonNull;

final class SearchParamUtil {
  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  private SearchParamUtil() {
  }

  static void requireElementsNotNull(@NonNull Collection<?> collection, String message) {
    for (Object element : collection) {
      Objects.requireNonNull(element, message);
    }
  }

  static void validateSearchParams(
      @NonNull Set<String> sources,
      @NonNull Set<Country> countries,
      @NonNull Set<Language> languages,
      @NonNull Set<Topic> topics,
      Integer page,
      Integer size
  ) {
    requireElementsNotNull(sources, "'sources' can't be null");
    requireElementsNotNull(countries, "'countries' can't be null");
    requireElementsNotNull(languages, "'languages' can't be null");
    requireElementsNotNull(topics, "'topics' can't be null");

    if (page != null && page < 0) {
      throw new IllegalArgumentException("'page' must be a positive integer or 0");
    }

    if (size != null && (size < 1 || size > 100)) {
      throw new IllegalArgumentException("'size' can only be in the range [1; 100]");
    }
  }

  static Map<String, Set<String>> rawSearchParams(
      String query,
      @NonNull Set<String> sources,
      @NonNull Set<Country> countries,
      @NonNull Set<Language> languages,
      @NonNull Set<Topic> topics,
      Integer page,
      Integer size,
      SortBy sortBy,
      LocalDate fromDate,
      LocalDate toDate
  ) {
    Map<String, Set<String>> result = new HashMap<>();

    if (query != null) {
      result.put("q", Collections.singleton(query));
    }

    if (page != null) {
      result.put("page", Collections.singleton(page.toString()));
    }

    if (size != null) {
      result.put("size", Collections.singleton(size.toString()));
    }

    if (sortBy != null) {
      result.put("sortBy", Collections.singleton(sortBy.getUrlName()));
    }

    if (fromDate != null) {
      result.put("from", Collections.singleton(fromDate.format(DATE_FORMAT)));
    }

    if (toDate != null) {
      result.put("to", Collections.singleton(toDate.format(DATE_FORMAT)));
    }

    if (!sources.isEmpty()) {
      result.put("source", sources);
    }

    if (!countries.isEmpty()) {
      result.put("country", countries.stream().map(Country::getUrlName).collect(Collectors.toSet()));
    }

    if (!languages.isEmpty()) {
      result.put("language", languages.stream().map(Language::getUrlName).collect(Collectors.toSet()));
    }

    if (!topics.isEmpty()) {
      result.put("topic", topics.stream().map(Topic::getUrlName).collect(Collectors.toSet()));
    }

    return result;
  }
}
