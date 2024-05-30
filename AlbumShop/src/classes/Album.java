package classes;

public class Album{
    private int ID;
    private String artist;
    private String title;
    private String genre;
    private int price;

    public int getAlbumID() {
        return ID;
    }

    public void setAlbumID(int id) {
        this.ID = id;
    }

    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setContent(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
