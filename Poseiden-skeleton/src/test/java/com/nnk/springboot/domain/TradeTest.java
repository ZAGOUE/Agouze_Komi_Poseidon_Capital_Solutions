package com.nnk.springboot.domain;

import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.*;

public class TradeTest {

    @Test
    public void testGettersAndSetters() {
        Trade trade = new Trade();

        trade.setTradeId(1);
        trade.setAccount("AccountX");
        trade.setType("TypeX");
        trade.setBuyQuantity(100.5);
        trade.setSellQuantity(200.5);
        trade.setBuyPrice(99.9);
        trade.setSellPrice(101.1);
        trade.setBenchmark("Benchmark1");
        trade.setTradeDate(new Timestamp(System.currentTimeMillis()));
        trade.setSecurity("SecurityX");
        trade.setStatus("Active");
        trade.setTrader("Trader Joe");
        trade.setBook("Book-X");
        trade.setCreationName("Admin");
        trade.setCreationDate(new Timestamp(System.currentTimeMillis()));
        trade.setRevisionName("RevA");
        trade.setRevisionDate(new Timestamp(System.currentTimeMillis()));
        trade.setDealName("Deal-Z");
        trade.setDealType("Swap");
        trade.setSourceListId("SRC-TRD");
        trade.setSide("SELL");

        assertEquals(1, trade.getTradeId());
        assertEquals("AccountX", trade.getAccount());
        assertEquals("TypeX", trade.getType());
        assertEquals(100.5, trade.getBuyQuantity());
        assertEquals(200.5, trade.getSellQuantity());
        assertEquals(99.9, trade.getBuyPrice());
        assertEquals(101.1, trade.getSellPrice());
        assertEquals("Benchmark1", trade.getBenchmark());
        assertEquals("SecurityX", trade.getSecurity());
        assertEquals("Active", trade.getStatus());
        assertEquals("Trader Joe", trade.getTrader());
        assertEquals("Book-X", trade.getBook());
        assertEquals("Admin", trade.getCreationName());
        assertEquals("RevA", trade.getRevisionName());
        assertEquals("Deal-Z", trade.getDealName());
        assertEquals("Swap", trade.getDealType());
        assertEquals("SRC-TRD", trade.getSourceListId());
        assertEquals("SELL", trade.getSide());
    }

    @Test
    public void testConstructorWithAccountAndType() {
        Trade trade = new Trade("MyAccount", "MyType");

        assertEquals("MyAccount", trade.getAccount());
        assertEquals("MyType", trade.getType());
    }
}
