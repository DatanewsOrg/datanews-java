package io.datanews.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.datanews.model.Source;
import lombok.NonNull;
import lombok.Value;

/**
 * Represents a response from {@code sources} endpoint.
 * <p>
 * See <a href="https://datanews.io/docs/sources">Sources API docs</a> for more info.
 */
@Value
public class SourcesResponse {
  int numResults;
  @SerializedName("hits")
  @NonNull List<Source> sources;
}
