package com.cards.entitty;

public class Card {
    private String term;
    private String definition;
    private int error;

    public Card(String term, String definition, int error) {
        this.term = term;
        this.definition = definition;
        this.error = error;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public void upError() {
        this.error = error+1;
    }


}
