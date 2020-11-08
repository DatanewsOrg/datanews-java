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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SourcesParamsTest {
  @Test
  void testIncorrectParameterValues() {
    // Size can be in the range [1; 100].
    assertThrows(IllegalArgumentException.class, () -> SourcesParams
        .builder()
        .size(0)
        .build());

    assertThrows(IllegalArgumentException.class, () -> SourcesParams
        .builder()
        .size(101)
        .build());

    // Page cannot be negative.
    assertThrows(IllegalArgumentException.class, () -> SourcesParams
        .builder()
        .page(-1)
        .build());

    // Test null values in countries.
    assertThrows(NullPointerException.class, () -> SourcesParams
        .builder()
        .countries(null)
        .build());

    assertThrows(NullPointerException.class, () -> SourcesParams
        .builder()
        .countries(Collections.singletonList(null))
        .build());

    assertThrows(NullPointerException.class, () -> SourcesParams
        .builder()
        .country(null)
        .build());

    // Test null values in topics.
    assertThrows(NullPointerException.class, () -> SourcesParams
        .builder()
        .topics(null)
        .build());

    assertThrows(NullPointerException.class, () -> SourcesParams
        .builder()
        .topics(Collections.singletonList(null))
        .build());

    assertThrows(NullPointerException.class, () -> SourcesParams
        .builder()
        .topic(null)
        .build());

    // Test null values in languages.
    assertThrows(NullPointerException.class, () -> SourcesParams
        .builder()
        .languages(null)
        .build());

    assertThrows(NullPointerException.class, () -> SourcesParams
        .builder()
        .languages(Collections.singletonList(null))
        .build());

    assertThrows(NullPointerException.class, () -> SourcesParams
        .builder()
        .language(null)
        .build());
  }

  @Test
  void testRawParams() {
    SourcesParams params = SourcesParams
        .builder()
        .languages(Arrays.asList(Language.DA, Language.SV))
        .country(Country.US)
        .country(Country.AT)
        .page(4)
        .build();

    Map<String, Set<String>> expected = new HashMap<>();
    expected.put("country", new HashSet<>(Arrays.asList(Country.US.getUrlName(), Country.AT.getUrlName())));
    expected.put("page", Collections.singleton("4"));
    expected.put("language", new HashSet<>(Arrays.asList(Language.DA.getUrlName(), Language.SV.getUrlName())));

    assertEquals(expected, params.getRawParams());
  }
}
