package data;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlMoviesDao implements MoviesDao {

    private Connection connection = null;


    public MySqlMoviesDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            this.connection = DriverManager.getConnection(
                    config.getUrl(), // <-- WHERE IS THE DB?
                    config.getUser(), // <-- WHO IS ACCESSING?
                    config.getPassword() // <-- WHAT IS THEIR PASSWORD?
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }


    @Override
    public List<Movie> all() throws SQLException {
        // TODO: Get ALL the movies
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM movies");

            List<Movie> movies = new ArrayList<>();

            while (rs.next()) {
                movies.add(new Movie(
                        rs.getString("title"),
                        rs.getString("poster"),
                        rs.getInt("year"),
                        rs.getString("genre"),
                        rs.getString("director"),
                        rs.getString("quote"),
                        rs.getString("actors"),
                        rs.getInt("id")
                        ));
            }
            return movies;
    }

//    @Override
//    public Movie findOne(int id) throws SQLException {
//        // TODO: Get one movie by id
//        Statement statement = connection.createStatement();
//        ResultSet rs = statement.executeQuery("SELECT * FROM movies WHERE id = ?");
//
//        Movie movies = new Movie();
//        movies.add(new Movie(
//                rs.getString("title"),
//                rs.getString("poster"),
//                rs.getInt("year"),
//                rs.getString("genre"),
//                rs.getString("director"),
//                rs.getString("quote"),
//                rs.getString("actors"),
//                rs.getInt("id")
//        ));
//     return movies;
//    }

//    @Override
//    public void insert(Movie movie) {
//        // TODO: Insert one movie
//        StringBuilder sql = new StringBuilder("INSERT INTO movies (" +
//                "id, title, year, director, actors, imdbId, poster, genre, plot) " +
//                "VALUES ");
//
//
//        // Add a interpolation template for each element in movies list
//        sql.append("(?, ?, ?, ?, ?, ?, ?, ?), ".repeat(movie.));
//
//        // Create a new String and take off the last comma and whitespace
//        sql = new StringBuilder(sql.substring(0, sql.length() - 2));
//
//        // Use the sql string to create a prepared statement
//        PreparedStatement statement = connection.prepareStatement(sql.toString());
//
//
//            statement.setString(1, movie.getTitle());
//            statement.setString(2, movie.getPoster());
//            statement.setInt(3, movie.getYear());
//            statement.setString(4, movie.getGenre());
//            statement.setString(5, movie.getDirector());
//            statement.setString(6, movie.getQuote());
//            statement.setString(7, movie.getActors());
//            statement.setInt(8, movie.getId());
//
//        statement.execute();
//    }

    @Override
    public void insertAll(Movie[] movies) throws SQLException {

        // Build sql template
        StringBuilder sql = new StringBuilder("INSERT INTO movies (" +
                "id, title, year, director, actors, poster, genre, plot) " +
                "VALUES ");


        // Add a interpolation template for each element in movies list
        sql.append("(?, ?, ?, ?, ?, ?, ?, ?), ".repeat(movies.length));

        // Create a new String and take off the last comma and whitespace
        sql = new StringBuilder(sql.substring(0, sql.length() - 2));

        // Use the sql string to create a prepared statement
        PreparedStatement statement = connection.prepareStatement(sql.toString());

        // Add each movie to the prepared statement using the index of each sql param: '?'
        // This is done by using a counter
        // You could use a for loop to do this as well and use its incrementor
        int counter = 0;
        for (Movie movie : movies) {
            statement.setString((counter * 8) + 1, movie.getTitle());
            statement.setString((counter * 8) + 2, movie.getPoster());
            statement.setInt((counter * 8) + 3, movie.getYear());
            statement.setString((counter * 8) + 4, movie.getGenre());
            statement.setString((counter * 8) + 5, movie.getDirector());
            statement.setString((counter * 8) + 6, movie.getQuote());
            statement.setString((counter * 8) + 7, movie.getActors());
            statement.setInt((counter * 8) + 8, movie.getId());
            counter++;
        }
        statement.executeUpdate();
    }

    @Override
    public void update(Movie movie) throws SQLException {
        //TODO: Update a movie here!
        String sql = "Update movies " +
                "SET title = ?, poster = ?, year = ?, genre = ?, director = ?, quote = ?, actor = ? " +
                "Where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);


            statement.setString( + 1, movie.getTitle());
            statement.setString( + 2, movie.getPoster());
            statement.setInt( + 3, movie.getYear());
            statement.setString( + 4, movie.getGenre());
            statement.setString( + 5, movie.getDirector());
            statement.setString( + 6, movie.getQuote());
            statement.setString( + 7, movie.getActors());
            statement.setInt( + 8, movie.getId());

            statement.executeUpdate();

    }

    @Override
    public void destroy(int id) throws SQLException {
        //TODO: Annihilate a movie
        String sql = "Delete From movies " + "Where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1,id);

        statement.execute();
    }
}