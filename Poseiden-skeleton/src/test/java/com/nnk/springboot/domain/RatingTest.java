package com.nnk.springboot.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RatingTest {
    
    @Test
    public void testGettersAndSetters() {
        Rating entity = new Rating();

        // Exemple de valeurs génériques
        entity.setId(1);
        
        assertEquals(1, entity.getId());
    }
}
