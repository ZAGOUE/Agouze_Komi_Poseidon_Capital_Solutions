package com.nnk.springboot.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RuleNameTest {
    
    @Test
    public void testGettersAndSetters() {
        RuleName entity = new RuleName();

        // Exemple de valeurs génériques
        entity.setId(1);
        
        assertEquals(1, entity.getId());
    }
}
