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
package org.odlabs.wiquery.core.effects;

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsStatement;

/**
 * $Id: WiqueryAjaxEffectBehavior.java 412 2010-09-17 21:23:25Z lionel.armanet $
 * <p>
 * Defines an {@link Effect} behavior triggering the
 * {@link #respond(AjaxRequestTarget)} method with an Ajax query.
 * </p>
 * 
 * @author Lionel Armanet
 * @since 1.0
 */
public abstract class WiqueryAjaxEffectBehavior extends AbstractDefaultAjaxBehavior implements IWiQueryPlugin {
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = 6423661892490365888L;
	
	/**
	 * The effect to use.
	 */
	private Effect effect;

	/**
	 * Creates this behavior for a given {@link Effect}.
	 */
	public WiqueryAjaxEffectBehavior(Effect effect) {
		this.effect = effect;
	}

	/**
	 * {@inheritDoc}
	 */
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#getCallbackScript()
	 */
	@Override
	protected CharSequence getCallbackScript() {
		// binds the ajax call to the wrapped event
		JsQuery query = new JsQuery(this.getComponent());
		effect.setCallback(JsScope.quickScope(getHandlerScript()));
		return query.$().chain(effect).render();
	}

	protected CharSequence getHandlerScript() {
		return super.getCallbackScript();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.ajax.AbstractDefaultAjaxBehavior#getPreconditionScript()
	 */
	@Override
	protected CharSequence getPreconditionScript() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public JsStatement statement() {
		JsQuery query = new JsQuery(this.getComponent());
		effect.setCallback(JsScope.quickScope(getHandlerScript()));
		query.$().chain(effect).render();
		return query.getStatement();
	}
}
