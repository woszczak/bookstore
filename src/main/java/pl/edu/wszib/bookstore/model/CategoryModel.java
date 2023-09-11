package pl.edu.wszib.bookstore.model;


import java.util.Date;

public class CategoryModel {


    private Long id;
    private String name;
    private Date createDate;

    private Date updateDate;

    public CategoryModel() {
    }

    public CategoryModel(Long id, String name, Date createDate, Date updateDate) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
