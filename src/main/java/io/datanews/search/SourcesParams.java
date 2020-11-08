package io.datanews.search;

import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import io.datanews.model.Country;
import io.datanews.model.Language;
import io.datanews.model.Topic;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

/**
 * This class represents parameters for the HTTP GET request to {@code sources} endpoint.
 * <p>
 * This class is immutable. This means that any attempt to modify any of its container fields will result in
 * an {@link UnsupportedOperationException}.
 * <p>
 * For more info, check out <a href="https://datanews.io/docs/sources">Sources API docs</a>.
 */
@Value
@Builder
public class SourcesParams {
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
   * {@link IllegalArgumentException} is thrown from {@link SourcesParamsBuilder#build()} if
   * this is negative.
   */
  @Nullable
  Integer page;

  /**
   * Corresponds to {@code size} parameter.
   *
   * {@link IllegalArgumentException} is thrown from {@link SourcesParamsBuilder#build()} if
   * this is not in the range [1; 100].
   */
  @Nullable
  Integer size;

  private SourcesParams(
      @NonNull Set<Country> countries,
      @NonNull Set<Language> languages,
      @NonNull Set<Topic> topics,
      @Nullable Integer page,
      @Nullable Integer size
  ) {
    SearchParamUtil.validateSearchParams(Collections.emptySet(), countries, languages, topics, page, size);
    this.countries = countries;
    this.languages = languages;
    this.topics = topics;
    this.page = page;
    this.size = size;
  }

  Map<String, Set<String>> getRawParams() {
    return SearchParamUtil.rawSearchParams(
        null, Collections.emptySet(), countries, languages, topics, page, size, null, null, null);
  }
}
