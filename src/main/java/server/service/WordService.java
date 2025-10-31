package server.service;

import server.domain.Word;

public interface WordService {

	Word getSubject();
	Word getVerb();
	Word getArticle();
	Word getAdjective();
	Word getNoun();
	
}
