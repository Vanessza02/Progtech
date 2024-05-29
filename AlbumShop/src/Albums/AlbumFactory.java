package Albums;

import Albums.AlbumAbstract;
public interface AlbumFactory {
    AlbumAbstract createAlbum(int user_id);
}
