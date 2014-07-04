package dfa;

public class StateAction<S, C extends Context> {
    S s;
    Action<C> a;

    StateAction(S s, Action<C> a) {
        this.s = s;
        this.a = a;
    }
}
