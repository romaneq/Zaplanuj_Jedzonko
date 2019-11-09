package pl.coderslab.xenteros.model;

public class dayName {
    private int id;
    private String name;
    private int display_order;

    public dayName() {
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

    public int getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(int display_order) {
        this.display_order = display_order;
    }

    @Override
    public String toString() {
        return "dayName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", display_order=" + display_order +
                '}';
    }
}
