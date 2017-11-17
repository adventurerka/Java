package ru.stqa.pft.rest;

public class Issue {

    private int id;
    private String subject;
    private String description;

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Issue withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Issue issue = (Issue) o;

        if (getId() != issue.getId()) return false;
        if (getSubject() != null ? !getSubject().equals(issue.getSubject()) : issue.getSubject() != null) return false;
        return getDescription() != null ? getDescription().equals(issue.getDescription()) : issue.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getSubject() != null ? getSubject().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}
