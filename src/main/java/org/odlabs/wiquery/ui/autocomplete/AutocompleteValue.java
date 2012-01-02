package org.odlabs.wiquery.ui.autocomplete;

import org.apache.wicket.Response;
import org.apache.wicket.model.IModel;

/**
 * @author Julien Roche
 *
 * @param <T>
 * @param <E>
 * @deprecated will be removed in 1.2
 */
@Deprecated
public class AutocompleteValue<T, E> {

	private IModel<T> key;
	
	private IModel<E> value;

	public AutocompleteValue(IModel<T> key, IModel<E> value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	public void write(Response response) {
		response.write(key.toString());
		response.write(":");
		response.write("'" + value.toString() + "'");
	}
	
}
