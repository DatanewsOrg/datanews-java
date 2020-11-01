package io.datanews.search;

import org.junit.jupiter.api.Test;

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

public class HeadlinesParamsTest {
  @Test
  void testIncorrectParameterValues() {
    // Size can be one of {10, 25, 100}.
    assertThrows(IllegalArgumentException.class, () -> HeadlinesParams
        .builder()
        .size(30)
        .build());

    // Page cannot be negative.
    assertThrows(IllegalArgumentException.class, () -> HeadlinesParams
        .builder()
        .page(-1)
        .build());

    // Test null values in countries.
    assertThrows(NullPointerException.class, () -> HeadlinesParams
        .builder()
        .countries(null)
        .build());

    assertThrows(NullPointerException.class, () -> HeadlinesParams
        .builder()
        .countries(Collections.singletonList(null))
        .build());

    assertThrows(NullPointerException.class, () -> HeadlinesParams
        .builder()
        .country(null)
        .build());

    // Test null values in topics.
    assertThrows(NullPointerException.class, () -> HeadlinesParams
        .builder()
        .topics(null)
        .build());

    assertThrows(NullPointerException.class, () -> HeadlinesParams
        .builder()
        .topics(Collections.singletonList(null))
        .build());

    assertThrows(NullPointerException.class, () -> HeadlinesParams
        .builder()
        .topic(null)
        .build());

    // Test null values in sources.
    assertThrows(NullPointerException.class, () -> HeadlinesParams
        .builder()
        .sources(null)
        .build());

    assertThrows(NullPointerException.class, () -> HeadlinesParams
        .builder()
        .sources(Collections.singletonList(null))
        .build());

    assertThrows(NullPointerException.class, () -> HeadlinesParams
        .builder()
        .source(null)
        .build());

    // Test null values in languages.
    assertThrows(NullPointerException.class, () -> HeadlinesParams
        .builder()
        .languages(null)
        .build());

    assertThrows(NullPointerException.class, () -> HeadlinesParams
        .builder()
        .languages(Collections.singletonList(null))
        .build());

    assertThrows(NullPointerException.class, () -> HeadlinesParams
        .builder()
        .language(null)
        .build());
  }

  @Test
  void testRawParams() {
    HeadlinesParams params = HeadlinesParams
        .builder()
        .query("SomeQuery")
        .languages(Arrays.asList(Language.DA, Language.SV))
        .source("SomeSource")
        .country(Country.US)
        .country(Country.AT)
        .page(4)
        .sortBy(SortBy.RELEVANCE)
        .build();

    Map<String, Set<String>> expected = new HashMap<>();
    expected.put("country", new HashSet<>(Arrays.asList(Country.US.getUrlName(), Country.AT.getUrlName())));
    expected.put("page", Collections.singleton("4"));
    expected.put("source", Collections.singleton("SomeSource"));
    expected.put("language", new HashSet<>(Arrays.asList(Language.DA.getUrlName(), Language.SV.getUrlName())));
    expected.put("q", Collections.singleton("SomeQuery"));
    expected.put("sortBy", Collections.singleton(SortBy.RELEVANCE.getUrlName()));

    assertEquals(expected, params.getRawParams());
  }
}
