package Models;

public class Subscribers  {
    private String id ;
    private String name ;
    private String reference ;

    public Subscribers(String id, String name , String reference) {
        this.id = id;
        this.name = name  ;
        this.reference = reference ;
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "Subscribers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reference='" + reference + '\'' +
                '}';
    }
}
