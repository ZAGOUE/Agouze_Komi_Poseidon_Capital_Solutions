package com.nnk.springboot;

import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.repositories.BidRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.List;
import java.util.Optional;


@SpringBootTest
public class BidTests {

	@Autowired
	private BidRepository bidRepository;

	@Test
	public void bidTest() {
		Bid bid = new Bid("Account Test", "Type Test", 10d);

		// Save
		bid = bidRepository.save(bid);
		assertNotNull(bid.getBidListId());
		assertEquals(10d, bid.getBidQuantity());

		// Update
		bid.setBidQuantity(20d);
		bid = bidRepository.save(bid);
		assertEquals(20d, bid.getBidQuantity());

		// Find
		List<Bid> listResult = bidRepository.findAll();
		assertTrue(listResult.size() > 0);

		// Delete
		Integer id = bid.getBidListId();
		bidRepository.delete(bid);
		Optional<Bid> bidList = bidRepository.findById(id);
		assertFalse(bidList.isPresent());
	}
}

