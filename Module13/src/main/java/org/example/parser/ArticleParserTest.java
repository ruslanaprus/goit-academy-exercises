package org.example.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.IOException;

public class ArticleParserTest {
    public static void main(String[] args) throws IOException {
        String url = "https://habr.com/en/feed/";

        Document document = Jsoup.connect(url).get();

        Elements articles = document
                .selectFirst("div.tm-articles-list")
                .select("article");
        for(Element article : articles){
            ArticleParser parser = new ArticleParser(article);

            String title = parser.getTitle();
            int rating = parser.getRating();
            System.out.println(title + " - " + rating);
        }
    }
}
