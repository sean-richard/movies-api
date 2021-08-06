import com.google.gson.Gson;
import data.Movie;

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

        try {
            PrintWriter out = response.getWriter();
            Movie movie = new Movie(2, "King Kong", "1942", "Harry Carey", "Erlsa Banks", "8675309", "there aint one", "cheap", "monkey on building");

            String movieString = new Gson().toJson(movie);

            out.println(movieString);


        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/java");
        PrintWriter out = null;

        try{
           out = response.getWriter();

            BufferedReader reader = request.getReader();

            Movie[] movies = new Gson().fromJson(reader, Movie[].class);

            for(Movie movie : movies){
                System.out.println(movie.getImbdID());
                System.out.println(movie.getTitle());
                System.out.println(movie.getDirector());
                System.out.println(movie.getActors());
                System.out.println(movie.getGenre());
                System.out.println(movie.getImbdID());
                System.out.println(movie.getPlot());
                System.out.println(movie.getPoster());
                System.out.println("*******************************");
            }

        }catch(Exception ex){
            System.out.println(ex.getMessage());
    }
        out.println(new Gson().toJson("{message: \"Movies Post was successful\"}"));
        response.setStatus(200);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json");

        PrintWriter out = null;

        try{
            out = response.getWriter();

            BufferedReader reader = request.getReader();

            Movie[] movies = new Gson().fromJson(reader, Movie[].class);

            for(Movie movie : movies) {
                System.out.println(movie.getImbdID());
                System.out.println(movie.getTitle());
                System.out.println(movie.getDirector());
                System.out.println(movie.getActors());
                System.out.println(movie.getGenre());
                System.out.println(movie.getImbdID());
                System.out.println(movie.getPlot());
                System.out.println(movie.getPoster());
                System.out.println("*******************************");
            }



        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        out.println(new Gson().toJson("{message: \"Movies Post was successful\"}"));
        response.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response){

        response.setContentType("application/json");

        PrintWriter out = null;

        try{
            out = response.getWriter();

            BufferedReader reader = request.getReader();

            int id = new Gson().fromJson(reader, int.class);

            System.out.println("The movie id to delete: " + id);

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        out.println(new Gson().toJson("{message: \"Movies Post was successful\"}"));
        response.setStatus(200);

    }
}
