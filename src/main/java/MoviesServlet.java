import com.google.gson.Gson;
import data.Movie;
import data.movies.DaoFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;

@WebServlet(name = "MovieServlet", urlPatterns = "/movies")
public class MoviesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out.println(new Gson().toJson(
                    DaoFactory.getMoviesDao(DaoFactory.ImplType.IN_MEMORY)
                            .all()));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        @Override
        protected void doPost (HttpServletRequest request, HttpServletResponse response){
            response.setContentType("application/json");
            PrintWriter out = null;
            try {
                DaoFactory.getMoviesDao(DaoFactory.ImplType.IN_MEMORY).insertAll(movies);
            } catch (SQLException e) {
                out.println(new Gson().toJson(e.getLocalizedMessage()));
                response.setStatus(500);
                e.printStackTrace();
                return;
            }
            out.println(new Gson().toJson("{message: \"Movies POST was successful\"}"));
            response.setStatus(200);
        }


        @Override
        protected void doPut (HttpServletRequest request, HttpServletResponse response){
            response.setContentType("application/json");
            PrintWriter out = null;
            try {
                Movie movie = new Gson().fromJson(request.getReader(), Movie.class);
                DaoFactory.getMoviesDao(DaoFactory.ImplType.IN_MEMORY).update(movie);
            } catch (SQLException e) {
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

            out.println(new Gson().toJson("{message: \"Movie UPDATE was successful\"}"));
            response.setStatus(200);
        }


        @Override
        protected void doDelete (HttpServletRequest request, HttpServletResponse response){
            response.setContentType("application/json");
            PrintWriter out = null;

            try {
                var id = new Gson().fromJson(request.getReader(), int.class);
                DaoFactory.getMoviesDao(DaoFactory.ImplType.IN_MEMORY).destroy(id);
            } catch (SQLException e) {
                out.println(new Gson().toJson(e.getLocalizedMessage()));
                response.setStatus(500);
                e.printStackTrace();
                return;
            }

            out.println(new Gson().toJson("{message: \"Movie DELETE was successful\"}"));
        }


    }
