package carsharing;

public class Company {
    private int id;
    private String name;

    Company(int id, String name) {
        this.id = id;
        this.name = name;
    }

    Company(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
