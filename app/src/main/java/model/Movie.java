package model;

/*
 * this class will be model for database
 * and also will be model for data binding
 * and also for Web Api
 * */

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.databinding.BaseObservable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movies")
public class Movie extends BaseObservable {
    /*
     * Annotations Start with Capital letter
     * */
    @PrimaryKey()
    @ColumnInfo(name = "id")
    @SerializedName(value = "id")
    private Integer id;
    @ColumnInfo(name = "popularity")
    @SerializedName(value = "popularity")
    private Float popularity ;
    @ColumnInfo(name = "vote_count")
    @SerializedName(value = "vote_count")
    private Integer vote_count ;
    @ColumnInfo(name = "video")
    @SerializedName(value = "video")
    private Boolean mVideo;
    @ColumnInfo(name = "vote_average")
    @SerializedName(value = "vote_average")
    private Float mVoteAverage;
    @ColumnInfo(name = "title")
    @SerializedName(value = "title")
    private String mTitle;
    @ColumnInfo(name = "poster_path")
    @SerializedName(value = "poster_path")
    private String mPosterPath;
    @ColumnInfo(name = "original_language")
    @SerializedName(value = "original_language")
    private String mOriginalLanguage;
    @ColumnInfo(name = "original_title")
    @SerializedName(value = "original_title")
    private String mOriginalTitle;
    @ColumnInfo(name = "backdrop_path")
    @SerializedName(value = "backdrop_path")
    private String mBackdropPath;
    @ColumnInfo(name = "adult")
    @SerializedName(value = "adult")
    private Boolean mAdult;
    @ColumnInfo(name = "overview")
    @SerializedName(value = "overview")
    private String mOverview;
    @ColumnInfo(name = "release_date")
    @SerializedName(value = "release_date")
    private String mReleaseDate;

    @Bindable
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Bindable
    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }
    @Bindable
    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }

    @Bindable
    public Boolean getVideo() {
        return mVideo;
    }

    public void setVideo(Boolean mVideo) {
        this.mVideo = mVideo;
    }
    @Bindable
    public Float getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(Float mVoteAverage) {
        this.mVoteAverage = mVoteAverage;
    }
    @Bindable
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
    @Bindable
    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String mPosterPath) {
        this.mPosterPath = mPosterPath;
    }
    @Bindable
    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String mOriginalLanguage) {
        this.mOriginalLanguage = mOriginalLanguage;
    }
    @Bindable
    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String mOriginalTitle) {
        this.mOriginalTitle = mOriginalTitle;
    }
    @Bindable
    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String mBackdropPath) {
        this.mBackdropPath = mBackdropPath;
    }
    @Bindable
    public Boolean getAdult() {
        return mAdult;
    }

    public void setAdult(Boolean mAdult) {
        this.mAdult = mAdult;
    }
    @Bindable
    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String mOverview) {
        this.mOverview = mOverview;
    }
    @Bindable
    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    //implement toString

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", popularity=" + popularity +
                ", vote_count=" + vote_count +
                ", mVideo=" + mVideo +
                ", mVoteAverage=" + mVoteAverage +
                ", mTitle='" + mTitle + '\'' +
                ", mPosterPath='" + mPosterPath + '\'' +
                ", mOriginalLanguage='" + mOriginalLanguage + '\'' +
                ", mOriginalTitle='" + mOriginalTitle + '\'' +
                ", mBackdropPath='" + mBackdropPath + '\'' +
                ", mAdult=" + mAdult +
                ", mOverview='" + mOverview + '\'' +
                ", mReleaseDate='" + mReleaseDate + '\'' +
                '}';
    }

   public static DiffUtil.ItemCallback<Movie> movieItemCallback = new DiffUtil.ItemCallback<Movie>() {
       @Override
       public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
           return oldItem.getId().equals(newItem.getId()) ;
       }

       @Override
       public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
           return oldItem.getId().equals(newItem.getId());
       }
   };
}

/*
 * {"popularity":66.579,"vote_count":647,"video":false,"poster_path":"\/tI8ocADh22GtQFV28vGHaBZVb0U.jpg","id":475430,"adult":false,"backdrop_path":"\/o0F8xAt8YuEm5mEZviX5pEFC12y.jpg","original_language":"en",
 * "original_title":"Artemis Fowl","genre_ids":[12,14,878,10751],"title":"Artemis Fowl","vote_average":5.9,"overview":"Artemis Fowl is a 12-year-old genius and descendant of a long line of criminal masterminds. He soon finds himself in an epic battle against a race of powerful underground fairies who may be behind his father's disappearance.","release_date":"2020-06-12"}*/
