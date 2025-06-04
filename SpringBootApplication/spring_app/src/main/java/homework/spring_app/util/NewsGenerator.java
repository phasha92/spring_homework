package homework.spring_app.util;

import homework.spring_app.model.News;


import java.time.Instant;
import java.util.UUID;

public class NewsGenerator {
    private static long idCounter = 0;

    private NewsGenerator() {
    }

    public static News generateNews() {
        return News.builder()
                .id(++idCounter)
                .title("News " + idCounter)
                .text("Text" + UUID.randomUUID())
                .date(Instant.now()).build();
    }
}
