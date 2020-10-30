package io.datanews.search;

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

@Value
@Builder
public class SourcesParams {
  @Singular Set<Country> countries;
  @Singular Set<Language> languages;
  @Singular Set<Topic> topics;
  Integer page;
  Integer size;

  private SourcesParams(
      @NonNull Set<Country> countries,
      @NonNull Set<Language> languages,
      @NonNull Set<Topic> topics,
      Integer page,
      Integer size
  ) {
    SearchParamUtil.validateSearchParams(Collections.emptySet(), countries, languages, topics, page, size);
    this.countries = countries;
    this.languages = languages;
    this.topics = topics;
    this.page = page;
    this.size = size;
  }

  public Map<String, Set<String>> getRawParams() {
    return SearchParamUtil.rawSearchParams(
        null, Collections.emptySet(), countries, languages, topics, page, size, null, null, null);
  }
}
