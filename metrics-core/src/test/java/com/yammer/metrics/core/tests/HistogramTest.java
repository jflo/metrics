package com.yammer.metrics.core.tests;

import com.yammer.metrics.core.Histogram;
import com.yammer.metrics.stats.Snapshot;
import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class HistogramTest {
    private final Histogram histogram = new Histogram(Histogram.SampleType.UNIFORM);

    @Test
    public void anEmptyHistogram() throws Exception {
        assertThat("the histogram has a count of zero",
                   histogram.getCount(),
                   is(0L));

        assertThat("the histogram has a max of zero",
                   histogram.getMax(),
                   is(0L));

        assertThat("the histogram has a min of zero",
                   histogram.getMin(),
                   is(0L));

        assertThat("the histogram has a mean of zero",
                   histogram.getMean(),
                   is(0L));

        assertThat("the histogram has a standard deviation of zero",
                   histogram.getStdDev(),
                   is(closeTo(0.0, 0.0001)));
        
        assertThat("the histogram has a sum of zero",
                   histogram.getSum(),
                   is(0L));

        final Snapshot snapshot = histogram.getSnapshot();

        assertThat("the histogram has a median of zero",
                   snapshot.getMedian(),
                   is(0L));

        assertThat("the histogram has a 75th percentile of zero",
                   snapshot.get75thPercentile(),
                   is(0L));

        assertThat("the histogram has a 99th percentile of zero",
                   snapshot.get99thPercentile(),
                   is(0L));

        assertThat("the histogram is empty",
                   snapshot.size(),
                   is(0));
    }

    @Test
    public void aHistogramWith1000Elements() throws Exception {
        for (int i = 1; i <= 1000; i++) {
            histogram.update(i);
        }

        assertThat("the histogram has a count of 1000",
                   histogram.getCount(),
                   is(1000L));

        assertThat("the histogram has a max of 1000",
                   histogram.getMax(),
                   is(1000L));

        assertThat("the histogram has a min of 1",
                   histogram.getMin(),
                   is(1L));

        assertThat("the histogram has a mean of 500.5",
                   histogram.getMean(),
                   is(500L));

        assertThat("the histogram has a standard deviation of 288.82",
                   histogram.getStdDev(),
                   is(closeTo(288.8194360957494, 0.0001)));
        
        assertThat("the histogram has a sum of 500500",
                   histogram.getSum(),
                   is(500500L));

        final Snapshot snapshot = histogram.getSnapshot();

        assertThat("the histogram has a median of 500",
                   snapshot.getMedian(),
                   is(500L));

        assertThat("the histogram has a 75th percentile of 750",
                   snapshot.get75thPercentile(),
                   is(750L));

        assertThat("the histogram has a 99th percentile of 990",
                   snapshot.get99thPercentile(),
                   is(990L));

        assertThat("the histogram has 1000 values",
                   snapshot.size(),
                   is(1000));
    }
}
