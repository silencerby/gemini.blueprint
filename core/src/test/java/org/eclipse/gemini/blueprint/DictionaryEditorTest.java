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

package org.eclipse.gemini.blueprint;

import java.util.Dictionary;

import javax.inject.Inject;

//@ContextConfiguration({"classpath:/org/eclipse/gemini/blueprint/dict-editor.xml"})
public class DictionaryEditorTest /*extends AbstractJUnit4SpringContextTests*/ {

	@Inject
	private Dictionary dictionary;

//	protected void customizeBeanFactory(DefaultListableBeanFactory beanFactory) {
//		beanFactory.registerCustomEditor(Dictionary.class, PropertiesEditor.class);
//		super.customizeBeanFactory(beanFactory);
//	}

	//@Test
	public void tstInjection() {
		//assertNotNull(dictionary);
	}

	//@Test
	public void tstInjectedValue() {
		//assertSame(applicationContext.getBean("dictionary"), dictionary);
	}

	//@Test
	public void testSanity() throws Exception {
		System.out.println(String[][].class);
	}

}