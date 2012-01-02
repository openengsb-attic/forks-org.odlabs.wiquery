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
package org.odlabs.wiquery.core.javascript;

import java.io.Serializable;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.IRequestTarget;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxRequestTarget.IJavascriptResponse;
import org.apache.wicket.ajax.AjaxRequestTarget.IListener;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;
import org.odlabs.wiquery.core.commons.CoreJavaScriptResourceReference;
import org.odlabs.wiquery.core.commons.WiQuerySettings;
import org.odlabs.wiquery.core.commons.WiqueryGeneratedJavaScriptResource;

/**
 * $Id: JsQuery.java 704 2011-02-07 09:57:01Z hielke.hoeve@gmail.com $
 * <p>
 * {@link JsQuery} is the main entry point of WickeXt's JavaScript integration.
 * This class is used to link JavaScript to a component and to render it.
 * </p>
 * <p>
 * This class implements the {@link IHeaderContributor} interface, so if you
 * want to append the generated JavaScript, just do this:
 * 
 * <pre>
 *  	<code>
 * JsQuery jsq = new JsQuery(myComponent);
 * jsq.$().chain(&quot;css&quot;, &quot;border&quot;, &quot;1px solid red&quot;);
 * myComponent.add(new HeaderContributor(jsq));
 * </code>
 *  </pre>
 * 
 * </p>
 * <p>
 * If you want to generate a statement concerning a component, do it as below:
 * <p>
 * <code>new JsQuery(yourComponent).$().chain("css", "border", "1px solid red"));</code>
 * </p>
 * </p>
 * 
 * @author Lionel Armanet
 * @since 0.7
 */
public class JsQuery implements Serializable, IHeaderContributor {

	private static final long serialVersionUID = -5351600688981395614L;

	/**
	 * The component attached to the statement.
	 */
	private Component component;

	/**
	 * The built statement.
	 */
	private JsStatement statement;

	/**
	 * Creates a new {@link JsQuery} linked to a {@link Component}.
	 */
	public JsQuery(Component component) {
		super();
		this.component = component;
		this.component.setOutputMarkupId(true);
	}

	/**
	 * Creates a new {@link JsQuery}.
	 */
	public JsQuery() {

	}

	/**
	 * @return a new {@link JsStatement} initialized with the <code>$</code>
	 *         statement.
	 */
	public JsStatement $() {
		return statement = new JsStatement().$(component);
	}

	/**
	 * Same as {@link #$()} but with a specified CSS selector. If this
	 * {@link JsQuery} is linked to a component, the resulting JavaScript code
	 * will be
	 * <p>
	 * <code>$("#yourComponentId cssSelector")</code>
	 * </p>
	 * 
	 * @param selector
	 * @return
	 */
	public JsStatement $(String selector) {
		return statement = new JsStatement().$(component, selector);
	}

	/**
	 * @return a new {@link JsStatement} initialized with the
	 *         <code>document</code> statement.
	 */
	public JsStatement document() {
		return statement = new JsStatement().document();
	}

	/**
	 * {@inheritDoc}
	 * @see org.apache.wicket.markup.html.IHeaderContributor#renderHead(org.apache.wicket.markup.html.IHeaderResponse)
	 */
	public void renderHead(IHeaderResponse response) {
		WiQuerySettings settings = WiQuerySettings.get();
		
		if(settings.isAutoImportJQueryResource()){
			JavascriptResourceReference ref = settings.getJQueryCoreResourceReference();
			response.renderJavascriptReference(ref == null ? CoreJavaScriptResourceReference.get() : ref);	
		}
		
		IRequestTarget requestTarget = (component != null ? component.getRequestCycle() : RequestCycle.get()).getRequestTarget();
		if (requestTarget == null
				|| !(requestTarget instanceof AjaxRequestTarget)) {
			// appending component statement
			// on dom ready, the code is executed.
			JsStatement onreadyStatement = new JsStatement();
			onreadyStatement.document().ready(
					JsScope.quickScope(JsQuery.this.statement.render()));
			response.renderString("<script type=\"text/javascript\">"
					+ onreadyStatement.render() + "</script>");
		} else {
			addAjaxJavascript(requestTarget, statement.render().toString());
		}
	}

	/**
	 * Adds this statement as a {@link HeaderContributor} for the given
	 * component.
	 */
	public void contribute(Component component) {
		this.component = component;
		component.add(new HeaderContributor(this));
	}

	/**
	 * @return the statement
	 */
	public JsStatement getStatement() {
		return statement;
	}

	/**
	 * FOR FRAMEWORK'S INTERNAL USE ONLY
	 */
	public void setStatement(JsStatement jsStatement) {
		this.statement = jsStatement;
	}

	/**
	 * FOR FRAMEWORK'S INTERNAL USE ONLY
	 */
	public void renderHead(IHeaderResponse response,
			IRequestTarget requestTarget) {
		WiQuerySettings settings = WiQuerySettings.get();
		final String js = statement == null ? null : statement.render().toString();
		
		if (js != null && js.trim().length() > 0) {
			JsStatement onreadyStatement = new JsStatement();
			onreadyStatement.document().ready(JsScope.quickScope(js));
			
			if (settings.isEmbedGeneratedStatements()) {
                if (settings.isAutoImportJQueryResource()) {
                    JavascriptResourceReference ref = settings.getJQueryCoreResourceReference();
                    response.renderJavascriptReference(ref == null ? CoreJavaScriptResourceReference.get() : ref);
                }

                if (requestTarget == null || !(requestTarget instanceof AjaxRequestTarget)) {
                    // appending component statement
                    // on dom ready, the code is executed.
                    response.renderJavascript(WiqueryGeneratedJavaScriptResource.wiqueryGeneratedJavascriptCode(onreadyStatement.render()), "wiquery-gen-"+System.currentTimeMillis());

                } else {
                	addAjaxJavascript(requestTarget, js);
                }

            } else if (AjaxRequestTarget.get() == null) {
				/*
				 * use {@link WiqueryGeneratedJavaScriptResourceReference} to
				 * compress, minimize, etc the given javascript, then
				 * immediately retrieve it.
				 */
            	response.renderJavascript(WiqueryGeneratedJavaScriptResource.wiqueryGeneratedJavascriptCode(onreadyStatement.render()), "wiquery-gen-"+System.currentTimeMillis());

			} else {
				addAjaxJavascript(requestTarget, js);
			}
		}
	}
	
	/**
	 * Private method to add javascript into the ajax request pool
	 * @param js
	 */
	private void addAjaxJavascript(IRequestTarget requestTarget, final String js){
		AjaxRequestTarget ajaxRequestTarget = (AjaxRequestTarget) requestTarget;
		ajaxRequestTarget.addListener(new IListener() {

			/**
			 * {@inheritDoc}
			 * @see org.apache.wicket.ajax.AjaxRequestTarget.IListener#onAfterRespond(java.util.Map, org.apache.wicket.ajax.AjaxRequestTarget.IJavascriptResponse)
			 */
			public void onAfterRespond(Map<String, Component> map,
					IJavascriptResponse response) {
				response.addJavascript(js);
			}

			/**
			 * {@inheritDoc}
			 * @see org.apache.wicket.ajax.AjaxRequestTarget.IListener#onBeforeRespond(java.util.Map, org.apache.wicket.ajax.AjaxRequestTarget)
			 */
			public void onBeforeRespond(Map<String, Component> map, AjaxRequestTarget target) {
				// Do nothing
			}
		});
	}
}
