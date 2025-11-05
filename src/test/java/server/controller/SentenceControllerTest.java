package server.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import server.service.SentenceService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SentenceControllerTest {

    @Mock
    private SentenceService sentenceService;

    @InjectMocks
    private SentenceController sentenceController;

    public SentenceControllerTest() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    // Test for the getSentences method
    @Test
    public void getSentences_ShouldReturnFormattedSentences() {
        // Arrange: Mock the behavior of sentenceService.buildSentence
        when(sentenceService.buildSentence()).thenReturn("Mocked Sentence");

        // Act: Call the method under test
        String result = sentenceController.getSentences();

        // Assert: Verify the result
        String expected = "<h3>Some Sentences</h3><br/>Mocked Sentence<br/><br/>Mocked Sentence<br/><br/>Mocked Sentence<br/><br/>Mocked Sentence<br/><br/>Mocked Sentence<br/><br/>";
        assertEquals(expected, result, "The returned sentences should match the expected format.");
    }

    // Test for the sentence method
    @Test
    public void sentence_ShouldReturnSentenceString() {
        // Act: Call the method under test
        String result = sentenceController.sentence();

        // Assert: Verify the result
        String expected = "sentence";
        assertEquals(expected, result, "The returned string should be 'sentence'.");
    }
}
