/*
 * Ark-Sys Kft. licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package hu.ibello.actions;

import java.io.File;

import hu.ibello.elements.WebElement;
import hu.ibello.pages.PageObject;

/**
 * Interface for building an <em>action</em>.
 * It contains fluent methods to build and execute interactive operations on an element.
 * <p>
 * During an action, the <em>ibello</em> tries to interact with an element on the page.
 * If the interaction fails, it will try it again and again, until it successes or the time
 * runs out. By default, an action uses the <em>ibello</em>
 * default timeout, but it can be changed with the {@link #withTimeout(Enum)} or
 * {@link #withTimeout(String)} method.
 * </p>
 * <p>
 * In some cases it is useful to wait a few seconds after an interaction occured on the page.
 * The action may change the page structure dynamically, or can navigate to another page.
 * With the {@link #withPageRefreshWait()} method, this wait can be performed right after
 * the action finishes.
 * </p>
 * <p>
 * During test execution, every action is logged with it's additional information. If an action
 * fails, then the test will be marked with "error" flag, and not with "failed". This is a
 * difference between actions and expectations - see {@link hu.ibello.expect}.
 * </p>
 * <p>
 * ActionBuilder instance can be obtained on page objects, with {@link PageObject#doWith(WebElement)}
 * method.
 * </p>
 * <p>
 * Examples:
 * </p>
 * <pre>
 * WebElement button = ...;
 * WebElement inputField = ...;
 * 
 * doWith(button).withPageRefreshWait().click();
 * doWith(inputField).sendKeys(keys().CONTROL(), "a");
 * </pre>
 * @author Kornél Simon
 *
 */
public interface ActionBuilder {

	/**
	 * Sets the timeout of the action by it's string identifier.
	 * The value of the timeout should be specified in the <em>ibello</em>
	 * configuration, with <code>"ibello.timeout."</code> prefix. For example,
	 * <code>withTimeout("LONG")</code> will set the timeout specified in the
	 * <code>ibello.timeout.LONG</code> configuration property. The timeout is
	 * specified in seconds.
	 * @param key identifier of the desired timeout
	 * @return the current {@link ActionBuilder} instance
	 */
	ActionBuilder withTimeout(String key);

	/**
	 * Sets the timeout of the action by it's enum identifier. The enum can be
	 * any valid java enum, it's not part of the <em>ibello</em> system. The given enum
	 * constant will be transformed to string.
	 * The value of the timeout should be specified in the <em>ibello</em>
	 * configuration, with <code>"ibello.timeout."</code> prefix. For example,
	 * <code>withTimeout(Timeouts.LONG)</code> will set the timeout specified in the
	 * <code>ibello.timeout.LONG</code> configuration property. The timeout is
	 * specified in seconds.
	 * @param key identifier of the desired timeout
	 * @return the current {@link ActionBuilder} instance
	 */
	ActionBuilder withTimeout(Enum<?> key);
	
	/**
	 * If this method is called on the {@link ActionBuilder} instance, and then an action
	 * is executed successfully, the program will wait until the page is refreshed and
	 * all dynamic changes in the DOM performed.
	 * @return the current {@link ActionBuilder} instance
	 */
	ActionBuilder withPageRefreshWait();
	
	/**
	 * Click the current element.
	 * <p>
	 * During the timeout, this method tries to execute the action until it is successfully
	 * performed. If the time runs out and the action was not performed, an exception will
	 * be thrown, the error will be logged and the current test will be interrupted and
	 * marked as "error".
	 * </p>
	 */
	void click();
	
	/**
	 * Drags the current element and drops to the given target.
	 * <p>
	 * During the timeout, this method tries to execute the action until it is successfully
	 * performed. If the time runs out and the action was not performed, an exception will
	 * be thrown, the error will be logged and the current test will be interrupted and
	 * marked as "error".
	 * </p>
	 * @param target drop target
	 */
	void dragAndDropTo(WebElement target);
	
	/**
	 * Right (context-) click on the current element.
	 * <p>
	 * During the timeout, this method tries to execute the action until it is successfully
	 * performed. If the time runs out and the action was not performed, an exception will
	 * be thrown, the error will be logged and the current test will be interrupted and
	 * marked as "error".
	 * </p>
	 */
	void contextClick();
	
	/**
	 * Double click on the current element.
	 * <p>
	 * During the timeout, this method tries to execute the action until it is successfully
	 * performed. If the time runs out and the action was not performed, an exception will
	 * be thrown, the error will be logged and the current test will be interrupted and
	 * marked as "error".
	 * </p>
	 */
	void doubleClick();
	
	/**
	 * Select an option from the dropdown list, with the given text.
	 * <p>
	 * During the timeout, this method tries to execute the action until it is successfully
	 * performed. If the time runs out and the action was not performed, an exception will
	 * be thrown, the error will be logged and the current test will be interrupted and
	 * marked as "error".
	 * </p>
	 * @param text the text of the option we want to select
	 */
	void selectOption(String text);
	
	/**
	 * Select an option from the dropdown list, with the given index. The index is 0-based,
	 * so the first option has index 0.
	 * <p>
	 * During the timeout, this method tries to execute the action until it is successfully
	 * performed. If the time runs out and the action was not performed, an exception will
	 * be thrown, the error will be logged and the current test will be interrupted and
	 * marked as "error".
	 * </p>
	 * @param index the index of the option we want to select
	 */
	void selectOption(int index);
	
	/**
	 * Move the cursor to the center of the current element.
	 * <p>
	 * During the timeout, this method tries to execute the action until it is successfully
	 * performed. If the time runs out and the action was not performed, an exception will
	 * be thrown, the error will be logged and the current test will be interrupted and
	 * marked as "error".
	 * </p>
	 */
	void moveTo();
	
	/**
	 * Scrolls the current element (if it is scrollable) to the given target element.
	 * The target should be a child of the current element.
	 * After the scrolling the target element will be visible inside of the current element.
	 * <p>
	 * During the timeout, this method tries to execute the action until it is successfully
	 * performed. If the time runs out and the action was not performed, an exception will
	 * be thrown, the error will be logged and the current test will be interrupted and
	 * marked as "error".
	 * </p>
	 * @param target the target element to which we want to scroll
	 */
	void scrollTo(WebElement target);
	
	/**
	 * Send the given character sequences (strings) to the current element.
	 * <p>
	 * During the timeout, this method tries to execute the action until it is successfully
	 * performed. If the time runs out and the action was not performed, an exception will
	 * be thrown, the error will be logged and the current test will be interrupted and
	 * marked as "error".
	 * </p>
	 * @param keys character sequences to send
	 */
	void sendKeys(CharSequence ... keys);
	
	/**
	 * Send the given character sequences (strings) to the current element, with the given
	 * {@link KeyModifier}.
	 * <p>
	 * During the timeout, this method tries to execute the action until it is successfully
	 * performed. If the time runs out and the action was not performed, an exception will
	 * be thrown, the error will be logged and the current test will be interrupted and
	 * marked as "error".
	 * </p>
	 * @param modifier key modifier which should be activated
	 * @param keys character sequences to send
	 * @see KeyModifier
	 */
	void sendKeys(KeyModifier modifier, CharSequence ... keys);
	
	/**
	 * Submit the current element, which should be a <code>form</code>.
	 * <p>
	 * During the timeout, this method tries to execute the action until it is successfully
	 * performed. If the time runs out and the action was not performed, an exception will
	 * be thrown, the error will be logged and the current test will be interrupted and
	 * marked as "error".
	 * </p>
	 */
	void submit();
	
	/**
	 * Set the value of the current element, which should be a text input field or a textarea.
	 * <p>
	 * During the timeout, this method tries to execute the action until it is successfully
	 * performed. If the time runs out and the action was not performed, an exception will
	 * be thrown, the error will be logged and the current test will be interrupted and
	 * marked as "error".
	 * </p>
	 * @param value the new value
	 */
	void setValue(String value);

	/**
	 * Set the value of the current element, which should be a file input.
	 * <p>
	 * During the timeout, this method tries to execute the action until it is successfully
	 * performed. If the time runs out and the action was not performed, an exception will
	 * be thrown, the error will be logged and the current test will be interrupted and
	 * marked as "error".
	 * </p>
	 * @param filePath path of the file to set
	 */
	void setFile(String filePath);
	
	/**
	 * Set the value of the current element, which should be a file input.
	 * <p>
	 * During the timeout, this method tries to execute the action until it is successfully
	 * performed. If the time runs out and the action was not performed, an exception will
	 * be thrown, the error will be logged and the current test will be interrupted and
	 * marked as "error".
	 * </p>
	 * @param file the file to set
	 */
	default void setFile(File file) {
		setFile(file.getAbsolutePath());
	}
	
	/**
	 * Fires an event on the element.
	 * Event names can be obtained from an {@link Events} instance.
	 * @param eventName the name of the event
	 */
	void fireEvent(String eventName);

	/**
	 * Fires an event on the element.
	 * Event names can be obtained from an {@link Events} instance.
	 * @param event the type of the event
	 */
	default void fireEvent(Events event) {
		fireEvent(event.eventName);
	}

}