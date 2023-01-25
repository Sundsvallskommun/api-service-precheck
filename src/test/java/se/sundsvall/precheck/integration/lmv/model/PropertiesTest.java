package se.sundsvall.precheck.integration.lmv.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

class PropertiesTest {

	@Test
	void testBean() {
		assertThat(Properties.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var objektstatus = "objektstatus";
		final var adressplatsattribut = Adressplatsattribut.create();
		
		final var properties = Properties.create()
			.withObjektstatus(objektstatus)
			.withAdressplatsattribut(adressplatsattribut);
		
		Assertions.assertThat(properties).isNotNull().hasNoNullFieldsOrProperties();
		Assertions.assertThat(properties.getObjektstatus()).isEqualTo(objektstatus);
		Assertions.assertThat(properties.getAdressplatsattribut()).isEqualTo(adressplatsattribut);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		Assertions.assertThat(Properties.create()).hasAllNullFieldsOrProperties();
		Assertions.assertThat(new Properties()).hasAllNullFieldsOrProperties();
	}
}
