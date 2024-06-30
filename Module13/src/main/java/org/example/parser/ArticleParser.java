package org.example.parser;

import org.jsoup.nodes.Element;

public class ArticleParser {
    private Element article;

    public ArticleParser(Element article) {
        this.article = article;
    }

    public String getTitle() {
        Element titleElement = article.getElementsByTag("h2").first();
        return titleElement != null ? titleElement.text() : "No title found";
    }

    public int getRating() {
        Element votesMeterDiv = article.selectFirst("div.tm-votes-meter");
        if (votesMeterDiv != null) {
            Element span = votesMeterDiv.selectFirst("span.tm-votes-meter__value");
            if (span != null) {
                String textRating = span.text();
                return Integer.parseInt(textRating.replace("+", ""));
            }
        }
        return 0;
    }
}
