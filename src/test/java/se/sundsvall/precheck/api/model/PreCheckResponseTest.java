package se.sundsvall.precheck.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import java.util.Map;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PreCheckResponseTest {

    @BeforeAll
    static void setup() {
        registerValueGenerator(() -> now().plusDays(new Random().nextInt()), LocalDate.class);
    }

    @Test
    void testBean() {
        assertThat(PreCheckResponse.class, allOf(
                hasValidBeanConstructor(),
                hasValidGettersAndSetters(),
                hasValidBeanHashCode(),
                hasValidBeanEquals(),
                hasValidBeanToString()));
    }

    @Test
    void testCreatePattern() {
        final var deliverable = false;
        final var futureDeliverable = true;
        final var plannedDevelopmentDate = LocalDate.now();
        final var metaData = Map.of("key1", "value1", "key2", "value2");

        PreCheckResponse preCheckResponse = PreCheckResponse.create()
                .withDeliverable(deliverable)
                .withFutureDeliverable(futureDeliverable)
                .withPlannedDevelopmentDate(plannedDevelopmentDate)
                .withMetaData(metaData);

        assertThat(preCheckResponse).isNotNull().hasNoNullFieldsOrPropertiesExcept("deliverable", "futureDeliverable");
        assertThat(preCheckResponse.isDeliverable()).isEqualTo(deliverable);
        assertThat(preCheckResponse.isFutureDeliverable()).isEqualTo(futureDeliverable);
        assertThat(preCheckResponse.getPlannedDevelopmentDate()).isEqualTo(plannedDevelopmentDate);
        assertThat(preCheckResponse.getMetaData()).isEqualTo(metaData);
    }

    @Test
    void testCreateWithNoValueSet() {
        assertThat(PreCheckResponse.create()).hasAllNullFieldsOrPropertiesExcept("deliverable", "futureDeliverable")
                .hasFieldOrPropertyWithValue("deliverable", false)
                .hasFieldOrPropertyWithValue("futureDeliverable", false);
    }
}