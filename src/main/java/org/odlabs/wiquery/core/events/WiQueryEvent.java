package org.odlabs.wiquery.core.events;

/**
 * @deprecated will be removed in 1.2
 * $Id: WiQueryEvent.java 415 2010-09-17 21:32:54Z lionel.armanet $
 *             <p>
 *             TODO insert comments here
 *             </p>
 * @author lionel
 * @since 1.0
 */
public enum WiQueryEvent implements EventLabel {

	READY;

	public String getEventLabel() {
		return "wiquery:ready";
	}

}
