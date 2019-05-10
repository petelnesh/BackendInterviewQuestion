/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.safaricom.hackathon.BackendInterviewQuestion.payloads;

/**
 *
 * @author techsavanna
 */
public class MovieRequest {

    private String title;

    private String description;

    private String recommendation;

    private Integer rating;

    private String watch_flag;

    public MovieRequest() {
    }

    public MovieRequest(String title, String description, String recommendation, Integer rating, String username,String watch_flag) {
        this.title = title;
        this.description = description;
        this.recommendation = recommendation;
        this.rating = rating;
        this.watch_flag = watch_flag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getWatch_flag() {
        return watch_flag;
    }

    public void setWatch_flag(String watch_flag) {
        this.watch_flag = watch_flag;
    }
    

}
