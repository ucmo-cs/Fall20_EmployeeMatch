package edu.ucmo.spring_example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ucmo.spring_example.model.EmployeePreferences;
import edu.ucmo.spring_example.model.Employer;
import edu.ucmo.spring_example.model.EmployerPreferences;
import edu.ucmo.spring_example.model.Matches;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class DataFromApi {
    public String data;
    public DataFromApi(String inputUrl ) throws IOException {
        URL url = new URL(inputUrl);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        //Getting the response code
        int responsecode = conn.getResponseCode();
        if (responsecode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        } else {

            String inline = "";
            Scanner scanner = new Scanner(url.openStream());

            //Write all the JSON data into a string using a scanner
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }

            //Close the scanner
            scanner.close();

            data=inline;

            //parse json


            //Using the JSON simple library parse the string into a json object
            /*final String     str    = JSType.toString(inline);
            final Global global = Context.getGlobal();
            //final boolean    dualFields = ((ScriptObject) global).useDualFields();
            final boolean    dualFields = true;
            final JSONParser parser = new JSONParser(str, global, dualFields);
            JSONObject data_obj = (JSONObject) parse.parse(inline);

            //Get the required object from the above created object
            JSONObject obj = (JSONObject) data_obj.get("Global");

            //Get the required data using its key
            System.out.println(obj.get("TotalRecovered"));

             */
        }
    }

    public String getDataString()   {
        return data;
    }

    //convert data to EmployeePreferences
    public EmployeePreferences[] getDataToEmployeePreferences(){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            EmployeePreferences[] returnValue = objectMapper.readValue(data, EmployeePreferences[].class);
            return returnValue;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //convert to EmployerPreferences
    public EmployerPreferences[] getDataToEmployerPreferences(){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            EmployerPreferences[] returnValue = objectMapper.readValue(data, EmployerPreferences[].class);
            return returnValue;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //convert to Matches
    public Matches[] getDataToMatches(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Matches[] returnValue = objectMapper.readValue(data, Matches[].class);
            return returnValue;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //convert to Employers
    public Employer[] getDataToEmployer(){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Employer[] returnValue = objectMapper.readValue(data, Employer[].class);
            return returnValue;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public Class<?>[] getDataToType(Class<?> clazz)  {
        ObjectMapper objectMapper = new ObjectMapper();
        clazz.getClass() clazzArray = {};
        try {
            Class<?>[] returnValue = objectMapper.readValue(data, clazzArray.getClass());
            return returnValue;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

     */

    /*
    public <E> E getDataToType(E clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        E[] T;
        try {
            E returnValue = (E)objectMapper.readValue(data, T.class);
            return returnValue;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

     */
}
