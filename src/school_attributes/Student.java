package school_attributes;

public class Student {
    private String name;
    private String email;
    private String id;

    public Student(String name, String email ) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    public String getId() {
        return id;
    }
}
