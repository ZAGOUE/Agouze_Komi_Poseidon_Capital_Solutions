package com.nnk.springboot.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    
    @Test
    public void testGettersAndSetters() {
        User entity = new User();

        // Exemple de valeurs génériques
        entity.setId(1);
        
        assertEquals(1, entity.getId());
    }
}
