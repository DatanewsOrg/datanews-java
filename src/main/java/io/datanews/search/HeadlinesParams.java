package io.datanews.search;

import java.util.Collections;
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

@Value
@Builder
public class HeadlinesParams {
  String query;
  @Singular Set<String> sources;
  @Singular Set<Country> countries;
  @Singular Set<Language> languages;
  @Singular Set<Topic> topics;
  Integer page;
  Integer size;
  SortBy sortBy;

  private HeadlinesParams(
      String query,
      @NonNull Set<String> sources,
      @NonNull Set<Country> countries,
      @NonNull Set<Language> languages,
      @NonNull Set<Topic> topics,
      Integer page,
      Integer size,
      SortBy sortBy
  ) {
    SearchParamUtil.validateSearchParams(sources, countries, languages, topics, page, size);
    this.query = query;
    this.sources = Collections.unmodifiableSet(sources);
    this.countries = Collections.unmodifiableSet(countries);
    this.languages = Collections.unmodifiableSet(languages);
    this.topics = Collections.unmodifiableSet(topics);
    this.page = page;
    this.size = size;
    this.sortBy = sortBy;
  }

  public Map<String, Set<String>> getRawParams() {
    return SearchParamUtil.rawSearchParams(query, sources, countries, languages, topics, page, size, sortBy, null, null);
  }
}
