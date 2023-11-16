package Models;

public enum topic_enum {
    ROMANCE, COMEDY, OTHER;

    @Override
    public String toString() {
        return name().toUpperCase(); // ou name().toUpperCase() pour majuscules

    }
}
