package dfa;

public interface Action<C extends Context> {

    void perform(C context);
}
