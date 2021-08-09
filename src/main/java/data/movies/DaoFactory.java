package data.movies;

import data.Config;
import data.MoviesDao;
import data.MySqlMoviesDao;

import java.io.ObjectInputFilter;
//import data.movies.MoviesDao;
//import data.movies.MySqlMoviesDao;


public class DaoFactory {

    private static MoviesDao moviesDao;
    private static Config config = new Config();
    public enum ImplType {MYSQL, IN_MEMORY} //Notice we have two values here

    public static MoviesDao getMoviesDao(ImplType implementationType){

        switch(implementationType){
            case IN_MEMORY:{ //yet we have one switch case. We'll get to that!
                if (moviesDao == null){
                    moviesDao = new InMemoryMoviesDao();
                }
            }
            case MYSQL:{
                if(moviesDao == null){
                    moviesDao = new MySqlMoviesDao(config);

                }
            }
        }
        return moviesDao;
    }
}
