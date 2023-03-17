public class LSystem {

    private String axiom;
    //for X -> F+[[X]-X]-F[-FX]+X
    private final String rule1;
    //for F -> FF
    private final String rule2;

    public LSystem() {
        axiom = "X";
        rule1 = "F+[[X]-X]-F[-FX]+X";
        rule2 = "FF";
    }

    public String getAxiom() {
        return axiom;
    }

    public void nextStep() {
        StringBuilder nextAxiom = new StringBuilder();

        for (int i = 0; i < axiom.length(); i++) {
            if (axiom.charAt(i) == 'X') {
                nextAxiom.append(rule1);
            }
            else if (axiom.charAt(i) == 'F') {
                nextAxiom.append(rule2);
            }
            else {
                nextAxiom.append(axiom.charAt(i));
            }
        }
        axiom = nextAxiom.toString();
    }
}