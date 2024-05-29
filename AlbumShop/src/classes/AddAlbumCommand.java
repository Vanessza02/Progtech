package classes;

import Albums.AlbumAbstract;

public class AddAlbumCommand implements ICommand{
    private AlbumAbstract album;
    public AlbumAbstract getAlbum() {
        return album;
    }

    public void setAlbum(AlbumAbstract album) {
        this.album = album;
    }

    public AddAlbumCommand(AlbumAbstract album) {
        this.album = album;
    }

    @Override
    public void ExecuteEvent() {
        album.addAlbum(album);
    }
}
