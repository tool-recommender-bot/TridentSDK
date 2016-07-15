/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2016 The TridentSDK Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.tridentsdk.config;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * This class represents a section within a configuration
 * file.
 *
 * <p>Policies:</p>
 *
 * <p><strong>Keys:</strong> Keys are Strings which are the
 * name of the value stored at them. Keys can contain
 * periods if the value is stored inside of a child config
 * section. For example, the key {@code sec1.sec2.key} leads
 * to:
 *
 * <pre>{@code
 *  {
 *      "comment": "The key for this is simply comment",
 *      "sec1": {
 *          "comment": "The key for this is sec1.comment",
 *          "sec2": {
 *              "comment": "The key for this is sec1.sec2.key",
 *              "key": "value"
 *          }
 *      }
 *  }
 * }</pre></p>
 *
 * <p><strong>Get value:</strong> Obtains the value at the
 * given key. A key that does not exist does not return
 * {@code null} or a default value, but throws a
 * {@link RuntimeException}. This can also happen if the
 * type cannot be casted.</p>
 *
 * <p><strong>Set value:</strong> Sets the value at the key.
 * This ignores any previous value and simply replaces the
 * value if the key already exists, or creates the key if
 * it does not already.</p>
 *
 * <p><strong>Committing changes:</strong> Changes are
 * never eagerly committed. Instead, the root config section
 * (always the {@link Config} object) needs to be saved in
 * order for any changes to be written. Returned values from
 * getXYZ methods also must be {@link #set(String, Object)}
 * before changes take effect. It is up to the
 * implementation on whether or not to commit changes
 * eagerly.</p>
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public interface ConfigSection {
    /**
     * Obtains the root config section which contains all of
     * the children sections, including this one.
     *
     * <p>I.e. the config that holds this section.</p>
     *
     * @return the config that holds this section
     */
    ConfigSection rootSection();

    /**
     * Obtains the config section that contains this config
     * section as its child.
     *
     * @return the parent config section
     */
    ConfigSection parent();

    /**
     * Creates a new child config section within this config
     * section.
     *
     * @param name the name of the new config section
     */
    void createChild(String name);

    /**
     * Obtains a child config section with the given name.
     *
     * @param name the name of the child to get
     * @return the child, or {@code null} if it doesn't
     * exist
     */
    @Nullable
    ConfigSection getChild(String name);

    /**
     * Obtains the children sections of this config section.
     *
     * @param deep {@code true} to obtain all child sections,
     *             {@code false} to obtain only the children
     *             of this config section
     * @return the children config sections
     */
    Collection<ConfigSection> children(boolean deep);

    /**
     * Obtains all the keys contained in the config section.
     *
     * @param deep {@code true} to obtain all the keys in
     *             the child sections as well, or
     *             {@code false} to get keys only in this
     *             config section
     * @return the keys in this config section
     */
    Set<String> keys(boolean deep);

    /**
     * Obtains all the values contained within this config
     * section (in arbitrary order).
     *
     * <p>The ordering of the values should not be relied on
     * as an indicator of how it is actually ordered in the
     * file.</p>
     *
     * {@code true} to obtain all the values in
     * the child sections as well, or
     * {@code false} to get values only in this
     * config section
     *
     * @return the collection of values stored in the config
     * section
     */
    Collection<Object> values(boolean deep);

    /**
     * Obtains the set of entries representing the keys
     * related with the values they are associated with in
     * this config section.
     *
     * <p>The ordering of the values should not be relied on
     * as an indicator of how it is actually ordered in the
     * file.</p>
     *
     * {@code true} to obtain all the entries in
     * the child sections as well, or
     * {@code false} to get entries only in this
     * config section
     *
     * @return the set of key-value entries
     */
    Set<Map.Entry<String, Object>> entries(boolean deep);

    /**
     * Obtains the value associated with the given key in
     * this config section.
     *
     * <p>Keys within children sections may be period
     * {@code .} separated with the child name.</p>
     *
     * @param key the key from which to find the value
     * @return the value, or {@code null} if it does not
     * exist
     */
    Object get(String key);

    /**
     * {@link #get(String)}.
     *
     * <p>This method is the same as the other
     * {@code get(String)} method, but provides a class to
     * cast to when the value is obtained.</p>
     *
     * @param key the key from which to find the value
     * @param type the type to cast
     * @param <T> the type of the value
     * @throws RuntimeException if the value cannot be cast
     * @return the value, or {@code null} if it does not
     *         exist
     */
    <T> T get(String key, Class<T> type);

    /**
     * Sets the key to the given value.
     *
     * <p>Keys within children sections may be period
     * {@code .} separated with the child name.</p>
     *
     * @param key the key to use
     * @param value the value to associate with the key
     */
    void set(String key, Object value);

    /**
     * Obtains an integer at the given key.
     *
     * <p>Keys within children sections may be period
     * {@code .} separated with the child name.</p>
     *
     * @param key the key at which to find the value
     * @return the value
     * @throws RuntimeException if the value is not an
     *         integer, or if it doesn't exist
     */
    int getInt(String key);

    /**
     * Sets the integer at the given key.
     *
     * <p>Keys within children sections may be period
     * {@code .} separated with the child name.</p>
     *
     * @param key the key which to set the value
     * @param value the value to set
     */
    void setInt(String key, int value);

    /**
     * Obtains a {@code short} at the given key.
     *
     * <p>Keys within children sections may be period
     * {@code .} separated with the child name.</p>
     *
     * @param key the key at which to find the value
     * @return the value
     * @throws RuntimeException if the value is not a
     *         {@code short}, or if it doesn't exist
     */
    short getShort(String key);

    /**
     * Sets the {@code short} at the given key.
     *
     * <p>Keys within children sections may be period
     * {@code .} separated with the child name.</p>
     *
     * @param key the key which to set the value
     * @param value the value to set
     */
    void setShort(String key, short value);

    /**
     * Obtains a {@code long} at the given key.
     *
     * <p>Keys within children sections may be period
     * {@code .} separated with the child name.</p>
     *
     * @param key the key at which to find the value
     * @return the value
     * @throws RuntimeException if the value is not a
     *         {@code long}, or if it doesn't exist
     */
    long getLong(String key);

    /**
     * Sets the {@code long} at the given key.
     *
     * <p>Keys within children sections may be period
     * {@code .} separated with the child name.</p>
     *
     * @param key the key which to set the value
     * @param value the value to set
     */
    void setLong(String key, long value);

    /**
     * Obtains a {@code byte} at the given key.
     *
     * <p>Keys within children sections may be period
     * {@code .} separated with the child name.</p>
     *
     * @param key the key at which to find the value
     * @return the value
     * @throws RuntimeException if the value is not a
     *         {@code byte}, or if it doesn't exist
     */
    byte getByte(String key);

    /**
     * Sets the {@code byte} at the given key.
     *
     * <p>Keys within children sections may be period
     * {@code .} separated with the child name.</p>
     *
     * @param key the key which to set the value
     * @param value the value to set
     */
    void setByte(String key, byte value);

    /**
     * Obtains a {@code float} at the given key.
     *
     * <p>Keys within children sections may be period
     * {@code .} separated with the child name.</p>
     *
     * @param key the key at which to find the value
     * @return the value
     * @throws RuntimeException if the value is not a
     *         {@code float}, or if it doesn't exist
     */
    float getFloat(String key);

    /**
     * Sets the {@code float} at the given key.
     *
     * <p>Keys within children sections may be period
     * {@code .} separated with the child name.</p>
     *
     * @param key the key which to set the value
     * @param value the value to set
     */
    void setFloat(String key, float value);

    /**
     * Obtains a {@code double} at the given key.
     *
     * <p>Keys within children sections may be period
     * {@code .} separated with the child name.</p>
     *
     * @param key the key at which to find the value
     * @return the value
     * @throws RuntimeException if the value is not a
     *         {@code double}, or if it doesn't exist
     */
    double getDouble(String key);

    /**
     * Sets the {@code double} at the given key.
     *
     * <p>Keys within children sections may be period
     * {@code .} separated with the child name.</p>
     *
     * @param key the key which to set the value
     * @param value the value to set
     */
    void setDouble(String key, double value);

    /**
     * Obtains a {@code char} at the given key.
     *
     * <p>Keys within children sections may be period
     * {@code .} separated with the child name.</p>
     *
     * @param key the key at which to find the value
     * @return the value
     * @throws RuntimeException if the value is not a
     *         {@code char}, or if it doesn't exist
     */
    char getChar(String key);

    /**
     * Sets the {@code char} at the given key.
     *
     * <p>Keys within children sections may be period
     * {@code .} separated with the child name.</p>
     *
     * @param key the key which to set the value
     * @param value the value to set
     */
    void setChar(String key, char value);

    /**
     * Obtains a {@code boolean} at the given key.
     *
     * <p>Keys within children sections may be period
     * {@code .} separated with the child name.</p>
     *
     * @param key the key at which to find the value
     * @return the value
     * @throws RuntimeException if the value is not a
     *         {@code boolean}, or if it doesn't exist
     */
    boolean getBoolean(String key);

    /**
     * Sets the {@code boolean} at the given key.
     *
     * <p>Keys within children sections may be period
     * {@code .} separated with the child name.</p>
     *
     * @param key the key which to set the value
     * @param value the value to set
     */
    void setBoolean(String key, boolean value);

    /**
     * Obtains a String at the given key.
     *
     * <p>Keys within children sections may be period
     * {@code .} separated with the child name.</p>
     *
     * @param key the key at which to find the value
     * @return the value
     * @throws RuntimeException if the value is not a
     *         String, or if it doesn't exist
     */
    String getString(String key);

    /**
     * Sets the String at the given key.
     *
     * <p>Keys within children sections may be period
     * {@code .} separated with the child name.</p>
     *
     * @param key the key which to set the value
     * @param value the value to set
     */
    void setString(String key, String value);

    /**
     * Obtains a Collection at the given key.
     *
     * <p>Keys within children sections may be period
     * {@code .} separated with the child name.</p>
     *
     * <p>Be sure to note that collections are not watched,
     * which means that in order for changes to take effect,
     * the client must use {@link #set(String, Object)} in
     * order for the collection to update.</p>
     *
     * @param key the key at which to find the value
     * @return the value
     * @throws RuntimeException if the value is not a
     *         Collection, or if it doesn't exist
     */
    @Nonnull
    Collection<?> getCollection(String key);

    /**
     * Obtains a Collection at the given key and casted
     * to a particular type.
     *
     * <p>Keys within children sections may be period
     * {@code .} separated with the child name.</p>
     *
     * <p>Be sure to note that collections are not watched,
     * which means that in order for changes to take effect,
     * the client must use {@link #set(String, Object)} in
     * order for the collection to update.</p>
     *
     * @param key the key at which to find the value
     * @param type the type of values in the collection
     * @param <T> the type of values in the collection
     * @return the value
     * @throws RuntimeException if the value is not a
     *         Collection of the specified type, or if it
     *         doesn't exist
     */
    @Nonnull
    <T> Collection<T> getCollection(String key, Class<T> type);
}