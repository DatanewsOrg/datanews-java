package io.datanews.search;

import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import io.datanews.model.Country;
import io.datanews.model.Language;
import io.datanews.model.SortBy;
import io.datanews.model.Topic;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

/**
 * This class represents parameters for the HTTP GET request to {@code news} endpoint.
 * <p>
 * This class is immutable. This means that any attempt to modify any of its container fields will result in
 * an {@link UnsupportedOperationException}.
 * <p>
 * For more info, check out <a href="https://datanews.io/docs/news">News API docs</a>.
 */
@Value
@Builder
public class NewsParams {
  /** Query parameter. Corresponds to {@code q} URL parameter. */
  @Nullable
  String query;

  /**
   * Corresponds to {@code source} URL parameter. This may have more than one value.
   * {@link NullPointerException} is thrown if it contains nulls.
   */
  @NonNull
  @Singular
  Set<String> sources;

  /**
   * Corresponds to {@code country} URL parameter. This may have more than one value.
   * {@link NullPointerException} is thrown if it contains nulls.
   */
  @NonNull
  @Singular
  Set<Country> countries;

  /**
   * Corresponds to {@code language} URL parameter. This may have more than one value.
   * {@link NullPointerException} is thrown if it contains nulls.
   */
  @NonNull
  @Singular
  Set<Language> languages;

  /**
   * Corresponds to {@code topic} URL parameter. This may have more than one value.
   * {@link NullPointerException} is thrown if it contains nulls.
   */
  @NonNull
  @Singular
  Set<Topic> topics;

  /**
   * Corresponds to {@code page} parameter.
   *
   * {@link IllegalArgumentException} is thrown from {@link NewsParamsBuilder#build()} if
   * this is negative.
   */
  @Nullable
  Integer page;

  /**
   * Corresponds to {@code size} parameter.
   *
   * {@link IllegalArgumentException} is thrown from {@link NewsParamsBuilder#build()} if
   * this is not in the range [1; 100].
   */
  @Nullable
  Integer size;

  /** Corresponds to {@code sortBy} parameter. */
  @Nullable
  SortBy sortBy;

  /** Corresponds to {@code from} parameter. */
  @Nullable
  LocalDate fromDate;

  /** Corresponds to {@code to} parameter. */
  @Nullable
  LocalDate toDate;

  private NewsParams(
      @Nullable String query,
      @NonNull Set<String> sources,
      @NonNull Set<Country> countries,
      @NonNull Set<Language> languages,
      @NonNull Set<Topic> topics,
      @Nullable Integer page,
      @Nullable Integer size,
      @Nullable SortBy sortBy,
      @Nullable LocalDate fromDate,
      @Nullable LocalDate toDate
  ) {
    SearchParamUtil.validateSearchParams(sources, countries, languages, topics, page, size);
    this.query = query;
    this.sources = sources;
    this.countries = countries;
    this.languages = languages;
    this.topics = topics;
    this.page = page;
    this.size = size;
    this.sortBy = sortBy;
    this.fromDate = fromDate;
    this.toDate = toDate;
  }

  Map<String, Set<String>> getRawParams() {
    return SearchParamUtil.rawSearchParams(query, sources, countries, languages, topics, page, size, sortBy, fromDate,
        toDate);
  }
}
