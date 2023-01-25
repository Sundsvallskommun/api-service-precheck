package se.sundsvall.precheck.integration.lmv.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

class FeatureCollectionTest {

	@Test
	void testBean() {
		assertThat(FeatureCollection.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var features = List.of(Feature.create());
		
		final var featureCollection = FeatureCollection.create()
			.withFeatures(features);
		
		Assertions.assertThat(featureCollection).isNotNull().hasNoNullFieldsOrProperties();
		Assertions.assertThat(featureCollection.getFeatures()).isEqualTo(features);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		Assertions.assertThat(FeatureCollection.create()).hasAllNullFieldsOrProperties();
		Assertions.assertThat(new FeatureCollection()).hasAllNullFieldsOrProperties();
	}
}
