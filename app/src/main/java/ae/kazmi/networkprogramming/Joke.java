package ae.kazmi.networkprogramming;

/**
 * Created by sulaiman on 9/5/16.
 */

/**
 * {@Joke} represents a joke content.
 */
public class Joke {

    /** Content of the joke */
    private String content;

    /**
     * Constructs a new {@link Joke}.
     *
     * @param jokeContent is the content of the joke
     */
    public Joke(String jokeContent) {
        content = jokeContent;
    }

    public void setContent(String jokeContent) { content = jokeContent; }
    public String getContent() { return content; }
}