package data.movies;

//import data.movies.InMemoryMoviesDao;
//import data.movies.MoviesDao;
//import data.movies.MySqlMoviesDao;

import data.MoviesDao;

public class DaoFactory {

    private static MoviesDao moviesDao;
//    private static Conig config = new Config();
    public enum ImplyType {MYSQL, IN_MEMORY};

    public static MoviesDao getMoviesDao(ImplyType implementationType){

        switch (implementationType){
            case IN_MEMORY:{
                moviesDao = new InMemoryMoviesDao();
            }
        }
        return moviesDao;
    }
}
