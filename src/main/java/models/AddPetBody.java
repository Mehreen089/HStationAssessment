package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddPetBody {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tags")
    @Expose
    private Tags[] tags;
    @SerializedName("status")
    @Expose
    private String status;

    public AddPetBody(String id, String name, Category category, Tags[] tags, String status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.tags = tags;
        this.status = status;
    }

    public AddPetBody(AddPetBody addPetBody){
        this.id = addPetBody.id;
        this.name = addPetBody.name;
        this.category = addPetBody.category;
        this.tags = addPetBody.tags;
        this.status = addPetBody.status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Tags[] getTags() {
        return tags;
    }

    public void setTags(Tags[] tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}