package dfa;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class Predicate {
    abstract boolean test(char c);

    public static final Predicate ALL = new Predicate() {
        @Override
        public boolean test(char c) {
            return true;
        }
    };

    public static Predicate not(final Predicate p) {
        return new Predicate() {
            @Override
            boolean test(char c) {
                return !p.test(c);
            }
        };
    };

    public static Predicate and(final Predicate p1, final Predicate p2) {
        return new Predicate() {
            @Override
            boolean test(char c) {
                return p1.test(c) && p2.test(c);
            }
        };
    };

    public static Predicate or(final Predicate p1, final Predicate p2) {
        return new Predicate() {
            @Override
            boolean test(char c) {
                return p1.test(c) || p2.test(c);
            }
        };
    };

    public static Predicate minus(final Predicate from, final Predicate p) {
        return and(from, not(p));
    };

    public static Predicate c(char c) {
        return new SimplePredicate(c);
    }

    public static Predicate cc(Character ... cs) {
        return new SetPredicate(cs);
    }

    static class SetPredicate extends Predicate {

        final Set<Character> set;

        public SetPredicate(Set<Character> set) {
            this.set = set;
        }

        public SetPredicate(Character ... cs) {
            set = new HashSet<Character>(Arrays.asList(cs));
        }

        @Override
        public boolean test(char c) {
            return set.contains(c);
        }
    }

    static class SimplePredicate extends Predicate {

        final char c;

        public SimplePredicate(char c) {
            this.c = c;
        }

        @Override
        public boolean test(char t) {
            return c == t;
        }
    }
}
