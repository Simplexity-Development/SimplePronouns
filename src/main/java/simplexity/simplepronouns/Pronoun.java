package simplexity.simplepronouns;

import org.jetbrains.annotations.NotNull;

public record Pronoun(String subjective, String objective, String possessive, String possessiveAdjective,
                      String reflexive) {
    
    public String toString() {
        return "Pronoun{" +
                "subjective='" + subjective + '\'' +
                ", objective='" + objective + '\'' +
                ", possessive='" + possessive + '\'' +
                ", reflexive='" + reflexive + '\'' +
                '}';
    }
    
    public static String serialize(@NotNull Pronoun pronoun) {
        return String.format("%s///%s///%s///%s///%s",
                pronoun.subjective, pronoun.objective, pronoun.possessive,
                pronoun.possessiveAdjective, pronoun.reflexive);
    }
    
    public static Pronoun deserialize(@NotNull String string) {
        String[] arr = string.split("///");
        if (arr.length != 5) return null;
        return new Pronoun(arr[0], arr[1], arr[2], arr[3], arr[4]);
    }
    
}
