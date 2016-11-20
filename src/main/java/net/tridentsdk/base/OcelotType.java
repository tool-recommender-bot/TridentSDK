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
package net.tridentsdk.base;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the different types of Ocelots.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
public enum OcelotType {

    /**
     * A wild ocelot.
     */
    WILD(0),

    /**
     * A tuxedo ocelot.
     */
    TUXEDO(1),

    /**
     * A tabby ocelot.
     */
    TABBY(2),

    /**
     * A siamese ocelot.
     */
    SIAMESE(3);

    @Getter
    private final int data;

    OcelotType(int data) {
        this.data = data;
    }

    private static final Map<Integer, OcelotType> dataToType = new HashMap<>();

    static {
        for (OcelotType type : values()) {
            dataToType.put(type.data, type);
        }
    }

    /**
     * Gets the ocelot type corresponding to the given internal identification number.
     * <br>
     * If none are found, an {@link IllegalArgumentException} will be thrown.
     *
     * @param id The identification number.
     *
     * @return The ocelot type.
     */
    public static OcelotType of(int id) {
        OcelotType type = dataToType.get(id);
        if (type == null) {
            throw new IllegalArgumentException("no ocelot type with id = " + id);
        }
        return type;
    }

}