/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
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
package net.tridentsdk.meta.entity.living;

import lombok.Getter;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/**
 * Represents the different types of Villagers.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Immutable
public enum VillagerProfession {
    /**
     * A farmer villager.
     */
    FARMER(0),

    /**
     * A librarian villager.
     */
    LIBRARIAN(1),

    /**
     * A priest villager.
     */
    PRIEST(2),

    /**
     * A blacksmith villager.
     */
    BLACKSMITH(3),

    /**
     * A butcher villager.
     */
    BUTCHER(4);

    @Getter
    private final int data;

    VillagerProfession(int data) {
        this.data = data;
    }

    /**
     * Gets the villager profession corresponding to the given internal identification number.
     * <br>
     * If none are found, an {@link IllegalArgumentException} will be thrown.
     *
     * @param id The identification number.
     *
     * @return The villager profession.
     */
    @Nonnull
    public static VillagerProfession of(int id) {
        for (VillagerProfession prof : values()) {
            if (prof.data == id) {
                return prof;
            }
        }

        throw new IllegalArgumentException("no villager profession with id = " + id);
    }
}