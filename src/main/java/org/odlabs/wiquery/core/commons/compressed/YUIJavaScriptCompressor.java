package org.odlabs.wiquery.core.commons.compressed;

import org.apache.wicket.javascript.IJavascriptCompressor;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wicket Javascript Compressor implementation which compresses Javascript using
 * the YUI Compressor.
 * 
 * @author Vincent Heet
 */
public class YUIJavaScriptCompressor implements IJavascriptCompressor {
	private static final Logger log = LoggerFactory
			.getLogger(YUIJavaScriptCompressor.class);

	public String compress(String original) {
		log.info("would compress javascript, but the implementation has been removed");
		// disable this compressor. It does not work with OSGi because the
		// implementation depends on classes private to
		// org.mozilla.javascript-package
		return original;
	}
}
