package server.dao;

import server.domain.Word;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("ARTICLE")
public interface ArticleClient {

    @GetMapping("/")
    public Word getWord();

}
