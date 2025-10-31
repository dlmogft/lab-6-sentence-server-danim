package server.service;

import server.dao.AdjectiveClient;
import server.dao.ArticleClient;
import server.dao.NounClient;
import server.dao.SubjectClient;
import server.dao.VerbClient;
import server.domain.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    CircuitBreakerFactory circuitBreakers;

	@Autowired VerbClient verbClient;
	@Autowired SubjectClient subjectClient;
	@Autowired ArticleClient articleClient;
	@Autowired AdjectiveClient adjectiveClient;
	@Autowired NounClient nounClient;
	
	
	@Override
	public Word getSubject() {
        return
                circuitBreakers
                        .create("subject")
                        .run(
                                () -> subjectClient.getWord(),
                                (throwable) -> getFallbackSubject()
                        );
        // return subjectClient.getWord();
	}
	
	@Override
	public Word getVerb() {
		return verbClient.getWord();
	}
	
	@Override
	public Word getArticle() {
		return articleClient.getWord();
	}
	
	@Override
	public Word getAdjective() {
        return
                circuitBreakers
                        .create("adjective")
                        .run(
                                () -> adjectiveClient.getWord(),
                                (throwable) -> getFallbackAdjective()
                        );
        // return adjectiveClient.getWord();
	}
	
	@Override
	public Word getNoun() {
        return
                circuitBreakers
                        .create("noun")
                        .run(
                                () -> nounClient.getWord(),
                                (throwable) -> getFallbackNoun()
                        );
		// return nounClient.getWord();
	}

    private Word getFallbackAdjective() {
        return new Word();
    }

    private Word getFallbackSubject() {
        return new Word("Someone");
    }

    private Word getFallbackNoun() {
        return new Word("Something");
    }

}
