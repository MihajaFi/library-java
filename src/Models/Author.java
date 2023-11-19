package Models;

public class Author {
    private  int id ;
    private String AuthorName ;
    private String sex ;

    public Author(int id, String authorName, String sex) {
        this.id = id;
        AuthorName = authorName;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", AuthorName='" + AuthorName + '\'' +
                ", sex=" + sex +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
