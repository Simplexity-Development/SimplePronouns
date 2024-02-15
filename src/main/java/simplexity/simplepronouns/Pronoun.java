package simplexity.simplepronouns;

public class Pronoun {
    String label;
    boolean selectable;
    String subjective;
    String objective;
    String possessive;
    String possessiveAdjective;
    String reflexive;
    public Pronoun(String label, boolean selectable, String subjective, String objective, String possessive, String possessiveAdjective, String reflexive) {
        this.label = label;
        this.selectable = selectable;
        this.subjective = subjective;
        this.objective = objective;
        this.possessive = possessive;
        this.possessiveAdjective = possessiveAdjective;
        this.reflexive = reflexive;
    }
    
    public String getLabel() {
        return label;
    }
    public boolean isSelectable() {
        return selectable;
    }
    public String getSubjective() {
        return subjective;
    }
    public String getObjective() {
        return objective;
    }
    public String getPossessive() {
        return possessive;
    }
    public String getPossessiveAdjective() {
        return possessiveAdjective;
    }
    public String getReflexive() {
        return reflexive;
    }
    public void setSubjective(String subjective) {
        this.subjective = subjective;
    }
    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }
    public void setObjective(String objective) {
        this.objective = objective;
    }
    public void setPossessive(String possessive) {
        this.possessive = possessive;
    }
    public void setReflexive(String reflexive) {
        this.reflexive = reflexive;
    }
    public String toString() {
        return "Pronoun{" +
                "subjective='" + subjective + '\'' +
                ", objective='" + objective + '\'' +
                ", possessive='" + possessive + '\'' +
                ", reflexive='" + reflexive + '\'' +
                '}';
    }

}
