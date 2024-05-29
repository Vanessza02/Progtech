package Albums;

import classes.AddAlbumCommand;
import classes.DeleteAlbumCommand;
import classes.Album;

import java.util.*;
import java.util.Observable;
public abstract class AlbumAbstract extends Observable {
    private int ID;
    private int user_id;
    private String artist;
    private String title;
    private String genre;
    private int price;

    public AlbumAbstract(int user_id)
    {
        this.user_id = user_id;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getArtist(){
        return artist;
    }

    public void setArtist(String artist) {
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public abstract AlbumAbstract addAlbumToDatabase(int user_id, String artist, String title, String genre, int price);

    List<AlbumAbstract> albumList = new ArrayList<AlbumAbstract>();

    public void addAlbum(AlbumAbstract album){
        albumList.add(album);
        setChanged();
        notifyObservers(new AddAlbumCommand(album));
    }
    public void removeAlbum(AlbumAbstract album){
        albumList.remove(album);
        setChanged();
        notifyObservers(new DeleteAlbumCommand(this));
    }
}