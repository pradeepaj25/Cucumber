package starter;

public enum WebServiceEndPoints {
    NYURL("https://api.nytimes.com/svc/books/v3/lists/best-sellers/history.json?api-key=YMYATVVm5Bgh6wrf4SGEz1ikPyXq5rAL"),
    LISTBOOKS("https://api.nytimes.com/svc/books/v3/lists/best-sellers/history.json?api-key=YMYATVVm5Bgh6wrf4SGEz1ikPyXq5rAL&author="),
	PUBLISHDATE("https://api.nytimes.com/svc/books/v3/lists/overview.json?api-key=YMYATVVm5Bgh6wrf4SGEz1ikPyXq5rAL&published_date=");

    private final String url;

    WebServiceEndPoints(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
