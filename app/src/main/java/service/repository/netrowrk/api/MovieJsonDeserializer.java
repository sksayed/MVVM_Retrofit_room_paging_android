package service.repository.netrowrk.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import Model.Movie;
import utilities.Constants;

class MovieJsonDeserializer implements JsonDeserializer<Movie> {
    public static final String TAG = MovieJsonDeserializer.class.getSimpleName();
    @Override
    public Movie deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ArrayList<Movie> movieArrayList = null ;
        try {
            JsonObject jsonObject = json.getAsJsonObject();
            JsonArray moviesJsonArray =jsonObject.getAsJsonArray(Constants.MOVIES_ARRAY_DATA_TAG);
            movieArrayList = new ArrayList<>(moviesJsonArray.size());

        }

        return null;
    }
}
