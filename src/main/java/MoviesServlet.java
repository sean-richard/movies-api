import com.google.gson.Gson;
import data.Movie;
import data.movies.DaoFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MovieServlet", urlPatterns = "/movies")
public class MoviesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");

        try {
            PrintWriter out = response.getWriter();

            List<Movie> movies = DaoFactory.getMoviesDao(DaoFactory.ImplType.MYSQL).all();
            //turn into json string
            String movieString = new Gson().toJson(movies);

            //inject into response
            out.println(movieString);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            //get the stream of characters from the request (eventually becomes our movie)
//            BufferedReader reader = request.getReader();

            // turn that stream into an array of Movies
            Movie[] movies = new Gson().fromJson(request.getReader(), Movie[].class);
            response.setContentType("application/json");
            DaoFactory.getMoviesDao(DaoFactory.ImplType.MYSQL).insertAll(movies);

        } catch (SQLException | IOException e) {
            out.println(new Gson().toJson(e.getLocalizedMessage()));
            response.setStatus(500);
            e.printStackTrace();
            return;
        }
        out.println(new Gson().toJson("{message: \"Movies Post was successful\"}"));
        response.setStatus(200);
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json");
        PrintWriter out = null;
        try{
            out = response.getWriter();

            BufferedReader reader = request.getReader();

            Movie movie = new Gson().fromJson(request.getReader(), Movie.class);
            DaoFactory.getMoviesDao(DaoFactory.ImplType.MYSQL).update(movie);

        }catch (SQLException e) {
            out.println(new Gson().toJson(e.getLocalizedMessage()));
            response.setStatus(500);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            out.println(new Gson().toJson(e.getLocalizedMessage()));
            response.setStatus(400);
            e.printStackTrace();
            return;
        }
        out.println(new Gson().toJson("{message: \"Movies Put was successful\"}"));
        response.setStatus(200);
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json");
        PrintWriter out = null;

        try{
            out = response.getWriter();
            BufferedReader reader = request.getReader();

            var id = new Gson().fromJson(request.getReader(), int.class);
            DaoFactory.getMoviesDao(DaoFactory.ImplType.MYSQL).destroy(id);

        }catch (SQLException | IOException e) {
            out.println(new Gson().toJson(e.getLocalizedMessage()));
            response.setStatus(500);
            e.printStackTrace();
            return;
        }
        out.println(new Gson().toJson("{message: \"Movies DELETE was successful\"}"));
        response.setStatus(200);
    }



}
