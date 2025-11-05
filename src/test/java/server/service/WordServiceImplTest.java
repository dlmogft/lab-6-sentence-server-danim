package server.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import server.dao.AdjectiveClient;
import server.dao.ArticleClient;
import server.dao.NounClient;
import server.dao.SubjectClient;
import server.dao.VerbClient;
import server.domain.Word;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WordServiceImplTest {

    private WordServiceImpl wordService;
    private CircuitBreakerFactory circuitBreakerFactory;
    private CircuitBreaker circuitBreaker;
    private VerbClient verbClient;
    private SubjectClient subjectClient;
    private ArticleClient articleClient;
    private AdjectiveClient adjectiveClient;
    private NounClient nounClient;

    @BeforeEach
    public void setUp() {
        circuitBreakerFactory = mock(CircuitBreakerFactory.class);
        circuitBreaker = mock(CircuitBreaker.class);
        verbClient = mock(VerbClient.class);
        subjectClient = mock(SubjectClient.class);
        articleClient = mock(ArticleClient.class);
        adjectiveClient = mock(AdjectiveClient.class);
        nounClient = mock(NounClient.class);

        when(circuitBreakerFactory.create("subject")).thenReturn(circuitBreaker);
        when(circuitBreakerFactory.create("adjective")).thenReturn(circuitBreaker);
        when(circuitBreakerFactory.create("noun")).thenReturn(circuitBreaker);

        wordService = new WordServiceImpl();
        wordService.circuitBreakers = circuitBreakerFactory;
        wordService.verbClient = verbClient;
        wordService.subjectClient = subjectClient;
        wordService.articleClient = articleClient;
        wordService.adjectiveClient = adjectiveClient;
        wordService.nounClient = nounClient;
    }

    @Test
    public void getSubject_ShouldReturnSubjectWord() {
        Word expectedWord = new Word("SubjectWord");
        when(subjectClient.getWord()).thenReturn(expectedWord);
        when(circuitBreaker.run(any(), any())).thenReturn(expectedWord);

        Word actualWord = wordService.getSubject();

        assertEquals(expectedWord, actualWord, "Expected subject word does not match actual word.");
    }

    @Test
    public void getSubject_ShouldReturnFallbackSubjectWord_WhenExceptionOccurs() {
        when(circuitBreaker.run(any(), any())).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> wordService.getSubject(), "Should throw exception when retrieving the word");
    }

    @Test
    public void getVerb_ShouldReturnVerbWord() {
        Word expectedWord = new Word("VerbWord");
        when(verbClient.getWord()).thenReturn(expectedWord);

        Word actualWord = wordService.getVerb();

        assertEquals(expectedWord, actualWord, "Expected verb word does not match actual word.");
    }

    @Test
    public void getArticle_ShouldReturnArticleWord() {
        Word expectedWord = new Word("ArticleWord");
        when(articleClient.getWord()).thenReturn(expectedWord);

        Word actualWord = wordService.getArticle();

        assertEquals(expectedWord, actualWord, "Expected article word does not match actual word.");
    }

    @Test
    public void getAdjective_ShouldReturnAdjectiveWord() {
        Word expectedWord = new Word("AdjectiveWord");
        when(adjectiveClient.getWord()).thenReturn(expectedWord);
        when(circuitBreaker.run(any(), any())).thenReturn(expectedWord);

        Word actualWord = wordService.getAdjective();

        assertEquals(expectedWord, actualWord, "Expected adjective word does not match actual word.");
    }

    @Test
    public void getAdjective_ShouldReturnFallbackAdjectiveWord_WhenExceptionOccurs() {
        when(circuitBreaker.run(any(), any())).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> wordService.getAdjective(), "Should throw exception when retrieving the word");
    }

    @Test
    public void getNoun_ShouldReturnNounWord() {
        Word expectedWord = new Word("NounWord");
        when(nounClient.getWord()).thenReturn(expectedWord);
        when(circuitBreaker.run(any(), any())).thenReturn(expectedWord);

        Word actualWord = wordService.getNoun();

        assertEquals(expectedWord, actualWord, "Expected noun word does not match actual word.");
    }

    @Test
    public void getNoun_ShouldReturnFallbackNounWord_WhenExceptionOccurs() {
        when(circuitBreaker.run(any(), any())).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> wordService.getNoun(), "Should throw exception when retrieving the word");
    }
}

