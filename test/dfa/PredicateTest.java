package dfa;

import org.junit.Assert;
import org.junit.Test;

import static dfa.Predicate.c;
import static dfa.Predicate.cc;
import static dfa.Predicate.minus;

public class PredicateTest {

    @Test
    public void tests() {
        final Predicate dash = c('-');
        final Predicate syms = cc('-', '+', '!');
        final Predicate notdash = minus(Predicate.ALL, dash);
        Assert.assertTrue(dash.test('-'));
        Assert.assertTrue(syms.test('-'));
        Assert.assertFalse(syms.test('0'));
        Assert.assertTrue(notdash.test('0'));
        Assert.assertFalse(notdash.test('-'));
    }
}
