package network.udp.client;

import com.google.gson.Gson;

public class JSONManager
{
    public static String convertObjectToJson(UpdatedState updatedState)
    {
        Gson gson = new Gson();
        return gson.toJson(updatedState);
    }

    public static UpdatedState convertJsonToObject(String json)
    {
        try {
            Gson gson = new Gson();
            var parsedJSON = gson.fromJson(json, UpdatedState.class);
            System.out.println("------------ SUCCESS ON ------------\n " + json + "\n------------------ END --------------------");
            return parsedJSON;
        } catch (Exception e) {
            System.out.println("------------ FAILED ON ------------\n " + json + "\n------------------ END --------------------");
        }
        return null;
    }
}
