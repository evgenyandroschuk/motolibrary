package motolibrary.model;

public enum Languages {

    EN(1), RU(2);
    private final int id;

    Languages(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
