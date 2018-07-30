package org.xstefank.lra.execution;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xstefank.lra.definition.Action;
import org.xstefank.lra.definition.LRABuilder;
import org.xstefank.lra.definition.LRADefinition;
import org.xstefank.lra.execution.model.LRAOutcome;
import org.xstefank.lra.execution.model.LRAResult;
import org.xstefank.lra.model.ActionResult;
import org.xstefank.lra.model.LRAData;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings(value = "unchecked")
public class LRAExecutorTest {

    private LRADefinition definition;
    private AtomicInteger counter;

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
        definition = LRABuilder.lra()
                .name("test LRA")
                .withAction(d -> {
                    counter.getAndIncrement();
                    return ActionResult.success();
                })
                .data(new StringBuilder("mutable"))
                .build();

        LRAExecutorStub lraExecutor = new LRAExecutorStub(definition);

        LRAResult lraResult = lraExecutor.executeLRA(definition);

        Assert.assertEquals(LRAOutcome.COMPLETED, lraResult.getOutcome());
        Assert.assertEquals(1, counter.get());
        Assert.assertEquals("mutable success", ((StringBuilder) definition.getData()).toString());

    }

    @Test
    public void testExecutionFailure() {
        definition = LRABuilder.lra()
                .name("test LRA")
                .withAction(d -> ActionResult.failure())
                .data(new StringBuilder("mutable"))
                .build();

        LRAExecutorStub lraExecutor = new LRAExecutorStub(definition);

        LRAResult lraResult = lraExecutor.executeLRA(definition);

        Assert.assertEquals(LRAOutcome.NEED_COMPENSATION, lraResult.getOutcome());
        Assert.assertEquals(0, counter.get());
        Assert.assertEquals("mutable failure", ((StringBuilder) definition.getData()).toString());
    }

    @Test
    public void testSimpleExecutionAsync() throws ExecutionException, InterruptedException {
        definition = LRABuilder.lra()
                .name("test LRA")
                .withAction(d -> {
                    counter.getAndIncrement();
                    return ActionResult.success();
                })
                .data(new StringBuilder("mutable"))
                .build();

        LRAExecutorStub lraExecutor = new LRAExecutorStub(definition);
        Future<LRAResult> future = lraExecutor.executeLRAAsync(definition);

        LRAResult lraResult = future.get();

        Assert.assertEquals(LRAOutcome.COMPLETED,lraResult.getOutcome());
        Assert.assertEquals(1, counter.get());
        Assert.assertEquals("mutable success", ((StringBuilder) definition.getData()).toString());

    }

    @Test
    public void testExecutionFailureAsync() throws InterruptedException, ExecutionException {
        definition = LRABuilder.lra()
                .name("test LRA")
                .withAction(d -> ActionResult.failure())
                .data(new StringBuilder("mutable"))
                .build();

        LRAExecutorStub lraExecutor = new LRAExecutorStub(definition);
        Future<LRAResult> future = lraExecutor.executeLRAAsync(definition);

        LRAResult lraResult = future.get();

        Assert.assertEquals(LRAOutcome.NEED_COMPENSATION, lraResult.getOutcome());
        Assert.assertEquals("mutable failure", ((StringBuilder) definition.getData()).toString());
    }

    @Test
    public void testFailureMessageIncluded() throws InterruptedException, ExecutionException {
        definition = LRABuilder.lra()
                .name("test LRA")
                .withAction(d -> ActionResult.failure("test failure message"))
                .data(new StringBuilder("mutable"))
                .build();

        LRAExecutorStub lraExecutor = new LRAExecutorStub(definition);
        Future<LRAResult> future = lraExecutor.executeLRAAsync(definition);

        LRAResult lraResult = future.get();

        Assert.assertEquals(LRAOutcome.NEED_COMPENSATION, lraResult.getOutcome());
        Assert.assertEquals("test failure message", lraResult.getMessage());
        Assert.assertEquals("mutable failure", ((StringBuilder) definition.getData()).toString());
    }

    @Test
    public void testExecutionWithActionResultIncluded() throws Exception {
        definition = LRABuilder.lra()
                .name("test LRA")
                .withAction(new Action() {
                    @Override
                    public ActionResult invoke(LRAData lraData) {
                        return ActionResult.success(" success action 1");
                    }

                    @Override
                    public String getResultName() {
                        return "resultAction1";
                    }
                })
                .withAction(d -> {
                    String s = d.getResults().get("resultAction1") + " success action 2";
                    ((StringBuilder) d.getData()).append(s);
                    return ActionResult.success(s);
                })
                .data(new StringBuilder("mutable"))
                .build();

        LRAExecutorStub lraExecutor = new LRAExecutorStub(definition);
        Future<LRAResult> future = lraExecutor.executeLRAAsync(definition);

        LRAResult lraResult = future.get();

        Assert.assertEquals(LRAOutcome.COMPLETED, lraResult.getOutcome());
        Assert.assertEquals("mutable success action 1 success action 2 success", ((StringBuilder) definition.getData()).toString());
    }

    private static final class LRAExecutorStub extends AbstractLRAExecutor {

        private LRADefinition definition;

        public LRAExecutorStub(LRADefinition definition) {
            this.definition = definition;
        }

        @Override
        public URL startLRA(LRADefinition lraDefinition) {
            try {
                return new URL("http://stub.lra");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void compensateLRA(LRAResult lraResult) {
            ((StringBuilder) definition.getData()).append(" failure");
        }

        @Override
        protected void completeLRA(LRAResult lraResult) {
            ((StringBuilder) definition.getData()).append(" success");
        }
    }
}
