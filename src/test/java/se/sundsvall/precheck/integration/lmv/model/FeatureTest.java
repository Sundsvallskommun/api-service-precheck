package se.sundsvall.precheck.integration.lmv.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class FeatureTest {

	@Test
	void testBean() {
		assertThat(Feature.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var type = "type";
		final var properties = Properties.create();
		
		final var feature = Feature.create()
			.withType(type)
			.withProperties(properties);
		
		Assertions.assertThat(feature).isNotNull().hasNoNullFieldsOrProperties();
		Assertions.assertThat(feature.getType()).isEqualTo(type);
		Assertions.assertThat(feature.getProperties()).isEqualTo(properties);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		Assertions.assertThat(Feature.create()).hasAllNullFieldsOrProperties();
		Assertions.assertThat(new Feature()).hasAllNullFieldsOrProperties();
	}
}
