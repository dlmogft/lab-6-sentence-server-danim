package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.domain.Word;

import java.util.Optional;

/**
 * Build a sentence by assembling randomly generated subjects, verbs, 
 * articles, adjectives, and nouns.  The individual parts of speech will 
 * be obtained by calling the various DAOs.
 */
@Service
public class SentenceServiceImpl implements SentenceService {

    @Autowired WordService wordService;


    /**
     * Assemble a sentence by gathering random words of each part of speech:
     */
    public String buildSentence() {
        return
                String.format("%s %s %s %s %s.",
                        Optional.ofNullable(wordService.getSubject()).map(Word::getString).orElse(null),
                        Optional.ofNullable(wordService.getVerb()).map(Word::getString).orElse(null),
                        Optional.ofNullable(wordService.getArticle()).map(Word::getString).orElse(null),
                        Optional.ofNullable(wordService.getAdjective()).map(Word::getString).orElse(null),
                        Optional.ofNullable(wordService.getNoun()).map(Word::getString).orElse(null) )
                ;
    }

}
