package org.odlabs.wiquery.core.commons;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.resources.CompressedResourceReference;

/**
 * <p>
 * {@link ResourceReference} which checks the {@link WiQuerySettings} if the
 * resources needs to fetch the normal (non-minimized) version or the minimized
 * version.
 * </p>
 * 
 * <p>
 * Note that this ResourceReference only loads files and does not minify on the
 * fly.
 * </p>
 * 
 * <p>
 * Always provide the normal (non-minimized) version, wiquery will reference to
 * the minimized version when {@link WiQuerySettings#isCompressedJavascript()}
 * is true.
 * </p>
 * <p>
 * The filename format for the 2 versions is:
 * <ul>
 * <li>Normal version: <i>foo.js</i> / <i>foo.css</i></li>
 * <li>Minimized version: <i>foo.min.js</i> / <i>foo.min.css</i></li>
 * </ul>
 * </p>
 * 
 * @author Hielke Hoeve
 */
public class WiQueryStyleSheetResourceReference extends
		CompressedResourceReference {
	private static final long serialVersionUID = 1L;

	public WiQueryStyleSheetResourceReference(Class<?> scope, String name) {
		super(scope, processName(name), null, null);
	}

	private static String processName(String name) {
		if (isMinifiedJavascript())
			return name.substring(0, name.length() - 3) + "min.css";

		return name;
	}

	public static boolean isMinifiedJavascript() {
		return WiQuerySettings.get().isMinifiedResources();
	}
}
