package io.datanews.search;

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

@Value
@Builder
public class NewsParams {
  String query;
  @Singular Set<String> sources;
  @Singular Set<Country> countries;
  @Singular Set<Language> languages;
  @Singular Set<Topic> topics;
  Integer page;
  Integer size;
  SortBy sortBy;
  LocalDate fromDate;
  LocalDate toDate;

  public NewsParams(
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

  public Map<String, Set<String>> getRawParams() {
    return SearchParamUtil.rawSearchParams(query, sources, countries, languages, topics, page, size, sortBy, fromDate, toDate);
  }
}
