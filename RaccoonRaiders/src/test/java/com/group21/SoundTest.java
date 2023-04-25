package com.group21;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class SoundTest {

    private Sound sound;

    @BeforeEach
    void setUp() {
        sound = new Sound();
        sound.initializeSoundURL();
    }

    @Test
    void testInitializeSoundURL() {
        assertNotNull(sound.soundURL);
        assertEquals(2, sound.soundURL.length);
    }
}
