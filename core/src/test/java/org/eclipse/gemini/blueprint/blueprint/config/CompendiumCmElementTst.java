/******************************************************************************
 * Copyright (c) 2006, 2010 VMware Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution. 
 * The Eclipse Public License is available at 
 * http://www.eclipse.org/legal/epl-v10.html and the Apache License v2.0
 * is available at http://www.opensource.org/licenses/apache2.0.php.
 * You may elect to redistribute this code under either of these licenses. 
 * 
 * Contributors:
 *   VMware Inc.
 *****************************************************************************/

package org.eclipse.gemini.blueprint.blueprint.config;

import java.io.IOException;
import java.util.Properties;

import junit.framework.TestCase;

import org.easymock.MockControl;
import org.eclipse.gemini.blueprint.compendium.config.MockConfigurationAdmin;
import org.eclipse.gemini.blueprint.context.support.BundleContextAwareProcessor;
import org.eclipse.gemini.blueprint.mock.MockBundleContext;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ObjectUtils;

/**
 * The Compendium namespace is borked.
 * 
 * @author Costin Leau
 * 
 */
public class CompendiumCmElementTst extends TestCase {

	private static final String CONFIG = "cm-config.xml";

	private GenericApplicationContext context;
	private XmlBeanDefinitionReader reader;

	private Configuration cfg;

	@Override
	protected void setUp() throws Exception {
		MockControl mc = MockControl.createNiceControl(Configuration.class);
		cfg = (Configuration) mc.getMock();
		mc.expectAndReturn(cfg.getProperties(), new Properties());
		mc.replay();
		BundleContext bundleContext = new MockBundleContext() {
			@Override
			public Object getService(ServiceReference reference) {
				String[] clazzes = (String[]) reference.getProperty(Constants.OBJECTCLASS);
				if (clazzes[0].equals(ConfigurationAdmin.class.getName())) {
					return new MockConfigurationAdmin() {
						@Override
						public Configuration getConfiguration(String pid) throws IOException {
							return cfg;
						}
					};
				} else
					return super.getService(reference);
			}

		};

		context = new GenericApplicationContext();
		context.getBeanFactory().addBeanPostProcessor(new BundleContextAwareProcessor(bundleContext));
		context.setClassLoader(getClass().getClassLoader());

		reader = new XmlBeanDefinitionReader(context);
		reader.loadBeanDefinitions(new ClassPathResource(CONFIG, getClass()));
		context.refresh();
	}

	@Override
	protected void tearDown() throws Exception {
		context.close();
		context = null;
	}

	public void testNumberOfBeans() throws Exception {
		System.out.println("The beans declared are: " + ObjectUtils.nullSafeToString(context.getBeanDefinitionNames()));
		assertTrue("not enough beans found", context.getBeanDefinitionCount() >= 13);
	}

}