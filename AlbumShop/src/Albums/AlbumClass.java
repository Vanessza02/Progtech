package Albums;

import Albums.AlbumAbstract;
import Pages.CreateAlbum;

import java.sql.*;
public class AlbumClass extends AlbumAbstract {
    public AlbumClass(int user_id) {
        super(user_id);
    }
    @Override
    public AlbumAbstract addAlbumToDatabase(int user_id, String artist, String title, String genre, int price) {
        AlbumAbstract alb = null;
        final String DB_URL = "jdbc:mysql://localhost/albumshop?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stm = conn.createStatement();
            String sql = "INSERT INTO album(user_id, artist, title, genre, price) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setString(3, artist);
            preparedStatement.setString(4, genre);
            preparedStatement.setInt(5, price);

            int addedRows = preparedStatement.executeUpdate();
            if(addedRows > 0){
                alb = new AlbumClass(user_id){};
                alb.setUser_id(user_id);
                alb.setArtist(artist);
                alb.setTitle(title);
                alb.setGenre(genre);
                alb.setPrice(price);
            }
            stm.close();
            conn.close();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

        return alb;
    }
}
