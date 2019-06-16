package campuspaths;

import campuspaths.utils.CORSFilter;
import com.google.gson.Gson;
import pathfinder.ModelConnector;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class SparkServer {

  public static void main(String[] args) {
    CORSFilter corsFilter = new CORSFilter();
    corsFilter.apply();
    // The above two lines help set up some settings that allow the
    // React application to make requests to the Spark server, even though it
    // comes from a different server.
    // You should leave these two lines at the very beginning of main().

    // TODO: Create all the Spark Java routes you need here:


    
    ModelConnector model = new ModelConnector();
    Gson gson = new Gson();

    // to tests this method works try entering this url in the browser
    // http://localhost:4567/findPath?start=MGH&end=CSE
    Spark.get("/findPath", new Route() {
      @Override
      public Object handle(Request request, Response response) throws Exception {
        String start = request.queryParams("start");
        String end = request.queryParams("end");
        if (start == null || end == null) {
          Spark.halt(400, "must have start and end");
        }
        Path<Point> path = model.findShortestPath(start, end);
        return gson.toJson(path);
      }
    });


    // to tests this method works try entering this url in the browser
    // http://localhost:4567/buildingNames

    Spark.get("/buildingNames", new Route() {
      @Override
      public Object handle(Request request, Response response) throws Exception {
        return gson.toJson(model.buildingNames());
      }
    });



  }

}
