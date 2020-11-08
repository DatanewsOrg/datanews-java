package io.datanews.search;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import io.datanews.model.Country;
import io.datanews.model.Language;
import io.datanews.model.SortBy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NewsParamsTest {
  @Test
  void testIncorrectParameterValues() {
    // Size can be in the range [1; 100].
    assertThrows(IllegalArgumentException.class, () -> NewsParams
        .builder()
        .size(0)
        .build());

    assertThrows(IllegalArgumentException.class, () -> NewsParams
        .builder()
        .size(101)
        .build());

    // Page cannot be negative.
    assertThrows(IllegalArgumentException.class, () -> NewsParams
        .builder()
        .page(-1)
        .build());

    // Test null values in countries.
    assertThrows(NullPointerException.class, () -> NewsParams
        .builder()
        .countries(null)
        .build());

    assertThrows(NullPointerException.class, () -> NewsParams
        .builder()
        .countries(Collections.singletonList(null))
        .build());

    assertThrows(NullPointerException.class, () -> NewsParams
        .builder()
        .country(null)
        .build());

    // Test null values in topics.
    assertThrows(NullPointerException.class, () -> NewsParams
        .builder()
        .topics(null)
        .build());

    assertThrows(NullPointerException.class, () -> NewsParams
        .builder()
        .topics(Collections.singletonList(null))
        .build());

    assertThrows(NullPointerException.class, () -> NewsParams
        .builder()
        .topic(null)
        .build());

    // Test null values in sources.
    assertThrows(NullPointerException.class, () -> NewsParams
        .builder()
        .sources(null)
        .build());

    assertThrows(NullPointerException.class, () -> NewsParams
        .builder()
        .sources(Collections.singletonList(null))
        .build());

    assertThrows(NullPointerException.class, () -> NewsParams
        .builder()
        .source(null)
        .build());

    // Test null values in languages.
    assertThrows(NullPointerException.class, () -> NewsParams
        .builder()
        .languages(null)
        .build());

    assertThrows(NullPointerException.class, () -> NewsParams
        .builder()
        .languages(Collections.singletonList(null))
        .build());

    assertThrows(NullPointerException.class, () -> NewsParams
        .builder()
        .language(null)
        .build());
  }

  @Test
  void testRawParams() {
    NewsParams params = NewsParams
        .builder()
        .query("SomeQuery")
        .languages(Arrays.asList(Language.DA, Language.SV))
        .source("SomeSource")
        .country(Country.US)
        .country(Country.AT)
        .page(4)
        .sortBy(SortBy.RELEVANCE)
        .fromDate(LocalDate.of(2020, 2, 13))
        .build();

    Map<String, Set<String>> expected = new HashMap<>();
    expected.put("country", new HashSet<>(Arrays.asList(Country.US.getUrlName(), Country.AT.getUrlName())));
    expected.put("page", Collections.singleton("4"));
    expected.put("source", Collections.singleton("SomeSource"));
    expected.put("language", new HashSet<>(Arrays.asList(Language.DA.getUrlName(), Language.SV.getUrlName())));
    expected.put("q", Collections.singleton("SomeQuery"));
    expected.put("sortBy", Collections.singleton(SortBy.RELEVANCE.getUrlName()));
    expected.put("from", Collections.singleton("2020-02-13"));

    assertEquals(expected, params.getRawParams());
  }
}
