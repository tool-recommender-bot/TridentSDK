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
package net.tridentsdk.base;

import net.tridentsdk.meta.entity.living.monster.SkeletonType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class SkeletonTypeTest {

    @Test
    public void testTypes() {
        for (SkeletonType type : SkeletonType.values()) {
            assertEquals(type, SkeletonType.of(type.getData()));
        }
    }

    @Test
    public void testIndex() {
        for (int i = 0; i <= 2; i++) {
            SkeletonType.of(i);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testError() {
        SkeletonType.of(3);
    }

    @Test
    public void testData() {
        assertEquals(SkeletonType.NORMAL, SkeletonType.of(0));
        assertEquals(SkeletonType.WITHER, SkeletonType.of(1));
        assertEquals(SkeletonType.STRAY, SkeletonType.of(2));
    }

}
