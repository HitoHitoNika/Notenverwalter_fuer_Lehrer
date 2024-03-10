package school_attributes;

public class Student {
    private String name;
    private String email;
    private String id;

    /**
     * 
     * @param name
     * @param email
     * @param id
     */
    public Student(String name, String email, String id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }
    /**
     * 
     * @return
     */
    public String getName() {
        return name;
    }
    /**
     * 
     * @return
     */
    public String getEmail() {
        return email;
    }
    /**
     * 
     * @return
     */
    public String getId() {
        return id;
    }
}
