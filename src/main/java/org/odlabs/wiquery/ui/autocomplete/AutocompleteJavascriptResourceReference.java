/*
 * Copyright (c) 2008 Objet Direct
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.odlabs.wiquery.ui.autocomplete;

import org.odlabs.wiquery.core.commons.WiQueryJavaScriptResourceReference;

/**
 * $Id: AutocompleteJavascriptResourceReference.java 457 2010-10-15 07:14:28Z hielke.hoeve@gmail.com $
 * <p>
 * 	References the JavaScript resource to get the Autocomplete component.
 * </p>
 * @author Julien Roche
 * @since 1.1
 */
public class AutocompleteJavascriptResourceReference extends
		WiQueryJavaScriptResourceReference {
	private static final long serialVersionUID = -4771815414204892357L;
	
	/**
	 * Singleton instance.
	 */
	private static AutocompleteJavascriptResourceReference instance = new AutocompleteJavascriptResourceReference();;

	/**
	 * Builds a new instance of {@link AutocompleteJavascriptResourceReference}.
	 */
	private AutocompleteJavascriptResourceReference() {
		super(AutocompleteJavascriptResourceReference.class, "jquery.ui.autocomplete.js");
	}

	/**
	 * Returns the {@link AutocompleteJavascriptResourceReference} instance.
	 */
	public static AutocompleteJavascriptResourceReference get() {
		return instance;
	}
}
