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
package net.tridentsdk.ui.chat;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class ChatTypeTest {

    @Test
    public void testChatTypes() {
        ChatType[] types = ChatType.values();
        assertEquals(types[0], ChatType.CHAT);
        assertEquals(types[1], ChatType.SYSTEM);
        assertEquals(types[2], ChatType.ABOVE_HOTBAR);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testError() {
        ChatType.values()[3].toString();
    }

}
