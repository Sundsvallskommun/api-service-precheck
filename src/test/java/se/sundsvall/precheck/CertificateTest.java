package se.sundsvall.precheck;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

class CertificateTest {

	private static final String PATH = "truststore/";
	private static final String CERTIFICATE = "lantmateriet.se.crt";
	private static final String FAIL_MESSAGE = "Certificate '%s' is close to expiration (%s) and the certificate needs to be replaced";
	private static final int MONTHS_UNTIL_CERTIFICATE_EXPIRES = 2;

	@Test
	void getSimpleCertExpiryDate() throws Exception {

		CertificateFactory factory = CertificateFactory.getInstance("X509");
		InputStream certificate = new ClassPathResource(PATH + CERTIFICATE).getInputStream();
		X509Certificate cert = (X509Certificate)factory.generateCertificate(certificate);

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + MONTHS_UNTIL_CERTIFICATE_EXPIRES);

		assertThat(cert.getNotAfter())
			.withFailMessage(FAIL_MESSAGE, CERTIFICATE, MONTHS_UNTIL_CERTIFICATE_EXPIRES, cert.getNotAfter())
			.isAfterOrEqualTo(cal.getTime());
	}
}