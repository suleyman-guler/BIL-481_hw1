/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package BIL._hw1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;



public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static boolean search(ArrayList<Integer> array, int e) {
        System.out.println("inside search");
        if (array == null) return false;
  
        for (int elt : array) {
          if (elt == e) return true;
        }
        return false;
    }

    public static ArrayList<Integer> change(ArrayList<Integer> source, int changeFrom, int changeTo){
      System.out.println("inside change");
      if(source == null)
        return null;
      for(int i = 0; i < source.size(); i++){
        if(source.get(i) == changeFrom)
          source.set(i,changeTo);
      }  
      return source;

    }
    public static void main(String[] args) {

        int port = Integer.parseInt(System.getenv("PORT"));
        port(port);

        // port(getHerokuAssignedPort());

        // get("/", (req, res) -> "Hello, World");

        post("/", (req, res) -> {
        
          
          String input1 = req.queryParams("input1");
          java.util.Scanner sc1 = new java.util.Scanner(input1);
          sc1.useDelimiter("[;\r\n]+");
          java.util.ArrayList<Integer> inputList = new java.util.ArrayList<>();
          while (sc1.hasNext())
          {
            int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
            inputList.add(value);
          }
          sc1.close();
          System.out.println("inputList:"+inputList);


          String input2 = req.queryParams("input2").replaceAll("\\s","");
          int input2AsInt = Integer.parseInt(input2);

          String input3 = req.queryParams("input3").replaceAll("\\s","");
          int input3AsInt = Integer.parseInt(input3);

          inputList = change(inputList,input2AsInt,input3AsInt);
          String result = inputList.toString();

          Map<String, String> map = new HashMap<String, String>();
          map.put("result", result);
          return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());

        get("/",
            (rq, rs) -> {
              Map<String, String> map = new HashMap<String, String>();
              map.put("result", "not computed yet!");
              return new ModelAndView(map, "compute.mustache");
            },
            new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    
}
