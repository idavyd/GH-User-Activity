public enum GitHubUserAction {
    CREATE("created"),
    DELETE("deleted");

    private final String label;

    GitHubUserAction(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
