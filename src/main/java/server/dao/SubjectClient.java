package server.dao;

import server.domain.Word;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("SUBJECT")
public interface SubjectClient {

    @GetMapping("/")
    public Word getWord();

}
