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

class AdressplatsattributTest {

	@Test
	void testBean() {
		assertThat(Adressplatsattribut.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var type = "type";
		final var adressplatspunkt = Adressplatspunkt.create();
		
		final var adressplatsattribut = Adressplatsattribut.create()
			.withType(type)
			.withAdressplatspunkt(adressplatspunkt);
		
		Assertions.assertThat(adressplatsattribut).isNotNull().hasNoNullFieldsOrProperties();
		Assertions.assertThat(adressplatsattribut.getType()).isEqualTo(type);
		Assertions.assertThat(adressplatsattribut.getAdressplatspunkt()).isEqualTo(adressplatspunkt);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		Assertions.assertThat(Adressplatsattribut.create()).hasAllNullFieldsOrProperties();
		Assertions.assertThat(new Adressplatsattribut()).hasAllNullFieldsOrProperties();
	}
}
