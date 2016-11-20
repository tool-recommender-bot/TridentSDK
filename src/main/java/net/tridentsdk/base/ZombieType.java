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
 * Represents the different types of Zombies.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
public enum ZombieType {

    /**
     * A normal zombie.
     */
    NORMAL(0),

    /**
     * A zombie that looks like a villager of type {@link VillagerProfession#FARMER}.
     */
    VILLAGER_FARMER(1),

    /**
     * A zombie that looks like a villager of type {@link VillagerProfession#LIBRARIAN}.
     */
    VILLAGER_LIBRARIAN(2),

    /**
     * A zombie that looks like a villager of type {@link VillagerProfession#PRIEST}.
     */
    VILLAGER_PRIEST(3),

    /**
     * A zombie that looks like a villager of type {@link VillagerProfession#BLACKSMITH}.
     */
    VILLAGER_BLACKSMITH(4),

    /**
     * A zombie that looks like a villager of type {@link VillagerProfession#BUTCHER}.
     */
    VILLAGER_BUTCHER(5),

    /**
     * A husk Zombie.
     */
    HUSK(6);

    @Getter
    private final int data;

    @Getter
    private final boolean villager;

    ZombieType(int data) {
        this.data = data;
        this.villager = data >= 1 && data <= 5;
    }

    private static final Map<Integer, ZombieType> dataToType = new HashMap<>();

    /**
     * Gets the zombie type corresponding to the given internal identification number.
     * <br>
     * If none are found, an {@link IllegalArgumentException} will be thrown.
     *
     * @param id The identification number.
     *
     * @return The zombie type.
     */
    public static ZombieType of(int id) {
        ZombieType type = dataToType.get(id);
        if (type == null) {
            throw new IllegalArgumentException("no zombie type with id = " + id);
        }
        return type;
    }

    static {
        for (ZombieType type : values()) {
            dataToType.put(type.data, type);
        }
    }

}