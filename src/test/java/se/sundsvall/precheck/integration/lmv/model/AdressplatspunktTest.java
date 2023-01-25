package se.sundsvall.precheck.integration.lmv.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

class AdressplatspunktTest {

	@Test
	void testBean() {
		assertThat(Adressplatspunkt.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var type = "type";
		final var coordinates = List.of(BigDecimal.valueOf(10), BigDecimal.valueOf(20));
		
		final var adressplatspunkt = Adressplatspunkt.create()
			.withType(type)
			.withCoordinates(coordinates);
		
		Assertions.assertThat(adressplatspunkt).isNotNull().hasNoNullFieldsOrProperties();
		Assertions.assertThat(adressplatspunkt.getType()).isEqualTo(type);
		Assertions.assertThat(adressplatspunkt.getCoordinates()).isEqualTo(coordinates);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		Assertions.assertThat(Adressplatspunkt.create()).hasAllNullFieldsOrProperties();
		Assertions.assertThat(new Adressplatspunkt()).hasAllNullFieldsOrProperties();
	}
}
