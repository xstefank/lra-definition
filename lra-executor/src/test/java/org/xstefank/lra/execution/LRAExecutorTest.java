package org.xstefank.lra.execution;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xstefank.lra.definition.LRABuilder;
import org.xstefank.lra.definition.LRADefinition;
import org.xstefank.lra.model.ActionResult;

import java.util.concurrent.atomic.AtomicInteger;

public class LRAExecutorTest {

    private LRADefinition definition;
    private AtomicInteger counter;
    private LRAExecutor lraExecutor;

    @Before
    public void before() {
        counter = new AtomicInteger(0);
        definition = null;
    }

    @After
    public void after() {
        counter = null;
    }

    @Test
    public void testSimpleExecution() {
        definition = new LRABuilder()
                .name("test LRA")
                .withAction(data -> {
                    counter.getAndIncrement();
                    return ActionResult.SUCCESS;
                })
                .data(new StringBuilder("mutable"))
                .build();

        LRAExecutorStub lraExecutor = new LRAExecutorStub(definition);
        lraExecutor.executeLRA();

        Assert.assertEquals(1, counter.get());
        Assert.assertEquals("mutable success", ((StringBuilder) definition.getData()).toString());

    }

    @Test
    public void testExecutionFailure() {
        definition = new LRABuilder()
                .name("test LRA")
                .withAction(data -> ActionResult.FAILURE)
                .data(new StringBuilder("mutable"))
                .build();

        LRAExecutorStub lraExecutor = new LRAExecutorStub(definition);
        lraExecutor.executeLRA();

        Assert.assertEquals(0, counter.get());
        Assert.assertEquals("mutable failure", ((StringBuilder) definition.getData()).toString());
    }


    private static final class LRAExecutorStub extends AbstractLRAExecutor {

        private LRADefinition definition;

        public LRAExecutorStub(LRADefinition definition) {
            this.definition = definition;
        }

        public void executeLRA() {
            super.executeLRA(definition);
        }

        @Override
        protected void compensateLRA() {
            ((StringBuilder) definition.getData()).append(" failure");
        }

        @Override
        protected void completeLRA() {
            ((StringBuilder) definition.getData()).append(" success");
        }
    }
}
