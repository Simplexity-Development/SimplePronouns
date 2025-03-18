package simplexity.simplepronouns;

import org.jetbrains.annotations.NotNull;

public final class Pronoun {

    private String subjective = "";
    private String objective = "";
    private String possessive = "";
    private String possessiveAdjective = "";
    private String reflexive = "";

    public Pronoun(String subjective, String objective, String possessive, String possessiveAdjective,
                   String reflexive) {
        this.subjective = subjective;
        this.objective = objective;
        this.possessive = possessive;
        this.possessiveAdjective = possessiveAdjective;
        this.reflexive = reflexive;
    }

    public String toString() {
        return "Pronoun{" +
               "subjective='" + subjective + '\'' +
               ", objective='" + objective + '\'' +
               ", possessive='" + possessive + '\'' +
               ", possessive_adjective='" + possessiveAdjective + '\'' +
               ", reflexive='" + reflexive + '\'' +
               '}';
    }

    public static String serialize(@NotNull Pronoun pronoun) {
        return String.join("///",
                pronoun.subjective.isEmpty() ? " " : pronoun.subjective,
                pronoun.objective.isEmpty() ? " " : pronoun.objective,
                pronoun.possessive.isEmpty() ? " " : pronoun.possessive,
                pronoun.possessiveAdjective.isEmpty() ? " " : pronoun.possessiveAdjective,
                pronoun.reflexive.isEmpty() ? " " : pronoun.reflexive);
    }

    public static Pronoun deserialize(@NotNull String string) {
        String[] arr = string.split("///", -1); // Ensure split keeps empty fields
        if (arr.length != 5) return null;
        return new Pronoun(arr[0].trim(), arr[1].trim(), arr[2].trim(), arr[3].trim(), arr[4].trim());
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

    public String getSubjective() {
        return subjective;
    }
}
