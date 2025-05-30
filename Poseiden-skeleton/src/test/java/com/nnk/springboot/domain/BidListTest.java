package com.nnk.springboot.domain;

import org.junit.jupiter.api.Test;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

public class BidListTest {

    @Test
    public void testNoArgsConstructorAndSetters() {
        BidList bidList = new BidList();

        bidList.setBidListId(1);
        bidList.setAccount("TestAccount");
        bidList.setType("TestType");
        bidList.setBidQuantity(100.0);
        bidList.setAskQuantity(200.0);
        bidList.setBid(50.5);
        bidList.setAsk(60.5);
        bidList.setBenchmark("Benchmark");
        bidList.setBidListDate(new Timestamp(System.currentTimeMillis()));
        bidList.setCommentary("Some commentary");
        bidList.setSecurity("Sec");
        bidList.setStatus("Active");
        bidList.setTrader("Trader X");
        bidList.setBook("Book-1");
        bidList.setCreationName("System");
        bidList.setCreationDate(new Timestamp(System.currentTimeMillis()));
        bidList.setRevisionName("Reviser");
        bidList.setRevisionDate(new Timestamp(System.currentTimeMillis()));
        bidList.setDealName("Deal-X");
        bidList.setDealType("Type-A");
        bidList.setSourceListId("SRC-123");
        bidList.setSide("BUY");

        assertEquals(1, bidList.getBidListId());
        assertEquals("TestAccount", bidList.getAccount());
        assertEquals("TestType", bidList.getType());
        assertEquals(100.0, bidList.getBidQuantity());
        assertEquals(200.0, bidList.getAskQuantity());
        assertEquals(50.5, bidList.getBid());
        assertEquals(60.5, bidList.getAsk());
        assertEquals("Benchmark", bidList.getBenchmark());
        assertEquals("Some commentary", bidList.getCommentary());
        assertEquals("Sec", bidList.getSecurity());
        assertEquals("Active", bidList.getStatus());
        assertEquals("Trader X", bidList.getTrader());
        assertEquals("Book-1", bidList.getBook());
        assertEquals("System", bidList.getCreationName());
        assertEquals("Reviser", bidList.getRevisionName());
        assertEquals("Deal-X", bidList.getDealName());
        assertEquals("Type-A", bidList.getDealType());
        assertEquals("SRC-123", bidList.getSourceListId());
        assertEquals("BUY", bidList.getSide());
    }

    @Test
    public void testAllArgsConstructor() {
        BidList bidList = new BidList("AccountA", "TypeB", 300.0);

        assertEquals("AccountA", bidList.getAccount());
        assertEquals("TypeB", bidList.getType());
        assertEquals(300.0, bidList.getBidQuantity());
    }
}
