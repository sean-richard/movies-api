package data;

public class Movie {
    private String title;
    private String poster;
    private int year;
    private String genre;
    private String director;
    private String quote;
    private String actors;
    private int id;

    public Movie() {

    }

    public Movie(String title, String poster, int year, String genre, String director, String quote, String actors, int id) {
        this.title = title;
        this.poster = poster;
        this.year = year;
        this.genre = genre;
        this.director = director;
        this.quote = quote;
        this.actors = actors;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

