/*
 * Copyright (c) 2010 WiQuery team
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
package org.odlabs.wiquery.ui.position;

import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.Options;

/**
 * $Id: PositionOptions.java 415 2010-09-17 21:32:54Z lionel.armanet $
 * 
 * <p>
 * 	Complex option to store the possible options for the position. This is used too
 * for the option position into the Autocomplete component
 * </p>
 *
 * @author Julien Roche
 * @since 1.1
 */
public class PositionOptions implements IComplexOption {
	/**
	 * Enumeration of collision values
	 * @author Julien Roche
	 * @since 1.1
	 *
	 */
	public enum Collision {
		FIT,
		FIT_FIT,
		FIT_FLIP,
		FIT_NONE,
		FLIP,
		FLIP_FIT,
		FLIP_FLIP,
		FLIP_NONE,
		NONE,
		NONE_FLIT,
		NONE_FLIP,
		NONE_NONE;
		
		/**
		 * Method searching the collision value
		 * @param value
		 * @return
		 */
		public static Collision getCollision(String value) {
			return valueOf(value.toUpperCase().replace(" ", "_"));
		}

		/**
		 * {@inheritDoc}
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return super.toString().toLowerCase().replace("_", " ");
		}
	}
	
	/**
	 * Enumeration of position values
	 * @author Julien Roche
	 * @since 1.1
	 *
	 */
	public enum Position {
		BOTTOM,
		CENTER,
		CENTER_BOTTOM,
		CENTER_CENTER,
		CENTER_TOP,
		LEFT,
		LEFT_BOTTOM,
		LEFT_CENTER,
		LEFT_TOP,
		RIGHT_BOTTOM,
		RIGHT_CENTER,
		RIGHT_TOP,
		TOP;
		
		/**
		 * Method searching the Position value
		 * @param value
		 * @return
		 */
		public static Position getPosition(String value) {
			return valueOf(value.toUpperCase().replace(" ", "_"));
		}

		/**
		 * {@inheritDoc}
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return super.toString().toLowerCase().replace("_", " ");
		}
	}
	
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -4264198141649645314L;
	
	// Properties
	private Options options;
	
	/**
	 * Default constructor
	 */
	public PositionOptions() {
		super();
		options = new Options();
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		return options.getJavaScriptOptions();
	}

	/**Method retrieving the options
	 * @return the options
	 */
	protected Options getOptions() {
		return options;
	}
	
	/*---- Options section ---*/
	
	/**
	 * Defines which position on the target element to align the positioned 
	 * element against: "horizontal vertical" alignment. A single value such as 
	 * "right" will default to "right center", "top" will default to "center top" 
	 * (following CSS convention). Acceptable values: "top", "center", "bottom", 
	 * "left", "right". Example: "left top" or "center center"
	 * @param at
	 * @return the instance
	 */
	public PositionOptions setAt(Position at) {
		options.putLiteral("at", at.toString());
		return this;
	}
	
	/**
	 * @return the at option
	 */
	public Position getAt() {
		String value = options.getLiteral("at");
		return value == null ? null : Position.getPosition(value);
	}
	
	/**
	 * Applies the bgiframe plugin when set to true. Only applies when bgiframe 
	 * is actually loaded, nothing happens otherwise.
	 * @param bgiframe
	 * @return the instance
	 */
	public PositionOptions setBgiframe(boolean bgiframe) {
		options.put("bgiframe", bgiframe);
		return this;
	}
	
	/**
	 * @return the bgiframe option
	 */
	public boolean isBgiframe() {
		if(options.containsKey("bgiframe")){
			return options.getBoolean("bgiframe");
		}
		
		return true;
	}
	
	/**
	 * When the positioned element overflows the window in some direction, move 
	 * it to an alternative position. Similar to my and at, this accepts a 
	 * single value or a pair for horizontal/vertical, eg. "flip", "fit", 
	 * "fit flip", "fit none".
	 * <ul>
	 * <li>flip: to the opposite side and the collision detection is run again to 
	 * see if it will fit. If it won't fit in either position, the center option 
	 * should be used as a fall back.</li>
	 * <li>fit: so the element keeps in the desired direction, but is re-positioned 
	 * so it fits.</li>
	 * <li>none: not do collision detection.</li>
	 * </ul>
	 * @param collision
	 * @return the instance
	 */
	public PositionOptions setCollision(Collision collision) {
		options.putLiteral("collision", collision.toString());
		return this;
	}
	
	/**
	 * @return the collision option
	 */
	public Collision getCollision() {
		String value = options.getLiteral("collision");
		return value == null ? null : Collision.getCollision(value);
	}
	
	/**
	 * Defines which position on the element being positioned to align with the 
	 * target element: "horizontal vertical" alignment. A single value such as 
	 * "right" will default to "right center", "top" will default to "center top" 
	 * (following CSS convention). Acceptable values: "top", "center", "bottom", 
	 * "left", "right". Example: "left top" or "center center"
	 * @param my
	 * @return the instance
	 */
	public PositionOptions setMy(Position my) {
		options.putLiteral("my", my.toString());
		return this;
	}
	
	/**
	 * @return the my option
	 */
	public Position getMy() {
		String value = options.getLiteral("my");
		return value == null ? null : Position.getPosition(value);
	}
	
	/**
	 * Element to position against. You can use a browser event object contains 
	 * pageX and pageY values. Example: "#top-menu"
	 * @param of
	 * @return the instance
	 */
	public PositionOptions setOf(String of) {
		options.putLiteral("of", of);
		return this;
	}
	
	/**
	 * @return the of option
	 */
	public String getOf() {
		return options.getLiteral("of");
	}
	
	/**
	 * Add these left-top values to the calculated position, eg. "50 50" (left top)
	 *  A single value such as "50" will apply to both.
	 * @param offset
	 * @return the instance
	 */
	public PositionOptions setOffset(PositionOffset offset) {
		options.put("offset", offset);
		return this;
	}
	
	/**
	 * @return the offset option
	 */
	public PositionOffset getOffset() {
		return (PositionOffset) options.getComplexOption("offset");
	}
	
	/*---- Events section ---*/
	
	/**
	 * When specified the actual property setting is delegated to this callback. 
	 * Receives a single parameter which is a hash of top and left values for 
	 * the position that should be set.
	 * @param by
	 * @return the instance
	 */
	public PositionOptions setBy(JsScopePositionEvent by) {
		options.put("by", by);
		return this;
	}
}
