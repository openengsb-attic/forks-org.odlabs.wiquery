/*
 * Copyright (c) 2009 WiQuery team
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
package org.odlabs.wiquery.core.commons;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.Component.IVisitor;

/**
 * $Id: WiQueryPluginVisitor.java 412 2010-09-17 21:23:25Z lionel.armanet $
 * <p>
 * 	Visits component hierarchy to find plugins both in components and behaviors
 * </p>
 * @author Lionel Armanet
 * @since 1.0-m2
 */
public class WiQueryPluginVisitor implements IVisitor<Component>, Serializable {

	private static final long serialVersionUID = -4147296857772880048L;
	
	private IWiQueryPlugin wiQueryPlugin;
	
	public WiQueryPluginVisitor(IWiQueryPlugin wiQueryPlugin) {
		super();
		this.wiQueryPlugin = wiQueryPlugin;
	}

	public Object component(Component component) {
		if (component.getBehaviors().contains(this.wiQueryPlugin)) {
			return Boolean.TRUE;
		}
		if (component.equals(this.wiQueryPlugin)) {
			return Boolean.TRUE;
		}
		return IVisitor.CONTINUE_TRAVERSAL;
	}
	
}
