package service.repository.netrowrk.api;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import model.Movie;
import utilities.Constants;

class MovieJsonDeserializer implements JsonDeserializer<ArrayList<Movie>> {
    public static final String TAG = MovieJsonDeserializer.class.getSimpleName();

    @Override
    public ArrayList<Movie> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        try {
            JsonObject jsonObject = json.getAsJsonObject();
            JsonArray moviesJsonArray = jsonObject.getAsJsonArray(Constants.MOVIES_ARRAY_DATA_TAG);
            movieArrayList = new ArrayList<>(moviesJsonArray.size());
            for (int i = 0; i < moviesJsonArray.size(); i++) {
                movieArrayList.add(context.deserialize(moviesJsonArray.get(i), Movie.class));
            }

        } catch (JsonParseException e) {
            Log.d(TAG, "error ouucred while debugging the movie");
        }

        return movieArrayList;
    }
}
