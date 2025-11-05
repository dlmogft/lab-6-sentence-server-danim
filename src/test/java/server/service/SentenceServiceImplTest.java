package server.service;

import server.domain.Word;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SentenceServiceImplTest {

    // Helper method to mock WordService
    private WordService mockWordService(String subject, String verb, String article, String adjective, String noun) {
        WordService wordService = mock(WordService.class);

        Word subjectWord = mock(Word.class);
        when(subjectWord.getString()).thenReturn(subject);
        when(wordService.getSubject()).thenReturn(subjectWord);

        Word verbWord = mock(Word.class);
        when(verbWord.getString()).thenReturn(verb);
        when(wordService.getVerb()).thenReturn(verbWord);

        Word articleWord = mock(Word.class);
        when(articleWord.getString()).thenReturn(article);
        when(wordService.getArticle()).thenReturn(articleWord);

        Word adjectiveWord = mock(Word.class);
        when(adjectiveWord.getString()).thenReturn(adjective);
        when(wordService.getAdjective()).thenReturn(adjectiveWord);

        Word nounWord = mock(Word.class);
        when(nounWord.getString()).thenReturn(noun);
        when(wordService.getNoun()).thenReturn(nounWord);

        return wordService;
    }

    @Test
    void buildSentence_ShouldReturnValidSentence() {
        // Arrange
        WordService wordService = mockWordService("The cat", "jumps", "over", "lazy", "dog");
        SentenceServiceImpl sentenceService = new SentenceServiceImpl();
        sentenceService.wordService = wordService;

        // Act
        String sentence = sentenceService.buildSentence();

        // Assert
        assertNotNull(sentence, "Sentence should not be null");
        assertEquals("The cat jumps over lazy dog.", sentence, "Sentence should match the expected format");
    }

    @Test
    void buildSentence_ShouldHandleEmptyWords() {
        // Arrange
        WordService wordService = mockWordService("", "", "", "", "");
        SentenceServiceImpl sentenceService = new SentenceServiceImpl();
        sentenceService.wordService = wordService;

        // Act
        String sentence = sentenceService.buildSentence();

        // Assert
        assertNotNull(sentence, "Sentence should not be null");
        assertEquals("    .", sentence, "Sentence should handle empty words gracefully");
    }

    @Test
    void buildSentence_ShouldHandleNullWords() {
        // Arrange
        WordService wordService = mock(WordService.class);
        when(wordService.getSubject()).thenReturn(null);
        when(wordService.getVerb()).thenReturn(null);
        when(wordService.getArticle()).thenReturn(null);
        when(wordService.getAdjective()).thenReturn(null);
        when(wordService.getNoun()).thenReturn(null);

        SentenceServiceImpl sentenceService = new SentenceServiceImpl();
        sentenceService.wordService = wordService;

        // Act
        String sentence = sentenceService.buildSentence();

        // Assert
        assertNotNull(sentence, "Sentence should not be null");
        assertEquals("null null null null null.", sentence, "Sentence should handle null words gracefully");
    }

    @Test
    void buildSentence_ShouldHandleSpecialCharacters() {
        // Arrange
        WordService wordService = mockWordService("!@#", "$%^", "&*(", ")*&", "^%$");
        SentenceServiceImpl sentenceService = new SentenceServiceImpl();
        sentenceService.wordService = wordService;

        // Act
        String sentence = sentenceService.buildSentence();

        // Assert
        assertNotNull(sentence, "Sentence should not be null");
        assertEquals("!@# $%^ &*( )*& ^%$.", sentence, "Sentence should handle special characters correctly");
    }

    @Test
    void buildSentence_ShouldHandleLongWords() {
        // Arrange
        WordService wordService = mockWordService("Supercalifragilisticexpialidocious", "antidisestablishmentarianism", "pneumonoultramicroscopicsilicovolcanoconiosis", "floccinaucinihilipilification", "hippopotomonstrosesquippedaliophobia");
        SentenceServiceImpl sentenceService = new SentenceServiceImpl();
        sentenceService.wordService = wordService;

        // Act
        String sentence = sentenceService.buildSentence();

        // Assert
        assertNotNull(sentence, "Sentence should not be null");
        assertEquals("Supercalifragilisticexpialidocious antidisestablishmentarianism pneumonoultramicroscopicsilicovolcanoconiosis floccinaucinihilipilification hippopotomonstrosesquippedaliophobia.", sentence, "Sentence should handle long words correctly");
    }
}
