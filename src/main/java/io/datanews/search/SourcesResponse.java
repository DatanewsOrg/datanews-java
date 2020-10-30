package io.datanews.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.datanews.model.Source;
import lombok.NonNull;
import lombok.Value;

@Value
public class SourcesResponse {
  int numResults;
  @SerializedName("hits")
  @NonNull List<Source> sources;
}
