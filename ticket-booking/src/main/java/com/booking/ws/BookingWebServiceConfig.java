package com.booking.ws;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class BookingWebServiceConfig extends WsConfigurerAdapter {

	private static final String TARGET_NAME_SPACE = "http://booking.com/ws/ticket";
	private static final String LOCATOIN_URI = "/ws";
	private static final String PORT_TYPE_NAME = "BookingPort";
	private static final String CLASSPATH_RESOURCE = "booking.xsd";
	private static final String MAPPING_URI = "/ws/*";

	@Bean(name = "bookingDetailsWsdl")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema ticketBookingSchema) {

		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName(PORT_TYPE_NAME);
		wsdl11Definition.setLocationUri(LOCATOIN_URI);
		wsdl11Definition.setTargetNamespace(TARGET_NAME_SPACE);
		wsdl11Definition.setSchema(ticketBookingSchema);

		return wsdl11Definition;
	}

	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
			ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(Boolean.TRUE);
		return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, MAPPING_URI);
	}

	@Bean
	public XsdSchema ticketBookingSchema() {
		return new SimpleXsdSchema(new ClassPathResource(CLASSPATH_RESOURCE));
	}

}
