package pl.coderslab.xenteros.model;

import java.sql.Date;

public class Plan {
    private int id;
    private String name;
    private String description;
    private String created;
    private int admin_id;


    public Plan (){

    }

    public Plan(String name, String description, String created, int admin_id) {
        this.name = name;
        this.description = description;
        this.created = created;
        this.admin_id = admin_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", created='" + created + '\'' +
                ", admin_id=" + admin_id +
                '}';
    }
}
