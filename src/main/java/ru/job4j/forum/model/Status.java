package ru.job4j.forum.model;

public enum Status {
    CLOSED("закрыта"), ACTIVE("активна");

    private String text;

    Status(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
