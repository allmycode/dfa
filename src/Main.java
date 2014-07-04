import dfa.Context;
import dfa.Predicate;
import dfa.TableDFA;

public class Main {

    static enum State {
        N, O
    }

    static class NOContext extends Context {

    }

    public static void main(String[] args) {
        TableDFA<State, NOContext> dfa = new TableDFA<State, NOContext>(State.values().length);
        dfa.putTransition(State.N, State.O, Predicate.c('1'));
        dfa.putTransition(State.O, State.N, Predicate.c('0'));

        dfa.run(State.N, new NOContext(), "10101010".toCharArray());

        try {
            dfa.run(State.N, new NOContext(), "010101010".toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            dfa.run(State.N, new NOContext(), "110101010".toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
