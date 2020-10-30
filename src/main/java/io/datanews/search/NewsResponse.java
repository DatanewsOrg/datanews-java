package io.datanews.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.datanews.model.Article;
import lombok.NonNull;
import lombok.Value;

@Value
public class NewsResponse {
  int numResults;
  @SerializedName("hits")
  @NonNull List<Article> articles;
}
