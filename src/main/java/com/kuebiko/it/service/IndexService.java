package com.kuebiko.it.service;

import static org.elasticsearch.action.support.WriteRequest.RefreshPolicy.IMMEDIATE;

import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndexService {

  private final RestHighLevelClient restHighLevelClient;

  @PostConstruct
  public void init() throws Exception {
    IndexRequest request =
        new IndexRequest("inventory", "elasticsearch", UUID.randomUUID().toString())
            .source(Map.of("feature", "high-level-rest-client"))
            .setRefreshPolicy(IMMEDIATE);

    IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
  }
}
