package dfa;

import java.util.ArrayList;
import java.util.List;

public class TableDFA<S extends Enum<S>, C extends Context> {


    static class PredicateStateAction<S, C extends Context> {
        Predicate p;
        StateAction<S, C> sa;

        PredicateStateAction(Predicate p, StateAction<S, C> sa) {
            this.p = p;
            this.sa = sa;
        }
    }

    List<PredicateStateAction<S, C>>[] table;

    public TableDFA(int numStates) {
        table = new List[numStates];
    }

    public void putTransition(S fromState, S toState, Predicate input) {
        putTransition(fromState, toState, input, null);
    }

    public void putTransition(S fromState, S toState, Predicate input, Action action) {
        List<PredicateStateAction<S, C>> l = table[fromState.ordinal()];
        if (l == null) {
            table[fromState.ordinal()] = l = new ArrayList<PredicateStateAction<S, C>>();
        }
        l.add(new PredicateStateAction<S, C>(input, new StateAction<S, C>(toState, action)));
    }

    public StateAction<S, C> getTransition(S fromState, char input) {
        List<PredicateStateAction<S, C>> l = table[fromState.ordinal()];
        if (l == null) {
            throw new RuntimeException("Not transitions registered for state: " + fromState);
        }

        for (PredicateStateAction<S, C> psa : l) {
            if (psa.p.test(input)) {
                return psa.sa;
            }
        }

        throw new RuntimeException("Not transition registered for state: " + fromState + " and input: '" + input + "'");
    }

    public void run(S initial, C context, char[] input) {
        S state = initial;
        for (int i = 0; i < input.length; i++) {
            char c = input[i];
            context.pos = i;
            context.c = c;
            StateAction<S, C> sa = getTransition(state, c);
            state = sa.s;
            if (sa.a != null) {
                sa.a.perform(context);
            }
        }
    }


}
